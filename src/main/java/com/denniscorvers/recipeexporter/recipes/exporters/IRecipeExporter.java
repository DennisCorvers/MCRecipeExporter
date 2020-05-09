package com.denniscorvers.recipeexporter.recipes.exporters;

import com.denniscorvers.recipeexporter.recipes.ModResolver;

public interface IRecipeExporter {
    void Export(ModResolver resolver);
}
