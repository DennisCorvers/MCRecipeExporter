package com.denniscorvers.recipeexporter.recipes;

import com.denniscorvers.recipeexporter.recipes.items.IMyItem;
import com.denniscorvers.recipeexporter.recipes.items.MyItem;
import com.denniscorvers.recipeexporter.util.IndexedMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Map;

public class ItemStackCache {
    private final IndexedMap<String> m_modCache;
    private final IndexedMap<IMyItem> m_itemCache;


    public ItemStackCache() {
        m_itemCache = new IndexedMap<>();
        m_modCache = new IndexedMap<>();

    }

    private static String getModName(ItemStack stack) {
        Item i = stack.getItem();
        ResourceLocation itemName = i.getRegistryName();

        if (itemName == null) {
            return "undef";
        }

        return itemName.getNamespace();
    }

    public int getItemID(ItemStack stack) {
        IMyItem mItem = new MyItem(stack.getDisplayName(), getModID(stack));
        return m_itemCache.getID(mItem);
    }

    private int getModID(ItemStack stack) {
        String modName = getModName(stack);
        return m_modCache.getID(modName);
    }

    public Map<Integer, String> getModList() {
        return m_modCache.getIndexedMap();
    }

    public Map<Integer, IMyItem> getItemList() {
        return m_itemCache.getIndexedMap();
    }
}
