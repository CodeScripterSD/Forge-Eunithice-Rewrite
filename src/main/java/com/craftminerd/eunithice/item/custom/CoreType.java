package com.craftminerd.eunithice.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class CoreType extends Item {

    Random random = new Random();

    public CoreType(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return false;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemstack) {
        ItemStack stack = itemstack.copy();
        hurtItem(stack);
        return stack;
    }

    protected void hurtItem(ItemStack stack) {
        if (stack.getMaxDamage() - stack.getDamageValue() <= 1) {
            stack.shrink(1);
        } else {
            stack.hurt(1, random, null);
        }
    }

}