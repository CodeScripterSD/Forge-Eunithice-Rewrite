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
    public static Tier LYMINE;
    public static Tier MYELITE;
    public static Tier SERRATED;

    static {
        NEUDONITE = TierSortingRegistry.registerTier(
                new ForgeTier(2, 750, 7.5f, 2.6f, 16,
                        EunithiceTags.Blocks.NEEDS_NEUDONITE_TOOL, () -> Ingredient.of(EunithiceItems.NEUDONITE_INGOT.get())),
                new ResourceLocation(Eunithice.MODID, "neudonite"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND)
        );

//        LEURIUM = TierSortingRegistry.registerTier(
//                new ForgeTier(3, 2048, 20.0f, 3.4f, 24,
//                        EunithiceTags.Blocks.NEEDS_LEURIUM_TOOL, () -> Ingredient.of(EunithiceItems.LEURIUM_INGOT.get())),
//                new ResourceLocation(Eunithice.MODID, "leurium"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE)
//        );

        LYMINE = TierSortingRegistry.registerTier(
                new ForgeTier(4, 2591, 12.0f, 4.0f, 21,
                        EunithiceTags.Blocks.NEEDS_LYMINE_TOOL, () -> Ingredient.of(EunithiceItems.LYMINE.get())),
                new ResourceLocation(Eunithice.MODID, "lymine"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE)
        );

        MYELITE = TierSortingRegistry.registerTier(
                new ForgeTier(4, 4000, 20.0f, 6.6f, 28,
                        EunithiceTags.Blocks.NEEDS_MYELITE_TOOL, () -> Ingredient.of(EunithiceItems.MYELITE_INGOT.get())),
                new ResourceLocation(Eunithice.MODID, "myelite"), List.of(Tiers.NETHERITE), List.of()
        );

        SERRATED = TierSortingRegistry.registerTier(
                new ForgeTier(0, 1561, 25f, 2f, 20,
                        EunithiceTags.Blocks.NEEDS_WOODEN_TOOL, () -> Ingredient.of(Items.POINTED_DRIPSTONE)),
                new ResourceLocation(Eunithice.MODID, "serrated"), List.of(), List.of()
        );
    }
}