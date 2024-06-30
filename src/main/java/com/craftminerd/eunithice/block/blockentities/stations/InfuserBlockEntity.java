package com.craftminerd.eunithice.block.blockentities.stations;

import com.craftminerd.eunithice.block.blockentities.EunithiceBlockEntities;
import com.craftminerd.eunithice.recipe.InfuserRecipe;
import com.craftminerd.eunithice.screen.InfuserMenu;
import com.craftminerd.eunithice.util.EunithiceTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class InfuserBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public InfuserBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(EunithiceBlockEntities.INFUSER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                switch (index){
                    case 0: return InfuserBlockEntity.this.progress;
                    case 1: return InfuserBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: InfuserBlockEntity.this.progress = value; break;
                    case 1: InfuserBlockEntity.this.maxProgress = value; break;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("block_entity.eunithice.display_name.infuser");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        clientSync();
        return new InfuserMenu(pContainerId,pInventory,this, this.data);
    }

    public void clientSync() {
        if (Objects.requireNonNull(this.getLevel()).isClientSide()) {
            return;
        }
        ServerLevel world = (ServerLevel) this.getLevel();
        List<ServerPlayer> players = world.getChunkSource().chunkMap.getPlayers(new ChunkPos(this.worldPosition), false);
        ClientboundBlockEntityDataPacket packet = this.getUpdatePacket();
        players.forEach(e -> {
            if (packet != null) e.connection.send(packet);
        });

    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return lazyFluidHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("infuser.progress", progress);
        pTag = FLUID_TANK.writeToNBT(pTag);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("infuser.progress");
        FLUID_TANK.readFromNBT(pTag);
    }

    private final FluidTank FLUID_TANK = new FluidTank(4000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
            clientSync();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return  (this.getFluid().isEmpty() || stack.getFluid().isSame(this.getFluid().getFluid())) && stack.getFluid().is(EunithiceTags.Fluids.EXTRACTOR_ACCEPTED_FLUIDS);
        }
    };

    private static boolean isFluidSameAsTankFluid(FluidStack stack, InfuserBlockEntity pEntity) {
        return stack.getFluid().isSame(pEntity.FLUID_TANK.getFluid().getFluid());
    }

    public void setFluid(FluidStack stack) {
        this.FLUID_TANK.setFluid(stack);
    }

    public FluidStack getFluidStack() {
        return this.FLUID_TANK.getFluid();
    }

    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i=0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, InfuserBlockEntity pBlockEntity) {
        if (hasRecipe(pBlockEntity)) {
            pBlockEntity.progress += 4;
            setChanged(pLevel,pPos,pState);
            if (pBlockEntity.progress > pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
                pBlockEntity.resetProgress();
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel,pPos,pState);
        }
    }

    private static void craftItem(InfuserBlockEntity entity) {
        Level level = entity.level;
        ItemStackHandler entityItemHandler = entity.itemHandler;

        SimpleContainer inventory = new SimpleContainer(entityItemHandler.getSlots());
        for (int i = 0; i < entityItemHandler.getSlots(); i++) {
            inventory.setItem(i, entityItemHandler.getStackInSlot(i));
        }

        Optional<InfuserRecipe> match = level.getRecipeManager()
                .getRecipeFor(InfuserRecipe.Type.INSTANCE, inventory, level);


        if (match.isPresent()) {
            if (entityItemHandler.getStackInSlot(0) != ItemStack.EMPTY) {
                if (entityItemHandler.getStackInSlot(0).isDamageableItem()) {
                    if (match.get().getIgnoreDurability()) {
                        entityItemHandler.extractItem(0, 1, false);
                    } else {
                        if (entityItemHandler.getStackInSlot(0).getDamageValue() +1 >= entityItemHandler.getStackInSlot(0).getMaxDamage()) {
                            entityItemHandler.extractItem(0, 1, false);
                        } else {
                            entityItemHandler.getStackInSlot(0).hurt(1, new Random(), null);
                        }
                    }
                } else {
                    entityItemHandler.extractItem(0, 1, false);
                }
            }
            if (entityItemHandler.getStackInSlot(1) != ItemStack.EMPTY) {
                if (entityItemHandler.getStackInSlot(1).isDamageableItem()) {
                    if (match.get().getIgnoreDurability()) {
                        entityItemHandler.extractItem(1, 1, false);
                    } else {
                        if (entityItemHandler.getStackInSlot(1).getDamageValue() +1 >= entityItemHandler.getStackInSlot(1).getMaxDamage()) {
                            entityItemHandler.extractItem(1, 1, false);
                        } else {
                            entityItemHandler.getStackInSlot(1).hurt(1, new Random(), null);
                        }
                    }
                } else {
                    entityItemHandler.extractItem(1, 1, false);
                }
            }
            entityItemHandler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(), entityItemHandler.getStackInSlot(2).getCount() + match.get().getResultItem().getCount()));
            entity.resetProgress();
        }


//        if (match.isPresent()) {
//            if (entityItemHandler.getStackInSlot(0) != ItemStack.EMPTY) {
//                if (entityItemHandler.getStackInSlot(0).is(EunithiceTags.Items.CORES)) {
//                    entityItemHandler.getStackInSlot(0).hurt(1, new Random(), null);
//                } else {
//                    entityItemHandler.extractItem(0,1,false);
//                }
//            }
//            if (entityItemHandler.getStackInSlot(1) != ItemStack.EMPTY) {
//                if (entityItemHandler.getStackInSlot(1).is(EunithiceTags.Items.HAMMERS)) {
//                    entityItemHandler.getStackInSlot(1).hurt(1, new Random(), null);
//                } else {
//                    entityItemHandler.extractItem(1,1,false);
//                }
//            }
//            entity.itemHandler.setStackInSlot(2, new ItemStack(match.get().getResultItem().getItem(), entityItemHandler.getStackInSlot(3).getCount() + match.get().getResultItem().getCount()));
//            entity.resetProgress();
//        }

    }


    private static boolean hasRecipe(InfuserBlockEntity entity) {
        ItemStackHandler entityItemHandler = entity.itemHandler;
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entityItemHandler.getSlots());
        for (int i= 0; i < entityItemHandler.getSlots(); i++) {
            inventory.setItem(i, entityItemHandler.getStackInSlot(i));
        }

        Optional<InfuserRecipe> match = level.getRecipeManager()
                .getRecipeFor(InfuserRecipe.Type.INSTANCE,inventory,level);
        return match.isPresent() && canInsertAmountIntoResultSlot(inventory, match.get().getResultItem()) && canInsertItemIntoResultSlot(inventory, match.get().getResultItem());
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoResultSlot(SimpleContainer inventory, ItemStack result) {
        return inventory.getItem(2).getItem() == result.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoResultSlot(SimpleContainer inventory, ItemStack result) {
        return result.getMaxStackSize() >= inventory.getItem(2).getCount() + result.getCount();
    }


}