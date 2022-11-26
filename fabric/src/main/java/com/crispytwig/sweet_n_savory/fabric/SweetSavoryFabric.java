package com.crispytwig.sweet_n_savory.fabric;

import net.fabricmc.api.ModInitializer;

public class SweetSavoryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        com.crispytwig.sweet_n_savory.SweetSavory.init();
        com.crispytwig.sweet_n_savory.SweetSavory.registerFlammables();
    }

}