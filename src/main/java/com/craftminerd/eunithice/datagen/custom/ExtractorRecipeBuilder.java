package com.craftminerd.eunithice.datagen.custom;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.recipe.ExtractorRecipe;
import com.craftminerd.eunithice.util.FluidJSONUtil;
import com.craftminerd.eunithice.util.ItemStackJSONUtil;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ExtractorRecipeBuilder implements RecipeBuilder {
    private final NonNullList<ItemStack> resultItems;
    private final NonNullList<Ingredient> recipeItems;
    private final FluidStack fluidStack;
    private final boolean ignoreDurability;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();
    public ExtractorRecipeBuilder(NonNullList<Ingredient> recipeItems, NonNullList<ItemStack> resultItems, FluidStack fluidStack, boolean ignoreDurability) {
        this.resultItems = resultItems;
        this.recipeItems = recipeItems;
        this.fluidStack = fluidStack;
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
        return resultItems.get(0).getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipe/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new ExtractorRecipeBuilder.Result(pRecipeId, this.resultItems, this.ignoreDurability, this.recipeItems,
                this.fluidStack, this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" +
                this.resultItems.get(0).getItem().getItemCategory().getRecipeFolderName() + "/" + pRecipeId.getPath())));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final NonNullList<ItemStack> resultItems;
        private final NonNullList<Ingredient> recipeItems;
        private final FluidStack stack;
        private final boolean ignoreDurability;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation id, NonNullList<ItemStack> resultItems, boolean ignoreDurability, NonNullList<Ingredient> recipeItems, FluidStack stack, Advancement.Builder advancement, ResourceLocation advancementId) {
            this.id = id;
            this.resultItems = resultItems;
            this.ignoreDurability = ignoreDurability;
            this.recipeItems = recipeItems;
            this.stack = stack;
            this.advancement = advancement;
            this.advancementId = advancementId;
        }

        @Override
        public void serializeRecipeData(JsonObject pJson) {
            pJson.addProperty("ignore_durability", ignoreDurability);
            JsonArray recipeIngredients = new JsonArray();
            for (Ingredient recipeItem : recipeItems) {
                recipeIngredients.add(recipeItem.toJson());
            }
            pJson.add("ingredients", recipeIngredients);
//            JsonObject fluidObject = new JsonObject();
//            fluidObject.addProperty("fluidname", stack.getFluid().getRegistryName().toString());
//            fluidObject.addProperty("amount", stack.getAmount());
            JsonArray recipeFluids = new JsonArray();
            recipeFluids.add(FluidJSONUtil.toJson(stack));
            pJson.add("fluids", recipeFluids);
            JsonArray recipeResults = new JsonArray();
            for (ItemStack resultItem : resultItems) {
                if (!resultItem.isEmpty())
                    recipeResults.add(ItemStackJSONUtil.toJson(resultItem));
            }
            pJson.add("results", recipeResults);
        }

        @Override
        public ResourceLocation getId() {
            return id;
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
