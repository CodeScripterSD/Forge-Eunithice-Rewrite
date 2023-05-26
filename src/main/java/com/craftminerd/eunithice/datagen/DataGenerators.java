package com.craftminerd.eunithice.datagen;

import com.craftminerd.eunithice.Eunithice;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Eunithice.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new EunithiceRecipeProvider(generator));
        generator.addProvider(new EunithiceLootTableProvider(generator));
        generator.addProvider(new EunithiceBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(new EunithiceItemModelProvider(generator, existingFileHelper));
    }
}
