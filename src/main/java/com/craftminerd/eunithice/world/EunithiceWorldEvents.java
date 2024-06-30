package com.craftminerd.eunithice.world;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.world.gen.EunithiceOreGeneration;
import com.craftminerd.eunithice.world.gen.EunithiceTreeGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Eunithice.MODID)
public class EunithiceWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        EunithiceTreeGeneration.generateTrees(event);
        EunithiceOreGeneration.generateOres(event);
    }
}
