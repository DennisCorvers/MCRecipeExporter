package com.denniscorvers.recipeexporter.util;

import com.denniscorvers.recipeexporter.recipes.ItemStackCache;
import com.denniscorvers.recipeexporter.recipes.items.IMyItemStack;
import com.denniscorvers.recipeexporter.recipes.items.MatchingItemStack;
import com.denniscorvers.recipeexporter.recipes.items.MyItemStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class ItemStackHelper {
    public static IMyItemStack parseVanillaRecipe(ItemStack stack, ItemStackCache cache) {
        return new MyItemStack(
                stack.getCount(),
                cache.getItemID(stack));
    }

    public static IMyItemStack parseVanillaIngredient(Ingredient ingredient, int amount, ItemStackCache cache) {
        ItemStack[] matchingStacks = ingredient.getMatchingStacks();

        // TODO: Figure out how compatible this is with 1.7.10
        if (matchingStacks.length > 1) {
            return parseMatchingIngredient(ingredient, amount, cache);
        }

        ItemStack firstStack = matchingStacks[0];
        return new MyItemStack(
                amount,
                cache.getItemID(firstStack));

    }

    private static IMyItemStack parseMatchingIngredient(Ingredient ingredient, int amount, ItemStackCache cache) {
        // First ItemStack is excluded from the alternative id list, because it is included as an
        // ItemStack in the IMyItemStack itself.
        ItemStack[] matchingStacks = ingredient.getMatchingStacks();
        Integer[] matchingStackIds = new Integer[matchingStacks.length - 1];
        ItemStack firstItem = matchingStacks[0];

        for (int i = 1; i < matchingStacks.length; i++) {
            matchingStackIds[i - 1] = cache.getItemID(matchingStacks[i]);
        }

        return new MatchingItemStack(
                amount,
                cache.getItemID(firstItem),
                matchingStackIds);
    }
}
