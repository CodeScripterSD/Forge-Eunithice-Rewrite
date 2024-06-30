package com.craftminerd.eunithice.world.feature;

import com.craftminerd.eunithice.config.EunithiceCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class EunithicePlacedFeatures {
    public static final Holder<PlacedFeature> NEUDONITE_ORE_PLACED = PlacementUtils.register("neudonite_ore_placed",
            EunithiceConfiguredFeatures.NEUDONITE_ORE, EunithiceOrePlacements.commonOrePlacement(EunithiceCommonConfigs.NEUDONITE_ORE_VEINS_PER_CHUNK.get(), // Veins per chunk
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(40))));

    public static final Holder<PlacedFeature> DARKWOOD_PLACED = PlacementUtils.register("darkwood_placed",
            EunithiceConfiguredFeatures.DARKWOOD_SPAWN, VegetationPlacements.treePlacement(
                    PlacementUtils.countExtra(0, 0.02f, 1) // p_195366: chance, p_195367: amount
            ));
}
