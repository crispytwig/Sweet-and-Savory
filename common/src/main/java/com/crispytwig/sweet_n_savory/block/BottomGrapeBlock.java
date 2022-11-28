package com.crispytwig.sweet_n_savory.block;

import com.crispytwig.sweet_n_savory.registry.ModBlocks;
import com.crispytwig.sweet_n_savory.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

import static com.crispytwig.sweet_n_savory.block.GrapeBlock.GRAPES;
import static net.minecraft.world.level.block.CaveVines.SHAPE;
public class BottomGrapeBlock extends GrowingPlantBodyBlock implements BonemealableBlock {
    public BottomGrapeBlock(BlockBehaviour.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
        this.registerDefaultState((BlockState) ((BlockState) this.stateDefinition.any()).setValue(GRAPES, false));
    }

    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) ModBlocks.BOTTOM_GRAPE_VINES.get();
    }

    protected BlockState updateHeadAfterConvertedFromBody(BlockState head, BlockState body) {
        return (BlockState) body.setValue(GRAPES, (Boolean) head.getValue(GRAPES));
    }

    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.GRAPES.get());
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return GrapeBlock.use(state, level, pos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GRAPES);
    }

    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
        return !(Boolean) state.getValue(GRAPES);
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, (BlockState) state.setValue(GRAPES, true), 2);
    }
}
