package com.craftminerd.eunithice.event;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.recipe.AsphaltInfuserRecipe;
import com.craftminerd.eunithice.recipe.ExtractorRecipe;
import com.craftminerd.eunithice.screen.AsphaltInfuserScreen;
import com.craftminerd.eunithice.screen.EunithiceMenuTypes;
import com.craftminerd.eunithice.screen.ExtractorScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = Eunithice.MODID, bus = Bus.MOD)
public class EunithiceEventBusEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.IRON_PLATE_DOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.IRON_PLATE_TRAPDOOR.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.LEURITE.get(), RenderType.cutout());

        MenuScreens.register(EunithiceMenuTypes.ASPHALT_INFUSER_MENU.get(), AsphaltInfuserScreen::new);
        MenuScreens.register(EunithiceMenuTypes.EXTRACTOR_MENU.get(), ExtractorScreen::new);
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, AsphaltInfuserRecipe.Type.ID, AsphaltInfuserRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, ExtractorRecipe.Type.ID, ExtractorRecipe.Type.INSTANCE);
    }

}
