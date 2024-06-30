package com.craftminerd.eunithice.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

//@Mod.EventBusSubscriber(modid = Eunithice.MODID)
public class SerratedBlade extends AbstractSerratedBlade {
    public SerratedBlade(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public double getDamageAddedFromKills(int kills) {
        return Math.pow(kills, 1 / Math.cbrt(2)) / Math.sqrt(4); // \frac{x^{\frac{1}{\sqrt[3]{2}}}}{\sqrt{4}}
    }

    // Still undecided whether hurtEnemy is better than killEntityEvent
    // Not fully tested the extent of isDeadOrDying

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        boolean toReturn = super.hurtEnemy(pStack, pTarget, pAttacker);

        if (!pAttacker.getLevel().isClientSide() && pTarget.isDeadOrDying())
            this.addKill(pStack);

        return toReturn;
    }

//    @SubscribeEvent
//    public void killEntityEvent(LivingDeathEvent event) {
//        Entity damageSource = event.getSource().getEntity();
//        if (damageSource instanceof Player player) {
//            ItemStack stack = player.getItemInHand(player.getUsedItemHand());
//            if (stack.getItem() instanceof AbstractSerratedBlade blade) {
//                blade.addStack(stack);
//            }
//        }
//    }

}
