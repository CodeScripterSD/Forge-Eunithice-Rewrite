package com.craftminerd.eunithice.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.world.item.ItemStack;

public class ItemStackJSONUtil {
    public static JsonElement toJson(ItemStack stack) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("item", stack.getItem().getRegistryName().getNamespace()+":"+stack.getItem().getRegistryName().getPath());
        if (stack.getCount() > 1) jsonObject.addProperty("count", stack.getCount());
        return jsonObject;
    }
}
