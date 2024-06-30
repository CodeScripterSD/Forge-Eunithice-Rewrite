package com.craftminerd.eunithice.datagen;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.block.blocks.EunithiceFlammableRotatedPillarBlock;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
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
        // Automatically make most
        for (RegistryObject<Block> entry : EunithiceBlocks.BLOCKS.getEntries()) {
            if (entry.get() instanceof EunithiceFlammableRotatedPillarBlock ||
                    entry.get() instanceof StairBlock ||
                    entry.get() instanceof SlabBlock ||
                    entry.get() instanceof FenceBlock ||
                    entry.get() instanceof FenceGateBlock ||
                    entry.get() instanceof WallBlock ||
                    entry.get() instanceof PressurePlateBlock ||
                    entry.get() instanceof ButtonBlock) {
                LOGGER.warn("An automatic model was not created for: " + entry.get().getRegistryName());
            } else if (entry.get() instanceof LeavesBlock leavesBlock) {
                simpleBlock(entry.get(), leaves(leavesBlock));
            } else if (entry.get() instanceof DoorBlock doorBlock) {
                doorBlock(doorBlock, new ResourceLocation(Eunithice.MODID, "block/" + doorBlock.getRegistryName().getPath() + "_bottom"),
                        new ResourceLocation(Eunithice.MODID, "block/" + doorBlock.getRegistryName().getPath() + "_top"));
            } else if (entry.get() instanceof TrapDoorBlock trapDoorBlock) {
                trapdoorBlock(trapDoorBlock, blockTexture(trapDoorBlock), true);
            } else if(entry.get() instanceof SaplingBlock saplingBlock) {
                simpleBlock(saplingBlock, models().cross(saplingBlock.getRegistryName().getPath(), blockTexture(saplingBlock)));
            } else if (entry.get() instanceof CropBlock cropBlock) {
                makeCrop(cropBlock, cropBlock.getRegistryName().getPath() + "_stage", cropBlock.getRegistryName().getPath() + "_stage");
            } else if (entry.get() instanceof RotatedPillarBlock pillarBlock) {
                axisBlock(pillarBlock, new ResourceLocation(Eunithice.MODID, "block/" + pillarBlock.getRegistryName().getPath() + "_side"),
                        new ResourceLocation(Eunithice.MODID, "block/" + pillarBlock.getRegistryName().getPath() + "_end"));
            } else if (!(entry.get() instanceof BaseEntityBlock baseEntityBlock) && !(entry.get() instanceof LiquidBlock liquidBlock)) {
                simpleBlock(entry.get());
            } else {
                LOGGER.warn("A model has not been created for " + entry.get().getRegistryName());
            }
        }
        // Manually make the states and models for the wood-related blocks
        logBlock((RotatedPillarBlock) EunithiceBlocks.DARKWOOD_LOG.get());
        logBlock((RotatedPillarBlock) EunithiceBlocks.STRIPPED_DARKWOOD_LOG.get());
        makeWood((RotatedPillarBlock) EunithiceBlocks.DARKWOOD_WOOD.get(), blockTexture(EunithiceBlocks.DARKWOOD_LOG.get()));
        makeWood((RotatedPillarBlock) EunithiceBlocks.STRIPPED_DARKWOOD_WOOD.get(), blockTexture(EunithiceBlocks.STRIPPED_DARKWOOD_LOG.get()));

        stairsBlock((StairBlock) EunithiceBlocks.DARKWOOD_STAIRS.get(), blockTexture(EunithiceBlocks.DARKWOOD_PLANKS.get()));
        slabBlock((SlabBlock) EunithiceBlocks.DARKWOOD_SLAB.get(), blockTexture(EunithiceBlocks.DARKWOOD_PLANKS.get()), blockTexture(EunithiceBlocks.DARKWOOD_PLANKS.get()));
        fenceBlock((FenceBlock) EunithiceBlocks.DARKWOOD_FENCE.get(), blockTexture(EunithiceBlocks.DARKWOOD_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) EunithiceBlocks.DARKWOOD_FENCE_GATE.get(), blockTexture(EunithiceBlocks.DARKWOOD_PLANKS.get()));
        buttonBlock((ButtonBlock) EunithiceBlocks.DARKWOOD_BUTTON.get(), blockTexture(EunithiceBlocks.DARKWOOD_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock) EunithiceBlocks.DARKWOOD_PRESSURE_PLATE.get(), blockTexture(EunithiceBlocks.DARKWOOD_PLANKS.get()));

    }

    public ModelFile leaves(Block block) {
        return models().withExistingParent(name(block), "block/leaves").texture("all", blockTexture(block));
    }

    public void makeWood(RotatedPillarBlock wood, ResourceLocation texture) {
        axisBlock(wood, texture, texture);
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

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }
}
