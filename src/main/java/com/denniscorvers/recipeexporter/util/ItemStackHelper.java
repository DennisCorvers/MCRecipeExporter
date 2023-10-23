package com.denniscorvers.recipeexporter.util;

import com.denniscorvers.recipeexporter.recipes.ItemStackCache;
import com.denniscorvers.recipeexporter.recipes.items.IMyItemStack;
import com.denniscorvers.recipeexporter.recipes.items.MyItemStack;
import com.denniscorvers.recipeexporter.recipes.items.MyOreDictItemStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemStackHelper {
    public static IMyItemStack parseVanillaRecipe(ItemStack stack, ItemStackCache cache) {
        return new MyItemStack(
                stack.getCount(),
                cache.getItemID(stack));
    }

    public static IMyItemStack parseOreDictionaryItem(ItemStack stack, ItemStackCache cache) {
        String[] names = getOreDictName(stack);
        if (names.length > 0) {
            return new MyOreDictItemStack(
                    stack.getCount(),
                    cache.getItemID(stack),
                    names);
        }

        return new MyItemStack(
                stack.getCount(),
                cache.getItemID(stack));
    }

    public static String[] getOreDictName(ItemStack stack) {
        int[] ids = OreDictionary.getOreIDs(stack);
        String[] list = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            list[i] = OreDictionary.getOreName(ids[i]);
        }
        return list;
    }
}
