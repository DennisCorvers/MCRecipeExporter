package com.denniscorvers.recipeexporter.util;

import com.denniscorvers.recipeexporter.recipes.ItemResolver;
import com.denniscorvers.recipeexporter.recipes.ModResolver;
import com.denniscorvers.recipeexporter.recipes.items.IMyItem;
import com.denniscorvers.recipeexporter.recipes.items.MyItem;
import com.denniscorvers.recipeexporter.recipes.items.MyOreDictItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

public class ItemStackHelper {
    public static String getModName(ItemStack stack) {
        Item i = stack.getItem();
        ResourceLocation itemName = i.getRegistryName();

        if (itemName == null) {
            return "undef";
        }

        return itemName.getNamespace();
    }

    public static IMyItem parseVanillaRecipe(ItemStack stack, ModResolver modResolver, ItemResolver itemResolver) {
        MyItem i = new MyItem();

        i.setItemID(itemResolver.resolve(stack));
        i.setAmount(stack.getCount());
        i.setModID(modResolver.Resolve(getModName(stack)));

        return i;
    }

    public static IMyItem parseOreDictionaryItem(ItemStack stack, ModResolver modResolver, ItemResolver itemResolver) {
        IMyItem i;

        String[] names = getOreDictName(stack);
        if (names.length > 0) {
            i = new MyOreDictItem();
            ((MyOreDictItem) i).setOreDictionaryNames(names);
        } else
            i = new MyItem();

        i.setItemID(itemResolver.resolve(stack));
        i.setAmount(stack.getCount());
        i.setModID(modResolver.Resolve(getModName(stack)));

        return i;
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
