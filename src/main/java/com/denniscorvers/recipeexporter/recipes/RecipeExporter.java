package com.denniscorvers.recipeexporter.recipes;

import com.denniscorvers.recipeexporter.ModRecipeExporter;
import com.denniscorvers.recipeexporter.config.Config;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.exporters.ShapedExporter;
import com.denniscorvers.recipeexporter.util.Chat;
import com.denniscorvers.recipeexporter.util.MyFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

        if (exporter.saveToFile(exportPath)) {
            Chat.addSystemMessage("Finished exporting " + exporter.recipeCount() + " recipes.");
            Chat.addSystemMessage("Saving to " + exportPath);
        } else {
            Chat.addSystemMessage("Unable to export recipes...");
        }
    }

    private void startCollecting() {
        ShapedExporter shapedEx = new ShapedExporter();

        if (Config.includeShaped)
            m_recipeList.addAll(shapedEx.Export(m_modResolver));

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

        File saveFile = MyFile.getSaveFile(path);

        try {
            FileWriter writer = new FileWriter(saveFile);
            writer.write(json);
            writer.close();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String recipeCount() {
        return String.format("%,d", m_recipeList.size());
    }
}
