package com.craftminerd.eunithice.item.tiers;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.util.EunithiceTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class EunithiceToolTiers {
    public static Tier NEUDONITE;
    public static Tier SERRATED;

    static {
        NEUDONITE = TierSortingRegistry.registerTier(
                new ForgeTier(2, 750, 10.0f, 2.6f, 16,
                        EunithiceTags.Blocks.NEEDS_NEUDONITE_TOOL, () -> Ingredient.of(EunithiceItems.NEUDONITE_INGOT.get())),
                new ResourceLocation(Eunithice.MODID, "neudonite"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND)
        );

        SERRATED = TierSortingRegistry.registerTier(
                new ForgeTier(3, 900, 25f, 6f, 90,
                        BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.POINTED_DRIPSTONE)),
                new ResourceLocation(Eunithice.MODID, "serrated"), List.of(Tiers.DIAMOND), List.of()
        );
    }

}