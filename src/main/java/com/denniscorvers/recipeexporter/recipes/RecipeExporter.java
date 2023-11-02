package com.denniscorvers.recipeexporter.recipes;

import com.denniscorvers.recipeexporter.ModRecipeExporter;
import com.denniscorvers.recipeexporter.config.Config;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.exporters.ExporterCompatibility;
import com.denniscorvers.recipeexporter.recipes.exporters.IRecipeExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.oredictionary.OreDictExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.oredictionary.ShapelessOreDictExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.vanilla.MiscExporter;
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
        MyFile exportFile = MyFile.CreateFile(
                Config.exportName,
                Config.exportPath,
                outData);

        if (exportFile != null && exportFile.tryPersist()) {
            Chat.addSystemMessage("Finished exporting " + outData.getRecipeCount() + " recipes.");
            Chat.addSystemMessage("Saving to " + exportFile.getOutputPath());
        } else {
            Chat.addSystemMessage("Unable to create export file. See the error log for details.");
        }
    }

    private static OutputData startCollecting() {
        List<IRecipeExporter> exporters = setupExporters();
        List<IMyRecipe> recipeList = new ArrayList<>();

        ItemStackCache cache = new ItemStackCache();

        for (IRecipe recipe : ForgeRegistries.RECIPES) {
            for (IRecipeExporter exporter : exporters) {
                ExporterCompatibility exc = exporter.checkCompatibility(recipe);

                // Exporter is disabled for this type. Skip trying to export Recipe.
                if (exc == ExporterCompatibility.Skip)
                    break;
                // Exporter doesn't operate on this type, continue to next exporter.
                if (exc == ExporterCompatibility.Incompatible)
                    continue;

                IMyRecipe result = exporter.process(cache, recipe);

                if (result != null) {
                    recipeList.add(result);
                    break;
                }
            }
        }

        return new OutputData(
                recipeList,
                cache.getModList(),
                cache.getItemList());
    }

    private static List<IRecipeExporter> setupExporters() {
        List<IRecipeExporter> exporters = new ArrayList<>(5);
        exporters.add(new ShapedExporter(Config.includeShaped));
        exporters.add(new ShapelessExporter(Config.includeShapeless));
        exporters.add(new OreDictExporter(Config.includeOreDictionary));
        exporters.add(new ShapelessOreDictExporter(Config.includeOreDictionary));
        exporters.add(new MiscExporter(Config.includeMisc));

        return exporters;
    }
}
