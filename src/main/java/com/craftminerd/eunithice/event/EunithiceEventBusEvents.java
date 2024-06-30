package com.craftminerd.eunithice.event;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.recipe.AsphaltInfuserRecipe;
import com.craftminerd.eunithice.recipe.ExtractorRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.resource.PathResourcePack;

@EventBusSubscriber(modid = Eunithice.MODID, bus = Bus.MOD)
public class EunithiceEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(final EntityAttributeCreationEvent event) {

    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, AsphaltInfuserRecipe.Type.ID, AsphaltInfuserRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, ExtractorRecipe.Type.ID, ExtractorRecipe.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            IModFileInfo modFileInfo = ModList.get().getModFileById(Eunithice.MODID);
            if (modFileInfo == null) {
                Eunithice.LOGGER.error("Could not find Eunithice mod file info; built-in resource packs will be missing!");
                return;
            }
            IModFile modFile = modFileInfo.getFile();
            event.addRepositorySource((pInfoConsumer, pInfoFactory) -> {
                pInfoConsumer.accept(Pack.create(new ResourceLocation(Eunithice.MODID, "alternate_omnitool_name").toString(), false,
                        () -> new PathResourcePack("Alternate Omnitool Name", modFile.findResource("resourcepacks/alternate_omnitool_name")),
                        pInfoFactory, Pack.Position.TOP, PackSource.DEFAULT));
            });
        }
    }

}
