package com.crispytwig.sweet_n_savory.registry;

import com.crispytwig.sweet_n_savory.SweetSavory;
import com.crispytwig.sweet_n_savory.block.BottomGrapeBlock;
import com.crispytwig.sweet_n_savory.block.CornCropBlock;
import com.crispytwig.sweet_n_savory.block.GrapeBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

public class ModBlocks {

    public static Supplier<Block> CHEESE_BLOCK = registerBlock("cheese_block", () -> new Block(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.HONEY_BLOCK).noOcclusion()));
    public static Supplier<Block> GRAPE_VINES = registerBlockOnly("grape_vines", () -> new GrapeBlock(BlockBehaviour.Properties.of(Material.PLANT).strength(0.2F).sound(SoundType.GRASS).noOcclusion().noCollission().instabreak().randomTicks()));
    public static Supplier<Block> BOTTOM_GRAPE_VINES = registerBlockOnly("grape_vines_bottom", () -> new BottomGrapeBlock(BlockBehaviour.Properties.of(Material.PLANT).strength(0.2F).sound(SoundType.GRASS).noOcclusion().noCollission().instabreak().randomTicks()));
    public static Supplier<Block> CORN_CROP = registerBlockOnly("corn_crop", () -> new CornCropBlock(BlockBehaviour.Properties.of(Material.PLANT).strength(0.2F).sound(SoundType.GRASS).noOcclusion().noCollission().instabreak().randomTicks()));

    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        Supplier<T> supplier = ModRegistry.registerBlock(name, block);
        ModRegistry.registerItem(name, () -> new BlockItem(supplier.get(), new Item.Properties().tab(SweetSavory.TAB)));
        return supplier;
    }

    public static <T extends Block> Supplier<T> registerBlockHidden(String name, Supplier<T> block) {
        Supplier<T> supplier = ModRegistry.registerBlock(name, block);
        ModRegistry.registerItem(name, () -> new BlockItem(supplier.get(), new Item.Properties()));
        return supplier;
    }

    public static <T extends Block> Supplier<T> registerBlockOnly(String name, Supplier<T> block) {
        return ModRegistry.registerBlock(name, block);
    }

    public static void init() {}
}
