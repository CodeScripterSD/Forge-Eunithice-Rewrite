package com.craftminerd.eunithice.datagen;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.item.EunithiceItems;
import com.craftminerd.eunithice.item.custom.HammerItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
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
                if (blockItem.getBlock() instanceof TrapDoorBlock) {
                    trapdoorBlock(blockItem);
                } else if (entry.get() != EunithiceItems.LEURITE_SEEDS.get()
                        && !(blockItem.getBlock() instanceof DoorBlock)) {
                    block(blockItem);
                } else {
                    simpleItem(blockItem);
                }
            } else if (entry.get() instanceof HammerItem
                    || entry.get() instanceof SwordItem
                    || entry.get() instanceof AxeItem
                    || entry.get() instanceof PickaxeItem
                    || entry.get() instanceof HoeItem
                    || entry.get() instanceof ShovelItem) {
                handheldItem(entry.get());
            } else {
                simpleItem(entry.get());
            }
        }
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Eunithice.MODID, "item/" + item.getRegistryName().getPath()));
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
