package com.denniscorvers.recipeexporter;

import com.denniscorvers.recipeexporter.config.Config;
import com.denniscorvers.recipeexporter.events.EventKeyInput;
import com.denniscorvers.recipeexporter.proxy.CommonProxy;
import com.denniscorvers.recipeexporter.util.Chat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


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
    @SidedProxy(clientSide = FULLMODID + ".proxy.ClientProxy")
    public static CommonProxy proxy;
    public static MyLogger logger;

    public static String getVersion() {
        return "${mod_version}";
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        logger = new MyLogger(event.getModLog());
        Config.init(new Configuration(event.getSuggestedConfigurationFile()));
        Chat.init();

        MinecraftForge.EVENT_BUS.register(new EventKeyInput());

        proxy.preInit();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.init();
    }
}