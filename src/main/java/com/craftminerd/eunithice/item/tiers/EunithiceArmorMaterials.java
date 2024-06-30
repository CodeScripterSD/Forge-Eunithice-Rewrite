package com.craftminerd.eunithice.item.tiers;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.item.EunithiceItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum EunithiceArmorMaterials implements ArmorMaterial {
    NEUDONITE("neudonite",26,
            new int[] { 2, 6, 6, 3 }, 11,
            SoundEvents.ARMOR_EQUIP_IRON, 0.5f, 0.0f,
            () -> Ingredient.of(EunithiceItems.NEUDONITE_INGOT.get())),
    LYMINE("lymine",32,
            new int[] { 6, 9, 8, 5 }, 24,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 2.5f, 0.2f,
            () -> Ingredient.of(EunithiceItems.LYMINE.get())),
    MYELITE("myelite",48,
            new int[] { 8, 10, 9, 7 }, 30,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0f, 0.5f,
            () -> Ingredient.of(EunithiceItems.MYELITE_INGOT.get()));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final int durabilityMultiplier;
    private final int enchantmentValue;
    private final String name;
    private final int[] slotProtections;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final float toughness;
    private final SoundEvent sound;

    private EunithiceArmorMaterials(String pName, int pDurabilityMultiplier, int[] pSlotProtections, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.slotProtections = pSlotProtections;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
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
