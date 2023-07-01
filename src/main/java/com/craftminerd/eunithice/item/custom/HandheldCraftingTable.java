package com.craftminerd.eunithice.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class HandheldCraftingTable extends Item {
    private static final Component CONTAINER_TITLE = new TranslatableComponent("container.eunithice.handheld_crafting_table");

    public HandheldCraftingTable(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide) {
            return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
        } else {
            pPlayer.openMenu(new SimpleMenuProvider((p_53124_, p_53125_, p_53126_) -> {
                return new CraftingMenu(p_53124_, p_53125_, ContainerLevelAccess.create(pLevel, new BlockPos(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ()))) {
                    @Override
                    public boolean stillValid(Player pPlayer) {
                        return true;
                    }
                };
            }, CONTAINER_TITLE));
//            pPlayer.openMenu(Blocks.CRAFTING_TABLE.defaultBlockState().getMenuProvider(pLevel, new BlockPos(pPlayer.position())));
            pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
        }
    }
}
