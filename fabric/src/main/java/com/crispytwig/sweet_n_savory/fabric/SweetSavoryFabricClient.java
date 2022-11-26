package com.crispytwig.sweet_n_savory.fabric;

import net.fabricmc.api.ClientModInitializer;

public class SweetSavoryFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        com.crispytwig.sweet_n_savory.client.SweetSavoryClient.init();
//        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
//                AFBlocks.OAK_TABLE.get(),
//        );
    }
}
