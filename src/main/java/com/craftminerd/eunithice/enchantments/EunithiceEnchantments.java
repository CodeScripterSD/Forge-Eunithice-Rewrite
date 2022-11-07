package com.craftminerd.eunithice.enchantments;

import com.craftminerd.eunithice.Eunithice;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Eunithice.MODID);

    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }

    public static final RegistryObject<Enchantment> WITHERED_THORNS = ENCHANTMENTS.register("withered_thorns",
            () -> new WitheredThornsEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.CHEST));
}
