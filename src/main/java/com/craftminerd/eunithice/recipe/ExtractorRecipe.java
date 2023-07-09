package com.craftminerd.eunithice.recipe;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.util.FluidJSONUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public class ExtractorRecipe implements Recipe<SimpleContainer> {
    private final String group;
    private final ResourceLocation id;
    private final NonNullList<ItemStack> resultItems = NonNullList.withSize(5, ItemStack.EMPTY);
    private final NonNullList<Ingredient> recipeItems;
    private final FluidStack fluidStack;
    private final boolean ignoreDurability;

    public ExtractorRecipe(ResourceLocation id, String group, NonNullList<ItemStack> resultItems, NonNullList<Ingredient> recipeItems, FluidStack fluidStack, boolean ignoreDurability) {
        this.group = group;
        this.id = id;
        for (int i = 0; i < resultItems.size(); i++) {
            this.resultItems.set(i, resultItems.get(i));
        }
        this.recipeItems = recipeItems;
        this.fluidStack = fluidStack;
        this.ignoreDurability = ignoreDurability;
    }

    public FluidStack getFluid() {
        return fluidStack;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        StackedContents stackedcontents = new StackedContents();
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for (int j = 0; j < pContainer.getContainerSize()-6; ++j) {
            ItemStack itemStack = pContainer.getItem(j);
            if (!itemStack.isEmpty()) {
                ++i;
                stackedcontents.accountStack(itemStack, 1);
//                inputs.add(itemStack);
            }
        }
        return i == this.recipeItems.size() && (stackedcontents.canCraft(this, (IntList)null)); /*(RecipeMatcher.findMatches(inputs, this.recipeItems) != null)*/
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) { return ItemStack.EMPTY; }
    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) { return true; }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    //    public ItemStack getResultItem() { return result; }
    public NonNullList<ItemStack> getResultItemStacks() { return resultItems; }
    public boolean getIgnoreDurability() { return ignoreDurability; }
    @Override
    public ResourceLocation getId() { return id;  }
    @Override
    public RecipeSerializer<?> getSerializer() { return ExtractorRecipe.Serializer.INSTANCE; }
    @Override
    public NonNullList<Ingredient> getIngredients() { return this.recipeItems; }
    @Override
    public RecipeType<?> getType() { return ExtractorRecipe.Type.INSTANCE; }

    public static class Type implements RecipeType<ExtractorRecipe> {
        private Type() {}
        public static final ExtractorRecipe.Type INSTANCE = new ExtractorRecipe.Type();
        public static final String ID = "extraction";
    }

    public static class Serializer implements RecipeSerializer<ExtractorRecipe> {
        public static final ExtractorRecipe.Serializer INSTANCE = new ExtractorRecipe.Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Eunithice.MODID, "extraction");

        @Override
        public ExtractorRecipe fromJson(ResourceLocation id, JsonObject json) {
            String s = GsonHelper.getAsString(json, "group", "");
            NonNullList<Ingredient> ingredients = ingredientsFromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            FluidStack fluid = FluidJSONUtil.readFluid(json.get("fluid").getAsJsonObject());

            boolean ignoreDurability = GsonHelper.getAsBoolean(json, "ignore_durability");

            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for Extraction recipe");
            } else if (ingredients.size() > 3) {
                throw new JsonParseException("Too many ingredients for recipe. The maximum is 3.");
            } else {
                NonNullList<ItemStack> resultItems = itemsFromJson(GsonHelper.getAsJsonArray(json, "results"));
//                ItemStack itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json,"result"));
                return new ExtractorRecipe(id,s,resultItems,ingredients, fluid, ignoreDurability);
            }
        }

        private static NonNullList<Ingredient> ingredientsFromJson(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for (int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }
            return nonnulllist;
        }

        private static NonNullList<ItemStack> itemsFromJson(JsonArray itemArray) {
            NonNullList<ItemStack> nonNullList = NonNullList.create();
            for (int i = 0; i < itemArray.size(); i++) {
                ItemStack stack = ShapedRecipe.itemStackFromJson(itemArray.get(i).getAsJsonObject());
                if (!stack.isEmpty()) nonNullList.add(stack);
            }
            return nonNullList;
        }

        @Nullable
        @Override
        public ExtractorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            String s = buf.readUtf();
            int i = buf.readVarInt();
            boolean ignoreDurability = buf.readBoolean();
            int l = buf.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            NonNullList<ItemStack> results = NonNullList.withSize(l, ItemStack.EMPTY);
            FluidStack stack = buf.readFluidStack();
            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.fromNetwork(buf));
            }
            for (int k = 0; k < results.size(); ++k) {
                results.set(k, buf.readItem());
            }

            return new ExtractorRecipe(id, s, results, nonnulllist, stack, ignoreDurability);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ExtractorRecipe recipe) {
            buf.writeUtf(recipe.group);
            buf.writeVarInt(recipe.getIngredients().size());
            buf.writeBoolean(recipe.ignoreDurability);
            buf.writeVarInt(recipe.getResultItemStacks().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            for (ItemStack stack : recipe.getResultItemStacks()) {
                buf.writeItem(stack);
            }
            buf.writeFluidStack(recipe.fluidStack);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return ExtractorRecipe.Serializer.castClass(RecipeSerializer.class);
        }

        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}
