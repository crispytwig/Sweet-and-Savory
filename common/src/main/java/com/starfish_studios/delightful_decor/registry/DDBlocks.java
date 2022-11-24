package com.starfish_studios.delightful_decor.registry;

import com.starfish_studios.delightful_decor.DelightfulDecor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class DDBlocks {


    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        Supplier<T> supplier = DDRegistry.registerBlock(name, block);
        DDRegistry.registerItem(name, () -> new BlockItem(supplier.get(), new Item.Properties().tab(DelightfulDecor.TAB)));
        return supplier;
    }

    public static <T extends Block> Supplier<T> registerBlockHidden(String name, Supplier<T> block) {
        Supplier<T> supplier = DDRegistry.registerBlock(name, block);
        DDRegistry.registerItem(name, () -> new BlockItem(supplier.get(), new Item.Properties()));
        return supplier;
    }

    public static <T extends Block> Supplier<T> registerBlockOnly(String name, Supplier<T> block) {
        return DDRegistry.registerBlock(name, block);
    }

    public static void init() {}
}
