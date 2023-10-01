package com.craftminerd.eunithice.event;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.entity.EunithiceEntities;
import com.craftminerd.eunithice.entity.ExampleEntity;
import com.craftminerd.eunithice.recipe.AsphaltInfuserRecipe;
import com.craftminerd.eunithice.recipe.ExtractorRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Eunithice.MODID, bus = Bus.MOD)
public class EunithiceEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(final EntityAttributeCreationEvent event) {
        event.put(EunithiceEntities.EXAMPLE_ENTITY.get(), ExampleEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, AsphaltInfuserRecipe.Type.ID, AsphaltInfuserRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, ExtractorRecipe.Type.ID, ExtractorRecipe.Type.INSTANCE);
    }

}
