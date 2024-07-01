package com.craftminerd.eunithice.datagen;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.util.EunithiceTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class EunithiceTagsProviders {
    public static class EunithiceBlockTagsProvider extends BlockTagsProvider {
        public EunithiceBlockTagsProvider(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, modId, existingFileHelper);
        }

        @Override
        public String getName() {
            return "Eunithice Block Tags";
        }

        @Override
        protected void addTags() {
            MakeVanillaBlockTags();
            MakeForgeBlockTags();
            MakeEunithiceBlockTags();
            MakeAutomatedTags();
        }

        private void MakeAutomatedTags() {
            EunithiceBlocks.BLOCKS.getEntries().forEach( blockRegistryObject -> {
                if (blockRegistryObject.get() instanceof DoorBlock doorBlock) {
                    if (!doorBlock.defaultBlockState().is(BlockTags.DOORS)) {
                        tag(BlockTags.DOORS).add(doorBlock);
                        if (doorBlock.defaultBlockState().getMaterial() == Material.WOOD) {
                            tag(BlockTags.WOODEN_DOORS).add(doorBlock);
                        }
                    }
                } else if (blockRegistryObject.get() instanceof FenceBlock fenceBlock) {
                    if (!fenceBlock.defaultBlockState().is(BlockTags.FENCES)) {
                        tag(BlockTags.FENCES).add(fenceBlock);
                        if (fenceBlock.defaultBlockState().getMaterial() == Material.WOOD) {
                            tag(BlockTags.WOODEN_FENCES).add(fenceBlock);
                        }
                    }
                } else if (blockRegistryObject.get() instanceof FenceGateBlock fenceGateBlock) {
                    if (!fenceGateBlock.defaultBlockState().is(BlockTags.FENCE_GATES)) {
                        tag(BlockTags.FENCE_GATES).add(fenceGateBlock);
                    }
                } else if (blockRegistryObject.get() instanceof ButtonBlock buttonBlock) {
                    if (!buttonBlock.defaultBlockState().is(BlockTags.BUTTONS)) {
                        tag(BlockTags.BUTTONS).add(buttonBlock);
                        if (buttonBlock.defaultBlockState().getMaterial() == Material.WOOD) {
                            tag(BlockTags.WOODEN_BUTTONS).add(buttonBlock);
                        }
                    }
                } else if (blockRegistryObject.get() instanceof SlabBlock slabBlock) {
                    if (!slabBlock.defaultBlockState().is(BlockTags.SLABS)) {
                        tag(BlockTags.SLABS).add(slabBlock);
                        if (slabBlock.defaultBlockState().getMaterial() == Material.WOOD) {
                            tag(BlockTags.WOODEN_SLABS).add(slabBlock);
                        }
                    }
                } else if (blockRegistryObject.get() instanceof StairBlock stairBlock) {
                    if (!stairBlock.defaultBlockState().is(BlockTags.STAIRS)) {
                        tag(BlockTags.STAIRS).add(stairBlock);
                        if (stairBlock.defaultBlockState().getMaterial() == Material.WOOD) {
                            tag(BlockTags.WOODEN_STAIRS).add(stairBlock);
                        }
                    }
                } else if (blockRegistryObject.get() instanceof PressurePlateBlock pressurePlateBlock) {
                    if (!pressurePlateBlock.defaultBlockState().is(BlockTags.PRESSURE_PLATES)) {
                        tag(BlockTags.PRESSURE_PLATES).add(pressurePlateBlock);
                        if (pressurePlateBlock.defaultBlockState().getMaterial() == Material.WOOD) {
                            tag(BlockTags.WOODEN_PRESSURE_PLATES).add(pressurePlateBlock);
                        }
                    }
                } else if (blockRegistryObject.get() instanceof LeavesBlock leavesBlock) {
                    if (!leavesBlock.defaultBlockState().is(BlockTags.LEAVES)) {
                        tag(BlockTags.LEAVES).add(leavesBlock);
                    }
                }
            });
        }

        private void MakeEunithiceBlockTags() {
            tag(EunithiceTags.Blocks.DARKWOOD_LOGS).add(
                    EunithiceBlocks.DARKWOOD_LOG.get(),
                    EunithiceBlocks.STRIPPED_DARKWOOD_LOG.get(),
                    EunithiceBlocks.DARKWOOD_WOOD.get(),
                    EunithiceBlocks.STRIPPED_DARKWOOD_WOOD.get()
            );
            tag(EunithiceTags.Blocks.MINEABLE_MULTITOOL).addTags(
                    BlockTags.MINEABLE_WITH_AXE,
                    BlockTags.MINEABLE_WITH_HOE,
                    BlockTags.MINEABLE_WITH_PICKAXE,
                    BlockTags.MINEABLE_WITH_SHOVEL
            );
            tag(EunithiceTags.Blocks.NEEDS_NEUDONITE_TOOL);
            tag(EunithiceTags.Blocks.NEEDS_LYMINE_TOOL).add(
                    EunithiceBlocks.MYELITE_BLOCK.get()
            );
            tag(EunithiceTags.Blocks.NEEDS_MYELITE_TOOL);
        }

        private void MakeForgeBlockTags() {
            tag(EunithiceTags.Blocks.FORGE_ORES).add(
                    EunithiceBlocks.NEUDONITE_ORE.get(),
                    EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get()
            );
            tag(EunithiceTags.Blocks.NEEDS_WOODEN_TOOL).add(
                    EunithiceBlocks.STONE_TRAPDOOR.get(),
                    EunithiceBlocks.STONE_DOOR.get()
            );
            tag(EunithiceTags.Blocks.NEEDS_NETHERITE_TOOL);
        }

        private void MakeVanillaBlockTags() {
            tag(BlockTags.CROPS).add(
                    EunithiceBlocks.LEURITE_CROP.get()
            );
            tag(BlockTags.LOGS).addTags(EunithiceTags.Blocks.DARKWOOD_LOGS);
            tag(BlockTags.LOGS_THAT_BURN).addTags(EunithiceTags.Blocks.DARKWOOD_LOGS);
            tag(BlockTags.PLANKS).add(
                    EunithiceBlocks.DARKWOOD_PLANKS.get()
            );
            tag(BlockTags.WOODEN_BUTTONS).add(
                    EunithiceBlocks.DARKWOOD_BUTTON.get()
            );
            tag(BlockTags.WOODEN_TRAPDOORS).add(
                    EunithiceBlocks.DARKWOOD_TRAPDOOR.get()
            );
            tag(BlockTags.MINEABLE_WITH_AXE);
            tag(BlockTags.MINEABLE_WITH_HOE).add(
                    EunithiceBlocks.DARKWOOD_LEAVES.get()
            );
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                    EunithiceBlocks.ASPHALT.get(),
                    EunithiceBlocks.SPEED_INFUSED_ASPHALT.get(),
                    EunithiceBlocks.HONEY_INFUSED_ASPHALT.get(),
                    EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get(),
                    EunithiceBlocks.SUPER_SPEED_INFUSED_ASPHALT.get(),
                    EunithiceBlocks.IRON_PLATE_TRAPDOOR.get(),
                    EunithiceBlocks.IRON_PLATE_DOOR.get(),
                    EunithiceBlocks.INFUSER.get(),
                    EunithiceBlocks.EXTRACTOR.get(),
                    EunithiceBlocks.RAW_NEUDONITE_BLOCK.get(),
                    EunithiceBlocks.NEUDONITE_ORE.get(),
                    EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get(),
                    EunithiceBlocks.NEUDONITE_BLOCK.get(),
                    EunithiceBlocks.LYMINE_BLOCK.get(),
                    EunithiceBlocks.MYELITE_BLOCK.get(),
                    EunithiceBlocks.STONE_TRAPDOOR.get(),
                    EunithiceBlocks.STONE_DOOR.get()
            );
            tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                    EunithiceBlocks.GRASS_SLAB.get(),
                    EunithiceBlocks.GRASS_STAIRS.get(),
                    EunithiceBlocks.DIRT_SLAB.get(),
                    EunithiceBlocks.DIRT_STAIRS.get()
            );
            tag(BlockTags.BEACON_BASE_BLOCKS).add(
                    EunithiceBlocks.NEUDONITE_BLOCK.get(),
                    EunithiceBlocks.LYMINE_BLOCK.get(),
                    EunithiceBlocks.MYELITE_BLOCK.get()
            );
            tag(BlockTags.NEEDS_DIAMOND_TOOL);
            tag(BlockTags.NEEDS_IRON_TOOL).add(
//                    EunithiceBlocks.INFUSER.get(),
//                    EunithiceBlocks.EXTRACTOR.get(),
                    EunithiceBlocks.NEUDONITE_ORE.get(),
                    EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get(),
                    EunithiceBlocks.RAW_NEUDONITE_BLOCK.get(),
                    EunithiceBlocks.NEUDONITE_BLOCK.get()
            );
            tag(BlockTags.NEEDS_STONE_TOOL).add(
                    EunithiceBlocks.IRON_PLATE_TRAPDOOR.get(),
                    EunithiceBlocks.IRON_PLATE_DOOR.get(),
                    EunithiceBlocks.ASPHALT.get(),
                    EunithiceBlocks.SPEED_INFUSED_ASPHALT.get(),
                    EunithiceBlocks.HONEY_INFUSED_ASPHALT.get(),
                    EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get(),
                    EunithiceBlocks.SUPER_SPEED_INFUSED_ASPHALT.get()
            );
        }
    }

    public static class EunithiceItemTagsProvider extends ItemTagsProvider {
        public EunithiceItemTagsProvider(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, pBlockTagsProvider, modId, existingFileHelper);
        }

        @Override
        public String getName() {
            return "Eunithice Item Tags";
        }

        @Override
        protected void addTags() {
            MakeVanillaItemTags();
            MakeForgeItemTags();
            MakeEunithiceItemTags();
        }

        private void MakeEunithiceItemTags() {
            copy(EunithiceTags.Blocks.DARKWOOD_LOGS, EunithiceTags.Items.DARKWOOD_LOGS);
            tag(EunithiceTags.Items.SHORTBOWS).add(
                    EunithiceItems.EXPERIMENTAL_BOW.get()
            );
        }

        private void MakeForgeItemTags() {
            tag(EunithiceTags.Items.FORGE_CROPS).add(
                    EunithiceItems.LEURITE_GRAINS.get()
            );
            tag(EunithiceTags.Items.FORGE_SEEDS).add(
                    EunithiceItems.LEURITE_SEEDS.get()
            );
        }

        private void MakeVanillaItemTags() {
            tag(ItemTags.BEACON_PAYMENT_ITEMS).add(
                    EunithiceItems.NEUDONITE_INGOT.get(),
                    EunithiceItems.LYMINE_INGOT.get(),
                    EunithiceItems.MYELITE.get()
            );
            copy(BlockTags.DOORS, ItemTags.DOORS);
            copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
            copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
            copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
            copy(BlockTags.SLABS, ItemTags.SLABS);
            copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
            copy(BlockTags.STAIRS, ItemTags.STAIRS);
            copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
            copy(BlockTags.LEAVES, ItemTags.LEAVES);
            copy(BlockTags.PLANKS, ItemTags.PLANKS);
            copy(BlockTags.LOGS, ItemTags.LOGS);
            copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);
            copy(BlockTags.FENCES, ItemTags.FENCES);
            copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
        }
    }

    public static class EunithiceFluidTagsProvider extends FluidTagsProvider {
        public EunithiceFluidTagsProvider(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, modId, existingFileHelper);
        }

        @Override
        public String getName() {
            return "Eunithice Fluid Tags";
        }

        @Override
        protected void addTags() {

        }
    }
}
