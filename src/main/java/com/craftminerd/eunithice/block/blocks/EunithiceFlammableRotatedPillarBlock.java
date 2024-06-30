package com.craftminerd.eunithice.block.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class EunithiceFlammableRotatedPillarBlock extends RotatedPillarBlock {
    Supplier<Block> strippedState;
    public EunithiceFlammableRotatedPillarBlock(Properties pProperties, Supplier<Block> stateSupplier) {
        super(pProperties);
        this.strippedState = stateSupplier;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        return strippedState != null && toolAction == ToolActions.AXE_STRIP ? strippedState.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS)) : null;
    }
}
