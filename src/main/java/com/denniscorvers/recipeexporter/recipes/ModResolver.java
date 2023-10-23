package com.denniscorvers.recipeexporter.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ModResolver {
    private final Map<String, Integer> m_modCache;
    private Integer m_lastID;

    public ModResolver() {
        m_lastID = 0;
        m_modCache = new HashMap<>();
    }

    public int resolve(ItemStack stack) {
        String modName = getModName(stack);

        Integer id = m_modCache.getOrDefault(modName, -1);
        if (id == -1) {
            m_modCache.put(modName, m_lastID++);
        }

        return m_lastID;
    }

    private static String getModName(ItemStack stack) {
        Item i = stack.getItem();
        ResourceLocation itemName = i.getRegistryName();

        if (itemName == null) {
            return "undef";
        }

        return itemName.getNamespace();
    }

    public Map<Integer, String> finalizeMap() {
        Map<Integer, String> map = new HashMap<>(m_modCache.size());

        for (Map.Entry<String, Integer> entry : m_modCache.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }

        return map;
    }

}
