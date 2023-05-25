package com.craftminerd.eunithice.world.biomemods;

import com.craftminerd.eunithice.Eunithice;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, Eunithice.MODID);

    public static RegistryObject<Codec<EunithiceOreBiomeModifier>> ORE_MODIFIER = BIOME_MODIFIERS.register("ores", () ->
            RecordCodecBuilder.create(builder -> builder.group(
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(EunithiceOreBiomeModifier::biomes),
                    PlacedFeature.CODEC.fieldOf("feature").forGetter(EunithiceOreBiomeModifier::feature)
            ).apply(builder, EunithiceOreBiomeModifier::new)));

    public static void register(IEventBus eventBus) {
        BIOME_MODIFIERS.register(eventBus);
    }

}
