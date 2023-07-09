package com.craftminerd.eunithice.integration;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.recipe.ExtractorRecipe;
import com.craftminerd.eunithice.util.EunithiceTags;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
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
import java.util.List;

@SuppressWarnings("removal")
public class ExtractorRecipeCategory implements IRecipeCategory<ExtractorRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(Eunithice.MODID, "extraction");
    public final static ResourceLocation TEXTURE = new ResourceLocation(Eunithice.MODID, "textures/gui/extractor_gui.png");
    private final IDrawable background;
    private final IDrawable icon;

    public ExtractorRecipeCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE, 41, 13, 120, 61).build();
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(EunithiceBlocks.EXTRACTOR.get()));
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("block_entity.eunithice.display_name.extractor");
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
    public Class<? extends ExtractorRecipe> getRecipeClass() {
        return ExtractorRecipe.class;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull ExtractorRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            if (recipe.getIngredients().get(i).getItems()[0].is(EunithiceTags.Items.HAMMERS)) {
                builder.addSlot(RecipeIngredientRole.INPUT, 28, 10).addIngredients(recipe.getIngredients().get(i)); // HAMMER SLOT
            } else {
                builder.addSlot(RecipeIngredientRole.INPUT, 28, 35).addIngredients(recipe.getIngredients().get(i));
            }
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 1).addIngredients(
                ForgeTypes.FLUID_STACK, List.of(recipe.getFluid())
        ).setFluidRenderer(16000, false, 16, 59);
        for (int i = 0; i < recipe.getResultItemStacks().size(); i++) {
            switch (i) {
                case 0: builder.addSlot(RecipeIngredientRole.OUTPUT, 68, 13).addItemStack(recipe.getResultItemStacks().get(0)); break;
                case 1: builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 13).addItemStack(recipe.getResultItemStacks().get(1)); break;
                case 2: builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 13).addItemStack(recipe.getResultItemStacks().get(2)); break;
                case 3: builder.addSlot(RecipeIngredientRole.OUTPUT, 68, 31).addItemStack(recipe.getResultItemStacks().get(3)); break;
                case 4: builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 31).addItemStack(recipe.getResultItemStacks().get(4)); break;
                case 5: builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 31).addItemStack(recipe.getResultItemStacks().get(5)); break;
            }
        }
    }

}