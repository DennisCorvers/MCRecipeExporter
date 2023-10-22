package com.denniscorvers.recipeexporter.recipes;

import com.denniscorvers.recipeexporter.util.ResolverMap;

import java.util.Map;

public abstract class NameResolver {
    private final ResolverMap<String, Integer> m_map;
    private Integer m_lastID;

    public NameResolver() {
        m_lastID = 0;
        m_map = new ResolverMap<>();
    }

    public int Resolve(String value) {
        Integer id = m_map.getOrDefault(value, -1);
        if (id == -1) {
            m_map.put(value, m_lastID++);
        }

        return m_lastID;
    }

    public Map<Integer, String> finalizeMap() {
        return m_map.getValueMap();
    }
}
