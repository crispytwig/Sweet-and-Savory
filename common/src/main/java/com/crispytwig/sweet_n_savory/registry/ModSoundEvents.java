package com.crispytwig.sweet_n_savory.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ModSoundEvents {


    public static Supplier<SoundEvent> register(String name) {
        return ModRegistry.registerSoundEvent(name, () -> new SoundEvent(new ResourceLocation(com.crispytwig.sweet_n_savory.SweetSavory.MOD_ID, name)));
    }

    public static void init() {}
}
