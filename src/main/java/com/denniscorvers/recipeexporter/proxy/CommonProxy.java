package com.denniscorvers.recipeexporter.proxy;

import com.denniscorvers.recipeexporter.config.Config;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.denniscorvers.recipeexporter.ModRecipeExporter.MODID;

public abstract class CommonProxy {

    public void preInit() {
    }

    public void init() {
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MODID)) {
            Config.syncConfig();
        }
    }
}
