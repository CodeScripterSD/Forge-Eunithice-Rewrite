package com.craftminerd.eunithice.enchantments;

import com.mojang.logging.LogUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import org.slf4j.Logger;

import java.util.Map;
import java.util.Random;

public class WitheredThornsEnchantment extends Enchantment {
    protected WitheredThornsEnchantment(Rarity pRarity, EquipmentSlot... pApplicableSlots) {
        super(pRarity, EnchantmentCategory.ARMOR_CHEST, pApplicableSlots);
    }

    @Override
    public int getMinCost(int pLevel) {
        return 10 + 20 * (pLevel - 1);
    }

    @Override
    public int getMaxCost(int pLevel) {
        return this.getMinCost(pLevel) + 10;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    @Override
    public void doPostHurt(LivingEntity pUser, Entity pAttacker, int pLevel) {
        Random random = pUser.getRandom();
        Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.getRandomItemWith(EunithiceEnchantments.WITHERED_THORNS.get(), pUser);
        if (shouldApplyEffect(pLevel, random)) {
            if (pAttacker != null && pAttacker instanceof LivingEntity) {
                ((LivingEntity)pAttacker).addEffect(new MobEffectInstance(MobEffects.WITHER, getDurationOfEffect(pLevel, random) + pLevel * 20, pLevel - 1));
                if (shouldApplyEffect(pLevel - 1, random)) {
                    int duration = getDurationOfEffect(pLevel, random);
                    if (duration >= 20)
                        pUser.addEffect(new MobEffectInstance(MobEffects.WITHER, duration, 0));
                }
            }
            if (entry != null) {
                entry.getValue().hurtAndBreak(2, pUser, (p_45208_) -> {
                    p_45208_.broadcastBreakEvent(entry.getKey());
                });
            }
        }
    }

    public static int getDurationOfEffect(int pLevel, Random pRnd) {
        return pLevel > 10 ? (pLevel - 10) * 20 : ((pLevel + (1 + pRnd.nextInt(4)) * 20));
    }

    private static boolean shouldApplyEffect(int pLevel, Random pRnd) {
        if (pLevel <= 0) {
            return false;
        } else {
            return pRnd.nextFloat() < 0.2F * (float)pLevel;
        }
    }

    public boolean checkCompatibility(Enchantment enchantment) {
        return !(enchantment instanceof ThornsEnchantment);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }
}
