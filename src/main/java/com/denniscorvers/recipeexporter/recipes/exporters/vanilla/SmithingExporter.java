package com.denniscorvers.recipeexporter.recipes.exporters.vanilla;

import com.denniscorvers.recipeexporter.recipes.exporters.Exporter;
import com.denniscorvers.recipeexporter.recipes.exporters.ExporterCompatibility;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.SmithingRecipe;

public class SmithingExporter extends Exporter {

    public SmithingExporter(boolean isActive) {
        super(isActive);
    }

    @Override
    protected boolean canProcess(IRecipe<?> recipe) {
        return recipe instanceof SmithingRecipe;
    }
}
