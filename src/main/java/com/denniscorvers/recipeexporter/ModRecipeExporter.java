package com.denniscorvers.recipeexporter;

import com.denniscorvers.recipeexporter.config.Config;
import com.denniscorvers.recipeexporter.events.EventKeyInput;
import com.denniscorvers.recipeexporter.proxy.CommonProxy;
import com.denniscorvers.recipeexporter.util.Chat;
import com.denniscorvers.recipeexporter.util.Constants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(
        modid = Constants.MODID, name = "Recipe Exporter",
        version = "${version}", clientSideOnly = true
)
public class ModRecipeExporter {

    @Mod.Instance(value = Constants.MODID)
    public static ModRecipeExporter instance;
    @SidedProxy(clientSide = Constants.FULLMODID + ".proxy.ClientProxy")
    public static CommonProxy proxy;
    public static MyLogger logger;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        logger = new MyLogger(event.getModLog());
        Config.init(new Configuration((event.getSuggestedConfigurationFile())));
        Chat.init();

        MinecraftForge.EVENT_BUS.register(new EventKeyInput());

        proxy.preInit();
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.init();
    }
}