package com.craftminerd.eunithice.datagen;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.datagen.custom.AsphaltInfuserRecipeBuilder;
import com.craftminerd.eunithice.datagen.custom.EunithiceCookingRecipeBuilder;
//import com.craftminerd.eunithice.datagen.custom.ExtractorRecipeBuilder;
import com.craftminerd.eunithice.datagen.custom.ExtractorRecipeBuilder;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.util.EunithiceTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.NonNullList;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class EunithiceRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public EunithiceRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {

        ////////////////
        // ARMOR RECIPES
        ////////////////
        ShapedRecipeBuilder.shaped(EunithiceItems.EXPERIMENTAL_BOW.get())
                .define('E', Items.ENDER_EYE)
                .define('S', Items.STRING)
                .define('O', Items.OBSIDIAN)
                .define('Q', Items.QUARTZ)
                .pattern(" QS")
                .pattern("OES")
                .pattern(" QS")
                .unlockedBy(getHasName(Items.OBSIDIAN), has(Items.OBSIDIAN))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_BOOTS.get())
                .define('#', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(EunithiceItems.NEUDONITE_INGOT.get()), has(EunithiceItems.NEUDONITE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_CHESTPLATE.get())
                .define('#', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(EunithiceItems.NEUDONITE_INGOT.get()), has(EunithiceItems.NEUDONITE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_HELMET.get())
                .define('#', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("###")
                .pattern("# #")
                .unlockedBy(getHasName(EunithiceItems.NEUDONITE_INGOT.get()), has(EunithiceItems.NEUDONITE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_LEGGINGS.get())
                .define('#', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(EunithiceItems.NEUDONITE_INGOT.get()), has(EunithiceItems.NEUDONITE_INGOT.get()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceItems.LYMINE_BOOTS.get())
                .define('#', EunithiceItems.LYMINE_INGOT.get())
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(EunithiceItems.LYMINE_INGOT.get()), has(EunithiceItems.LYMINE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.LYMINE_CHESTPLATE.get())
                .define('#', EunithiceItems.LYMINE_INGOT.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(EunithiceItems.LYMINE_INGOT.get()), has(EunithiceItems.LYMINE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.LYMINE_HELMET.get())
                .define('#', EunithiceItems.LYMINE_INGOT.get())
                .pattern("###")
                .pattern("# #")
                .unlockedBy(getHasName(EunithiceItems.LYMINE_INGOT.get()), has(EunithiceItems.LYMINE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.LYMINE_LEGGINGS.get())
                .define('#', EunithiceItems.LYMINE_INGOT.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(EunithiceItems.LYMINE_INGOT.get()), has(EunithiceItems.LYMINE_INGOT.get()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE_BOOTS.get())
                .define('#', EunithiceItems.MYELITE.get())
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(EunithiceItems.MYELITE.get()), has(EunithiceItems.MYELITE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE_CHESTPLATE.get())
                .define('#', EunithiceItems.MYELITE.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(EunithiceItems.MYELITE.get()), has(EunithiceItems.MYELITE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE_HELMET.get())
                .define('#', EunithiceItems.MYELITE.get())
                .pattern("###")
                .pattern("# #")
                .unlockedBy(getHasName(EunithiceItems.MYELITE.get()), has(EunithiceItems.MYELITE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE_LEGGINGS.get())
                .define('#', EunithiceItems.MYELITE.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(EunithiceItems.MYELITE.get()), has(EunithiceItems.MYELITE.get()))
                .save(pFinishedRecipeConsumer);

        ////////////////
        // BLOCK RECIPES
        ////////////////

        eunithicePlanksFromLogs(pFinishedRecipeConsumer, EunithiceBlocks.DARKWOOD_PLANKS.get(), EunithiceTags.Items.DARKWOOD_LOGS);
        eunithiceWoodFromLogs(pFinishedRecipeConsumer, EunithiceBlocks.DARKWOOD_WOOD.get(), EunithiceBlocks.DARKWOOD_LOG.get());
        eunithiceWoodFromLogs(pFinishedRecipeConsumer, EunithiceBlocks.STRIPPED_DARKWOOD_WOOD.get(), EunithiceBlocks.STRIPPED_DARKWOOD_LOG.get());
        eunithiceFence(pFinishedRecipeConsumer, EunithiceBlocks.DARKWOOD_FENCE.get(), EunithiceBlocks.DARKWOOD_PLANKS.get());
        eunithiceFenceGate(pFinishedRecipeConsumer, EunithiceBlocks.DARKWOOD_FENCE_GATE.get().asItem(), EunithiceBlocks.DARKWOOD_PLANKS.get());
        eunithiceSlab(pFinishedRecipeConsumer, EunithiceBlocks.DARKWOOD_SLAB.get(), EunithiceBlocks.DARKWOOD_PLANKS.get());
        eunithiceStairs(pFinishedRecipeConsumer, EunithiceBlocks.DARKWOOD_STAIRS.get(), EunithiceBlocks.DARKWOOD_PLANKS.get());
        eunithiceDoor(pFinishedRecipeConsumer, EunithiceBlocks.DARKWOOD_DOOR.get(), EunithiceBlocks.DARKWOOD_PLANKS.get());
        eunithiceTrapdoor(pFinishedRecipeConsumer, EunithiceBlocks.DARKWOOD_TRAPDOOR.get(), EunithiceBlocks.DARKWOOD_PLANKS.get());
        eunithiceButton(pFinishedRecipeConsumer, EunithiceBlocks.DARKWOOD_BUTTON.get(), EunithiceBlocks.DARKWOOD_PLANKS.get());
        eunithicePressurePlate(pFinishedRecipeConsumer, EunithiceBlocks.DARKWOOD_PRESSURE_PLATE.get(), EunithiceBlocks.DARKWOOD_PLANKS.get());

        eunithiceSlab(pFinishedRecipeConsumer, EunithiceBlocks.DIRT_SLAB.get(), Blocks.DIRT);
        eunithiceStairs(pFinishedRecipeConsumer, EunithiceBlocks.DIRT_STAIRS.get(), Blocks.DIRT);
        eunithiceSlab(pFinishedRecipeConsumer, EunithiceBlocks.GRASS_SLAB.get(), Blocks.GRASS_BLOCK);
        eunithiceStairs(pFinishedRecipeConsumer, EunithiceBlocks.GRASS_STAIRS.get(), Blocks.GRASS_BLOCK);

        ShapelessRecipeBuilder.shapeless(Blocks.DIRT, 6)
                .requires(EunithiceBlocks.DIRT_STAIRS.get(), 4)
                .unlockedBy(getHasName(EunithiceBlocks.DIRT_STAIRS.get()), has(EunithiceBlocks.DIRT_STAIRS.get()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID,
                        Blocks.DIRT.asItem().getRegistryName().getPath()+"_from_"+EunithiceBlocks.DIRT_STAIRS.get().asItem().getRegistryName().getPath()));
        ShapelessRecipeBuilder.shapeless(Blocks.DIRT, 1)
                .requires(EunithiceBlocks.DIRT_SLAB.get(), 2)
                .unlockedBy(getHasName(EunithiceBlocks.DIRT_SLAB.get()), has(EunithiceBlocks.DIRT_SLAB.get()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID,
                        Blocks.DIRT.asItem().getRegistryName().getPath()+"_from_"+EunithiceBlocks.DIRT_SLAB.get().asItem().getRegistryName().getPath()));
        ShapelessRecipeBuilder.shapeless(Blocks.GRASS_BLOCK, 6)
                .requires(EunithiceBlocks.GRASS_STAIRS.get(), 4)
                .unlockedBy(getHasName(EunithiceBlocks.GRASS_STAIRS.get()), has(EunithiceBlocks.GRASS_STAIRS.get()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID,
                        Blocks.GRASS_BLOCK.asItem().getRegistryName().getPath()+"_from_"+EunithiceBlocks.GRASS_STAIRS.get().asItem().getRegistryName().getPath()));
        ShapelessRecipeBuilder.shapeless(Blocks.GRASS_BLOCK, 1)
                .requires(EunithiceBlocks.GRASS_SLAB.get(), 2)
                .unlockedBy(getHasName(EunithiceBlocks.GRASS_SLAB.get()), has(EunithiceBlocks.GRASS_SLAB.get()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID,
                        Blocks.GRASS_BLOCK.asItem().getRegistryName().getPath()+"_from_"+EunithiceBlocks.GRASS_SLAB.get().asItem().getRegistryName().getPath()));

        ShapelessRecipeBuilder.shapeless(EunithiceItems.LYMINE_INGOT.get(), 1)
                .requires(Items.NETHERITE_INGOT)
                .requires(Items.NETHER_WART_BLOCK, 2)
                .requires(Items.DIAMOND)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(EunithiceBlocks.ASPHALT.get(), 4)
                .requires(Items.BLACK_DYE)
                .requires(Items.SAND, 2)
                .requires(Items.COBBLESTONE, 2)
                .unlockedBy("has_cobblestone", inventoryTrigger(ItemPredicate.Builder.item().of(Items.COBBLESTONE).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.RAW_NEUDONITE_BLOCK.get())
                .define('#', EunithiceItems.RAW_NEUDONITE.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_raw_neudonite", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.RAW_NEUDONITE.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.NEUDONITE_BLOCK.get())
                .define('#', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.SUPER_SPEED_INFUSED_ASPHALT.get())
                .define('#', Items.BONE_BLOCK)
                .define('A', EunithiceBlocks.SPEED_INFUSED_ASPHALT.get())
                .pattern("###")
                .pattern("#A#")
                .pattern("###")
                .unlockedBy("has_speed_asphalt", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceBlocks.SPEED_INFUSED_ASPHALT.get()).build()))
                .save(pFinishedRecipeConsumer);

        ////////////////
        // ITEM RECIPES
        ////////////////

        ShapedRecipeBuilder.shaped(EunithiceItems.WOODEN_OMNITOOL.get())
                .define('#', Items.STICK)
                .define('P', Items.WOODEN_PICKAXE)
                .define('A', Items.WOODEN_AXE)
                .define('S', Items.WOODEN_SHOVEL)
                .define('H', Items.WOODEN_HOE)
                .pattern("ASP")
                .pattern("H# ")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.WOODEN_PICKAXE), has(Items.WOODEN_PICKAXE))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.GOLDEN_OMNITOOL.get())
                .define('#', Items.STICK)
                .define('P', Items.GOLDEN_PICKAXE)
                .define('A', Items.GOLDEN_AXE)
                .define('S', Items.GOLDEN_SHOVEL)
                .define('H', Items.GOLDEN_HOE)
                .pattern("ASP")
                .pattern("H# ")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.GOLDEN_PICKAXE), has(Items.GOLDEN_PICKAXE))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.STONE_OMNITOOL.get())
                .define('#', Items.STICK)
                .define('P', Items.STONE_PICKAXE)
                .define('A', Items.STONE_AXE)
                .define('S', Items.STONE_SHOVEL)
                .define('H', Items.STONE_HOE)
                .pattern("ASP")
                .pattern("H# ")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.STONE_PICKAXE), has(Items.STONE_PICKAXE))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.IRON_OMNITOOL.get())
                .define('#', Items.STICK)
                .define('P', Items.IRON_PICKAXE)
                .define('A', Items.IRON_AXE)
                .define('S', Items.IRON_SHOVEL)
                .define('H', Items.IRON_HOE)
                .pattern("ASP")
                .pattern("H# ")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.IRON_PICKAXE), has(Items.IRON_PICKAXE))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.DIAMOND_OMNITOOL.get())
                .define('#', Items.STICK)
                .define('P', Items.DIAMOND_PICKAXE)
                .define('A', Items.DIAMOND_AXE)
                .define('S', Items.DIAMOND_SHOVEL)
                .define('H', Items.DIAMOND_HOE)
                .pattern("ASP")
                .pattern("H# ")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.DIAMOND_PICKAXE), has(Items.DIAMOND_PICKAXE))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NETHERITE_OMNITOOL.get())
                .define('#', Items.STICK)
                .define('P', Items.NETHERITE_PICKAXE)
                .define('A', Items.NETHERITE_AXE)
                .define('S', Items.NETHERITE_SHOVEL)
                .define('H', Items.NETHERITE_HOE)
                .pattern("ASP")
                .pattern("H# ")
                .pattern(" # ")
                .unlockedBy(getHasName(Items.NETHERITE_PICKAXE), has(Items.NETHERITE_PICKAXE))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.LYMINE_OMNITOOL.get())
                .define('#', Items.STICK)
                .define('P', EunithiceItems.LYMINE_PICKAXE.get())
                .define('A', EunithiceItems.LYMINE_AXE.get())
                .define('S', EunithiceItems.LYMINE_SHOVEL.get())
                .define('H', EunithiceItems.LYMINE_HOE.get())
                .pattern("ASP")
                .pattern("H# ")
                .pattern(" # ")
                .unlockedBy(getHasName(EunithiceItems.LYMINE_PICKAXE.get()), has(EunithiceItems.LYMINE_PICKAXE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE_OMNITOOL.get())
                .define('#', Items.STICK)
                .define('P', EunithiceItems.MYELITE_PICKAXE.get())
                .define('A', EunithiceItems.MYELITE_AXE.get())
                .define('S', EunithiceItems.MYELITE_SHOVEL.get())
                .define('H', EunithiceItems.MYELITE_HOE.get())
                .pattern("ASP")
                .pattern("H# ")
                .pattern(" # ")
                .unlockedBy(getHasName(EunithiceItems.MYELITE_PICKAXE.get()), has(EunithiceItems.MYELITE_PICKAXE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_OMNITOOL.get())
                .define('#', Items.STICK)
                .define('P', EunithiceItems.NEUDONITE_PICKAXE.get())
                .define('A', EunithiceItems.NEUDONITE_AXE.get())
                .define('S', EunithiceItems.NEUDONITE_SHOVEL.get())
                .define('H', EunithiceItems.NEUDONITE_HOE.get())
                .pattern("ASP")
                .pattern("H# ")
                .pattern(" # ")
                .unlockedBy(getHasName(EunithiceItems.NEUDONITE_PICKAXE.get()), has(EunithiceItems.NEUDONITE_PICKAXE.get()))
                .save(pFinishedRecipeConsumer);

        eunithiceNetheriteSmithing(pFinishedRecipeConsumer, EunithiceItems.DIAMOND_OMNITOOL.get(), EunithiceItems.NETHERITE_OMNITOOL.get());
//        eunithiceSmithing(pFinishedRecipeConsumer, Items.DIAMOND, Items.POPPED_CHORUS_FRUIT, EunithiceItems.MYELITE.get());

        ShapedRecipeBuilder.shaped(EunithiceItems.LYMINE_SWORD.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.LYMINE_INGOT.get())
                .pattern("@")
                .pattern("@")
                .pattern("#")
                .unlockedBy(getHasName(EunithiceItems.LYMINE_INGOT.get()), has(EunithiceItems.LYMINE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.LYMINE_PICKAXE.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.LYMINE_INGOT.get())
                .pattern("@@@")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy(getHasName(EunithiceItems.LYMINE_INGOT.get()), has(EunithiceItems.LYMINE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.LYMINE_AXE.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.LYMINE_INGOT.get())
                .pattern("@@")
                .pattern("#@")
                .pattern("# ")
                .unlockedBy(getHasName(EunithiceItems.LYMINE_INGOT.get()), has(EunithiceItems.LYMINE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.LYMINE_SHOVEL.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.LYMINE_INGOT.get())
                .pattern("@")
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(EunithiceItems.LYMINE_INGOT.get()), has(EunithiceItems.LYMINE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.LYMINE_HOE.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.LYMINE_INGOT.get())
                .pattern("@@")
                .pattern("# ")
                .pattern("# ")
                .unlockedBy(getHasName(EunithiceItems.LYMINE_INGOT.get()), has(EunithiceItems.LYMINE_INGOT.get()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE.get(), 1)
                .define('C', Items.POPPED_CHORUS_FRUIT)
                .define('D', Items.DIAMOND)
                .pattern("CDC")
                .pattern("DCD")
                .pattern("CDC")
                .unlockedBy(getHasName(Items.CHORUS_FRUIT), has(Items.CHORUS_FRUIT))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceItems.LEURITE_SEEDS.get(), 2)
                .define('G', Items.GOLD_INGOT)
                .define('D', Items.DIAMOND)
                .define('S', EunithiceTags.Items.FORGE_SEEDS)
                .pattern(" D ")
                .pattern("GSG")
                .pattern(" D ")
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_SWORD.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("@")
                .pattern("@")
                .pattern("#")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_PICKAXE.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("@@@")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_AXE.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("@@")
                .pattern("#@")
                .pattern("# ")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_SHOVEL.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("@")
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_HOE.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("@@")
                .pattern("# ")
                .pattern("# ")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE_SWORD.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.MYELITE.get())
                .pattern("@")
                .pattern("@")
                .pattern("#")
                .unlockedBy(getHasName(EunithiceItems.MYELITE.get()), has(EunithiceItems.MYELITE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE_PICKAXE.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.MYELITE.get())
                .pattern("@@@")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy(getHasName(EunithiceItems.MYELITE.get()), has(EunithiceItems.MYELITE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE_AXE.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.MYELITE.get())
                .pattern("@@")
                .pattern("#@")
                .pattern("# ")
                .unlockedBy(getHasName(EunithiceItems.MYELITE.get()), has(EunithiceItems.MYELITE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE_SHOVEL.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.MYELITE.get())
                .pattern("@")
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(EunithiceItems.MYELITE.get()), has(EunithiceItems.MYELITE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.MYELITE_HOE.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.MYELITE.get())
                .pattern("@@")
                .pattern("# ")
                .pattern("# ")
                .unlockedBy(getHasName(EunithiceItems.MYELITE.get()), has(EunithiceItems.MYELITE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.SERRATED_BLADE.get())
                .define('R', Items.POINTED_DRIPSTONE)
                .define('D', Items.DIAMOND_SWORD)
                .pattern("R")
                .pattern("R")
                .pattern("D")
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.BURN_CORE.get())
                .define('#', Items.CHARCOAL)
                .define('I', Items.FLINT_AND_STEEL)
                .define('@', Items.DIAMOND)
                .define('N', Items.NETHERRACK)
                .pattern("#I#")
                .pattern("#@#")
                .pattern("NNN")
                .unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
                .save(pFinishedRecipeConsumer);
        // temp disabled as it has no use
//        ShapedRecipeBuilder.shaped(EunithiceItems.EXTRACTION_CORE.get())
//                .define('#', Blocks.GOLD_BLOCK)
//                .define('D', Items.DIAMOND)
//                .define('@', Blocks.OBSIDIAN)
//                .define('N', Blocks.REDSTONE_BLOCK)
//                .pattern("###")
//                .pattern("@D@")
//                .pattern("NNN")
//                .unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
//                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.FRUIT_DISH.get())
                .requires(Items.APPLE)
                .requires(Items.MELON_SLICE)
                .requires(Items.PUMPKIN_PIE)
                .requires(Items.SWEET_BERRIES)
                .requires(Items.GLOW_BERRIES)
                .unlockedBy("empty", inventoryTrigger(ItemPredicate.Builder.item().of(Items.AIR).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.LEURITE_BREAD.get())
                .define('#', EunithiceItems.LEURITE_GRAINS.get())
                .pattern("###")
                .unlockedBy("has_leurite_grains", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.LEURITE_GRAINS.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.MEATBALL_SOUP.get())
                .requires(Items.COOKED_PORKCHOP)
                .requires(Items.COOKED_COD)
                .requires(Items.COOKED_SALMON)
                .requires(Items.COOKED_BEEF)
                .requires(Items.COOKED_CHICKEN)
                .requires(Items.RABBIT_STEW)
                .requires(Items.COOKED_MUTTON)
                .unlockedBy("empty", inventoryTrigger(ItemPredicate.Builder.item().of(Items.AIR).build()))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.REVIVAL_SANDWICH.get())
                .requires(EunithiceItems.FRUIT_DISH.get())
                .requires(EunithiceItems.MEATBALL_SOUP.get())
                .requires(EunithiceItems.VEGETABLE_SALAD.get())
                .requires(EunithiceItems.LEURITE_BREAD.get())
                .requires(Items.ENCHANTED_GOLDEN_APPLE)
                .unlockedBy("empty", inventoryTrigger(ItemPredicate.Builder.item().of(Items.AIR).build()))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.VEGETABLE_SALAD.get())
                .requires(Items.CARROT)
                .requires(Items.BAKED_POTATO)
                .requires(Items.BEETROOT_SOUP)
                .requires(Items.DRIED_KELP)
                .unlockedBy("empty", inventoryTrigger(ItemPredicate.Builder.item().of(Items.AIR).build()))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.BOUNCE_GEL.get(), 8)
                .requires(Items.SLIME_BALL)
                .requires(Items.SLIME_BALL)
                .requires(Items.SLIME_BALL)
                .requires(Items.SLIME_BALL)
                .requires(Blocks.SLIME_BLOCK)
                .requires(Items.SLIME_BALL)
                .requires(Items.SLIME_BALL)
                .requires(Items.SLIME_BALL)
                .requires(Items.SLIME_BALL)
                .unlockedBy(getHasName(Items.SLIME_BALL), has(Items.SLIME_BALL))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.HONEY_GEL.get(), 8)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.SLIME_BALL)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.HONEY_BOTTLE)
                .unlockedBy(getHasName(Items.HONEY_BOTTLE), has(Items.HONEY_BOTTLE))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.SPEED_GEL.get(), 8)
                .requires(Items.SUGAR, 3)
                .requires(Items.SLIME_BALL, 3)
                .requires(Items.SUGAR, 3)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.RAW_NEUDONITE.get(), 9)
                .requires(EunithiceBlocks.RAW_NEUDONITE_BLOCK.get())
                .unlockedBy(getHasName(EunithiceBlocks.RAW_NEUDONITE_BLOCK.get()), has(EunithiceBlocks.RAW_NEUDONITE_BLOCK.get()))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.NEUDONITE_INGOT.get(), 9)
                .requires(EunithiceBlocks.NEUDONITE_BLOCK.get())
                .unlockedBy(getHasName(EunithiceBlocks.NEUDONITE_BLOCK.get()), has(EunithiceBlocks.NEUDONITE_BLOCK.get()))
                .save(pFinishedRecipeConsumer);
