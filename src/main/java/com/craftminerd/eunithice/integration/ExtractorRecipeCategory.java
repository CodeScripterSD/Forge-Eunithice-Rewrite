package com.craftminerd.eunithice.integration;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.recipe.ExtractorRecipe;
import com.craftminerd.eunithice.util.EunithiceTags;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

@SuppressWarnings("removal")
public class ExtractorRecipeCategory implements IRecipeCategory<ExtractorRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Eunithice.MODID, "extraction");
    public final static ResourceLocation TEXTURE = new ResourceLocation(Eunithice.MODID, "textures/gui/extractor_gui.png");
    private final IDrawable background;
    private final IDrawable icon;

    public ExtractorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 23, 20, 130, 44);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(EunithiceBlocks.EXTRACTOR.get()));
    }

    @Override
    public RecipeType<ExtractorRecipe> getRecipeType() {
        return JEIEunithicePlugin.EXTRACTION;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block_entity.eunithice.display_name.extractor");
    }
    @Override
    public IDrawable getBackground() {
        return this.background;
    }
    @Override
    public IDrawable getIcon() {
        return this.icon;
    }
//    @Override
//    public ResourceLocation getUid() {
//        return UID;
//    }
//    @Override
//    public Class<? extends ExtractorRecipe> getRecipeClass() {
//        return ExtractorRecipe.class;
//    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull ExtractorRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            if (recipe.getIngredients().get(i).getItems()[0].is(EunithiceTags.Items.CORES)) {
                builder.addSlot(RecipeIngredientRole.INPUT, 2, 14).addIngredients(recipe.getIngredients().get(i)); // CORE SLOT
            }
            else if (recipe.getIngredients().get(i).getItems()[0].is(EunithiceTags.Items.HAMMERS)) {
                builder.addSlot(RecipeIngredientRole.INPUT, 36, 2).addIngredients(recipe.getIngredients().get(i)); // HAMMER SLOT
            } else {
                builder.addSlot(RecipeIngredientRole.INPUT, 36, 26).addIngredients(recipe.getIngredients().get(i));
            }
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 76, 5).addItemStack(recipe.getResultItem());
    }
}