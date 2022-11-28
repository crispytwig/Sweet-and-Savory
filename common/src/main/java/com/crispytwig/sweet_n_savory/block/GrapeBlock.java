package com.crispytwig.sweet_n_savory.block;

import com.crispytwig.sweet_n_savory.block.properties.ModBlockStateProperties;
import com.crispytwig.sweet_n_savory.registry.ModBlocks;
import com.crispytwig.sweet_n_savory.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.CaveVines.SHAPE;

public class GrapeBlock extends GrowingPlantHeadBlock implements BonemealableBlock {
    private static final float CHANCE_OF_GRAPES_ON_GROWTH = 0.11F;

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return (Integer)state.getValue(AGE) < 0;
    }

    public static IntegerProperty AGE = null;
    public static final int MAX_AGE = 0;

    public GrapeBlock(Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(GRAPES, false)));
    }

    static BooleanProperty GRAPES = ModBlockStateProperties.GRAPES;

    static InteractionResult use(BlockState state, Level level, BlockPos pos) {
        if ((Boolean)state.getValue(GRAPES)) {
            Block.popResource(level, pos, new ItemStack(ModItems.GRAPES.get(), 1));
            float f = Mth.randomBetween(level.random, 0.8F, 1.2F);
            level.playSound((Player)null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
            level.setBlock(pos, (BlockState)state.setValue(GRAPES, false), 2);
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }
    
    protected int getBlocksToGrowWhenBonemealed(RandomSource random) {
        return 1;
    }

    protected boolean canGrowInto(BlockState state) {
        return state.isAir();
    }


    protected Block getBodyBlock() {
        return Blocks.CAVE_VINES_PLANT;
    }

    protected BlockState updateBodyAfterConvertedFromHead(BlockState head, BlockState body) {
        return (BlockState)body.setValue(GRAPES, (Boolean)head.getValue(GRAPES));
    }

    protected BlockState getGrowIntoState(BlockState state, RandomSource random) {
        return (BlockState)super.getGrowIntoState(state, random).setValue(GRAPES, random.nextFloat() < 0.11F);
    }

    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.GRAPES.get());
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        return GrapeBlock.use(state, level, pos);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(GRAPES);
    }

    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
        return !(Boolean)state.getValue(GRAPES);
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, (BlockState)state.setValue(GRAPES, true), 2);
    }
}