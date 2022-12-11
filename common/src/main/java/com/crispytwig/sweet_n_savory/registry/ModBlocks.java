package com.crispytwig.sweet_n_savory.registry;

import com.crispytwig.sweet_n_savory.SweetSavory;
import com.crispytwig.sweet_n_savory.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.function.Supplier;

import static com.crispytwig.sweet_n_savory.block.properties.ModBlockStateProperties.litBlockEmission;
import static net.minecraft.world.level.block.Blocks.*;

public class ModBlocks {

    public static Supplier<Block> CHEESE_BLOCK = registerBlockOnly("cheese_block", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.HONEY_BLOCK).noOcclusion()));
    public static Supplier<Block> GRAPE_VINES = registerBlockOnly("grape_vines_bottom", () -> new GrapeBlock(BlockBehaviour.Properties.of(Material.PLANT).strength(0.2F).sound(SoundType.GRASS).noOcclusion().noCollission().instabreak().randomTicks()));
    public static Supplier<Block> BOTTOM_GRAPE_VINES = registerBlockOnly("grape_vines", () -> new BottomGrapeBlock(BlockBehaviour.Properties.of(Material.PLANT).strength(0.2F).sound(SoundType.GRASS).noOcclusion().noCollission().instabreak().randomTicks()));
    // public static Supplier<Block> GRAPE_BARREL = registerBlockOnly("grape_barrel", () -> new GrapeBarrelBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static Supplier<Block> CORN_CROP = registerBlockOnly("corn_crop", () -> new CornCropBlock(BlockBehaviour.Properties.of(Material.PLANT).strength(0.2F).sound(SoundType.GRASS).noOcclusion().noCollission().instabreak().randomTicks()));

    public static Supplier<Block> CHOCOLATE_CAKE = registerBlockOnly("chocolate_cake", () -> new ChocolateCakeBlock(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL).noOcclusion()));
    public static Supplier<Block> CHOCOLATE_CANDLE_CAKE = registerBlockOnly("chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(CANDLE, BlockBehaviour.Properties.copy(CAKE).lightLevel(litBlockEmission(3))));

    public static Supplier<Block> WHITE_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("white_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(WHITE_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> ORANGE_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("orange_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(ORANGE_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> MAGENTA_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("magenta_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(MAGENTA_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> LIGHT_BLUE_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("light_blue_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(LIGHT_BLUE_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> YELLOW_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("yellow_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(YELLOW_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> LIME_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("lime_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(LIME_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> PINK_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("pink_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(PINK_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> GRAY_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("gray_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(GRAY_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> LIGHT_GRAY_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("light_gray_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(LIGHT_GRAY_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> CYAN_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("cyan_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(CYAN_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> PURPLE_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("purple_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(PURPLE_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> BLUE_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("blue_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(BLUE_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> BROWN_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("brown_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(BROWN_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> GREEN_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("green_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(GREEN_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> RED_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("red_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(RED_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));
    public static Supplier<Block> BLACK_CHOCOLATE_CANDLE_CAKE = registerBlockOnly("black_chocolate_candle_cake", () -> new ChocolateCandleCakeBlock(BLACK_CANDLE, BlockBehaviour.Properties.copy(CHOCOLATE_CANDLE_CAKE.get())));


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