//        ShapelessRecipeBuilder.shapeless(EunithiceItems.IRON_PLATE.get(), 4)
//                .requires(Items.IRON_INGOT)
//                .requires(EunithiceTags.Items.HAMMERS)
//                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
//                .save(pFinishedRecipeConsumer);
//        ShapelessRecipeBuilder.shapeless(EunithiceItems.GOLD_PLATE.get(), 4)
//                .requires(Items.GOLD_INGOT)
//                .requires(EunithiceTags.Items.HAMMERS)
//                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
//                .save(pFinishedRecipeConsumer);
//        ShapelessRecipeBuilder.shapeless(EunithiceItems.DIAMOND_PLATE.get(), 4)
//                .requires(Items.DIAMOND)
//                .requires(EunithiceItems.TIER_TWO_HAMMER.get())
//                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
//                .save(pFinishedRecipeConsumer);

        ////////////////
        // BLOCKS
        ////////////////

        ShapedRecipeBuilder.shaped(EunithiceBlocks.IRON_PLATE_DOOR.get())
                .define('#', Items.IRON_NUGGET)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.IRON_PLATE_TRAPDOOR.get())
                .define('#', Items.IRON_NUGGET)
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceBlocks.STONE_DOOR.get(), 3)
                .define('#', Blocks.STONE_SLAB)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(Blocks.STONE_SLAB), has(Blocks.STONE_SLAB))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.STONE_TRAPDOOR.get(), 2)
                .define('#', Blocks.STONE_SLAB)
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(Blocks.STONE_SLAB), has(Blocks.STONE_SLAB))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceBlocks.ASPHALT_INFUSER.get())
                .define('#', EunithiceItems.NEUDONITE_INGOT.get())
                .define('S', Items.REDSTONE)
                .define('A', Items.IRON_INGOT)
                .pattern("#S#")
                .pattern("SAS")
                .pattern("#S#")
                .unlockedBy(getHasName(EunithiceItems.NEUDONITE_INGOT.get()), has(EunithiceItems.NEUDONITE_INGOT.get()))
                .save(pFinishedRecipeConsumer);
        // temp disabled as it has no use
