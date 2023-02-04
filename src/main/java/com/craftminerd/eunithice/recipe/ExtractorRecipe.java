package com.craftminerd.eunithice.recipe;

import com.craftminerd.eunithice.Eunithice;
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
import org.jetbrains.annotations.Nullable;


public class ExtractorRecipe implements Recipe<SimpleContainer> {
    private final String group;
    private final ResourceLocation id;
    private final ItemStack result;
//    private final NonNullList<Ingredient> resultItemStacks;
    private final NonNullList<Ingredient> recipeItems;
    private final boolean ignoreDurability;

    public ExtractorRecipe(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> recipeItems, boolean ignoreDurability) {
        this.group = group;
        this.id = id;
        this.result = result;
        this.recipeItems = recipeItems;
        this.ignoreDurability = ignoreDurability;
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
    public ItemStack assemble(SimpleContainer pContainer) { return result; }
    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) { return true; }
    @Override
    public ItemStack getResultItem() { return result; }
//    public NonNullList<ItemStack> getResultItemStacks() {  }
    public boolean getIgnoreDurability() { return ignoreDurability; }
    @Override
    public ResourceLocation getId() { return id;  }
    @Override
    public RecipeSerializer<?> getSerializer() { return Serializer.INSTANCE; }
    @Override
    public NonNullList<Ingredient> getIngredients() { return this.recipeItems; }
    @Override
    public RecipeType<?> getType() { return Type.INSTANCE; }

    public static class Type implements RecipeType<ExtractorRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "extraction";
    }

    public static class Serializer implements RecipeSerializer<ExtractorRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Eunithice.MODID, "extraction");

        @Override
        public ExtractorRecipe fromJson(ResourceLocation id, JsonObject json) {
            String s = GsonHelper.getAsString(json, "group", "");
            NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(json, "ingredients"));
            boolean ignoreDurability = GsonHelper.getAsBoolean(json, "ignore_durability");
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for Extraction recipe");
            } else if (nonnulllist.size() > 3) {
                throw new JsonParseException("Too many ingredients for recipe. The maximum is 3.");
            } else {
                ItemStack itemStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json,"result"));
                return new ExtractorRecipe(id,s,itemStack,nonnulllist, ignoreDurability);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for (int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }
            return nonnulllist;
        }

        @Nullable
        @Override
        public ExtractorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            String s = buf.readUtf();
            int i = buf.readVarInt();
            boolean ignoreDurability = buf.readBoolean();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.fromNetwork(buf));
            }

            ItemStack itemStack = buf.readItem();
            return new ExtractorRecipe(id, s, itemStack, nonnulllist, ignoreDurability);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ExtractorRecipe recipe) {
            buf.writeUtf(recipe.group);
            buf.writeVarInt(recipe.getIngredients().size());
            buf.writeBoolean(recipe.ignoreDurability);
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItem(recipe.getResultItem());
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
            return Serializer.castClass(RecipeSerializer.class);
        }

        private static <G> Class<G> castClass(Class<?> cls) {
            return (Class<G>)cls;
        }
    }
}