package com.craftminerd.eunithice.item.tiers;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.item.EunithiceItems;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum EunithiceToolTiers implements Tier {
    PLATED_IRON(3, 750, 14.0f, 2.5f, 20,
            () -> Ingredient.of(EunithiceBlocks.PLATED_IRON_BLOCK.get())),
    PLATED_GOLD(4, 1600, 20.0f, 3.5f, 60,
            () -> Ingredient.of(EunithiceBlocks.COMPRESSED_PLATED_GOLD_BLOCK.get())),
    PLATED_DIAMOND(5, 3200, 30.0f, 5.0f, 90,
            () -> Ingredient.of(EunithiceBlocks.DIAMOND_PLATE_BLOCK.get()));

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