//        ShapedRecipeBuilder.shaped(EunithiceBlocks.EXTRACTOR.get())
//                .define('#', Items.IRON_INGOT)
//                .define('S', Blocks.REDSTONE_BLOCK)
//                .define('A', EunithiceBlocks.NEUDONITE_BLOCK.get())
//                .pattern("#S#")
//                .pattern("SAS")
//                .pattern("#S#")
//                .unlockedBy(getHasName(EunithiceBlocks.NEUDONITE_BLOCK.get()), has(EunithiceBlocks.NEUDONITE_BLOCK.get()))
//                .save(pFinishedRecipeConsumer);

        ////////////////
        // SMELTING RECIPES
        ////////////////
        eunithiceOreSmelting(pFinishedRecipeConsumer, List.of(EunithiceBlocks.NEUDONITE_ORE.get(), EunithiceItems.RAW_NEUDONITE.get(), EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get()), EunithiceItems.NEUDONITE_INGOT.get(), 0.7F, 200, "neudonite_ingot");
        eunithiceOreBlasting(pFinishedRecipeConsumer, List.of(EunithiceBlocks.NEUDONITE_ORE.get(), EunithiceItems.RAW_NEUDONITE.get(), EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get()), EunithiceItems.NEUDONITE_INGOT.get(), 0.7F, 100, "neudonite_ingot");


        ////////////////
        // MACHINE RECIPES
        ////////////////

        eunithiceAsphaltInfusing(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceItems.BOUNCE_GEL.get()), Ingredient.of(EunithiceBlocks.ASPHALT.get())),
                EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get(), 2, false);
        eunithiceAsphaltInfusing(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceItems.SPEED_GEL.get()), Ingredient.of(EunithiceBlocks.ASPHALT.get())),
                EunithiceBlocks.SPEED_INFUSED_ASPHALT.get(), 2, false);
        eunithiceAsphaltInfusing(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceItems.HONEY_GEL.get()), Ingredient.of(EunithiceBlocks.ASPHALT.get())),
                EunithiceBlocks.HONEY_INFUSED_ASPHALT.get(), 2, false);

        // Temporarily disabled as they cause a disconnect on servers

