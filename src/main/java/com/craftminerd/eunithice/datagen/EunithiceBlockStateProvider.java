package com.craftminerd.eunithice.datagen;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.function.Function;

public class EunithiceBlockStateProvider extends BlockStateProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    public EunithiceBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Eunithice.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (RegistryObject<Block> entry : EunithiceBlocks.BLOCKS.getEntries()) {
            if (entry.get() instanceof DoorBlock doorBlock) {
                doorBlock(doorBlock, new ResourceLocation(Eunithice.MODID, "block/" + doorBlock.getRegistryName().getPath() + "_bottom"),
                        new ResourceLocation(Eunithice.MODID, "block/" + doorBlock.getRegistryName().getPath() + "_top"));
            } else if (entry.get() instanceof TrapDoorBlock trapDoorBlock) {
                trapdoorBlock(trapDoorBlock, blockTexture(trapDoorBlock), true);
            } else if (entry.get() instanceof CropBlock cropBlock) {
                makeCrop(cropBlock, cropBlock.getRegistryName().getPath() + "_stage", cropBlock.getRegistryName().getPath() + "_stage");
            } else if (entry.get() instanceof RotatedPillarBlock pillarBlock) {
                axisBlock(pillarBlock, new ResourceLocation(Eunithice.MODID, "block/" + pillarBlock.getRegistryName().getPath() + "_side"),
                        new ResourceLocation(Eunithice.MODID, "block/" + pillarBlock.getRegistryName().getPath() + "_end"));
            } else if (!(entry.get() instanceof BaseEntityBlock baseEntityBlock)) {
                simpleBlock(entry.get());
            } else {
                LOGGER.warn("A model has not been created for " + baseEntityBlock.getRegistryName());
            }
        }
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(block.getAgeProperty()),
                new ResourceLocation(Eunithice.MODID, "block/" + textureName + state.getValue(block.getAgeProperty()))));

        return models;
    }
}
