package com.craftminerd.eunithice.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class EunithicePlacedFeatures {
    public static final Holder<PlacedFeature> NEUDONITE_ORE_PLACED = PlacementUtils.register("neudonite_ore_placed",
            EunithiceConfiguredFeatures.NEUDONITE_ORE, EunithiceOrePlacements.commonOrePlacement(9, // Veins per chunk
                    HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(128))));
}
