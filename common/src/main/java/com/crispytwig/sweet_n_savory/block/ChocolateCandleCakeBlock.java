//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.crispytwig.sweet_n_savory.block;

import com.crispytwig.sweet_n_savory.registry.ModBlocks;
import com.crispytwig.sweet_n_savory.registry.ModItems;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChocolateCandleCakeBlock extends AbstractCandleBlock {
    public static final BooleanProperty LIT;
    protected static final float AABB_OFFSET = 1.0F;
    protected static final VoxelShape CAKE_SHAPE;
    protected static final VoxelShape CANDLE_SHAPE;
    protected static final VoxelShape SHAPE;
    private static final Map<Block, ChocolateCandleCakeBlock> BY_CANDLE;
    private static final Iterable<Vec3> PARTICLE_OFFSETS;

    public ChocolateCandleCakeBlock(Block block, BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(LIT, false));
        BY_CANDLE.put(block, this);
    }

    protected Iterable<Vec3> getParticleOffsets(BlockState state) {
        return PARTICLE_OFFSETS;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!itemStack.is(Items.FLINT_AND_STEEL) && !itemStack.is(Items.FIRE_CHARGE)) {
            if (candleHit(hit) && player.getItemInHand(hand).isEmpty() && (Boolean)state.getValue(LIT)) {
                extinguish(player, state, level, pos);
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                InteractionResult interactionResult = ChocolateCakeBlock.eat(level, pos, ModBlocks.CHOCOLATE_CAKE.get().defaultBlockState(), player);
                if (interactionResult.consumesAction()) {
                    dropResources(state, level, pos);
                }

                return interactionResult;
            }
        } else {
            return InteractionResult.PASS;
        }
    }

    private static boolean candleHit(BlockHitResult hit) {
        return hit.getLocation().y - (double)hit.getBlockPos().getY() > 0.5;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.CHOCOLATE_CAKE.get());
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).getMaterial().isSolid();
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return ChocolateCakeBlock.FULL_CAKE_SIGNAL;
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public boolean isPathfindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type) {
        return false;
    }

    public static BlockState byCandle(Block candleBlock) {
        return ((ChocolateCandleCakeBlock)BY_CANDLE.get(candleBlock)).defaultBlockState();
    }

    public static boolean canLight(BlockState state) {
        return state.is(BlockTags.CANDLE_CAKES, (blockStateBase) -> {
            return blockStateBase.hasProperty(LIT) && !(Boolean)state.getValue(LIT);
        });
    }

    static {
        LIT = AbstractCandleBlock.LIT;
        CAKE_SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 8.0, 15.0);
        CANDLE_SHAPE = Block.box(7.0, 8.0, 7.0, 9.0, 14.0, 9.0);
        SHAPE = Shapes.or(CAKE_SHAPE, CANDLE_SHAPE);
        BY_CANDLE = Maps.newHashMap();
        PARTICLE_OFFSETS = ImmutableList.of(new Vec3(0.5, 1.0, 0.5));
    }
}
