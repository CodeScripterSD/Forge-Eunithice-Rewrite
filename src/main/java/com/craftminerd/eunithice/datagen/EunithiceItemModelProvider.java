package com.craftminerd.eunithice.datagen;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.item.custom.CustomBowItem;
import com.craftminerd.eunithice.item.custom.HammerItem;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceItemModelProvider extends ItemModelProvider {
    public EunithiceItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Eunithice.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> entry : EunithiceItems.ITEMS.getEntries()) {
            if (entry.get() instanceof BlockItem blockItem) {
                if (blockItem.getBlock() instanceof FenceBlock ||
                        blockItem.getBlock() instanceof FenceGateBlock ||
                        blockItem.getBlock() instanceof ButtonBlock) {
                    Eunithice.LOGGER.warn("An automatic item model was not created for: " + entry.get().getRegistryName());
                } else if (blockItem.getBlock() instanceof SaplingBlock) {
                    saplingItem(blockItem);
                } else if (blockItem.getBlock() instanceof TrapDoorBlock) {
                    trapdoorBlock(blockItem);
                } else if ((entry.get() != EunithiceItems.LEURITE_SEEDS.get()
                        && !(blockItem.getBlock() instanceof DoorBlock))) {
                    block(blockItem);
                } else {
                    simpleItem(blockItem);
                }
            } else if (entry.get() instanceof CustomBowItem) {
                customBowItem(entry.get());
            } else if (entry.get() instanceof HammerItem
                    || entry.get() instanceof SwordItem
                    || entry.get() instanceof DiggerItem) {
                handheldItem(entry.get());
            } else {
                simpleItem(entry.get());
            }
        }

        buttonInventory(EunithiceBlocks.DARKWOOD_BUTTON.get().getRegistryName().getPath(), new ResourceLocation(Eunithice.MODID, "block/" + EunithiceBlocks.DARKWOOD_PLANKS.get().getRegistryName().getPath()));
        fenceInventory(EunithiceBlocks.DARKWOOD_FENCE.get().getRegistryName().getPath(), new ResourceLocation(Eunithice.MODID, "block/" + EunithiceBlocks.DARKWOOD_PLANKS.get().getRegistryName().getPath()));
        fenceGate(EunithiceBlocks.DARKWOOD_FENCE_GATE.get().getRegistryName().getPath(), new ResourceLocation(Eunithice.MODID, "block/" + EunithiceBlocks.DARKWOOD_PLANKS.get().getRegistryName().getPath()));
    }

    private ItemModelBuilder customBowItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Eunithice.MODID, "item/" + item.getRegistryName().getPath()))
                .transforms()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
                .rotation(-80F, 260F, -40F)
                .translation(-1F, -2F, 2.5F)
                .scale(0.9F).end()
                .transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND)
                .rotation(-80F, -280F, 40F)
                .translation(-1F, -2F, 2.5F)
                .scale(0.9F).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
                .rotation(0F, -90F, 25F)
                .translation(1.13F, 3.2F, 1.13F)
                .scale(0.68F).end()
                .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND)
                .rotation(0F, 90F, -25F)
                .translation(1.13F, 3.2F, 1.13F)
                .scale(0.68F).end()
                .end();
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Eunithice.MODID, "item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder saplingItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Eunithice.MODID, "block/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Eunithice.MODID, "item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder block(BlockItem blockItem) {
        return withExistingParent(blockItem.getRegistryName().getPath(), Eunithice.MODID + ":block/"+blockItem.getBlock().getRegistryName().getPath());
    }

    private ItemModelBuilder trapdoorBlock(BlockItem blockItem) {
        return withExistingParent(blockItem.getRegistryName().getPath(), Eunithice.MODID + ":block/"+blockItem.getBlock().getRegistryName().getPath() + "_bottom");
    }
}
