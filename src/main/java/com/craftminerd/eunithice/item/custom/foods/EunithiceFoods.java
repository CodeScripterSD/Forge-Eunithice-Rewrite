package com.craftminerd.eunithice.item.custom.foods;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class EunithiceFoods {
    // TODO: delete this probably idk
    public static final FoodProperties LEURITE_BREAD = (new FoodProperties.Builder()).nutrition(10).saturationMod(0.8F).build();
    public static final FoodProperties MEATBALL_SOUP = (new FoodProperties.Builder()).nutrition(11).saturationMod(0.7F).build();
    public static final FoodProperties VEGETABLE_SALAD = (new FoodProperties.Builder()).nutrition(9).saturationMod(0.7F).build();
    public static final FoodProperties FRUIT_DISH = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.6F).build();

    public static final FoodProperties REVIVAL_SANDWICH = (new FoodProperties.Builder()).nutrition(18).saturationMod(0.5f)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 800, 2), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3600, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 7200, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2400, 4), 1.0F).build();
}
