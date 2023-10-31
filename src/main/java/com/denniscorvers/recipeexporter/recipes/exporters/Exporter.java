package com.denniscorvers.recipeexporter.recipes.exporters;

import com.denniscorvers.recipeexporter.recipes.ItemStackCache;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.crafting.MyRecipe;
import com.denniscorvers.recipeexporter.recipes.items.IMyItemStack;
import com.denniscorvers.recipeexporter.recipes.items.ItemStackProxy;
import com.denniscorvers.recipeexporter.util.ItemStackHelper;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;

import java.util.HashMap;
import java.util.Map;

public abstract class Exporter implements IRecipeExporter {
    protected final HashMap<ItemStackProxy, Integer> RecipeCache;
    protected final boolean IsActive;

    public Exporter(boolean isActive) {
        RecipeCache = new HashMap<>(9);
        IsActive = isActive;
    }

    public final IMyRecipe process(ItemStackCache cache, IRecipe<?> recipe) {
        if (!canProcess(recipe))
            return null;

        RecipeCache.clear();
        IMyRecipe result = processRecipe(cache, recipe);
        if (result == null || !result.isValid())
            return null;

        return result;
    }

    public final ExporterCompatibility checkCompatibility(IRecipe<?> recipe) {
        if (!IsActive)
            return ExporterCompatibility.Skip;

        return canProcess(recipe) ?
                ExporterCompatibility.Compatible :
                ExporterCompatibility.Incompatible;
    }

    protected IMyRecipe processRecipe(ItemStackCache cache, IRecipe<?> recipe) {
        for (Ingredient ingr : recipe.getIngredients()) {

            if (ingr.getMatchingStacks().length < 1)
                continue;

            //Create wrapper for checking if the item was already added before.
            ItemStackProxy myItem = new ItemStackProxy(ingr.getMatchingStacks()[0]);
            RecipeCache.put(myItem, RecipeCache.getOrDefault(myItem, 0) + 1);
        }

        MyRecipe shRec = new MyRecipe();
        for (Map.Entry<ItemStackProxy, Integer> entry : RecipeCache.entrySet()) {
            IMyItemStack input = ItemStackHelper.parseVanillaRecipe(entry.getKey().getStack(), cache);
            input.setAmount(entry.getValue());
            shRec.addInput(input);
        }

        shRec.setOutput(ItemStackHelper.parseVanillaRecipe(recipe.getRecipeOutput(), cache));

        return shRec;
    }

    protected abstract boolean canProcess(IRecipe<?> recipe);
}
