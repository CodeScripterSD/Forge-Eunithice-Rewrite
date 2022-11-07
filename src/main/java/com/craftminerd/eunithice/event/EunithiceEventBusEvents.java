package com.craftminerd.eunithice.event;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
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
    }

}