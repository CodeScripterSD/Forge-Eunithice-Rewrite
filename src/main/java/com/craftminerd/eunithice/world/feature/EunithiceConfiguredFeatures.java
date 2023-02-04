package com.craftminerd.eunithice.world.feature;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

import java.util.List;

public class EunithiceConfiguredFeatures {

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_NEUDONITE_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, EunithiceBlocks.NEUDONITE_ORE.get().defaultBlockState())
    );

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NEUDONITE_ORE = FeatureUtils.register("neudonite_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_NEUDONITE_ORES, 9));
}
