package com.denniscorvers.recipeexporter;

import com.denniscorvers.recipeexporter.config.Config;
import com.denniscorvers.recipeexporter.events.Keybindings;
import com.denniscorvers.recipeexporter.util.Chat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ModRecipeExporter.MODID)
public class ModRecipeExporter {

    public static final String MODNAME = "Recipe Exporter";
    public static final String MODID = "recipeexporter";
    public static final String FULLMODID = "com.denniscorvers." + MODID;

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public ModRecipeExporter() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loadComplete);

        Config.init();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings("unused")
    private void setup(FMLCommonSetupEvent event) {
        Chat.init();
    }

    @SuppressWarnings("unused")
    private void loadComplete(FMLLoadCompleteEvent event) {

    }
}