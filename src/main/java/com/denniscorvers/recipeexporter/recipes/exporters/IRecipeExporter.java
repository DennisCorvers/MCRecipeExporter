package com.denniscorvers.recipeexporter.recipes.exporters;

import com.denniscorvers.recipeexporter.recipes.ModResolver;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import net.minecraft.item.crafting.IRecipe;

public interface IRecipeExporter {
    IMyRecipe process(ModResolver resolver, IRecipe recipe);
}
