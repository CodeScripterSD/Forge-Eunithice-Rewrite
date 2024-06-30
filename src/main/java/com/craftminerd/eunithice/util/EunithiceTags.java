package com.craftminerd.eunithice.util;

import com.craftminerd.eunithice.Eunithice;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.common.Tags;

public class EunithiceTags {
    public static class Fluids {
        public static final TagKey<Fluid> EXTRACTOR_ACCEPTED_FLUIDS = tag("extractor_accepted_fluids");
        private static TagKey<Fluid> tag(String name) {
            return FluidTags.create(new ResourceLocation(Eunithice.MODID, name));
        }
        private static TagKey<Fluid> forgeTag(String name) {
            return FluidTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> FORGE_ORES = forgeTag("ores");
        public static final TagKey<Block> NEEDS_WOODEN_TOOL = forgeTag("needs_wooden_tool");
        public static final TagKey<Block> NEEDS_NETHERITE_TOOL = forgeTag("needs_netherite_tool");

        public static final TagKey<Block> DARKWOOD_LOGS = tag("darkwood_logs");
        public static final TagKey<Block> MINEABLE_MULTITOOL = tag("mineable/multitool");
        public static final TagKey<Block> NEEDS_NEUDONITE_TOOL = tag("needs_neudonite_tool");
        public static final TagKey<Block> NEEDS_LYMINE_TOOL = tag("needs_lymine_tool");
        public static final TagKey<Block> NEEDS_MYELITE_TOOL = tag("needs_myelite_tool");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Eunithice.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> FORGE_CROPS = forgeTag("crops");
        public static final TagKey<Item> FORGE_SEEDS = forgeTag("seeds");

        public static final TagKey<Item> DARKWOOD_LOGS = tag("darkwood_logs");
//        public static final TagKey<Item> HAMMERS = tag("hammers");
        public static final TagKey<Item> CORES = tag("cores");
        public static final TagKey<Item> SHORTBOWS = tag("shortbows");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Eunithice.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}