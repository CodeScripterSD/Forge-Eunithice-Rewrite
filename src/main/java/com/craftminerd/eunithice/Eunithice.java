package com.craftminerd.eunithice;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.block.blockentities.EunithiceBlockEntities;
import com.craftminerd.eunithice.config.EunithiceCommonConfigs;
import com.craftminerd.eunithice.effect.EunithiceEffects;
import com.craftminerd.eunithice.enchantments.EunithiceEnchantments;
import com.craftminerd.eunithice.event.loot.EunithiceGlobalLootModifiers;
import com.craftminerd.eunithice.fluid.EunithiceFluids;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.recipe.EunithiceRecipes;
import com.craftminerd.eunithice.screen.EunithiceMenuTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Eunithice.MODID)
public class Eunithice
{
    public static final String MODID = "eunithice";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab EUNITHICE_ITEMS_TAB = new CreativeModeTab(MODID) { // itemGroup.eunithice
        @Override
        public ItemStack makeIcon() {
            return EunithiceItems.NEUDONITE_OMNITOOL.get().getDefaultInstance();
        }
    };

    public Eunithice()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        EunithiceItems.register(eventBus);
        EunithiceBlocks.register(eventBus);
        EunithiceBlockEntities.register(eventBus);
        EunithiceMenuTypes.register(eventBus);
        EunithiceRecipes.register(eventBus);
        EunithiceEnchantments.register(eventBus);
        EunithiceGlobalLootModifiers.register(eventBus);
//        EunithiceFluids.register(eventBus);


        // Register the setup method for modloading
        eventBus.addListener(this::setup);

//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, EunithiceClientConfigs.SPEC, "eunithice-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EunithiceCommonConfigs.SPEC, "eunithice-common.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(EunithiceBlocks.DARKWOOD_SAPLING.get().asItem(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(EunithiceBlocks.DARKWOOD_LEAVES.get().asItem(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(EunithiceItems.LEURITE_GRAINS.get(), 0.75f);
            ComposterBlock.COMPOSTABLES.put(EunithiceItems.LEURITE_BREAD.get(), 0.9f);
        });
    }
}
