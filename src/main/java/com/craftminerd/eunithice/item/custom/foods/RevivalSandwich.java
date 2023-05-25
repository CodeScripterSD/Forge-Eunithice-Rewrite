package com.craftminerd.eunithice.item.custom.foods;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class RevivalSandwich extends Item {
    public RevivalSandwich(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pToolTipComponents, TooltipFlag pIsAdvanced) {
        pToolTipComponents.add(Component.translatable("tooltip.eunithice.revival_sandwich"));
        super.appendHoverText(pStack, pLevel, pToolTipComponents, pIsAdvanced);
    }

}
