package com.denniscorvers.recipeexporter.util;

import com.denniscorvers.recipeexporter.recipes.ModResolver;
import com.denniscorvers.recipeexporter.recipes.items.IMyItem;
import com.denniscorvers.recipeexporter.recipes.items.MyItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemStackHelper {

    public static String getItemName(ItemStack stack) {
        return stack.getDisplayName();
    }

    public static int getItemAmount(ItemStack stack) {
        return stack.getCount();
    }

    public static String getModName(ItemStack stack) {
        Item i = stack.getItem();
        ResourceLocation itemName = i.getRegistryName();

        if (itemName == null) {
            return "undef";
        }

        return itemName.getNamespace();
    }

    public static IMyItem parseVanillaRecipe(ItemStack stack, ModResolver resolver) {
        MyItem i = new MyItem();

        i.setName(stack.getDisplayName());
        i.setAmount(stack.getCount());
        if (i.getName().equals("Paper")) {
            int a = 0;
        }
        i.setModID(resolver.Resolve(getModName(stack)));

        return i;
    }
}
