package com.denniscorvers.recipeexporter;

import com.denniscorvers.recipeexporter.config.Config;
import com.denniscorvers.recipeexporter.events.EventKeyInput;
import com.denniscorvers.recipeexporter.events.Keybindings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(
        modid = ModRecipeExporter.MODID, name = ModRecipeExporter.MODNAME,
        version = "${mod_version}", clientSideOnly = true,
        guiFactory = ModRecipeExporter.FULLMODID + ".config.ConfigGui$Factory"
)
public class ModRecipeExporter {

    public static final String MODNAME = "Recipe Exporter";
    public static final String MODID = "recipeexporter";
    public static final String FULLMODID = "com.denniscorvers." + MODID;

    @Mod.Instance(value = MODID)
    public static ModRecipeExporter instance;
    public static Logger LOGGER = LogManager.getLogger(MODID);

    public ModRecipeExporter() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        Config.init(new Configuration(event.getSuggestedConfigurationFile()));

        Keybindings.register();

        MinecraftForge.EVENT_BUS.register(Config.class);
        MinecraftForge.EVENT_BUS.register(EventKeyInput.class);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {

    }
}