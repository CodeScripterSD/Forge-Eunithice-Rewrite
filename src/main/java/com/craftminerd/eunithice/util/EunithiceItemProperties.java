package com.craftminerd.eunithice.util;

import com.craftminerd.eunithice.item.EunithiceItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Objects;

public class EunithiceItemProperties {
    public static void addCustomItemProperties() {
//        makeHandheldFurnace(EunithiceItems.HANDHELD_FURNACE.get());
    }
    private static void makeHandheldFurnace(Item item) {
        ItemProperties.register(item, new ResourceLocation("lit"),  (itemStack, clientLevel, livingEntity, p_174633_) -> {
            return livingEntity != null && (itemStack.getTag() != null ? itemStack.getTag().getInt("litTime") : 0) > 0 ? 1.0F : 0.0F;
        });
    }
}
