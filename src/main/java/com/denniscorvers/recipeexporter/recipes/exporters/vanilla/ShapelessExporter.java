package com.denniscorvers.recipeexporter.recipes.exporters.vanilla;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;

public class ShapelessExporter extends VanillaExporter {

    @Override
    public boolean canProcess(IRecipe recipe) {
        return recipe instanceof ShapelessRecipes;
    }
}
