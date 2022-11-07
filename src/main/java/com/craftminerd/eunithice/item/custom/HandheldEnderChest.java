package com.craftminerd.eunithice.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HandheldEnderChest extends Item {
    private static final Component CONTAINER_TITLE = new TranslatableComponent("container.eunithice.handheld_ender_chest");

    public HandheldEnderChest(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        PlayerEnderChestContainer playerenderchestcontainer = pPlayer.getEnderChestInventory();
        if (playerenderchestcontainer != null) {
            if (pLevel.isClientSide()) {
                return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
            } else {
                pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ENDER_CHEST_OPEN, SoundSource.PLAYERS, (float) 0.5, 1);
                pPlayer.openMenu(new SimpleMenuProvider((p_53124_, p_53125_, p_53126_) -> {
                    return ChestMenu.threeRows(p_53124_, p_53125_, playerenderchestcontainer);
                }, CONTAINER_TITLE));
                pPlayer.awardStat(Stats.OPEN_ENDERCHEST);
                return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
            }
        } else {
            return InteractionResultHolder.sidedSuccess(pPlayer.getItemInHand(pUsedHand), true);
        }
    }
}
