package com.craftminerd.eunithice.item.custom.foods;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class MeatballSoup extends Item {
    public MeatballSoup(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pToolTipComponents, TooltipFlag pIsAdvanced) {
        pToolTipComponents.add(Component.translatable("tooltip.eunithice.meatball_soup"));
        super.appendHoverText(pStack, pLevel, pToolTipComponents, pIsAdvanced);
    }
}
