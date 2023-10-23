package com.denniscorvers.recipeexporter.recipes.exporters;

import com.denniscorvers.recipeexporter.recipes.ItemStackCache;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import net.minecraft.item.crafting.IRecipe;

public interface IRecipeExporter {
    IMyRecipe process(ItemStackCache cache, IRecipe recipe);

    boolean canProcess(IRecipe recipe);
}
