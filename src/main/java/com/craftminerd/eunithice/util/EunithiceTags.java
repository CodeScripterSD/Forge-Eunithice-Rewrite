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

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Eunithice.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> HAMMERS = tag("hammers");
        public static final TagKey<Item> CORES = tag("cores");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Eunithice.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}