package com.craftminerd.eunithice.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractSerratedBlade extends SwordItem {
    protected final float attackDamage;
    private final float attackSpeedModifier;

    public AbstractSerratedBlade(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.attackDamage = pAttackDamageModifier + pTier.getAttackDamageBonus();
        this.attackSpeedModifier = pAttackSpeedModifier;
    }

    public void damageAction(ItemStack stack, DamageSource damageSource) {};
    public abstract double getDamageAddedFromKills(int kills);

    public void addKill(ItemStack stack) {
        CompoundTag tag = stack.getTag();
        if (tag == null || !tag.contains("kills")) {
            stack.getOrCreateTag().putInt("kills", 1);
        } else {
            tag.putInt("kills", getKills(stack) + 1);
        }
    }



    public int getKills(ItemStack stack) {
        if (stack.getOrCreateTag().contains("kills")) {
            return stack.getTag().getInt("kills");
        }
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("tooltip.eunithice.damage_increase_per_kill"));
        pTooltipComponents.add(TextComponent.EMPTY);

        // Could change to be all internal, but I'd like to give command-using players the ability to set the amount of damage stacks in-game
        pTooltipComponents.add(new TranslatableComponent("tooltip.eunithice.kills_counter", new TextComponent(String.valueOf(getKills(pStack))).withStyle(ChatFormatting.GRAY)));
//        pTooltipComponents.add(new TextComponent("Kills: " + getKills(pStack)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        int kills = 0;
        if (stack.getItem() instanceof AbstractSerratedBlade blade) {
            kills = blade.getKills(stack);
        }

        Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage + this.getDamageAddedFromKills(kills), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)attackSpeedModifier, AttributeModifier.Operation.ADDITION));


        return slot == EquipmentSlot.MAINHAND ? builder.build() : super.getAttributeModifiers(slot, stack);
    }
}
