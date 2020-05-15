package com.denniscorvers.recipeexporter.recipes.exporters;

import com.denniscorvers.recipeexporter.recipes.ModResolver;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;

import java.util.ArrayList;
import java.util.List;

public class OreDictExporter implements IRecipeExporter {

    @Override
    public List<IMyRecipe> Export(ModResolver resolver) {
        return new ArrayList<>();
    }
}
