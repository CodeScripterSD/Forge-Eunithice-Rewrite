package com.craftminerd.eunithice.world.feature;

import com.craftminerd.eunithice.Eunithice;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class EunithicePlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, Eunithice.MODID);

    public static final RegistryObject<PlacedFeature> NEUDONITE_ORE_PLACED = PLACED_FEATURES.register("neudonite_ore_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>)(Holder<? extends ConfiguredFeature<?, ?>>)
        EunithiceConfiguredFeatures.NEUDONITE_ORE, EunithiceOrePlacements.commonOrePlacement(9, // Veins per chunk
                    HeightRangePlacement.triangle(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(128)))));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
