package com.craftminerd.eunithice.item.custom;

import com.craftminerd.eunithice.enchantments.EunithiceEnchantments;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomBowItem extends BowItem {
    public CustomBowItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        return;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 0;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.NONE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        boolean hasAmmo = !pPlayer.getProjectile(itemstack).isEmpty();

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, pLevel, pPlayer, pHand, hasAmmo);
        if (ret != null) return ret;

        if (!pPlayer.getAbilities().instabuild && !hasAmmo) {
            return InteractionResultHolder.fail(itemstack);
        } else {
            ItemStack bowstack = pPlayer.getItemInHand(pHand);
            boolean flag = pPlayer.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bowstack) > 0;
            ItemStack ammostack = pPlayer.getProjectile(bowstack);

            int i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bowstack, pLevel, pPlayer, 25, !ammostack.isEmpty() || flag);
            if (i < 0) return InteractionResultHolder.consume(bowstack);

            if (!ammostack.isEmpty() || flag) {
                if (ammostack.isEmpty()) {
                    ammostack = new ItemStack(Items.ARROW);
                }

                float f = getPowerForTime(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = pPlayer.getAbilities().instabuild || (ammostack.getItem() instanceof ArrowItem && ((ArrowItem)ammostack.getItem()).isInfinite(ammostack, bowstack, pPlayer));
                    if (!pLevel.isClientSide) {
                        ArrowItem arrowitem = (ArrowItem)(ammostack.getItem() instanceof ArrowItem ? ammostack.getItem() : Items.ARROW);
                        AbstractArrow abstractarrow = arrowitem.createArrow(pLevel, ammostack, pPlayer);
                        abstractarrow = customArrow(abstractarrow);
//                        abstractarrow.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, f * 3.0F, 1.0F);
                        shootFromRotation(abstractarrow, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            abstractarrow.setCritArrow(true);
                        }

                        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowstack);
                        if (j > 0) {
                            abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bowstack);
                        if (k > 0) {
                            abstractarrow.setKnockback(k);
                        }

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bowstack) > 0) {
                            abstractarrow.setSecondsOnFire(100);
                        }

                        bowstack.hurtAndBreak(1, pPlayer, (p_40665_) -> {
                            p_40665_.broadcastBreakEvent(pPlayer.getUsedItemHand());
                        });
                        if (flag1 || pPlayer.getAbilities().instabuild && (ammostack.is(Items.SPECTRAL_ARROW) || ammostack.is(Items.TIPPED_ARROW))) {
                            abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        pLevel.addFreshEntity(abstractarrow);
                    }

                    pLevel.playSound((Player)null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !pPlayer.getAbilities().instabuild) {
                        ammostack.shrink(1);
                        if (ammostack.isEmpty()) {
                            pPlayer.getInventory().removeItem(ammostack);
                        }
                    }

                    pPlayer.awardStat(Stats.ITEM_USED.get(this));
                }
            }
            pPlayer.getCooldowns().addCooldown(this, getCooldownDuration(bowstack));
            return InteractionResultHolder.consume(itemstack);
        }
    }

    private int getCooldownDuration(ItemStack bowstack) {
        int enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(EunithiceEnchantments.FIRING_SPEED.get(), bowstack);

        return Math.max(20 - (enchantLevel * 5), 0);
    }


    // Custom shootFromRotation method to remove the player's movement from the arrow
    private void shootFromRotation(AbstractArrow arrow, float pX, float pY, float pZ, float pVelocity, float pInaccuracy) {
        float f = -Mth.sin(pY * ((float)Math.PI / 180F)) * Mth.cos(pX * ((float)Math.PI / 180F));
        float f1 = -Mth.sin((pX + pZ) * ((float)Math.PI / 180F));
        float f2 = Mth.cos(pY * ((float)Math.PI / 180F)) * Mth.cos(pX * ((float)Math.PI / 180F));
        arrow.shoot((double)f, (double)f1, (double)f2, pVelocity, pInaccuracy);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("tooltip.eunithice.shortbow"));
        if (pStack.isEnchanted()) pTooltipComponents.add(new TextComponent(" "));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
