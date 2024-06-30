package com.craftminerd.eunithice.event.loot;

import com.craftminerd.eunithice.Eunithice;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoSmeltWithEnchantmentAdditionModifier extends LootModifier {

    public AutoSmeltWithEnchantmentAdditionModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        ArrayList<ItemStack> itemList = new ArrayList<>();
        generatedLoot.forEach((stack) -> itemList.add(getSmeltedResult(stack, context)));
        return itemList;
    }

    private static ItemStack getSmeltedResult(ItemStack stack, LootContext context) {
        return context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(stack), context.getLevel())
                .map(SmeltingRecipe::getResultItem)
                .filter(itemStack -> !itemStack.isEmpty())
                .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                .orElse(stack);
    }

    public static class Serializer extends GlobalLootModifierSerializer<AutoSmeltWithEnchantmentAdditionModifier> {
        @Override
        public AutoSmeltWithEnchantmentAdditionModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] conditionsIn) {
            return new AutoSmeltWithEnchantmentAdditionModifier(conditionsIn);
        }

        @Override
        public JsonObject write(AutoSmeltWithEnchantmentAdditionModifier instance) {
            return makeConditions(instance.conditions);
        }
    }
}
