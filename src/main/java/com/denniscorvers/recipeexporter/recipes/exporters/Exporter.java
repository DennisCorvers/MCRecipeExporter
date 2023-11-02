package com.denniscorvers.recipeexporter.recipes.exporters;

import com.denniscorvers.recipeexporter.recipes.ItemStackCache;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.crafting.MyRecipe;
import com.denniscorvers.recipeexporter.recipes.items.IMyItemStack;
import com.denniscorvers.recipeexporter.recipes.items.MyIngredient;
import com.denniscorvers.recipeexporter.util.ItemStackHelper;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;

import java.util.HashMap;
import java.util.Map;

public abstract class Exporter implements IRecipeExporter {
    protected final boolean IsActive;

    public Exporter(boolean isActive) {
        IsActive = isActive;
    }

    @Override
    public final IMyRecipe process(ItemStackCache cache, IRecipe recipe) {
        if (!canProcess(recipe))
            return null;

        IMyRecipe result = processRecipe(cache, recipe);
        if (result == null || !result.isValid())
            return null;

        return result;
    }

    @Override
    public final ExporterCompatibility checkCompatibility(IRecipe recipe) {
        if (!IsActive)
            return ExporterCompatibility.Skip;

        return canProcess(recipe) ?
                ExporterCompatibility.Compatible :
                ExporterCompatibility.Incompatible;
    }

    protected IMyRecipe processRecipe(ItemStackCache cache, IRecipe recipe) {
        HashMap<MyIngredient, Integer> ingredientCache = new HashMap<>(9);
        for (Ingredient ingr : recipe.getIngredients()) {

            if (ingr.getMatchingStacks().length == 0)
                continue;

            MyIngredient myIngredient = new MyIngredient(ingr);
            ingredientCache.put(myIngredient, ingredientCache.getOrDefault(myIngredient, 0) + 1);
        }

        MyRecipe shRec = new MyRecipe();
        for (Map.Entry<MyIngredient, Integer> entry : ingredientCache.entrySet()) {
            IMyItemStack input = ItemStackHelper.parseVanillaIngredient(
                    entry.getKey().getIngredient(),
                    entry.getValue(),
                    cache);
            shRec.addInput(input);
        }

        shRec.setOutput(ItemStackHelper.parseVanillaRecipe(recipe.getRecipeOutput(), cache));

        return shRec;
    }

    protected abstract boolean canProcess(IRecipe recipe);
}
