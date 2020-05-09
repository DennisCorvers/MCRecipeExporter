package com.denniscorvers.recipeexporter.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.nio.file.Paths;

public class Config {

    /**
     * Values from config usable within code
     */
    public static String exportPath = "";
    public static boolean includeShaped = true;
    public static boolean includeShapeless = true;
    public static boolean includeOreDictionary = true;

    /**
     * Config properties
     */
    private static Property m_exportPath;
    private static Property m_includeShaped;
    private static Property m_includeShapeless;
    private static Property m_includeOreDictionary;

    private static Configuration config;

    public static Configuration getConfig() {
        return config;
    }

    public static void init(Configuration c) {
        config = c;
        config.load();

        String configPath = c.getConfigFile().getAbsolutePath();
        exportPath = Paths.get(configPath).getParent().getParent() + "\\RecipeExporter";
        m_exportPath = config.get(Category.GENERAL, "ExportPath", exportPath);
        m_exportPath.setComment("The root folder to which the recipe files are exported");

        m_includeShaped = config.get(Category.GENERAL, "Export Shaped Recipes", includeShaped);
        m_includeShapeless = config.get(Category.GENERAL, "Export Shapeless Recipes", includeShapeless);
        m_includeOreDictionary = config.get(Category.GENERAL, "Export OreDict Shaped Recipes", includeOreDictionary);

        syncConfig();
    }

    public static void syncConfig() {
        exportPath = m_exportPath.getString();

        includeShaped = m_includeShaped.getBoolean();
        includeShapeless = m_includeShapeless.getBoolean();
        includeOreDictionary = m_includeOreDictionary.getBoolean();

        config.save();
    }

    private static class Category {
        public static final String GENERAL = "General";
    }
}
