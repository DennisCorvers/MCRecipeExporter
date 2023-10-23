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

    protected final HashMap<ItemStackProxy, Integer> m_recipeCache;

    public Exporter() {
        m_recipeCache = new HashMap<>(9);
    }

    @Override
    public IMyRecipe process(ItemStackCache cache, IRecipe recipe) {
        if (!canProcess(recipe)) return null;

        for (Ingredient ingr : recipe.getIngredients()) {

            if (ingr.getMatchingStacks().length < 1)
                continue;

            //Create wrapper for checking if the item was already added before.
            ItemStackProxy myItem = new ItemStackProxy(ingr.getMatchingStacks()[0]);
            m_recipeCache.put(myItem, m_recipeCache.getOrDefault(myItem, 0) + 1);
        }

        MyRecipe shRec = new MyRecipe();
        for (Map.Entry<ItemStackProxy, Integer> entry : m_recipeCache.entrySet()) {
            IMyItemStack input = ItemStackHelper.parseVanillaRecipe(entry.getKey().getStack(), cache);
            input.setAmount(entry.getValue());
            shRec.addInput(input);
        }

        shRec.setOutput(ItemStackHelper.parseVanillaRecipe(recipe.getRecipeOutput(), cache));

        //Clear cache after every recipe!
        m_recipeCache.clear();
        return shRec;
    }

    public abstract boolean canProcess(IRecipe recipe);
}
