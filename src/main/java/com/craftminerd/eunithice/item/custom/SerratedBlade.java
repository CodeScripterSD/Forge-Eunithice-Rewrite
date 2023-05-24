package com.craftminerd.eunithice.item.custom;

import com.craftminerd.eunithice.effect.EunithiceEffects;
import com.mojang.logging.LogUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.slf4j.Logger;

public class SerratedBlade extends SwordItem {

    public SerratedBlade(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        int amplifier = 0;
        if (pTarget.hasEffect(EunithiceEffects.WEAKENED.get())) {
            amplifier =  pTarget.getEffect(EunithiceEffects.WEAKENED.get()).getAmplifier();
            pTarget.hurt(DamageSource.mobAttack(pAttacker), (super.getDamage() *0.2f*(amplifier)));
            amplifier += 1;
            pTarget.removeEffect(EunithiceEffects.WEAKENED.get());
        }
        pTarget.addEffect(new MobEffectInstance(EunithiceEffects.WEAKENED.get(), 40* (amplifier+1), amplifier), pAttacker);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