//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.SPEED_INFUSED_ASPHALT.get().asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(EunithiceBlocks.ASPHALT.get().asItem(), 1)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 10), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.HONEY_INFUSED_ASPHALT.get().asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(EunithiceBlocks.ASPHALT.get().asItem(), 1)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 10), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get().asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(EunithiceBlocks.ASPHALT.get().asItem(), 1)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 10), false);
//
//
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.COAL_ORE.asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.COAL, 3)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 50), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_COAL_ORE.asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.COAL, 5)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 100), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.IRON_ORE.asItem()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.RAW_IRON, 2)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 100), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_IRON_ORE.asItem()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.RAW_IRON, 4)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 150), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.COPPER_ORE.asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.RAW_COPPER, 4)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 75), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_COPPER_ORE.asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.RAW_COPPER, 6)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 100), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.GOLD_ORE.asItem()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.RAW_GOLD, 2)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 100), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_GOLD_ORE.asItem()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.RAW_GOLD, 4)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 150), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.REDSTONE_ORE.asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.REDSTONE, 8)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 100), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_REDSTONE_ORE.asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.REDSTONE, 12)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 150), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.EMERALD_ORE.asItem()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.EMERALD, 2)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 100), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_EMERALD_ORE.asItem()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.EMERALD, 3)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 150), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.LAPIS_ORE.asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.LAPIS_LAZULI, 6)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 100), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_LAPIS_ORE.asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.LAPIS_LAZULI, 9)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 150), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DIAMOND_ORE.asItem()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.DIAMOND, 2)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 150), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_DIAMOND_ORE.asItem()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.DIAMOND, 4)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 250), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.NETHER_GOLD_ORE.asItem()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.GOLD_NUGGET, 6)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 75), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.NETHER_QUARTZ_ORE.asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(Items.QUARTZ, 4)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 200), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.NEUDONITE_ORE.get().asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(EunithiceItems.RAW_NEUDONITE.get(), 3)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 100), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get().asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(EunithiceItems.RAW_NEUDONITE.get(), 4)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 150), false);
//
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Items.DIAMOND.asItem()), Ingredient.of(Items.POPPED_CHORUS_FRUIT.asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(EunithiceItems.LYMINE.get(), 1)),
//                new FluidStack(EunithiceFluids.CHROMA.get(), 300), false);

