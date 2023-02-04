package com.craftminerd.eunithice.screen;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.block.entity.stations.ExtractorBlockEntity;
import com.craftminerd.eunithice.screen.slot.EunithiceCoreSlot;
import com.craftminerd.eunithice.screen.slot.EunithiceHammerOnlySlot;
import com.craftminerd.eunithice.screen.slot.EunithiceNormalItemSlot;
import com.craftminerd.eunithice.screen.slot.EunithiceResultSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ExtractorMenu extends AbstractContainerMenu {
    private final ExtractorBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public ExtractorMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }
    public ExtractorMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(EunithiceMenuTypes.EXTRACTOR_MENU.get(), pContainerId);
        checkContainerSize(inv, 9);
        blockEntity = ((ExtractorBlockEntity)entity);
        this.level = inv.player.level;
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iItemHandler -> {
            this.addSlot(new EunithiceCoreSlot(iItemHandler, 0, 25, 34));
            this.addSlot(new EunithiceHammerOnlySlot(iItemHandler, 1, 59, 22));
            this.addSlot(new EunithiceNormalItemSlot(iItemHandler, 2, 59, 46));
            this.addSlot(new EunithiceResultSlot(iItemHandler, 3, 99, 25));
            this.addSlot(new EunithiceResultSlot(iItemHandler, 4, 117, 25));
            this.addSlot(new EunithiceResultSlot(iItemHandler, 5, 135, 25));
            this.addSlot(new EunithiceResultSlot(iItemHandler, 6, 99, 43));
            this.addSlot(new EunithiceResultSlot(iItemHandler, 7, 117, 43));
            this.addSlot(new EunithiceResultSlot(iItemHandler, 8, 135, 43));
        });

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 16;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }


    // Special thanks to diesieben07 for the quickMoveStack code | https://github.com/diesieben07/SevenCommons

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_ROW_COUNT * PLAYER_INVENTORY_COLUMN_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int THIS_INVENTORY_FIRST_SLOT_INDEX = VANILLA_SLOT_COUNT + VANILLA_FIRST_SLOT_INDEX;


    private static final int THIS_INVENTORY_SLOT_COUNT = 9;

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot==null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copiedSourceStack = sourceStack.copy();

        // Check if slot clicked is from vanilla container slots
        if (pIndex < VANILLA_FIRST_SLOT_INDEX+VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, THIS_INVENTORY_FIRST_SLOT_INDEX, THIS_INVENTORY_FIRST_SLOT_INDEX +THIS_INVENTORY_SLOT_COUNT, false)){
                return ItemStack.EMPTY;
            }
        } else if (pIndex < THIS_INVENTORY_FIRST_SLOT_INDEX + THIS_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX+VANILLA_SLOT_COUNT,false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }

        if (sourceStack.getCount()==0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(pPlayer, sourceStack);
        return copiedSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),pPlayer, EunithiceBlocks.EXTRACTOR.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i=0;i<3;i++) {
            for (int l=0;l<9;l++) {
                this.addSlot(new Slot(playerInventory, l+i*9+9, 8 + l * 18, 86+i*18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i=0;i<9;i++) {
            this.addSlot(new Slot(playerInventory,i,8+i*18,144));
        }
    }
}