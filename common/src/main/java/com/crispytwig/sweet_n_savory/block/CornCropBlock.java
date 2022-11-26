package com.crispytwig.sweet_n_savory.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class CornCropBlock extends CropBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

    public CornCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER).setValue(this.getAgeProperty(), Integer.valueOf(0)));

    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 3;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        return blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(context) ? super.getStateForPlacement(context) : null;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState blockState, LevelAccessor level, BlockPos blockPos, BlockPos blockPos1) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (direction.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || blockState.is(this) && blockState.getValue(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(level, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, blockState, level, blockPos, blockPos1);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getRawBrightness(pos, 0) >= 9) {
            if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
                int i = this.getAge(state);
                if (i < this.getMaxAge()) {
                    float f = getGrowthSpeed(this, level, pos);
                    if (random.nextInt((int) (25.0F / f) + 1) == 0) {
                        level.setBlock(pos, this.getStateForAge(i + 1), 2);
                    }
                }
            }
        }
    }

        public void growCrops (Level level, BlockPos blockPos, BlockState blockState){
            int i = this.getAge(blockState) + this.getBonemealAgeIncrease(level);
            int j = this.getMaxAge();
            if (i > j) {
                i = j;
            }

            level.setBlock(blockPos, this.getStateForAge(i), 2);
            if (i >= 2) {
                placeTop(level, blockState, blockPos, i);
            }
        }

        @Override
        public boolean canSurvive (BlockState blockState, LevelReader levelReader, BlockPos blockPos){
            if (blockState.getValue(HALF) != DoubleBlockHalf.UPPER) {
                return super.canSurvive(blockState, levelReader, blockPos);
            } else {
                BlockState blockstate = levelReader.getBlockState(blockPos.below());
                if (blockState.getBlock() != this)
                    return super.canSurvive(blockState, levelReader, blockPos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
                return blockstate.is(this) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER;
            }
        }

        public void playerWillDestroy (Level level, BlockPos blockPos, BlockState blockState, Player player){
            if (!level.isClientSide) {
                if (player.isCreative()) {
                    preventCreativeDropFromBottomPart(level, blockPos, blockState, player);
                } else {
                    dropResources(blockState, level, blockPos, (BlockEntity) null, player, player.getMainHandItem());
                }
            }

            super.playerWillDestroy(level, blockPos, blockState, player);
        }

        public void playerDestroy (Level level, Player player, BlockPos blockPos, BlockState
        blockState, @javax.annotation.Nullable BlockEntity blockEntity, ItemStack itemStack){
            super.playerDestroy(level, player, blockPos, Blocks.AIR.defaultBlockState(), blockEntity, itemStack);
        }

        protected static void preventCreativeDropFromBottomPart (Level level, BlockPos blockPos, BlockState
        blockState, Player player){
            DoubleBlockHalf doubleblockhalf = blockState.getValue(HALF);
            if (doubleblockhalf == DoubleBlockHalf.UPPER) {
                BlockPos blockpos = blockPos.below();
                BlockState blockstate = level.getBlockState(blockpos);
                if (blockstate.is(blockState.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                    BlockState blockstate1 = blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && blockstate.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                    level.setBlock(blockpos, blockstate1, 35);
                    level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
                }
            }

        }

        public long getSeed (BlockState blockState, BlockPos blockPos){
            return Mth.getSeed(blockPos.getX(), blockPos.below(blockState.getValue(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), blockPos.getZ());
        }

        public void placeTop (Level level, BlockState state, BlockPos pos,int stage){
            level.setBlock(pos.above(), this.getStateForAge(stage).setValue(HALF, DoubleBlockHalf.UPPER), 2);
        }

        @Override
        public float getMaxHorizontalOffset () {
            return 0;
        }

        protected int getBonemealAgeIncrease (Level level){
            return super.getBonemealAgeIncrease(level) / 3;
        }

        protected void createBlockStateDefinition (StateDefinition.Builder < Block, BlockState > builder){
            builder.add(AGE, HALF);
        }

        public VoxelShape getShape (BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext
        context){
            DoubleBlockHalf doubleblockhalf = blockState.getValue(HALF);
            if (doubleblockhalf == DoubleBlockHalf.LOWER) {
                return SHAPE_BY_AGE[blockState.getValue(this.getAgeProperty())];
            } else {
                return SHAPE_BY_AGE[Mth.clamp(blockState.getValue(this.getAgeProperty()) - 2, 0, this.getMaxAge())];
            }
        }
    }
