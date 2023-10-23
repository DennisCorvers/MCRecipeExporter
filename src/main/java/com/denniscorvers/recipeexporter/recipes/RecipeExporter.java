package com.denniscorvers.recipeexporter.recipes;

import com.denniscorvers.recipeexporter.ModRecipeExporter;
import com.denniscorvers.recipeexporter.config.Config;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.exporters.IRecipeExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.oredictionary.OreDictExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.oredictionary.ShapelessOreDictExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.vanilla.ShapedExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.vanilla.ShapelessExporter;
import com.denniscorvers.recipeexporter.util.Chat;
import com.denniscorvers.recipeexporter.util.MyFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.lingala.zip4j.util.Zip4jConstants;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecipeExporter {
    private RecipeExporter() {
    }

    public static void export() {
        Chat.addSystemMessage("Starting Export...");

        OutputData outData = startCollecting();

        //After export...
        String exportPath = MyFile.formatExportPath(Config.exportPath);

        //TODO Multithread this part?
        if (saveToFile(outData, exportPath)) {
            Chat.addSystemMessage("Finished exporting " + outData.getRecipeCount() + " recipes.");
            Chat.addSystemMessage("Saving to " + exportPath);
        } else {
            Chat.addSystemMessage("Unable to export recipes...");
        }
    }

    private static OutputData startCollecting() {
        List<IRecipeExporter> exporters = setupExporters();
        List<IMyRecipe> recipeList = new ArrayList<>();

        ModResolver modResolver = new ModResolver();
        ItemResolver itemResolver = new ItemResolver();

        for (IRecipe recipe : ForgeRegistries.RECIPES) {
            for (IRecipeExporter exporter : exporters) {
                IMyRecipe result = exporter.process(modResolver, itemResolver, recipe);

                if (result != null) {
                    recipeList.add(result);
                    break;
                }
            }
        }


        return new OutputData(
                recipeList,
                modResolver.finalizeMap(),
                itemResolver.finalizeMap());
    }

    private static List<IRecipeExporter> setupExporters() {
        List<IRecipeExporter> exporters = new ArrayList<>(4);
        if (Config.includeShaped)
            exporters.add(new ShapedExporter());
        if (Config.includeShapeless)
            exporters.add(new ShapelessExporter());
        if (Config.includeOreDictionary) {
            exporters.add(new OreDictExporter());
            exporters.add(new ShapelessOreDictExporter());
        }

        return exporters;
    }

    private static boolean saveToFile(OutputData data, String path) {
        Gson gson = (new GsonBuilder()).serializeNulls().create();
        String json;

        try {
            json = gson.toJson(data);
        } catch (Exception e) {
            e.printStackTrace();
            ModRecipeExporter.logger.error(e.getMessage());
            return false;
        }

        //if (true) return true; //TODO: Remove this to store files on disk!
        File saveFile = MyFile.getSaveFile(path);
        if (saveFile == null) return false;
        if (!MyFile.trySaveJson(saveFile, json)) return false;

        return MyFile.tryCompress(saveFile, Zip4jConstants.COMP_DEFLATE, Zip4jConstants.DEFLATE_LEVEL_FASTEST);
    }
}
