package com.denniscorvers.recipeexporter.config;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;

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

    public static Configuration getConfig() {
        return config;
    }

    public static void init(Configuration c) {
        config = c;
        config.load();

        m_exportPath = config.get(Configuration.CATEGORY_GENERAL, "ExportPath", "");
        m_exportPath.setComment("Export file name. Leave empty for datetime stamped file name.");

        m_exportName = config.get(Configuration.CATEGORY_GENERAL, "ExportName", "");
        m_exportName.setComment("Default export directory. Leave empty to output in current directory.");

        m_includeShaped = config.get(Configuration.CATEGORY_GENERAL, "Export Shaped Recipes", includeShaped);
        m_includeShapeless = config.get(Configuration.CATEGORY_GENERAL, "Export Shapeless Recipes", includeShapeless);
        m_includeOreDictionary = config.get(Configuration.CATEGORY_GENERAL, "Export OreDict Shaped Recipes", includeOreDictionary);
        m_includeMiscItems = config.get(Configuration.CATEGORY_GENERAL, "Export OreDict Shaped Recipes", includeMisc);

        syncConfig();
    }

    public static void syncConfig() {
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