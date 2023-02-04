package com.craftminerd.eunithice.screen;

import com.craftminerd.eunithice.Eunithice;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Eunithice.MODID);
    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

    public static final RegistryObject<MenuType<AsphaltInfuserMenu>> ASPHALT_INFUSER_MENU = registerMenuType(AsphaltInfuserMenu::new,"asphalt_infuser_menu");
    public static final RegistryObject<MenuType<ExtractorMenu>> EXTRACTOR_MENU = registerMenuType(ExtractorMenu::new,"extractor_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

}
