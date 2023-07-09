package com.craftminerd.eunithice.world.feature;

import com.craftminerd.eunithice.config.EunithiceCommonConfigs;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class EunithicePlacedFeatures {
    public static final Holder<PlacedFeature> NEUDONITE_ORE_PLACED = PlacementUtils.register("neudonite_ore_placed",
            EunithiceConfiguredFeatures.NEUDONITE_ORE, EunithiceOrePlacements.commonOrePlacement(EunithiceCommonConfigs.NEUDONITE_ORE_VEINS_PER_CHUNK.get(), // Veins per chunk
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(40))));
}
