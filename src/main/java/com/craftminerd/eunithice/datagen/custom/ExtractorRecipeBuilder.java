package com.craftminerd.eunithice.datagen.custom;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.recipe.ExtractorRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ExtractorRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final NonNullList<Ingredient> recipeItems;
    private final int count;
    private final boolean ignoreDurability;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    public ExtractorRecipeBuilder(NonNullList<Ingredient> recipeItems, ItemLike result, int count, boolean ignoreDurability) {
        this.result = result.asItem();
        this.recipeItems = recipeItems;
        this.count = count;
        this.ignoreDurability = ignoreDurability;
    }
    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipe/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new ExtractorRecipeBuilder.Result(pRecipeId, this.result, this.count, this.ignoreDurability, this.recipeItems,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" +
                this.result.getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Item result;
        private final NonNullList<Ingredient> recipeItems;
        private final int count;
        private final boolean ignoreDurability;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, Item result, int count, boolean ignoreDurability, NonNullList<Ingredient> recipeItems, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.result = result;
            this.count = count;
            this.ignoreDurability = ignoreDurability;
            this.recipeItems = recipeItems;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            JsonArray jsonArray = new JsonArray();
            pJson.addProperty("ignore_durability", ignoreDurability);
            for (int i= 0; i < recipeItems.size(); i++) {
                jsonArray.add(recipeItems.get(i).toJson());
            }
            pJson.add("ingredients", jsonArray);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", this.result.getRegistryName().toString());
            if (this.count > 1) {
                jsonObject.addProperty("count", this.count);
            }
            pJson.add("result", jsonObject);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(Eunithice.MODID, this.result.getRegistryName().getPath() + "_from_extracting_" + this.recipeItems.get(0).getItems()[0].getItem().getRegistryName().getPath());
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ExtractorRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
