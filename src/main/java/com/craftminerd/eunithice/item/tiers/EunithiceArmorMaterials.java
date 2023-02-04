package com.craftminerd.eunithice.item.tiers;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum EunithiceArmorMaterials implements ArmorMaterial {

    PLATED_IRON("plated_iron",27, new int[] { 3, 6, 7, 3 }, 20, SoundEvents.ARMOR_EQUIP_IRON, 0.0f, 0.0f, () -> {
        return Ingredient.of(EunithiceBlocks.PLATED_IRON_BLOCK.get());
    }),
    PLATED_GOLD("plated_gold",60, new int[] { 5, 7, 9, 5 }, 60, SoundEvents.ARMOR_EQUIP_GOLD, 2.0f, 0.1f, () -> {
        return Ingredient.of(EunithiceBlocks.COMPRESSED_PLATED_GOLD_BLOCK.get());
    }),
    PLATED_DIAMOND("plated_diamond",90, new int[] { 7, 9, 11, 7 }, 90, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0f, 0.1f, () -> {
        return Ingredient.of(EunithiceBlocks.DIAMOND_PLATE_BLOCK.get());
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final int durabilityMultiplier;
    private final int enchantmentValue;
    private final String name;
    private final int[] slotProtections;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final float toughness;
    private final SoundEvent sound;

    private EunithiceArmorMaterials(String p_40474_, int p_40475_, int[] p_40476_, int p_40477_, SoundEvent p_40478_, float p_40479_, float p_40480_, Supplier<Ingredient> p_40481_) {
        this.name = p_40474_;
        this.durabilityMultiplier = p_40475_;
        this.slotProtections = p_40476_;
        this.enchantmentValue = p_40477_;
        this.sound = p_40478_;
        this.toughness = p_40479_;
        this.knockbackResistance = p_40480_;
        this.repairIngredient = new LazyLoadedValue<>(p_40481_);
    }

    public int getDurabilityForSlot(EquipmentSlot p_40484_) {
        return HEALTH_PER_SLOT[p_40484_.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot p_40487_) {
        return this.slotProtections[p_40487_.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return Eunithice.MODID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}
