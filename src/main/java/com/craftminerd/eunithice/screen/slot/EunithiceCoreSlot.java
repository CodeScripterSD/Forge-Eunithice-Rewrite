package com.craftminerd.eunithice.screen.slot;

import com.craftminerd.eunithice.util.EunithiceTags;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class EunithiceCoreSlot extends SlotItemHandler {
    public EunithiceCoreSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.is(EunithiceTags.Items.CORES);
    }
}