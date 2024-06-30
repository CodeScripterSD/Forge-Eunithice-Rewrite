package com.craftminerd.eunithice.datagen.loot;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import com.craftminerd.eunithice.block.blocks.LeuriteCrop;
import com.craftminerd.eunithice.item.EunithiceItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

public class EunithiceBlockLootTables extends BlockLoot {
    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };
    @Override
    protected void addTables() {
        this.dropSelf(EunithiceBlocks.DARKWOOD_LOG.get());
        this.dropSelf(EunithiceBlocks.STRIPPED_DARKWOOD_LOG.get());
        this.dropSelf(EunithiceBlocks.DARKWOOD_WOOD.get());
        this.dropSelf(EunithiceBlocks.STRIPPED_DARKWOOD_WOOD.get());
        this.dropSelf(EunithiceBlocks.DARKWOOD_PLANKS.get());
        this.dropSelf(EunithiceBlocks.DARKWOOD_STAIRS.get());
        this.dropSelf(EunithiceBlocks.DARKWOOD_SLAB.get());
        this.dropSelf(EunithiceBlocks.DARKWOOD_FENCE.get());
        this.dropSelf(EunithiceBlocks.DARKWOOD_FENCE_GATE.get());
        this.dropSelf(EunithiceBlocks.DARKWOOD_BUTTON.get());
        this.dropSelf(EunithiceBlocks.DARKWOOD_PRESSURE_PLATE.get());
        this.dropSelf(EunithiceBlocks.DARKWOOD_TRAPDOOR.get());
        this.add(EunithiceBlocks.DARKWOOD_DOOR.get(), BlockLoot::createDoorTable);
        this.dropSelf(EunithiceBlocks.DARKWOOD_SAPLING.get());
        this.add(EunithiceBlocks.DARKWOOD_LEAVES.get(), createLeavesDrops(EunithiceBlocks.DARKWOOD_LEAVES.get(), EunithiceBlocks.DARKWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(EunithiceBlocks.ASPHALT.get());
        this.dropSelf(EunithiceBlocks.RAW_NEUDONITE_BLOCK.get());
        this.dropSelf(EunithiceBlocks.NEUDONITE_BLOCK.get());
        this.dropSelf(EunithiceBlocks.LYMINE_BLOCK.get());
        this.dropSelf(EunithiceBlocks.MYELITE_BLOCK.get());
        this.dropSelf(EunithiceBlocks.SPEED_INFUSED_ASPHALT.get());
        this.dropSelf(EunithiceBlocks.SUPER_SPEED_INFUSED_ASPHALT.get());
        this.dropSelf(EunithiceBlocks.BOUNCE_INFUSED_ASPHALT.get());
        this.dropSelf(EunithiceBlocks.HONEY_INFUSED_ASPHALT.get());
        this.dropSelf(EunithiceBlocks.IRON_PLATE_TRAPDOOR.get());
        this.dropSelf(EunithiceBlocks.STONE_TRAPDOOR.get());

        this.add(EunithiceBlocks.NEUDONITE_ORE.get(),
                (block) -> createOreDrop(EunithiceBlocks.NEUDONITE_ORE.get(), EunithiceItems.RAW_NEUDONITE.get()));
        this.add(EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get(),
                (block) -> createOreDrop(EunithiceBlocks.DEEPSLATE_NEUDONITE_ORE.get(), EunithiceItems.RAW_NEUDONITE.get()));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(EunithiceBlocks.LEURITE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LeuriteCrop.AGE, 7));
        this.add(EunithiceBlocks.LEURITE_CROP.get(), createEunithiceCropDrops(EunithiceBlocks.LEURITE_CROP.get(), EunithiceItems.LEURITE_GRAINS.get(), EunithiceItems.LEURITE_SEEDS.get(), 2, 0.20F, lootitemcondition$builder));
        this.add(EunithiceBlocks.ASPHALT_INFUSER.get(), BlockLoot::createNameableBlockEntityTable);
        this.add(EunithiceBlocks.INFUSER.get(), BlockLoot::createNameableBlockEntityTable);
        this.add(EunithiceBlocks.EXTRACTOR.get(), BlockLoot::createNameableBlockEntityTable);
        this.add(EunithiceBlocks.DISPLAY_CASE.get(), BlockLoot::createNameableBlockEntityTable);
        this.add(EunithiceBlocks.IRON_PLATE_DOOR.get(), BlockLoot::createDoorTable);
        this.add(EunithiceBlocks.STONE_DOOR.get(), BlockLoot::createDoorTable);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return EunithiceBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected static LootTable.Builder createEunithiceCropDrops(Block pCropBlock, Item pGrownCropItem, Item pSeedsItem, int bonus , float chance, LootItemCondition.Builder pDropGrownCropCondition) {
        return applyExplosionDecay(pCropBlock,
                LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(pGrownCropItem).when(pDropGrownCropCondition)
                        .otherwise(LootItem.lootTableItem(pSeedsItem))))
                        .withPool(LootPool.lootPool().when(pDropGrownCropCondition).add(LootItem.lootTableItem(pSeedsItem).apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, chance, bonus)))));
    }
}
