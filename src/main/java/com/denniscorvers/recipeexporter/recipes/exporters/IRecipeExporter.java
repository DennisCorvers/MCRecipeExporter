package com.denniscorvers.recipeexporter.recipes.exporters;

import com.denniscorvers.recipeexporter.recipes.ModResolver;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;

import java.util.List;

public interface IRecipeExporter {
    List<IMyRecipe> Export(ModResolver resolver);
}
