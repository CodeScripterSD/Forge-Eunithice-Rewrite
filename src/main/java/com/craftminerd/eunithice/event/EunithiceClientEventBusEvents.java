package com.craftminerd.eunithice.event;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.fluid.EunithiceFluids;
import com.craftminerd.eunithice.screen.AsphaltInfuserScreen;
import com.craftminerd.eunithice.screen.EunithiceMenuTypes;
import com.craftminerd.eunithice.screen.ExtractorScreen;
import com.craftminerd.eunithice.util.EunithiceItemProperties;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Eunithice.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EunithiceClientEventBusEvents {
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.IRON_PLATE_DOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.IRON_PLATE_TRAPDOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.DISPLAY_CASE.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.LEURITE.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(EunithiceFluids.EXTRACTION_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(EunithiceFluids.EXTRACTION_FLUID.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(EunithiceFluids.EXTRACTION_FLOWING.get(), RenderType.translucent());

        EunithiceItemProperties.addCustomItemProperties();

        MenuScreens.register(EunithiceMenuTypes.ASPHALT_INFUSER_MENU.get(), AsphaltInfuserScreen::new);
        MenuScreens.register(EunithiceMenuTypes.EXTRACTOR_MENU.get(), ExtractorScreen::new);
    }
}
