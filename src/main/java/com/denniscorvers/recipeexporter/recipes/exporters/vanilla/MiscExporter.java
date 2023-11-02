package com.denniscorvers.recipeexporter.recipes.exporters.vanilla;

import com.denniscorvers.recipeexporter.recipes.exporters.Exporter;
import net.minecraft.item.crafting.IRecipe;

public class MiscExporter extends Exporter {
    public MiscExporter(boolean isActive) {
        super(isActive);
    }

    @Override
    protected boolean canProcess(IRecipe recipe) {
        // This exporter can process any recipe.
        return true;
    }
}
