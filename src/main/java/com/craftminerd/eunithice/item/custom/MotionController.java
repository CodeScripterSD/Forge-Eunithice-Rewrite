package com.craftminerd.eunithice.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MotionController extends Item {
    public MotionController(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack heldItem = pPlayer.getItemInHand(pUsedHand);
        if (!pPlayer.isShiftKeyDown()) {
            Vec3 vec3 = pPlayer.getDeltaMovement();
            pPlayer.setDeltaMovement(-vec3.x, -vec3.y, -vec3.z);
            return InteractionResultHolder.success(heldItem);
        }
        return super.use(pLevel, pPlayer, pUsedHand);

    }




}
