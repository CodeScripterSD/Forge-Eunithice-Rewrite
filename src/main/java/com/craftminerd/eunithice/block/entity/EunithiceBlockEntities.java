package com.craftminerd.eunithice.block.entity;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.block.entity.stations.AsphaltInfuserBlockEntity;
import com.craftminerd.eunithice.block.entity.stations.ExtractorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Eunithice.MODID);

    public static final RegistryObject<BlockEntityType<AsphaltInfuserBlockEntity>> ASPHALT_INFUSER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("asphalt_infuser_block_entity", ()->
                    BlockEntityType.Builder.of(AsphaltInfuserBlockEntity::new, EunithiceBlocks.ASPHALT_INFUSER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ExtractorBlockEntity>> EXTRACTOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("extractor_block_entity", ()->
                    BlockEntityType.Builder.of(ExtractorBlockEntity::new, EunithiceBlocks.EXTRACTOR.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}