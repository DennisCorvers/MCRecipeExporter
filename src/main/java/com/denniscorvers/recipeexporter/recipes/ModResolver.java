package com.denniscorvers.recipeexporter.recipes;

import com.google.gson.annotations.SerializedName;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

public class ModResolver {
    private final HashMap<String, Integer> m_modMap;
    private Integer m_lastID;

    public ModResolver() {
        m_lastID = 0;
        m_modMap = new HashMap<>();
    }

    public int Resolve(String modName) {
        Integer id = m_modMap.getOrDefault(modName, -1);
        if (id == -1) {
            m_modMap.put(modName, m_lastID++);
            id = m_lastID;
        }

        return id;
    }
}
