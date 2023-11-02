package com.denniscorvers.recipeexporter.config;

import com.denniscorvers.recipeexporter.ModRecipeExporter;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class Config {
    /**
     * Values from config usable within code
     */
    public static String exportPath = "";
    public static String exportName = "";
    public static boolean includeShaped = true;
    public static boolean includeShapeless = true;
    public static boolean includeOreDictionary = true;
    public static boolean includeMisc = true;
    /**
     * Config properties
     */
    private static Property m_exportPath;
    private static Property m_exportName;
    private static Property m_includeShaped;
    private static Property m_includeShapeless;
    private static Property m_includeOreDictionary;
    private static Property m_includeMiscItems;

    private static Configuration config;

    public static void init(Configuration c) {
        config = c;
        config.load();

        m_exportPath = config.get(Configuration.CATEGORY_GENERAL, "ExportPath", "");
        m_exportPath.setComment("Export file name. Leave empty for datetime stamped file name.");

        m_exportName = config.get(Configuration.CATEGORY_GENERAL, "ExportName", "");
        m_exportName.setComment("Default export directory. Leave empty to output in current directory.");

        m_includeShaped = config.get(Configuration.CATEGORY_GENERAL, "IncludeShaped", includeShaped);
        m_includeShaped.setComment("True to export all shaped recipes.");

        m_includeShapeless = config.get(Configuration.CATEGORY_GENERAL, "IncludeShapeless", includeShapeless);
        m_includeShapeless.setComment("True to export all shapeless recipes.");

        m_includeOreDictionary = config.get(Configuration.CATEGORY_GENERAL, "IncludeOreDictionary", includeOreDictionary);
        m_includeOreDictionary.setComment("True to export all ore dictionary recipes.");

        m_includeMiscItems = config.get(Configuration.CATEGORY_GENERAL, "IncludeMiscItems", includeMisc);
        m_includeMiscItems.setComment("True to export all recipes not covered by previous options.");

        syncConfig();
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (!event.getModID().equals(ModRecipeExporter.MODID)) {
            return;
        }

        syncConfig();
    }

    private static void syncConfig() {
        exportPath = m_exportPath.getString();
        exportName = m_exportName.getString();

        includeShaped = m_includeShaped.getBoolean();
        includeShapeless = m_includeShapeless.getBoolean();
        includeOreDictionary = m_includeOreDictionary.getBoolean();
        includeMisc = m_includeMiscItems.getBoolean();

        config.save();
    }

    public static List<IConfigElement> getConfigElements() {
        ArrayList<IConfigElement> elements = new ArrayList<>();
        for (Property property : config.getCategory(Configuration.CATEGORY_GENERAL).getOrderedValues()) {
            elements.add(new ConfigElement(property));
        }
        return elements;
    }
}