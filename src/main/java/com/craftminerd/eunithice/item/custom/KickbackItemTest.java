package com.craftminerd.eunithice.item.custom;

import com.craftminerd.eunithice.Eunithice;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class KickbackItemTest extends Item {
    final Vec3 strength;

    public KickbackItemTest(Properties pProperties, double recoilStrength) {
        super(pProperties);
        strength = new Vec3(recoilStrength, recoilStrength/2, recoilStrength);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.resetFallDistance();
        Propel(pLevel, pPlayer.getLookAngle(), pPlayer, true);
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        Propel(pTarget.level, pAttacker.getLookAngle(), pTarget, false);

        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    void Propel(Level level, Vec3 angle, LivingEntity target, boolean reverse) {
        if (!level.isClientSide) {
            Eunithice.LOGGER.info(angle.toString());
            Eunithice.LOGGER.info("X:" + target.getXRot());
            Eunithice.LOGGER.info("Y:" + target.getYRot());
        }
        Vec3 playerMovement = target.getDeltaMovement();
        Vec3 affectedDir = angle.multiply(strength);
        target.setDeltaMovement(playerMovement.add(reverse ? affectedDir.reverse() : affectedDir));
    }

}
