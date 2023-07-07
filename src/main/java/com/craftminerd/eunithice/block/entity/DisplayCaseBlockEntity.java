package com.craftminerd.eunithice.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.checkerframework.checker.units.qual.C;

import javax.annotation.Nonnull;

public class DisplayCaseBlockEntity extends BlockEntity implements Clearable {
    private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public DisplayCaseBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(EunithiceBlockEntities.DISPLAY_CASE_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        ContainerHelper.loadAllItems(pTag, this.items);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        ContainerHelper.saveAllItems(pTag, this.items, true);
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag compoundTag = new CompoundTag();
        ContainerHelper.saveAllItems(compoundTag, this.items, true);
        return compoundTag;
    }

    public boolean swapItem(Player player, ItemStack externalStack, InteractionHand hand) {
        ItemStack internalStack = this.getItems().get(0);
        if (internalStack.isEmpty() && externalStack.isEmpty()) {
            return false;
        } else if (player.getAbilities().instabuild && internalStack.isEmpty() && !externalStack.isEmpty()) {
            ItemStack itemStack2 = externalStack.copy();
            itemStack2.setCount(1);
            this.setHeldItem(itemStack2);
            this.markUpdated();
            return true;
        } else if (!externalStack.isEmpty() && externalStack.getCount() > 1) {
            if (!internalStack.isEmpty()) {
                return false;
            } else {
                ItemStack itemStack1 = externalStack.copy();
                itemStack1.setCount(1);
                this.setHeldItem(itemStack1);
                this.markUpdated();
                externalStack.shrink(1);
                return true;
            }
        } else {
            this.setHeldItem(externalStack);
            this.markUpdated();
            player.setItemInHand(hand, internalStack);
            return true;
        }
    }

    public void setHeldItem(ItemStack stack) {
        this.items.set(0, stack);
    }

    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    public void clearContent() {
        this.items.clear();
    }

    private void markUpdated() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }
}
