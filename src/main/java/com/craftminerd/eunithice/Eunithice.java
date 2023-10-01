package com.craftminerd.eunithice;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.block.entity.EunithiceBlockEntities;
import com.craftminerd.eunithice.config.EunithiceClientConfigs;
import com.craftminerd.eunithice.config.EunithiceCommonConfigs;
import com.craftminerd.eunithice.effect.EunithiceEffects;
import com.craftminerd.eunithice.enchantments.EunithiceEnchantments;
import com.craftminerd.eunithice.entity.EunithiceEntities;
import com.craftminerd.eunithice.event.loot.EunithiceGlobalLootModifiers;
import com.craftminerd.eunithice.fluid.EunithiceFluids;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.networking.EunithiceMessages;
import com.craftminerd.eunithice.recipe.EunithiceRecipes;
import com.craftminerd.eunithice.screen.EunithiceMenuTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreativeModeTab EUNITHICE_ITEMS_TAB = new CreativeModeTab(MODID) { // itemGroup.eunithice
        @Override
        public ItemStack makeIcon() {
            return EunithiceItems.BURN_CORE.get().getDefaultInstance();
        }
    };

    public Eunithice()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        eventBus.addListener(this::setup);

        EunithiceItems.register(eventBus);
        EunithiceBlocks.register(eventBus);
        EunithiceBlockEntities.register(eventBus);
        EunithiceEntities.ENTITIES.register(eventBus);
        EunithiceMenuTypes.register(eventBus);
        EunithiceRecipes.register(eventBus);
        EunithiceEnchantments.register(eventBus);
        EunithiceEffects.register(eventBus);
        EunithiceGlobalLootModifiers.register(eventBus);
        EunithiceFluids.register(eventBus);

//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, EunithiceClientConfigs.SPEC, "eunithice-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EunithiceCommonConfigs.SPEC, "eunithice-common.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            EunithiceMessages.register();
        });
        // some preinit code
//        LOGGER.info("HELLO FROM PREINIT");
//        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
