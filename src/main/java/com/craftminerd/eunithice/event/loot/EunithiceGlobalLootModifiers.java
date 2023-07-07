package com.craftminerd.eunithice.event.loot;

import com.craftminerd.eunithice.Eunithice;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceGlobalLootModifiers {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, Eunithice.MODID);

    public static final RegistryObject<AutoSmeltWithEnchantmentAdditionModifier.Serializer> SMELTING = LOOT_MODIFIERS.register("smelting", AutoSmeltWithEnchantmentAdditionModifier.Serializer::new);
}
