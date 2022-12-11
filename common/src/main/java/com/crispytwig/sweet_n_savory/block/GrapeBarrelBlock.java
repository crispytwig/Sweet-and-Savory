package com.crispytwig.sweet_n_savory.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.WorldlyContainerHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GrapeBarrelBlock extends Block implements WorldlyContainerHolder {
    /* private static final VoxelShape OUTER_SHAPE = Shapes.box(0, 0, 0, 1, 0.5, 1);
    private static final VoxelShape INNER_SHAPE = Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.5, 0.9375);
    private static final VoxelShape SHAPES = Shapes.or(OUTER_SHAPE, INNER_SHAPE);
*/
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {

        return Shapes.join(box(0, 0, 0, 16, 8, 16), box(1, 3, 1, 15, 8, 15), BooleanOp.ONLY_FIRST);
    }

    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.playSound(SoundEvents.HONEY_BLOCK_SLIDE, 1.0F, 1.0F);
    }

    public GrapeBarrelBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public WorldlyContainer getContainer(BlockState state, LevelAccessor level, BlockPos pos) {
        return null;
    }
}
