package com.crispytwig.sweet_n_savory.registry;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CHEESE = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties CHOCOLATE = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).build();
    public static final FoodProperties CORN = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.6F).build();
    public static final FoodProperties GRAPES = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).fast().build();
    public static final FoodProperties RAISINS = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.4F).fast().build();
    public static final FoodProperties JERKY = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).build();
    public static final FoodProperties PEANUTS = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.3F).build();
    public static final FoodProperties POPCORN = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.1F).fast().build();

    public static final FoodProperties GRAPE_PBJ = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();
    public static final FoodProperties BERRY_PBJ = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();
    public static final FoodProperties HAM_AND_CHEESE = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.6F).build();
}
