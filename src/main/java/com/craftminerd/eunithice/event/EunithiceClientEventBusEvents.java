package com.craftminerd.eunithice.event;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.screen.AsphaltInfuserScreen;
import com.craftminerd.eunithice.screen.EunithiceMenuTypes;
import com.craftminerd.eunithice.screen.ExtractorScreen;
import com.craftminerd.eunithice.util.EunithiceItemProperties;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Eunithice.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EunithiceClientEventBusEvents {
    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.STONE_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.STONE_TRAPDOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.IRON_PLATE_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.IRON_PLATE_TRAPDOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.DARKWOOD_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.DARKWOOD_TRAPDOOR.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.DISPLAY_CASE.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.LEURITE_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.DARKWOOD_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.DARKWOOD_SAPLING.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.GRASS_STAIRS.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(EunithiceBlocks.GRASS_SLAB.get(), RenderType.cutoutMipped());

//        ItemBlockRenderTypes.setRenderLayer(EunithiceFluids.CHROMA_BLOCK.get(), RenderType.translucent());
//        ItemBlockRenderTypes.setRenderLayer(EunithiceFluids.CHROMA.get(), RenderType.translucent());
//        ItemBlockRenderTypes.setRenderLayer(EunithiceFluids.CHROMA_FLOWING.get(), RenderType.translucent());

        EunithiceItemProperties.addCustomItemProperties();

        MenuScreens.register(EunithiceMenuTypes.ASPHALT_INFUSER_MENU.get(), AsphaltInfuserScreen::new);
        MenuScreens.register(EunithiceMenuTypes.EXTRACTOR_MENU.get(), ExtractorScreen::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(final ColorHandlerEvent.Block event) {
        event.getBlockColors().register((pState, blockAndTintGetter, pPos, pTintIndex) ->
                        0x4c4c4c,
                        EunithiceBlocks.DARKWOOD_LEAVES.get()
        );
        event.getBlockColors().register((state, blockAndTintGetter, pos, pTintIndex) -> {
            if (pTintIndex == 1) {
                return blockAndTintGetter != null && pos != null ? BiomeColors.getAverageGrassColor(blockAndTintGetter, pos) : GrassColor.get(0.5D, 1.0D);
            }
            return -1;
        }, EunithiceBlocks.GRASS_SLAB.get(), EunithiceBlocks.GRASS_STAIRS.get());
    }

    @SubscribeEvent
    public static void registerItemColors(final ColorHandlerEvent.Item event) {
        event.getItemColors().register((pStack, pTintIndex) ->
                        0x4c4c4c,
                        EunithiceBlocks.DARKWOOD_LEAVES.get().asItem());

        event.getItemColors().register((p_92687_, p_92688_) -> {
            BlockState blockstate = ((BlockItem)p_92687_.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, (BlockAndTintGetter)null, (BlockPos)null, p_92688_);
        }, EunithiceBlocks.GRASS_SLAB.get().asItem(), EunithiceBlocks.GRASS_STAIRS.get().asItem());
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {

    }
}
