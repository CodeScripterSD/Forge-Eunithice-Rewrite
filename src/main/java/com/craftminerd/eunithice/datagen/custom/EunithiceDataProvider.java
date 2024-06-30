package com.craftminerd.eunithice.datagen.custom;

import com.craftminerd.eunithice.enchantments.EunithiceEnchantments;
import com.craftminerd.eunithice.event.loot.AutoSmeltWithEnchantmentAdditionModifier;
import com.craftminerd.eunithice.event.loot.EunithiceGlobalLootModifiers;
import net.minecraft.advancements.critereon.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class EunithiceDataProvider extends GlobalLootModifierProvider {
    public EunithiceDataProvider(DataGenerator gen, String modid) {
        super(gen, modid);
    }

    @Override
    protected void start() {
        add("smelting_block_drop", EunithiceGlobalLootModifiers.SMELTING.get(), new AutoSmeltWithEnchantmentAdditionModifier(
                new LootItemCondition[]{
                        MatchTool.toolMatches(
                                ItemPredicate.Builder.item().hasEnchantment(
                                        new EnchantmentPredicate(EunithiceEnchantments.SMELTING.get(), MinMaxBounds.Ints.atLeast(1))))
                                .build()
                })
        );
        add("smelting_entity_drop", EunithiceGlobalLootModifiers.SMELTING.get(), new AutoSmeltWithEnchantmentAdditionModifier(
                new LootItemCondition[]{
                        DamageSourceCondition.hasDamageSource(
                                DamageSourcePredicate.Builder.damageType().source(
                                        EntityPredicate.Builder.entity().equipment(
                                                EntityEquipmentPredicate.Builder.equipment().mainhand(
                                                        ItemPredicate.Builder.item().hasEnchantment(
                                                                new EnchantmentPredicate(EunithiceEnchantments.SMELTING.get(), MinMaxBounds.Ints.atLeast(1)))
                                                                .build())
                                                        .build())
                                                .build()))
                                .build()
                })
        );
    }
}
