package com.craftminerd.eunithice.item.custom;

import com.craftminerd.eunithice.block.EunithiceBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GelItem extends Item {
    private final Block gelType;
    public GelItem(Properties pProperties, Block gelType) {
        super(pProperties);
        this.gelType = gelType;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        BlockPos posClicked = pContext.getClickedPos();
        BlockState blockClicked = level.getBlockState(pContext.getClickedPos());
        if (blockClicked.getBlock() == EunithiceBlocks.ASPHALT.get()) {
            level.setBlock(posClicked, gelType.defaultBlockState(), 3);
            level.playSound(player, posClicked, SoundEvents.SLIME_SQUISH, SoundSource.BLOCKS, 0.5f, 1);
            pContext.getItemInHand().hurtAndBreak(1, player, (p_41300_) -> {
                p_41300_.broadcastBreakEvent(pContext.getHand());
            });
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    protected ParticleOptions getParticleType() {
        return ParticleTypes.ITEM_SLIME;
    }
}
