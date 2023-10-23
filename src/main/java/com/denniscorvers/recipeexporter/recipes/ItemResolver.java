package com.denniscorvers.recipeexporter.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemResolver {
    private final Map<Integer, String> m_itemNameCache;

    public ItemResolver() {
        m_itemNameCache = new HashMap<>();
    }

    public int resolve(ItemStack itemStack) {
        Item item = itemStack.getItem();
        int itemID = Item.getIdFromItem(item);
        String name = itemStack.getDisplayName();

        m_itemNameCache.putIfAbsent(itemID, name);
        return itemID;
    }

    public Map<Integer, String> finalizeMap() {
        return m_itemNameCache;
    }
}
