package com.craftminerd.eunithice.world.feature;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.config.EunithiceCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class EunithiceConfiguredFeatures {
    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_NEUDONITE_ORES = List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, EunithiceBlocks.NEUDONITE_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get().defaultBlockState())
    );

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> NEUDONITE_ORE = FeatureUtils.register("neudonite_ore",
            Feature.ORE, new OreConfiguration(OVERWORLD_NEUDONITE_ORES, EunithiceCommonConfigs.NEUDONITE_ORE_VEIN_SIZE.get()));

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> DARKWOOD_TREE =
            FeatureUtils.register("darkwood", Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(EunithiceBlocks.DARKWOOD_LOG.get()),
                    new StraightTrunkPlacer(5, 3, 0),
                    BlockStateProvider.simple(EunithiceBlocks.DARKWOOD_LEAVES.get()),
                    // Radius, Offset, Height
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(2, 0, 2)
            ).build());

    public static final Holder<PlacedFeature> DARKWOOD_CHECKED = PlacementUtils.register("darkwood_checked", DARKWOOD_TREE,
            PlacementUtils.filteredByBlockSurvival(EunithiceBlocks.DARKWOOD_SAPLING.get()));

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> DARKWOOD_SPAWN =
            FeatureUtils.register("darkwood_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(DARKWOOD_CHECKED,
                            0.01f)), DARKWOOD_CHECKED)); // p_204787: chance
}
