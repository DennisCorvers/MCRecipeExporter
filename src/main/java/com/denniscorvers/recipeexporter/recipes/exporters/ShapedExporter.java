package com.denniscorvers.recipeexporter.recipes.exporters;

import com.denniscorvers.recipeexporter.recipes.ModResolver;
import com.denniscorvers.recipeexporter.recipes.crafting.IMyRecipe;
import com.denniscorvers.recipeexporter.recipes.crafting.MyShapedRecipe;
import com.denniscorvers.recipeexporter.recipes.items.IMyItem;
import com.denniscorvers.recipeexporter.recipes.items.MyItemStack;
import com.denniscorvers.recipeexporter.util.ItemStackHelper;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShapedExporter implements IRecipeExporter {

    private final HashMap<MyItemStack, Integer> m_recipeCache;

    public ShapedExporter() {
        m_recipeCache = new HashMap<>(9);
    }

    public List<IMyRecipe> Export(ModResolver resolver) {
        List<IMyRecipe> shapedRecipes = new ArrayList<>();

        for (IRecipe recipe : ForgeRegistries.RECIPES) {
            if (!(recipe instanceof ShapedRecipes)) {
                continue;
            }

            ShapedRecipes mcRec = (ShapedRecipes) recipe;
            for (Ingredient ingr : mcRec.getIngredients()) {
                try {
                    if (ingr.getMatchingStacks().length < 1) {
                        continue;
                    }

                    //Create wrapper for checking if the item was already added before.
                    MyItemStack myItem = new MyItemStack(ingr.getMatchingStacks()[0]);
                    m_recipeCache.put(myItem, m_recipeCache.getOrDefault(myItem, 0) + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            MyShapedRecipe shRec = new MyShapedRecipe();
            for (Map.Entry<MyItemStack, Integer> entry : m_recipeCache.entrySet()) {
                IMyItem input = ItemStackHelper.parseVanillaRecipe(entry.getKey().getStack(), resolver);
                input.setAmount(entry.getValue());
                shRec.addInput(input);
            }

            shRec.setOutput(ItemStackHelper.parseVanillaRecipe(mcRec.getRecipeOutput(), resolver));

            //Clear cache after every recipe!
            m_recipeCache.clear();
            shapedRecipes.add(shRec);
        }

        return shapedRecipes;
    }
}
