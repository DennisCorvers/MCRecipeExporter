package com.denniscorvers.recipeexporter.util;

import java.util.HashMap;
import java.util.Map;

public class IndexedMap<T> {
    private final HashMap<T, Integer> m_indexedMap;
    private int m_lastID;

    public IndexedMap() {
        m_indexedMap = new HashMap<>();
        m_lastID = 0;
    }

    public int getID(T item) {
        if (m_indexedMap.containsKey(item))
            return m_indexedMap.get(item);

        m_indexedMap.put(item, m_lastID);
        return m_lastID++;
    }

    public Map<Integer, T> getIndexedMap() {
        Map<Integer, T> map = new HashMap<>(m_indexedMap.size());

        for (Map.Entry<T, Integer> entry : m_indexedMap.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        return map;
    }
}
