package com.craftminerd.eunithice.integration.jei;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.recipe.AsphaltInfuserRecipe;
import com.craftminerd.eunithice.recipe.ExtractorRecipe;
import com.craftminerd.eunithice.screen.ExtractorScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIEunithicePlugin implements IModPlugin {

    public static final RecipeType<AsphaltInfuserRecipe> ASPHALT_INFUSER =
            RecipeType.create(Eunithice.MODID, "infuser", AsphaltInfuserRecipe.class);

    public static final RecipeType<ExtractorRecipe> EXTRACTION =
            RecipeType.create(Eunithice.MODID, "extraction", ExtractorRecipe.class);

    private IRecipeCategory<AsphaltInfuserRecipe> asphaltInfuserCategory;

    private IRecipeCategory<ExtractorRecipe> extractorRecipeCategory;

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Eunithice.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(asphaltInfuserCategory = new
                AsphaltInfuserRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

        registration.addRecipeCategories(extractorRecipeCategory = new
                ExtractorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<AsphaltInfuserRecipe> asphaltInfuserRecipes = rm.getAllRecipesFor(AsphaltInfuserRecipe.Type.INSTANCE);
        List<ExtractorRecipe> extractorRecipes = rm.getAllRecipesFor(ExtractorRecipe.Type.INSTANCE);
        registration.addRecipes(ASPHALT_INFUSER, asphaltInfuserRecipes);
        registration.addRecipes(EXTRACTION, extractorRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(EunithiceBlocks.ASPHALT_INFUSER.get()), ASPHALT_INFUSER);
        registration.addRecipeCatalyst(new ItemStack(EunithiceBlocks.EXTRACTOR.get()), EXTRACTION);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ExtractorScreen.class, 87, 38, 19, 20, EXTRACTION);
    }
}
