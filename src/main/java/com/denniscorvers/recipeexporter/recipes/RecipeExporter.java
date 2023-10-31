package com.denniscorvers.recipeexporter.recipes;

import com.denniscorvers.recipeexporter.config.Config;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.exporters.ExporterCompatibility;
import com.denniscorvers.recipeexporter.recipes.exporters.IRecipeExporter;
import com.denniscorvers.recipeexporter.recipes.exporters.vanilla.*;
import com.denniscorvers.recipeexporter.util.Chat;
import com.denniscorvers.recipeexporter.util.MyFile;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipe;

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
                Config.VARS.exportName.get(),
                Config.VARS.exportPath.get(),
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

        for (IRecipe<?> recipe : Minecraft.getInstance().world.getRecipeManager().getRecipes()) {
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
        List<IRecipeExporter> exporters = new ArrayList<>(4);
        exporters.add(new ShapedExporter(Config.VARS.includeShaped.get()));
        exporters.add(new ShapelessExporter(Config.VARS.includeShapeless.get()));
        exporters.add(new SingleItemExporter(Config.VARS.includeSingleItem.get()));
        exporters.add(new SmithingExporter(Config.VARS.includeSmithing.get()));
        exporters.add(new MiscExporter(Config.VARS.includeMiscItems.get()));

        return exporters;
    }
}
