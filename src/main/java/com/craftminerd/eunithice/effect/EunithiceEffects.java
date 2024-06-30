package com.craftminerd.eunithice.effect;

import com.craftminerd.eunithice.Eunithice;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceEffects {
    public static final DeferredRegister<MobEffect> EUNITHICE_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Eunithice.MODID);

    public static void register(IEventBus eventBus) {
        EUNITHICE_EFFECTS.register(eventBus);
    }
}
