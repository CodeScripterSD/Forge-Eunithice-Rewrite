package com.craftminerd.eunithice.datagen.custom;

import com.craftminerd.eunithice.Eunithice;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCookingSerializer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class EunithiceCookingRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final float experience;
    private final int cookingTime;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    private final SimpleCookingSerializer<?> serializer;
    public EunithiceCookingRecipeBuilder(Ingredient ingredient, ItemLike result, float experience, int cookingTime, SimpleCookingSerializer<?> serializer) {
        this.result = result.asItem();
        this.ingredient = ingredient;
        this.experience = experience;
        this.cookingTime = cookingTime;
        this.serializer = serializer;
    }

    public static EunithiceCookingRecipeBuilder cooking(Ingredient pIngredient, ItemLike pResult, float pExperience, int pCookingTime, SimpleCookingSerializer<?> pSerializer) {
        return new EunithiceCookingRecipeBuilder(pIngredient, pResult, pExperience, pCookingTime, pSerializer);
    }

    public static EunithiceCookingRecipeBuilder campfireCooking(Ingredient pIngredient, ItemLike pResult, float pExperience, int pCookingTime) {
        return cooking(pIngredient, pResult, pExperience, pCookingTime, RecipeSerializer.CAMPFIRE_COOKING_RECIPE);
    }

    public static EunithiceCookingRecipeBuilder blasting(Ingredient pIngredient, ItemLike pResult, float pExperience, int pCookingTime) {
        return cooking(pIngredient, pResult, pExperience, pCookingTime, RecipeSerializer.BLASTING_RECIPE);
    }

    public static EunithiceCookingRecipeBuilder smelting(Ingredient pIngredient, ItemLike pResult, float pExperience, int pCookingTime) {
        return cooking(pIngredient, pResult, pExperience, pCookingTime, RecipeSerializer.SMELTING_RECIPE);
    }

    public static EunithiceCookingRecipeBuilder smoking(Ingredient pIngredient, ItemLike pResult, float pExperience, int pCookingTime) {
        return cooking(pIngredient, pResult, pExperience, pCookingTime, RecipeSerializer.SMOKING_RECIPE);
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

    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipe/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new EunithiceCookingRecipeBuilder.Result(pRecipeId, this.ingredient, this.result, this.experience, this.cookingTime,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(),"recipes/" +
                this.result.getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath()), this.serializer));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final Item result;
        private final float experience;
        private final int cookingTime;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final RecipeSerializer<? extends AbstractCookingRecipe> serializer;

        public Result(ResourceLocation pId, Ingredient pIngredient, Item pResult, float pExperience, int pCookingTime, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId, RecipeSerializer<? extends AbstractCookingRecipe> pSerializer) {
            this.id = pId;
            this.ingredient = pIngredient;
            this.result = pResult;
            this.experience = pExperience;
            this.cookingTime = pCookingTime;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
            this.serializer = pSerializer;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredient", this.ingredient.toJson());
            pJson.addProperty("result", Registry.ITEM.getKey(this.result).toString());
            pJson.addProperty("experience", this.experience);
            pJson.addProperty("cookingtime", this.cookingTime);
        }

        @Override
        public ResourceLocation getId() {
            return new ResourceLocation(Eunithice.MODID, this.id.getPath());
        }

        @Override
        public RecipeSerializer<?> getType() {
            return this.serializer;
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
