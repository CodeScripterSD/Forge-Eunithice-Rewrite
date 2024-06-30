package com.craftminerd.eunithice.enchantments;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.item.custom.CustomBowItem;
import com.craftminerd.eunithice.util.EunithiceTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Eunithice.MODID);

    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }

    static EnchantmentCategory SWORD_AND_DIGGING = EnchantmentCategory.create("eunithice_sword_and_digging_category", (item) -> item instanceof SwordItem || item instanceof DiggerItem);
    static EnchantmentCategory SHIELD = EnchantmentCategory.create("eunithice_shield_category", (item) -> item instanceof ShieldItem);
    static EnchantmentCategory CUSTOM_BOW = EnchantmentCategory.create("eunithice_custom_bow_category", (item) -> item.getDefaultInstance().is(EunithiceTags.Items.SHORTBOWS));

    public static final RegistryObject<Enchantment> SMELTING = ENCHANTMENTS.register("smelting",
            () -> new AutoSmeltEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> TELEPORTITIS = ENCHANTMENTS.register("teleportitis",
            () -> new TeleportitisEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> FIRING_SPEED = ENCHANTMENTS.register("firing_speed",
            () -> new FiringSpeedEnchantment(Enchantment.Rarity.UNCOMMON, CUSTOM_BOW, EquipmentSlot.MAINHAND));

}
