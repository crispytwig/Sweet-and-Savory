package com.crispytwig.sweet_n_savory.registry;

import com.crispytwig.sweet_n_savory.SweetSavory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;

import java.util.function.Supplier;

import static com.crispytwig.sweet_n_savory.registry.ModRegistry.registerItem;

public class ModItems {

    // Foods
    public static final Supplier<Item> CHOCOLATE_CAKE = registerItem("chocolate_cake", () -> new ItemNameBlockItem(ModBlocks.CHOCOLATE_CAKE.get(), new Item.Properties().tab(SweetSavory.TAB)));
    public static final Supplier<Item> CHOCOLATE_CAKE_SLICE = registerItem("chocolate_cake_slice", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE_CAKE_SLICE).tab(SweetSavory.TAB)));


   public static final Supplier<Item> CHEESE = registerItem("cheese", () -> new ItemNameBlockItem(ModBlocks.CHEESE_BLOCK.get(), new Item.Properties().tab(SweetSavory.TAB)));
    public static final Supplier<Item> CHEESE_SLICE = registerItem("cheese_slice", () -> new Item(new Item.Properties().food(ModFoods.CHEESE).tab(SweetSavory.TAB)));
    public static final Supplier<Item> CHOCOLATE = registerItem("chocolate", () -> new Item(new Item.Properties().food(ModFoods.CHOCOLATE).tab(SweetSavory.TAB)));
    public static final Supplier<Item> GRAPES = registerItem("grapes", () -> new ItemNameBlockItem(ModBlocks.GRAPE_VINES.get(), new Item.Properties().food(ModFoods.GRAPES).tab(SweetSavory.TAB)));
    public static final Supplier<Item> RAISINS = registerItem("raisins", () -> new Item(new Item.Properties().food(ModFoods.RAISINS).tab(SweetSavory.TAB)));
    public static final Supplier<Item> JERKY = registerItem("jerky", () -> new Item(new Item.Properties().food(ModFoods.JERKY).tab(SweetSavory.TAB)));
    public static final Supplier<Item> CORN = registerItem("corn", () -> new Item(new Item.Properties().food(ModFoods.CORN).tab(SweetSavory.TAB)));
    public static final Supplier<Item> PEANUTS = registerItem("peanuts", () -> new Item(new Item.Properties().food(ModFoods.PEANUTS).tab(SweetSavory.TAB)));
    public static final Supplier<Item> POPCORN = registerItem("popcorn", () -> new Item(new Item.Properties().food(ModFoods.POPCORN).tab(SweetSavory.TAB)));

    public static final Supplier<Item> GRAPE_PBJ = registerItem("grape_pbj", () -> new Item(new Item.Properties().food(ModFoods.GRAPE_PBJ).tab(SweetSavory.TAB)));
    public static final Supplier<Item> BERRY_PBJ = registerItem("berry_pbj", () -> new Item(new Item.Properties().food(ModFoods.BERRY_PBJ).tab(SweetSavory.TAB)));
    public static final Supplier<Item> HAM_AND_CHEESE = registerItem("ham_and_cheese", () -> new Item(new Item.Properties().food(ModFoods.HAM_AND_CHEESE).tab(SweetSavory.TAB)));


    // Normal Items
    public static final Supplier<Item> CORN_KERNELS = registerItem("corn_kernels", () -> new ItemNameBlockItem(ModBlocks.CORN_CROP.get(), new Item.Properties().tab(SweetSavory.TAB)));

    public static void init() {}
}
