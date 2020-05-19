package com.denniscorvers.recipeexporter.recipes;

import com.denniscorvers.recipeexporter.ModRecipeExporter;
import com.denniscorvers.recipeexporter.config.Config;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.exporters.IRecipeExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.oredictionary.OreDictExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.vanilla.ShapedExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.vanilla.ShapelessExporter;
import com.denniscorvers.recipeexporter.util.Chat;
import com.denniscorvers.recipeexporter.util.MyFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import net.lingala.zip4j.util.Zip4jConstants;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeExporter {

    @SerializedName("Recipes")
    private final List<IMyRecipe> m_recipeList;

    private transient final ModResolver m_modResolver;

    @SerializedName("Mods")
    private Map<Integer, String> m_modList;

    private RecipeExporter() {
        m_recipeList = new ArrayList<>(256);
        m_modResolver = new ModResolver();
    }

    public static void export() {
        Chat.addSystemMessage("Starting Export...");

        RecipeExporter exporter = new RecipeExporter();
        exporter.startCollecting();

        //After export...
        String exportPath = MyFile.formatExportPath(Config.exportPath);

        //TODO Multithread this part?
        if (exporter.saveToFile(exportPath)) {
            Chat.addSystemMessage("Finished exporting " + exporter.recipeCount() + " recipes.");
            Chat.addSystemMessage("Saving to " + exportPath);
        } else {
            Chat.addSystemMessage("Unable to export recipes...");
        }
    }

    private void startCollecting() {
        List<IRecipeExporter> exporters = setupExporters();

        for (IRecipe recipe : ForgeRegistries.RECIPES) {
            IMyRecipe result = null;

            for (IRecipeExporter exporter : exporters) {
                result = exporter.process(m_modResolver, recipe);

                if (result != null) {
                    m_recipeList.add(result);
                    break;
                }
            }
        }

        //Collect mod look-up dictionary
        m_modList = m_modResolver.finalizeMap();
    }

    private boolean saveToFile(String path) {
        Gson gson = (new GsonBuilder()).serializeNulls().create();
        String json;

        try {
            json = gson.toJson(this);
        } catch (Exception e) {
            e.printStackTrace();
            ModRecipeExporter.logger.error(e.getMessage());
            return false;
        }

        if (true) return true; //TODO: Remove this to store files on disk!
        File saveFile = MyFile.getSaveFile(path);
        if (saveFile == null) return false;
        if (!MyFile.trySaveJson(saveFile, json)) return false;

        return MyFile.tryCompress(saveFile, Zip4jConstants.COMP_DEFLATE, Zip4jConstants.DEFLATE_LEVEL_FASTEST);
    }

    private String recipeCount() {
        return String.format("%,d", m_recipeList.size());
    }

    private List<IRecipeExporter> setupExporters() {
        List<IRecipeExporter> exporters = new ArrayList<>(4);
        if (Config.includeShaped)
            exporters.add(new ShapedExporter());
        if (Config.includeShapeless)
            exporters.add(new ShapelessExporter());
        if (Config.includeOreDictionary)
            exporters.add(new OreDictExporter());
        //Add Shapeless Ore Dictionary?

        return exporters;
    }
}
