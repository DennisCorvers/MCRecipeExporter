package com.denniscorvers.recipeexporter.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class ItemResolver {
    private final Map<String, Integer> m_itemCache;
    private Integer m_lastID;

    public ItemResolver() {
        m_itemCache = new HashMap<>();
        m_lastID = 0;
    }

    public int resolve(ItemStack itemStack) {
        String displayName = itemStack.getDisplayName();
        if (m_itemCache.containsKey(displayName)) {
            return m_itemCache.get(displayName);
        }

        Integer id = m_lastID++;
        m_itemCache.put(displayName, id);
        return id;
    }

    private String formatID(Integer id, Integer meta) {
        if (meta == null)
            return id.toString();

        return MessageFormat.format("{0}:{1}", id, meta);
    }

    public Map<Integer, String> finalizeMap() {
        Map<Integer, String> map = new HashMap<>(m_itemCache.size());

        for (Map.Entry<String, Integer> entry : m_itemCache.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }

        return map;
    }

    private class ItemData {
        private String displayName;
        private int id;

        public ItemData(String displayName, int ID) {
            this.displayName = displayName;
            this.id = ID;
        }
    }
}
