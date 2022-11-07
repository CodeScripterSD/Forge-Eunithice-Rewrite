package com.craftminerd.eunithice.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class HammerItem extends Item {

    private final int BURNTIME;
    private final String HOVERTEXT;

    public Random random = new Random();

    public HammerItem(Properties p_41383_) {
        super(p_41383_);
        this.BURNTIME = -1;
        this.HOVERTEXT = null;
    }

    public HammerItem(Properties p_41383_, int burnTime) {
        super(p_41383_);
        this.BURNTIME = burnTime;
        this.HOVERTEXT = null;
    }

    public HammerItem(Properties p_41383_, String hoverText) {
        super(p_41383_);
        this.BURNTIME = -1;
        this.HOVERTEXT = hoverText;
    }

    public HammerItem(Properties p_41383_, int burnTime, String hoverText) {
        super(p_41383_);
        this.BURNTIME = burnTime;
        this.HOVERTEXT = hoverText;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Nonnull
    @Override
    public ItemStack getContainerItem(@Nonnull ItemStack itemstack) {
        ItemStack stack = itemstack.copy();
        hurtItem(stack);
        return stack;
    }

    private void hurtItem(ItemStack stack) {
        if (stack.getMaxDamage() - stack.getDamageValue() <= 1) {
            stack.shrink(1);
        } else {
            stack.hurt(1, random, null);
        }
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.BURNTIME;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> pToolTipComponents, TooltipFlag p_41424_) {
        if (HOVERTEXT != null) {
            pToolTipComponents.add(new TranslatableComponent(HOVERTEXT));
        }
        super.appendHoverText(p_41421_, p_41422_, pToolTipComponents, p_41424_);
    }
}
