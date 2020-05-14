package com.denniscorvers.recipeexporter.recipes;

import com.denniscorvers.recipeexporter.util.ResolverMap;

import java.util.Map;

public class ModResolver {
    private final ResolverMap<String, Integer> m_modMap;
    private Integer m_lastID;

    public ModResolver() {
        m_lastID = 0;
        m_modMap = new ResolverMap<>();
    }

    public int Resolve(String modName) {
        Integer id = m_modMap.getOrDefault(modName, -1);
        if (id == -1) {
            m_modMap.put(modName, m_lastID);
            id = m_lastID++;
        }

        return id;
    }

    public Map<Integer, String> finalizeMap() {
        return m_modMap.getValueMap();
    }
}
