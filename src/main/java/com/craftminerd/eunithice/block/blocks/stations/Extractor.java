package com.craftminerd.eunithice.block.blocks.stations;

import com.craftminerd.eunithice.Eunithice;
import com.craftminerd.eunithice.block.blockentities.EunithiceBlockEntities;
import com.craftminerd.eunithice.block.blockentities.stations.ExtractorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Extractor extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final Property<Boolean> ACTIVE = BooleanProperty.create("active");

    public Extractor(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(ACTIVE, false);
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor level, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING).add(ACTIVE);
    }

//    @Override
//    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
//        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
//        if (blockEntity instanceof ExtractorBlockEntity) {
//            ExtractorBlockEntity extractorBlockEntity = (ExtractorBlockEntity) blockEntity;
////            if (!pLevel.isClientSide && pPlayer.isCreative() && !extractorBlockEntity.isEmpty()) {
////
////            }
//            if (!pLevel.isClientSide) {
//                extractorBlockEntity.unpackLootTable()
//            }
//        }
//
//        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
//    }


    @Override
    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        BlockEntity blockEntity = pBuilder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof ExtractorBlockEntity) {
            ExtractorBlockEntity extractorBlockEntity = (ExtractorBlockEntity) blockEntity;
            pBuilder = pBuilder.withDynamicDrop(new ResourceLocation(Eunithice.MODID, "contents"), (lootContext, itemStackConsumer) -> {

            });
        }

        return super.getDrops(pState, pBuilder);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof ExtractorBlockEntity) {
                ((ExtractorBlockEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof ExtractorBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer)pPlayer), (ExtractorBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ExtractorBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return createTickerHelper(pBlockEntityType, EunithiceBlockEntities.EXTRACTOR_BLOCK_ENTITY.get(), ExtractorBlockEntity::tick);
    }
}
