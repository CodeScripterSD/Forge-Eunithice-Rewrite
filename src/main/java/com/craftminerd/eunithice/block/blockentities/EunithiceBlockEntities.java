package com.craftminerd.eunithice.block.blockentities;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.block.blockentities.stations.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Eunithice.MODID);

    public static final RegistryObject<BlockEntityType<InfuserBlockEntity>> INFUSER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("infuser_block_entity", ()->
                    BlockEntityType.Builder.of(InfuserBlockEntity::new, EunithiceBlocks.INFUSER.get()).build(null));

    public static final RegistryObject<BlockEntityType<AsphaltInfuserBlockEntity>> ASPHALT_INFUSER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("asphalt_infuser_block_entity", ()->
                    BlockEntityType.Builder.of(AsphaltInfuserBlockEntity::new, EunithiceBlocks.ASPHALT_INFUSER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ExtractorBlockEntity>> EXTRACTOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("extractor_block_entity", ()->
                    BlockEntityType.Builder.of(ExtractorBlockEntity::new, EunithiceBlocks.EXTRACTOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<DisplayCaseBlockEntity>> DISPLAY_CASE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("display_case_block_entity", () ->
                    BlockEntityType.Builder.of(DisplayCaseBlockEntity::new, EunithiceBlocks.DISPLAY_CASE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}