//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceItems.NEUDONITE_INGOT.get().asItem()), Ingredient.of(EunithiceItems.TIER_TWO_HAMMER.get().asItem())),
//                NonNullList.of(ItemStack.EMPTY, new ItemStack(EunithiceItems.LEURIUM_INGOT.get(), 1)),
//                new FluidStack(Fluids.LAVA.getSource(), 500), false);

//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.SPEED_INFUSED_ASPHALT.get()), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get())),
//                EunithiceBlocks.ASPHALT.get(), 1, false);
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get()), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get())),
//                EunithiceBlocks.ASPHALT.get(), 1, false);
//        eunithiceItemExtracting(pFinishedRecipeConsumer,
//                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.HONEY_INFUSED_ASPHALT.get()), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get())),
//                EunithiceBlocks.ASPHALT.get(), 1, false);

        simple3x3PackingCorrelation(pFinishedRecipeConsumer, EunithiceItems.LYMINE_INGOT.get(), EunithiceBlocks.LYMINE_BLOCK.get());
        simple3x3PackingCorrelation(pFinishedRecipeConsumer, EunithiceItems.MYELITE.get(), EunithiceBlocks.MYELITE_BLOCK.get());
    }

    public static void eunithiceFence(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike fence, ItemLike material) {
        fenceBuilder(fence, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, fence.asItem().getRegistryName().getPath()));
    }

    public static void eunithiceFenceGate(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike fenceGate, ItemLike material) {
        fenceGateBuilder(fenceGate, Ingredient.of(material)).unlockedBy(getHasName(material), has(material)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, fenceGate.asItem().getRegistryName().getPath()));
    }

    public static void eunithiceSlab(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pSlab, ItemLike pMaterial) {
        slabBuilder(pSlab, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, pSlab.asItem().getRegistryName().getPath()));
    }

    public static void eunithiceStairs(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike stairs, ItemLike pMaterial) {
        stairBuilder(stairs, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, stairs.asItem().getRegistryName().getPath()));
    }

    public static void eunithiceDoor(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike door, ItemLike pMaterial) {
        doorBuilder(door, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, door.asItem().getRegistryName().getPath()));
    }

    public static void eunithiceTrapdoor(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike trapdoor, ItemLike pMaterial) {
        trapdoorBuilder(trapdoor, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, trapdoor.asItem().getRegistryName().getPath()));
    }

    public static void eunithiceButton(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike button, ItemLike pMaterial) {
        buttonBuilder(button, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, button.asItem().getRegistryName().getPath()));
    }

    public static void eunithicePressurePlate(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pressurePlate, ItemLike pMaterial) {
        pressurePlateBuilder(pressurePlate, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, pressurePlate.asItem().getRegistryName().getPath()));
    }

    public static void eunithicePlanksFromLogs(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike planks, TagKey<Item> logs) {
        ShapelessRecipeBuilder.shapeless(planks, 4).requires(logs).group("planks").unlockedBy("has_logs", has(logs)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, planks.asItem().getRegistryName().getPath()));
    }

    public static void eunithiceWoodFromLogs(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike wood, ItemLike log) {
        ShapedRecipeBuilder.shaped(wood, 3).define('#', log).pattern("##").pattern("##").group("bark").unlockedBy("has_log", has(log)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, wood.asItem().getRegistryName().getPath()));
    }

    // Creates a recipe for packing and unpacking items
    protected static void simple3x3PackingCorrelation(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike unpacked, ItemLike packed) {
        ShapedRecipeBuilder.shaped(packed)
                .define('#', unpacked)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(unpacked), has(packed))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(unpacked, 9)
                .requires(packed)
                .unlockedBy(getHasName(packed), has(packed))
                .save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, unpacked.asItem().getRegistryName().getPath()+"_from_"+packed.asItem().getRegistryName().getPath()));
    }
    protected static void simple2x2PackingCorrelation(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike unpacked, ItemLike packed) {
        ShapedRecipeBuilder.shaped(packed)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unpacked), has(packed))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(unpacked, 4)
                .requires(packed)
                .unlockedBy(getHasName(packed), has(packed))
                .save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, unpacked.asItem().getRegistryName().getPath()+"_from_"+packed.asItem().getRegistryName().getPath()));
    }

    protected static void eunithiceOreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        eunithiceOreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void eunithiceOreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        eunithiceOreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void eunithiceOreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, SimpleCookingSerializer<?> pCookingSerializer, List< ItemLike > pIngredients, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            EunithiceCookingRecipeBuilder.cooking(Ingredient.of(itemlike), pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void eunithiceAsphaltInfusing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, NonNullList<Ingredient> pIngredients, ItemLike pResult, int pCount, boolean pIgnoreDurability) {
        new AsphaltInfuserRecipeBuilder(pIngredients, pResult, pCount, pIgnoreDurability)
                .unlockedBy(getHasName(pIngredients.get(0).getItems()[0].getItem()), has(pIngredients.get(0).getItems()[0].getItem()))
                .save(pFinishedRecipeConsumer, getItemName(pResult) + "_from_infusing" + "_" + getItemName(pIngredients.get(0).getItems()[0].getItem()));
    }
    protected static void eunithiceItemExtracting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, NonNullList<Ingredient> pIngredients, NonNullList<ItemStack> pResults, FluidStack stack, boolean pIgnoreDurability) {
        new ExtractorRecipeBuilder(pIngredients, pResults, stack, pIgnoreDurability)
                .unlockedBy(getHasName(pIngredients.get(0).getItems()[0].getItem()), has(pIngredients.get(0).getItems()[0].getItem()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, "extraction/extracting_" + getItemName(pIngredients.get(0).getItems()[0].getItem())));
    }

    protected static void eunithiceNetheriteSmithing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item pIngredientItem, Item pResultItem) {
        eunithiceSmithing(pFinishedRecipeConsumer, pIngredientItem, Items.NETHERITE_INGOT, pResultItem);
    }

    protected static void eunithiceSmithing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item pIngredientItem, Item pSmithIngredient, Item pResultItem) {
        UpgradeRecipeBuilder.smithing(Ingredient.of(pIngredientItem), Ingredient.of(pSmithIngredient), pResultItem).unlocks(getHasName(pSmithIngredient), has(pSmithIngredient)).save(pFinishedRecipeConsumer, new ResourceLocation(Eunithice.MODID, getItemName(pResultItem) + "_smithing"));
    }
}
