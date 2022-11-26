package com.crispytwig.sweet_n_savory;

import com.crispytwig.sweet_n_savory.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SweetSavory {
    public static final String MOD_ID = "sweet_n_savory";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final CreativeModeTab TAB = ModRegistry.registerCreativeModeTab(new ResourceLocation(MOD_ID, "tab"), () -> new ItemStack(ModItems.GRAPES.get()));

    public static void init() {
        ModBlocks.init();
        ModItems.init();
        ModSoundEvents.init();
        ModEntityTypes.init();
        ModBlockEntityTypes.init();
    }

    public static void registerFlammables() {

    }
}