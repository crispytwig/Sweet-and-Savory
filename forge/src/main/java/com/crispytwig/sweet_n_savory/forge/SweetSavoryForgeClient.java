package com.crispytwig.sweet_n_savory.forge;

import com.crispytwig.sweet_n_savory.SweetSavory;
import com.crispytwig.sweet_n_savory.client.SweetSavoryClient;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SweetSavory.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SweetSavoryForgeClient {

    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {
        SweetSavoryClient.init();
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

    }


}
