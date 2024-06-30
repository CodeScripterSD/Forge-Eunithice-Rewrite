package com.craftminerd.eunithice.datagen;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.datagen.custom.EunithiceDataProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
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
        BlockTagsProvider blockTags = new EunithiceTagsProviders.EunithiceBlockTagsProvider(generator, Eunithice.MODID, existingFileHelper);

        generator.addProvider(blockTags);
        generator.addProvider(new EunithiceRecipeProvider(generator));
        generator.addProvider(new EunithiceLootTableProvider(generator));
        generator.addProvider(new EunithiceBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(new EunithiceItemModelProvider(generator, existingFileHelper));
        generator.addProvider(new EunithiceDataProvider(generator, Eunithice.MODID));
        generator.addProvider(new EunithiceTagsProviders.EunithiceItemTagsProvider(generator, blockTags, Eunithice.MODID, existingFileHelper));
    }
}
