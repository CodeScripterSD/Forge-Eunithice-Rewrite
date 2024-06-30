package com.craftminerd.eunithice.item.custom;

import com.craftminerd.eunithice.util.EunithiceTags;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class OmnitoolItem extends DiggerItem {
    public OmnitoolItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pAttackDamageModifier, pAttackSpeedModifier, pTier, EunithiceTags.Blocks.MINEABLE_MULTITOOL, pProperties.defaultDurability(pTier.getUses()*4));
    }
    public OmnitoolItem(Tier pTier, Properties pProperties) {
        super(3.0f, -3.4f, pTier, EunithiceTags.Blocks.MINEABLE_MULTITOOL, pProperties.defaultDurability(pTier.getUses()*4));
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        Player player = pContext.getPlayer();
        BlockState blockstate = level.getBlockState(blockpos);
        Optional<BlockState> optional = Optional.ofNullable(blockstate.getToolModifiedState(pContext, net.minecraftforge.common.ToolActions.AXE_STRIP, false));
        Optional<BlockState> optional1 = optional.isPresent() ? Optional.empty() : Optional.ofNullable(blockstate.getToolModifiedState(pContext, net.minecraftforge.common.ToolActions.AXE_SCRAPE, false));
        Optional<BlockState> optional2 = optional.isPresent() || optional1.isPresent() ? Optional.empty() : Optional.ofNullable(blockstate.getToolModifiedState(pContext, net.minecraftforge.common.ToolActions.AXE_WAX_OFF, false));
        Optional<BlockState> optionalHoe = ((!player.isCrouching()) || (optional.isPresent() || optional1.isPresent() || optional2.isPresent())) ? Optional.empty() : Optional.ofNullable(blockstate.getToolModifiedState(pContext, ToolActions.HOE_TILL, false));
        Optional<BlockState> optionalShovel = optional.isPresent() || optional1.isPresent() || optional2.isPresent() || optionalHoe.isPresent() ? Optional.empty() : Optional.ofNullable(blockstate.getToolModifiedState(pContext, net.minecraftforge.common.ToolActions.SHOVEL_FLATTEN, false));
        ItemStack itemstack = pContext.getItemInHand();
        Optional<BlockState> optional3 = Optional.empty();
        if (optional.isPresent()) {
            level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            optional3 = optional;
        } else if (optional1.isPresent()) {
            level.playSound(player, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3005, blockpos, 0);
            optional3 = optional1;
        } else if (optional2.isPresent()) {
            level.playSound(player, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.levelEvent(player, 3004, blockpos, 0);
            optional3 = optional2;
        } else if (optionalHoe.isPresent()) {
            Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = Pair.of(ctx -> true, changeIntoState(optionalHoe.get()));
            Predicate<UseOnContext> predicate = pair.getFirst();
            Consumer<UseOnContext> consumer = pair.getSecond();
            if (predicate.test(pContext)) {
                level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide) {
                    consumer.accept(pContext);
                    if (player != null) {
                        pContext.getItemInHand().hurtAndBreak(1, player, (p_150845_) -> {
                            p_150845_.broadcastBreakEvent(pContext.getHand());
                        });
                    }
                }
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.PASS;
            }
        } else if (optionalShovel.isPresent()) {
            if (pContext.getClickedFace() == Direction.DOWN) {
                return InteractionResult.PASS;
            } else {
                BlockState blockstate1 = optionalShovel.get();
                BlockState blockstate2 = null;
                if (level.isEmptyBlock(blockpos.above())) {
                    level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    blockstate2 = blockstate1;
                } else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
                    if (!level.isClientSide()) {
                        level.levelEvent((Player)null, 1009, blockpos, 0);
                    }

                    CampfireBlock.dowse(pContext.getPlayer(), level, blockpos, blockstate);
                    blockstate2 = blockstate.setValue(CampfireBlock.LIT, Boolean.valueOf(false));
                }

                if (blockstate2 != null) {
                    if (!level.isClientSide) {
                        level.setBlock(blockpos, blockstate2, 11);
                        if (player != null) {
                            pContext.getItemInHand().hurtAndBreak(1, player, (p_43122_) -> {
                                p_43122_.broadcastBreakEvent(pContext.getHand());
                            });
                        }
                    }

                    return InteractionResult.sidedSuccess(level.isClientSide);
                } else {
                    return InteractionResult.PASS;
                }
            }
        }

        if (optional3.isPresent()) {
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, blockpos, itemstack);
            }

            level.setBlock(blockpos, optional3.get(), 11);
            if (player != null) {
                itemstack.hurtAndBreak(1, player, (p_150686_) -> {
                    p_150686_.broadcastBreakEvent(pContext.getHand());
                });
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    public static Consumer<UseOnContext> changeIntoState(BlockState pState) {
        return (p_150848_) -> {
            p_150848_.getLevel().setBlock(p_150848_.getClickedPos(), pState, 11);
        };
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SHOVEL_ACTIONS.contains(toolAction)
                || net.minecraftforge.common.ToolActions.DEFAULT_HOE_ACTIONS.contains(toolAction)
                || net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction)
                || net.minecraftforge.common.ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction);
    }
}
