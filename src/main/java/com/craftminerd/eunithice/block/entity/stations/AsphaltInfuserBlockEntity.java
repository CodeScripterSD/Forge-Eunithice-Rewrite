package com.craftminerd.eunithice.block.entity.stations;

import com.craftminerd.eunithice.block.entity.EunithiceBlockEntities;
import com.craftminerd.eunithice.recipe.AsphaltInfuserRecipe;
import com.craftminerd.eunithice.screen.AsphaltInfuserMenu;
import com.craftminerd.eunithice.util.EunithiceTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Random;

public class AsphaltInfuserBlockEntity extends BlockEntity implements MenuProvider {

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

    public AsphaltInfuserBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(EunithiceBlockEntities.ASPHALT_INFUSER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                switch (index){
                    case 0: return AsphaltInfuserBlockEntity.this.progress;
                    case 1: return AsphaltInfuserBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: AsphaltInfuserBlockEntity.this.progress = value; break;
                    case 1: AsphaltInfuserBlockEntity.this.maxProgress = value; break;
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
        return new TranslatableComponent("block_entity.eunithice.display_name.asphalt_infuser");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new AsphaltInfuserMenu(pContainerId,pInventory,this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("asphalt_infuser.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("asphalt_infuser.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i=0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, AsphaltInfuserBlockEntity pBlockEntity) {
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

    private static void craftItem(AsphaltInfuserBlockEntity entity) {
        Level level = entity.level;
        ItemStackHandler entityItemHandler = entity.itemHandler;

        SimpleContainer inventory = new SimpleContainer(entityItemHandler.getSlots());
        for (int i = 0; i < entityItemHandler.getSlots(); i++) {
            inventory.setItem(i, entityItemHandler.getStackInSlot(i));
        }

        Optional<AsphaltInfuserRecipe> match = level.getRecipeManager()
                .getRecipeFor(AsphaltInfuserRecipe.Type.INSTANCE, inventory, level);

        
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


    private static boolean hasRecipe(AsphaltInfuserBlockEntity entity) {
        ItemStackHandler entityItemHandler = entity.itemHandler;
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entityItemHandler.getSlots());
        for (int i= 0; i < entityItemHandler.getSlots(); i++) {
            inventory.setItem(i, entityItemHandler.getStackInSlot(i));
        }

        Optional<AsphaltInfuserRecipe> match = level.getRecipeManager()
                .getRecipeFor(AsphaltInfuserRecipe.Type.INSTANCE,inventory,level);
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