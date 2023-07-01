package com.craftminerd.eunithice.item.tiers;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.item.EunithiceItems;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum EunithiceToolTiers implements Tier {
    NEUDONITE(2, 750, 10.0f, 2f, 16,
            () -> Ingredient.of(EunithiceItems.NEUDONITE_INGOT.get())),
    DRIPSTONE(3, 900, 25f, 6f, 90,
            () -> Ingredient.of(Items.POINTED_DRIPSTONE));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private EunithiceToolTiers(int harvestLevel, int durability, float speed, float damageModifier, int enchantability, Supplier<Ingredient> p_43337_) {
        this.level = harvestLevel;
        this.uses = durability;
        this.speed = speed;
        this.damage = damageModifier;
        this.enchantmentValue = enchantability;
        this.repairIngredient = new LazyLoadedValue<>(p_43337_);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}