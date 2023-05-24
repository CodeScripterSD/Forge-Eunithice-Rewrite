package com.craftminerd.eunithice.block.entity.stations;

import com.craftminerd.eunithice.block.entity.EunithiceBlockEntities;
import com.craftminerd.eunithice.recipe.ExtractorRecipe;
import com.craftminerd.eunithice.screen.ExtractorMenu;
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

public class ExtractorBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(9) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
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

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("block_entity.eunithice.display_name.extractor");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new ExtractorMenu(pContainerId,pInventory,this, this.data);
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
        pTag.putInt("extractor.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("extractor.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i=0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i,itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ExtractorBlockEntity pBlockEntity) {
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

    private static void craftItem(ExtractorBlockEntity entity) {
        Level level = entity.level;
        ItemStackHandler entityItemHandler = entity.itemHandler;

        SimpleContainer inventory = new SimpleContainer(entityItemHandler.getSlots());
        for (int i = 0; i < entityItemHandler.getSlots(); i++) {
            inventory.setItem(i, entityItemHandler.getStackInSlot(i));
        }

        Optional<ExtractorRecipe> match = level.getRecipeManager()
                .getRecipeFor(ExtractorRecipe.Type.INSTANCE, inventory, level);

        
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
            if (entityItemHandler.getStackInSlot(2) != ItemStack.EMPTY) {
                if (entityItemHandler.getStackInSlot(2).isDamageableItem()) {
                    if (match.get().getIgnoreDurability()) {
                        entityItemHandler.extractItem(2, 1, false);
                    } else {
                        if (entityItemHandler.getStackInSlot(2).getDamageValue() +1 >= entityItemHandler.getStackInSlot(2).getMaxDamage()) {
                            entityItemHandler.extractItem(2, 1, false);
                        } else {
                            entityItemHandler.getStackInSlot(2).hurt(1, new Random(), null);
                        }
                    }
                } else {
                    entityItemHandler.extractItem(2, 1, false);
                }
            }
            sendResult(match, entityItemHandler);
            entity.resetProgress();
        }
    }

    private static void sendResult(Optional<ExtractorRecipe> match, ItemStackHandler entityItemHandler) {
        ItemStack resultStack = match.get().getResultItem().copy();

        ItemStack out = resultStack.copy();
        int remaining = out.getCount();
        for (int i = 3; i < 9; i++) {
            if (remaining > 0) {
                if (entityItemHandler.getStackInSlot(i).isEmpty()) {
                    entityItemHandler.setStackInSlot(i, new ItemStack(match.get().getResultItem().getItem(), remaining));
                    remaining = 0;
                } else if (entityItemHandler.getStackInSlot(i).getItem() == out.getItem()) {
                    if (entityItemHandler.getStackInSlot(i).getCount() < 64) {
                        if (remaining + entityItemHandler.getStackInSlot(i).getCount() > 64) {
                            remaining = remaining + entityItemHandler.getStackInSlot(i).getCount() - 64;
                            entityItemHandler.setStackInSlot(i, new ItemStack(match.get().getResultItem().getItem(), 64));
                        } else {
                            entityItemHandler.setStackInSlot(i, new ItemStack(match.get().getResultItem().getItem(), entityItemHandler.getStackInSlot(i).getCount() + remaining));
                            remaining = 0;
                        }
                    }
                }
            } else {
                break;
            }
        }
    }


    private static boolean hasRecipe(ExtractorBlockEntity entity) {
        ItemStackHandler entityItemHandler = entity.itemHandler;
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entityItemHandler.getSlots());
        for (int i= 0; i < entityItemHandler.getSlots(); i++) {
            inventory.setItem(i, entityItemHandler.getStackInSlot(i));
        }

        Optional<ExtractorRecipe> match = level.getRecipeManager()
                .getRecipeFor(ExtractorRecipe.Type.INSTANCE,inventory,level);
        return match.isPresent() && canResultAmountFitInResults(inventory, match.get().getResultItem());
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoResults(SimpleContainer inventory, ItemStack result) {
        return canResultAmountFitInResults(inventory, result);
    }

    private static boolean canResultAmountFitInResults(SimpleContainer inventory, ItemStack result) {
        if (result.getCount() > 64) return false;
        ItemStack out = result.copy();
        int remaining = out.getCount();
        for (int i = 3; i < 9; i++) {
            if (remaining > 0) {
                if (inventory.getItem(i).isEmpty()) {
                    remaining = 0;
                    break;
                }
                if (inventory.getItem(i).getItem() == out.getItem()) {
                    if (inventory.getItem(i).getCount() < 64) {
                        if (remaining + inventory.getItem(i).getCount() > 64) {
                            remaining = remaining + inventory.getItem(i).getCount() - 64;
                        } else if (remaining + inventory.getItem(i).getCount() <= 64) {
                            remaining = 0;
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }
        return remaining <= 0;
    }


}