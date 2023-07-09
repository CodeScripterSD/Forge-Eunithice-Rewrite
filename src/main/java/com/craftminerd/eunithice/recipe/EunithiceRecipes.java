package com.craftminerd.eunithice.recipe;

import com.craftminerd.eunithice.Eunithice;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Eunithice.MODID);

    public static final RegistryObject<RecipeSerializer<AsphaltInfuserRecipe>> ASPHALT_INFUSER_RECIPE_SERIALIZER = SERIALIZERS.register("asphalt_infuser", ()->
            AsphaltInfuserRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<ExtractorRecipe>> EXTRACTOR_RECIPE_SERIALIZER = SERIALIZERS.register("extraction", ()->
            ExtractorRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) { SERIALIZERS.register(eventBus); }
}