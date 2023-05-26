package com.craftminerd.eunithice.datagen;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.datagen.custom.AsphaltInfuserRecipeBuilder;
import com.craftminerd.eunithice.datagen.custom.EunithiceCookingRecipeBuilder;
import com.craftminerd.eunithice.datagen.custom.ExtractorRecipeBuilder;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.util.EunithiceTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.NonNullList;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
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

        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_BOOTS.get())
                .define('#', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_CHESTPLATE.get())
                .define('#', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_HELMET.get())
                .define('#', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("###")
                .pattern("# #")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.NEUDONITE_LEGGINGS.get())
                .define('#', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
                .save(pFinishedRecipeConsumer);

        ////////////////
        // BLOCK RECIPES
        ////////////////

        ShapelessRecipeBuilder.shapeless(EunithiceBlocks.ASPHALT.get(), 4)
                .requires(Items.BLACK_DYE)
                .requires(Items.SAND)
                .requires(Items.SAND)
                .requires(Items.COBBLESTONE)
                .requires(Items.COBBLESTONE)
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

        ShapedRecipeBuilder.shaped(EunithiceItems.LEURITE_SEEDS.get(), 2)
                .define('?', EunithiceBlocks.PLATED_GOLD_BLOCK.get())
                .define('#', Blocks.DIAMOND_BLOCK)
                .define('Q', EunithiceTags.Items.HAMMERS)
                .pattern("?#?")
                .pattern("#Q#")
                .pattern("?#?")
                .unlockedBy("has_hammer", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceTags.Items.HAMMERS).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.HANDHELD_ENDER_CHEST.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.NEUDONITE_INGOT.get())
                .define('&', Blocks.ENDER_CHEST)
                .pattern(" @&")
                .pattern(" #@")
                .pattern("#  ")
                .unlockedBy("has_neudonite_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.NEUDONITE_INGOT.get()).build()))
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
        ShapedRecipeBuilder.shaped(EunithiceItems.SERRATED_BLADE.get())
                .define('#', Items.STICK)
                .define('@', EunithiceItems.LEURITE_GRAINS.get())
                .define('D', Items.DIAMOND)
                .define('R', Items.POINTED_DRIPSTONE)
                .pattern("DRD")
                .pattern("@R@")
                .pattern("D#D")
                .unlockedBy("has_leurite_grains", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.LEURITE_GRAINS.get()).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.TIER_ONE_HAMMER.get())
                .define('#', Items.STICK)
                .define('I', Items.IRON_INGOT)
                .pattern(" I ")
                .pattern(" #I")
                .pattern("#  ")
                .unlockedBy("has_iron_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(Items.IRON_INGOT).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceItems.TIER_TWO_HAMMER.get())
                .define('#', Items.IRON_INGOT)
                .define('I', EunithiceItems.IRON_PLATE.get())
                .define('@', EunithiceItems.NEUDONITE_INGOT.get())
                .pattern(" @I")
                .pattern(" #@")
                .pattern("#  ")
                .unlockedBy("has_iron_plate", inventoryTrigger(ItemPredicate.Builder.item().of(EunithiceItems.IRON_PLATE.get()).build()))
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
        ShapedRecipeBuilder.shaped(EunithiceItems.EXTRACTION_CORE.get())
                .define('#', Blocks.GOLD_BLOCK)
                .define('I', EunithiceTags.Items.HAMMERS)
                .define('@', Blocks.OBSIDIAN)
                .define('N', Blocks.REDSTONE_BLOCK)
                .pattern("###")
                .pattern("@I@")
                .pattern("NNN")
                .unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
                .save(pFinishedRecipeConsumer);
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
        ShapelessRecipeBuilder.shapeless(EunithiceItems.BOUNCE_GEL.get())
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
        ShapelessRecipeBuilder.shapeless(EunithiceItems.HONEY_GEL.get())
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
        ShapelessRecipeBuilder.shapeless(EunithiceItems.SPEED_GEL.get())
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .requires(Items.SLIME_BALL)
                .requires(Items.SLIME_BALL)
                .requires(Items.SLIME_BALL)
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
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
        ShapelessRecipeBuilder.shapeless(EunithiceItems.IRON_PLATE.get(), 4)
                .requires(Items.IRON_INGOT)
                .requires(EunithiceTags.Items.HAMMERS)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.GOLD_PLATE.get(), 4)
                .requires(Items.GOLD_INGOT)
                .requires(EunithiceTags.Items.HAMMERS)
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(pFinishedRecipeConsumer);
        ShapelessRecipeBuilder.shapeless(EunithiceItems.DIAMOND_PLATE.get(), 4)
                .requires(Items.DIAMOND)
                .requires(EunithiceItems.TIER_TWO_HAMMER.get())
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pFinishedRecipeConsumer);

        ////////////////
        // BLOCKS
        ////////////////

        ShapedRecipeBuilder.shaped(EunithiceBlocks.DIAMOND_PLATE_BLOCK.get())
                .define('#', EunithiceItems.DIAMOND_PLATE.get())
                .define('S', Blocks.DIAMOND_BLOCK)
                .pattern(" # ")
                .pattern("#S#")
                .pattern(" # ")
                .unlockedBy(getHasName(EunithiceItems.DIAMOND_PLATE.get()), has(EunithiceItems.DIAMOND_PLATE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.COMPRESSED_DIAMOND_PLATE_BLOCK.get())
                .define('#', EunithiceItems.DIAMOND_PLATE.get())
                .define('S', Items.DIAMOND)
                .define('A', EunithiceBlocks.DIAMOND_PLATE_BLOCK.get())
                .define('B', Blocks.DIAMOND_BLOCK)
                .pattern("#S#")
                .pattern("BAB")
                .pattern("#S#")
                .unlockedBy(getHasName(EunithiceItems.DIAMOND_PLATE.get()), has(EunithiceItems.DIAMOND_PLATE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.HEAVY_COMPRESSED__DIAMOND_PLATE_BLOCK.get())
                .define('#', Items.DIAMOND)
                .define('S', Blocks.DIAMOND_BLOCK)
                .define('A', EunithiceBlocks.COMPRESSED_DIAMOND_PLATE_BLOCK.get())
                .define('B', EunithiceItems.DIAMOND_PLATE.get())
                .pattern("#S#")
                .pattern("BAB")
                .pattern("#S#")
                .unlockedBy(getHasName(EunithiceItems.DIAMOND_PLATE.get()), has(EunithiceItems.DIAMOND_PLATE.get()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceBlocks.PLATED_IRON_BLOCK.get())
                .define('#', EunithiceItems.IRON_PLATE.get())
                .define('S', Blocks.IRON_BLOCK)
                .pattern(" # ")
                .pattern("#S#")
                .pattern(" # ")
                .unlockedBy(getHasName(EunithiceItems.IRON_PLATE.get()), has(EunithiceItems.IRON_PLATE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.COMPRESSED_PLATED_IRON_BLOCK.get())
                .define('#', EunithiceItems.IRON_PLATE.get())
                .define('S', Items.IRON_INGOT)
                .define('A', EunithiceBlocks.PLATED_IRON_BLOCK.get())
                .define('B', Blocks.IRON_BLOCK)
                .pattern("#S#")
                .pattern("BAB")
                .pattern("#S#")
                .unlockedBy(getHasName(EunithiceItems.IRON_PLATE.get()), has(EunithiceItems.IRON_PLATE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.HEAVY_COMPRESSED_PLATED_IRON_BLOCK.get())
                .define('#', Items.IRON_INGOT)
                .define('S', Blocks.IRON_BLOCK)
                .define('A', EunithiceBlocks.COMPRESSED_PLATED_IRON_BLOCK.get())
                .define('B', EunithiceItems.IRON_PLATE.get())
                .pattern("#S#")
                .pattern("BAB")
                .pattern("#S#")
                .unlockedBy(getHasName(EunithiceItems.IRON_PLATE.get()), has(EunithiceItems.IRON_PLATE.get()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceBlocks.PLATED_GOLD_BLOCK.get())
                .define('#', EunithiceItems.GOLD_PLATE.get())
                .define('S', Blocks.GOLD_BLOCK)
                .pattern(" # ")
                .pattern("#S#")
                .pattern(" # ")
                .unlockedBy(getHasName(EunithiceItems.GOLD_PLATE.get()), has(EunithiceItems.GOLD_PLATE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.COMPRESSED_PLATED_GOLD_BLOCK.get())
                .define('#', EunithiceItems.GOLD_PLATE.get())
                .define('S', Items.GOLD_INGOT)
                .define('A', EunithiceBlocks.PLATED_GOLD_BLOCK.get())
                .define('B', Blocks.GOLD_BLOCK)
                .pattern("#S#")
                .pattern("BAB")
                .pattern("#S#")
                .unlockedBy(getHasName(EunithiceItems.GOLD_PLATE.get()), has(EunithiceItems.GOLD_PLATE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.HEAVY_COMPRESSED_PLATED_GOLD_BLOCK.get())
                .define('#', Items.GOLD_INGOT)
                .define('S', Blocks.GOLD_BLOCK)
                .define('A', EunithiceBlocks.COMPRESSED_PLATED_GOLD_BLOCK.get())
                .define('B', EunithiceItems.GOLD_PLATE.get())
                .pattern("#S#")
                .pattern("BAB")
                .pattern("#S#")
                .unlockedBy(getHasName(EunithiceItems.GOLD_PLATE.get()), has(EunithiceItems.GOLD_PLATE.get()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceBlocks.IRON_PLATE_DOOR.get())
                .define('#', EunithiceItems.IRON_PLATE.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(EunithiceItems.IRON_PLATE.get()), has(EunithiceItems.IRON_PLATE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.IRON_PLATE_TRAPDOOR.get())
                .define('#', EunithiceItems.IRON_PLATE.get())
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(EunithiceItems.IRON_PLATE.get()), has(EunithiceItems.IRON_PLATE.get()))
                .save(pFinishedRecipeConsumer);

        ShapedRecipeBuilder.shaped(EunithiceBlocks.STONE_DOOR.get())
                .define('#', Blocks.STONE_SLAB)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(EunithiceItems.IRON_PLATE.get()), has(EunithiceItems.IRON_PLATE.get()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(EunithiceBlocks.STONE_TRAPDOOR.get())
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
        ShapedRecipeBuilder.shaped(EunithiceBlocks.EXTRACTOR.get())
                .define('#', Items.IRON_INGOT)
                .define('S', Blocks.REDSTONE_BLOCK)
                .define('A', EunithiceBlocks.NEUDONITE_BLOCK.get())
                .pattern("#S#")
                .pattern("SAS")
                .pattern("#S#")
                .unlockedBy(getHasName(EunithiceBlocks.NEUDONITE_BLOCK.get()), has(EunithiceBlocks.NEUDONITE_BLOCK.get()))
                .save(pFinishedRecipeConsumer);

        ////////////////
        // SMELTING RECIPES
        ////////////////

        eunithiceOreSmelting(pFinishedRecipeConsumer, List.of(EunithiceBlocks.NEUDONITE_ORE.get(), EunithiceItems.RAW_NEUDONITE.get()), EunithiceItems.NEUDONITE_INGOT.get(), 0.7F, 200, "neudonite_ingot");
        eunithiceOreBlasting(pFinishedRecipeConsumer, List.of(EunithiceBlocks.NEUDONITE_ORE.get(), EunithiceItems.RAW_NEUDONITE.get()), EunithiceItems.NEUDONITE_INGOT.get(), 0.7F, 100, "neudonite_ingot");

        ////////////////
        // MACHINE RECIPES
        ////////////////

        eunithiceItemInfusing(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceItems.BOUNCE_GEL.get()), Ingredient.of(EunithiceBlocks.ASPHALT.get())),
                EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get(), 2, false);
        eunithiceItemInfusing(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceItems.SPEED_GEL.get()), Ingredient.of(EunithiceBlocks.ASPHALT.get())),
                EunithiceBlocks.SPEED_INFUSED_ASPHALT.get(), 2, false);
        eunithiceItemInfusing(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceItems.HONEY_GEL.get()), Ingredient.of(EunithiceBlocks.ASPHALT.get())),
                EunithiceBlocks.HONEY_INFUSED_ASPHALT.get(), 2, false);

        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.SPEED_INFUSED_ASPHALT.get()), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get())),
                EunithiceBlocks.ASPHALT.get(), 1, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get()), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get())),
                EunithiceBlocks.ASPHALT.get(), 1, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.HONEY_INFUSED_ASPHALT.get()), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get())),
                EunithiceBlocks.ASPHALT.get(), 1, false);

        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.IRON_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.RAW_IRON, 4, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_IRON_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.RAW_IRON, 6, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Items.RAW_IRON), Ingredient.of(EunithiceItems.BURN_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.IRON_INGOT, 2, false);

        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.GOLD_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.RAW_GOLD, 4, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_GOLD_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.RAW_GOLD, 6, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Items.RAW_GOLD), Ingredient.of(EunithiceItems.BURN_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.GOLD_INGOT, 2, false);

        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.COPPER_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.RAW_COPPER, 8, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_COPPER_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.RAW_COPPER, 14, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Items.RAW_COPPER), Ingredient.of(EunithiceItems.BURN_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.COPPER_INGOT, 2, false);

        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DIAMOND_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.DIAMOND, 3, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_DIAMOND_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.DIAMOND, 5, false);

        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.COAL_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.COAL, 3, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_COAL_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.COAL, 5, false);

        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.EMERALD_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.EMERALD, 3, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(Blocks.DEEPSLATE_EMERALD_ORE), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                Items.EMERALD, 5, false);

        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceBlocks.NEUDONITE_ORE.get()), Ingredient.of(EunithiceItems.EXTRACTION_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                EunithiceItems.RAW_NEUDONITE.get(), 4, false);
        eunithiceItemExtracting(pFinishedRecipeConsumer,
                NonNullList.of(Ingredient.EMPTY, Ingredient.of(EunithiceItems.RAW_NEUDONITE.get()), Ingredient.of(EunithiceItems.BURN_CORE.get()), Ingredient.of(EunithiceTags.Items.HAMMERS)),
                EunithiceItems.NEUDONITE_INGOT.get(), 2, false);

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

    protected static void eunithiceItemInfusing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, NonNullList<Ingredient> pIngredients, ItemLike pResult, int pCount, boolean pIgnoreDurability) {
        new AsphaltInfuserRecipeBuilder(pIngredients, pResult, pCount, pIgnoreDurability)
                .unlockedBy(getHasName(pIngredients.get(0).getItems()[0].getItem()), has(pIngredients.get(0).getItems()[0].getItem()))
                .save(pFinishedRecipeConsumer, getItemName(pResult) + "_from_infusing" + "_" + getItemName(pIngredients.get(0).getItems()[0].getItem()));
    }
    protected static void eunithiceItemExtracting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, NonNullList<Ingredient> pIngredients, ItemLike pResult, int pCount, boolean pIgnoreDurability) {
        new ExtractorRecipeBuilder(pIngredients, pResult, pCount, pIgnoreDurability)
                .unlockedBy(getHasName(pIngredients.get(0).getItems()[0].getItem()), has(pIngredients.get(0).getItems()[0].getItem()))
                .save(pFinishedRecipeConsumer, getItemName(pResult) + "_from_extracting" + "_" + getItemName(pIngredients.get(0).getItems()[0].getItem()));
    }
}
