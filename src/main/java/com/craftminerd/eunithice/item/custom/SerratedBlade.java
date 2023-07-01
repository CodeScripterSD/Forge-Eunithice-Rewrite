package com.craftminerd.eunithice.item.custom;

import com.craftminerd.eunithice.effect.EunithiceEffects;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SerratedBlade extends SwordItem {
    private final float attackdamage;

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public SerratedBlade(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.attackdamage = pAttackDamageModifier + pTier.getAttackDamageBonus();
        Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackdamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)pAttackSpeedModifier, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
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
        pTarget.addEffect(new MobEffectInstance(EunithiceEffects.WEAKENED.get(), (1/(amplifier+1))*200, amplifier+0), pAttacker); //60* 1/(amplifier+1)
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }
}
