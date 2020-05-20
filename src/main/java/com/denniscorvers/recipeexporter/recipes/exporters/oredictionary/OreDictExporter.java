package com.denniscorvers.recipeexporter.recipes.exporters.oredictionary;

import com.denniscorvers.recipeexporter.recipes.ModResolver;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.crafting.MyRecipe;
import com.denniscorvers.recipeexporter.recipes.exporters.Exporter;
import com.denniscorvers.recipeexporter.recipes.items.IMyItem;
import com.denniscorvers.recipeexporter.recipes.items.MyItemStack;
import com.denniscorvers.recipeexporter.util.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.Map;


public class OreDictExporter extends Exporter {

    @Override
    public IMyRecipe process(ModResolver resolver, IRecipe recipe) {
        if (!canProcess(recipe)) return null;

        ShapedOreRecipe shOre = (ShapedOreRecipe) recipe;
        for (Ingredient ingr : shOre.getIngredients()) {

            if (ingr.getMatchingStacks().length < 1)
                continue;

            //Create wrapper for checking if the item was already added before.
            MyItemStack myItem = new MyItemStack(ingr.getMatchingStacks()[0]);
            m_recipeCache.put(myItem, m_recipeCache.getOrDefault(myItem, 0) + 1);
        }

        MyRecipe shRec = new MyRecipe();
        for (Map.Entry<MyItemStack, Integer> entry : m_recipeCache.entrySet()) {
            IMyItem input = ItemStackHelper.parseOreDictionaryItem(entry.getKey().getStack(), resolver);

            input.setAmount(entry.getValue());
            shRec.addInput(input);
        }

        shRec.setOutput(ItemStackHelper.parseVanillaRecipe(recipe.getRecipeOutput(), resolver));

        //Clear cache after every recipe!
        m_recipeCache.clear();
        return shRec;
    }

    @Override
    public boolean canProcess(IRecipe recipe) {
        return recipe instanceof ShapedOreRecipe;
    }
}
