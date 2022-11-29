package com.crispytwig.sweet_n_savory.block.properties;

import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;

import java.util.function.ToIntFunction;

public class ModBlockStateProperties extends BlockStateProperties {
    public static final BooleanProperty GRAPES = BooleanProperty.create("grapes");
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (blockState) -> {
            return (Boolean)blockState.getValue(BlockStateProperties.LIT) ? lightValue : 0;
        };
    }

}