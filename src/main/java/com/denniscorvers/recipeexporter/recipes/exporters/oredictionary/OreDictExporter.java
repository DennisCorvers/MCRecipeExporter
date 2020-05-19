package com.denniscorvers.recipeexporter.recipes.exporters.oredictionary;

import com.denniscorvers.recipeexporter.recipes.ModResolver;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.exporters.IRecipeExporter;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.HashMap;


public class OreDictExporter implements IRecipeExporter {

    protected HashMap<String, Integer> m_recipeCache;

    public OreDictExporter() {
        m_recipeCache = new HashMap<>(9);
    }

    @Override
    public IMyRecipe process(ModResolver resolver, IRecipe recipe) {
        if (!(recipe instanceof ShapedOreRecipe))
            return null;

        ShapedOreRecipe mcRec = (ShapedOreRecipe) recipe;

/*        for (Ingredient ingr : mcRec.getIngredients()) {

        }*/

        return null;
    }

    @Override
    public boolean canProcess(IRecipe recipe) {
        return recipe instanceof ShapedOreRecipe;
    }
}
