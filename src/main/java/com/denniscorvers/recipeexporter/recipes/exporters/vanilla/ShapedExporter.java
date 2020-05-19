package com.denniscorvers.recipeexporter.recipes.exporters.vanilla;

import com.denniscorvers.recipeexporter.recipes.exporters.Exporter;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

public class ShapedExporter extends Exporter {

    @Override
    public boolean canProcess(IRecipe recipe) {
        return recipe instanceof ShapedRecipes;
    }
}
