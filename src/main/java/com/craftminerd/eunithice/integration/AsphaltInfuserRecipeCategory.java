package com.craftminerd.eunithice.integration;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.recipe.AsphaltInfuserRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

@SuppressWarnings("removal")
public class AsphaltInfuserRecipeCategory implements IRecipeCategory<AsphaltInfuserRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Eunithice.MODID, "asphalt_infuser");
    public final static ResourceLocation TEXTURE = new ResourceLocation(Eunithice.MODID, "textures/gui/asphalt_infuser_gui.png");
    private final IDrawable background;
    private final IDrawable icon;

    public AsphaltInfuserRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 60, 16, 56, 62);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(EunithiceBlocks.ASPHALT_INFUSER.get()));
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("block_entity.eunithice.display_name.asphalt_infuser");
    }
    @Override
    public IDrawable getBackground() {
        return this.background;
    }
    @Override
    public IDrawable getIcon() {
        return this.icon;
    }
    @Override
    public ResourceLocation getUid() {
        return UID;
    }
    @Override
    public Class<? extends AsphaltInfuserRecipe> getRecipeClass() {
        return AsphaltInfuserRecipe.class;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull AsphaltInfuserRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        int slotsLeft = 2;
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            switch (slotsLeft) {
                case 2:
                    builder.addSlot(RecipeIngredientRole.INPUT, 2, 2).addIngredients(recipe.getIngredients().get(i));
                    slotsLeft -= 1;
                    break;
                case 1:
                    builder.addSlot(RecipeIngredientRole.INPUT, 38, 2).addIngredients(recipe.getIngredients().get(i));
                    slotsLeft -= 1;
                    break;
                default:
                    throw new IndexOutOfBoundsException("Error occurred whilst setting recipe in JEI integration");
            }
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 20, 44).addItemStack(recipe.getResultItem());
    }
}