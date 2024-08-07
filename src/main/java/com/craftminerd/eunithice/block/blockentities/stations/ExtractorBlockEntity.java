package com.craftminerd.eunithice.block.blockentities.stations;

import com.craftminerd.eunithice.block.blockentities.EunithiceBlockEntities;
import com.craftminerd.eunithice.block.blocks.stations.Extractor;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.item.custom.ExtractionCore;
import com.craftminerd.eunithice.recipe.ExtractorRecipe;
import com.craftminerd.eunithice.screen.ExtractorMenu;
import com.craftminerd.eunithice.util.EunithiceTags;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
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
import net.minecraft.world.item.BucketItem;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ExtractorBlockEntity extends BlockEntity implements MenuProvider {
    private static final Logger LOGGER = LogUtils.getLogger();

    private final ItemStackHandler itemHandler = new ItemStackHandler(10) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0, 1 -> stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).isPresent() || stack.is(EunithiceItems.EXTRACTION_CORE.get());
                default -> super.isItemValid(slot, stack);
            };
        }

        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0, 1 -> 1;
                default -> super.getStackLimit(slot, stack);
            };
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;

    public ExtractorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(EunithiceBlockEntities.EXTRACTOR_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                switch (index){
                    case 0: return ExtractorBlockEntity.this.progress;
                    case 1: return ExtractorBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: ExtractorBlockEntity.this.progress = value; break;
                    case 1: ExtractorBlockEntity.this.maxProgress = value; break;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Nonnull
    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("block_entity.eunithice.display_name.extractor");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        clientSync();
        return new ExtractorMenu(pContainerId,pInventory,this, this.data);
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
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("extractor.progress", progress);
        nbt = FLUID_TANK.writeToNBT(nbt);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("extractor.progress");
        FLUID_TANK.readFromNBT(nbt);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i=0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
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

    private static boolean isFluidSameAsTankFluid(FluidStack stack, ExtractorBlockEntity pEntity) {
        return stack.getFluid().isSame(pEntity.FLUID_TANK.getFluid().getFluid());
    }

    public void setFluid(FluidStack stack) {
        this.FLUID_TANK.setFluid(stack);
    }

    public FluidStack getFluidStack() {
        return this.FLUID_TANK.getFluid();
    }

    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    public static void tick(Level level, BlockPos pPos, BlockState pState, ExtractorBlockEntity pBlockEntity) {
        ItemStackHandler entityItemHandler = pBlockEntity.itemHandler;
        if (level.isClientSide()) return;
        SimpleContainer inputInventory = new SimpleContainer(entityItemHandler.getSlots()-2);
        for (int i= 0; i < entityItemHandler.getSlots() - 8; i++) {
            inputInventory.setItem(i, entityItemHandler.getStackInSlot(i+2));
        }
        SimpleContainer outputInventory = new SimpleContainer(entityItemHandler.getSlots()-4);
        for (int i = 0; i < entityItemHandler.getSlots()-4; i++) {
            outputInventory.setItem(i, entityItemHandler.getStackInSlot(i+4));
        }

        Optional<ExtractorRecipe> match = level.getRecipeManager()
                .getRecipeFor(ExtractorRecipe.Type.INSTANCE,inputInventory,level);
        if (match.isPresent() && hasCorrectFluidAmountInTank(pBlockEntity, match) && hasCorrectFluid(pBlockEntity, match)) {
            pState = pState.setValue(Extractor.ACTIVE, true);
            level.setBlock(pPos, pState, 3);
            SimpleContainer sampledInventory = canAllResultsFitInOutputSlots(outputInventory, match.get().getResultItemStacks());
            if (sampledInventory.equals(outputInventory)) return;
            pBlockEntity.progress += 4;
            setChanged(level,pPos,pState);
            if (pBlockEntity.progress > pBlockEntity.maxProgress) {
                craftItem(pBlockEntity, match, sampledInventory);
                pBlockEntity.resetProgress();
            }
        } else {
            pState = pState.setValue(Extractor.ACTIVE, false);
            level.setBlock(pPos, pState, 3);
            pBlockEntity.resetProgress();
            setChanged(level,pPos,pState);
        }

        if (hasFluidItemInSourceSlot(pBlockEntity)) {
            transferItemFluidToFluidTank(pBlockEntity);
        }
        if (hasFluidItemInFluidOutSlot(pBlockEntity)) {
            transferTankFluidToItem(pBlockEntity);
        }
    }

    private static boolean hasCorrectFluid(ExtractorBlockEntity pBlockEntity, Optional<ExtractorRecipe> match) {
        return match.get().getFluid().equals(pBlockEntity.FLUID_TANK.getFluid());
    }

    private static boolean hasCorrectFluidAmountInTank(ExtractorBlockEntity pBlockEntity, Optional<ExtractorRecipe> match) {
        return pBlockEntity.FLUID_TANK.getFluidAmount() >= match.get().getFluid().getAmount();
    }

    private static void transferItemFluidToFluidTank(@NotNull ExtractorBlockEntity pEntity) {
        pEntity.itemHandler.getStackInSlot(0).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(handler -> {
            if (handler.getContainer().getItem() instanceof BucketItem && handler.getFluidInTank(0).getAmount() == 1000) {
                int drainAmount = 1000;
                if (pEntity.FLUID_TANK.getSpace() < drainAmount) return;
                FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
                if (pEntity.FLUID_TANK.isFluidValid(stack)) {
                    handler.drain(stack, IFluidHandler.FluidAction.EXECUTE);
                    fillTankWithFluid(pEntity, stack, handler.getContainer());
                }
            } else {
                int drainAmount = Math.min(Math.min(pEntity.FLUID_TANK.getSpace(), 1000), handler.getFluidInTank(0).getAmount());
                FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
                if (pEntity.FLUID_TANK.isFluidValid(stack)) {
                    handler.drain(stack, IFluidHandler.FluidAction.EXECUTE);
                    fillTankWithFluid(pEntity, stack, handler.getContainer());
                }
            }
        });
    }

    private static void transferTankFluidToItem(@NotNull ExtractorBlockEntity pEntity) {
        pEntity.itemHandler.getStackInSlot(1).getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(handler -> {
            if (handler.getContainer().getItem() instanceof BucketItem && handler.getFluidInTank(0).getAmount() == 0) {
                int drainAmount = 1000;
                FluidStack stack = pEntity.FLUID_TANK.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
                if (pEntity.FLUID_TANK.getFluidAmount() >= drainAmount && handler.isFluidValid(0, stack)) {
                    handler.fill(stack, IFluidHandler.FluidAction.EXECUTE);
                    fillItemWithFluid(pEntity, stack, handler.getContainer());
                }
            } else {
                int drainAmount = Math.min(Math.min(pEntity.FLUID_TANK.getFluidAmount(), 1000), handler.getTankCapacity(0)-handler.getFluidInTank(0).getAmount());
                FluidStack stack = pEntity.FLUID_TANK.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
                if (pEntity.FLUID_TANK.getFluidAmount() >= drainAmount && handler.isFluidValid(0, stack)) {
                    handler.fill(stack, IFluidHandler.FluidAction.EXECUTE);
                    fillItemWithFluid(pEntity, stack, handler.getContainer());
                }
            }
        });
    }

    private static void fillItemWithFluid(ExtractorBlockEntity pEntity, FluidStack pStack, ItemStack pContainer) {
        pEntity.FLUID_TANK.drain(pStack, IFluidHandler.FluidAction.EXECUTE);
        pEntity.itemHandler.extractItem(1, 1, false);
        pEntity.itemHandler.insertItem(1, pContainer, false);
    }

    private static void fillTankWithFluid(ExtractorBlockEntity pEntity, FluidStack stack, ItemStack container) {
        pEntity.FLUID_TANK.fill(stack, IFluidHandler.FluidAction.EXECUTE);
        if (pEntity.itemHandler.getStackInSlot(0).getItem() instanceof ExtractionCore) {
            if (pEntity.itemHandler.getStackInSlot(0).hurt(1, pEntity.level.getRandom(), null)) pEntity.itemHandler.extractItem(0, 1, false);
        } else {
            pEntity.itemHandler.extractItem(0, 1, false);
            pEntity.itemHandler.insertItem(0, container, false);
        }

    }

    private static boolean hasFluidItemInSourceSlot(ExtractorBlockEntity pEntity) {
        return pEntity.itemHandler.getStackInSlot(0).getCount() > 0;
    }

    private static boolean hasFluidItemInFluidOutSlot(ExtractorBlockEntity pEntity) {
        return pEntity.itemHandler.getStackInSlot(1).getCount() > 0;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static SimpleContainer canAllResultsFitInOutputSlots(SimpleContainer inventory, NonNullList<ItemStack> results) {

        SimpleContainer sampleInventory = new SimpleContainer(inventory.getContainerSize());
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            sampleInventory.setItem(i, inventory.getItem(i));
        }
        int totalRemainder = 0;
        for (int j = 0; j < results.size(); j++) {
            ItemStack result = results.get(j);
            int count = results.get(j).getCount();
            for (int i = 0; i < sampleInventory.getContainerSize(); i++) {
                if (count > 0) {
                    if (sampleInventory.getItem(i).isEmpty()) {
                        if (count > 64) {
                            sampleInventory.setItem(i, new ItemStack(result.getItem(), 64));
                            count -= 64;
                        } else {
                            sampleInventory.setItem(i, new ItemStack(result.getItem(), count));
                            count = 0;
                        }
                    } else if (sampleInventory.getItem(i).getItem() == result.getItem()) {
                        if (sampleInventory.getItem(i).getCount() < 64) {
                            if (count + sampleInventory.getItem(i).getCount() > 64) {
                                count = count + sampleInventory.getItem(i).getCount() - 64;
                                sampleInventory.setItem(i, new ItemStack(result.getItem(), 64));
                            } else if (count + sampleInventory.getItem(i).getCount() <= 64) {
                                sampleInventory.setItem(i, new ItemStack(result.getItem(), count + sampleInventory.getItem(i).getCount()));
                                count = 0;
                            }
                        }
                    }
                }
            }
            totalRemainder += count;
        }

        return totalRemainder == 0 ? sampleInventory : inventory;
    }

    private static void craftItem(ExtractorBlockEntity entity, Optional<ExtractorRecipe> match, SimpleContainer replacementInventory) {
        Level level = entity.level;
        ItemStackHandler itemStackHandler = entity.itemHandler;
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots() - 2; i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i+2));
        }
        entity.FLUID_TANK.drain(match.get().getFluid().getAmount(), IFluidHandler.FluidAction.EXECUTE);
        if (entity.itemHandler.getStackInSlot(2) != ItemStack.EMPTY) if (entity.itemHandler.getStackInSlot(2).hurt(1, entity.level.getRandom(), null)) entity.itemHandler.extractItem(2, 1, false);
        entity.itemHandler.extractItem(3, 1, false);
        sendResult(entity.itemHandler, replacementInventory);
    }

    private static void sendResult(ItemStackHandler entityItemHandler, SimpleContainer replacementInventory) {
        for (int i = 0; i < replacementInventory.getContainerSize(); i++) {
            entityItemHandler.setStackInSlot(i+4, replacementInventory.getItem(i));
        }
    }

}
