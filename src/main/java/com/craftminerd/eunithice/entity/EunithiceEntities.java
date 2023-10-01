package com.craftminerd.eunithice.entity;

import com.craftminerd.eunithice.Eunithice;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Eunithice.MODID);

    public static final RegistryObject<EntityType<ExampleEntity>> EXAMPLE_ENTITY = ENTITIES.register("example_entity",
            () -> EntityType.Builder.of(ExampleEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 0.6f)
                    .build(new ResourceLocation(Eunithice.MODID, "example_entity").toString()));
}
