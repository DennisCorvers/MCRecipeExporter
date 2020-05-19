package com.denniscorvers.recipeexporter.recipes.exporters.vanilla;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;

public class ShapedExporter extends VanillaExporter {

    @Override
    public boolean canProcess(IRecipe recipe) {
        return recipe instanceof ShapedRecipes;
    }
}
