package com.craftminerd.eunithice.datagen.loot;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.block.custom.LeuriteCrop;
import com.craftminerd.eunithice.item.EunithiceItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceBlockLootTables extends BlockLoot {
    @Override
    protected void addTables() {
        this.dropSelf(EunithiceBlocks.ASPHALT.get());
        this.dropSelf(EunithiceBlocks.RAW_NEUDONITE_BLOCK.get());
        this.dropSelf(EunithiceBlocks.NEUDONITE_BLOCK.get());
        this.dropSelf(EunithiceBlocks.SPEED_INFUSED_ASPHALT.get());
        this.dropSelf(EunithiceBlocks.SUPER_SPEED_INFUSED_ASPHALT.get());
        this.dropSelf(EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get());
        this.dropSelf(EunithiceBlocks.HONEY_INFUSED_ASPHALT.get());
        this.dropSelf(EunithiceBlocks.PLATED_IRON_BLOCK.get());
        this.dropSelf(EunithiceBlocks.COMPRESSED_PLATED_IRON_BLOCK.get());
        this.dropSelf(EunithiceBlocks.HEAVY_COMPRESSED_PLATED_IRON_BLOCK.get());
        this.dropSelf(EunithiceBlocks.PLATED_GOLD_BLOCK.get());
        this.dropSelf(EunithiceBlocks.COMPRESSED_PLATED_GOLD_BLOCK.get());
        this.dropSelf(EunithiceBlocks.HEAVY_COMPRESSED_PLATED_GOLD_BLOCK.get());
        this.dropSelf(EunithiceBlocks.DIAMOND_PLATE_BLOCK.get());
        this.dropSelf(EunithiceBlocks.COMPRESSED_DIAMOND_PLATE_BLOCK.get());
        this.dropSelf(EunithiceBlocks.HEAVY_COMPRESSED__DIAMOND_PLATE_BLOCK.get());
        this.dropSelf(EunithiceBlocks.IRON_PLATE_TRAPDOOR.get());
        this.dropSelf(EunithiceBlocks.STONE_TRAPDOOR.get());

        this.add(EunithiceBlocks.NEUDONITE_ORE.get(),
                (block) -> createOreDrop(EunithiceBlocks.NEUDONITE_ORE.get(), EunithiceItems.RAW_NEUDONITE.get()));
        this.add(EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get(),
                (block) -> createOreDrop(EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get(), EunithiceItems.RAW_NEUDONITE.get()));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(EunithiceBlocks.LEURITE.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LeuriteCrop.AGE, 7));
        this.add(EunithiceBlocks.LEURITE.get(), createCropDrops(EunithiceBlocks.LEURITE.get(), EunithiceItems.LEURITE_GRAINS.get(), EunithiceItems.LEURITE_SEEDS.get(), lootitemcondition$builder));
        this.add(EunithiceBlocks.ASPHALT_INFUSER.get(), BlockLoot::createNameableBlockEntityTable);
        this.add(EunithiceBlocks.EXTRACTOR.get(), BlockLoot::createNameableBlockEntityTable);
        this.add(EunithiceBlocks.DISPLAY_CASE.get(), BlockLoot::createNameableBlockEntityTable);
        this.add(EunithiceBlocks.IRON_PLATE_DOOR.get(), BlockLoot::createDoorTable);
        this.add(EunithiceBlocks.STONE_DOOR.get(), BlockLoot::createDoorTable);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return EunithiceBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
