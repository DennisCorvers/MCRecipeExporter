package com.denniscorvers.recipeexporter.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResolverMap<K, V> implements Map<K, V> {
    private final HashMap<K, V> m_keyMap;
    private final HashMap<V, K> m_valueMap;

    public ResolverMap(int initialCapacity) {
        m_keyMap = new HashMap<>(initialCapacity);
        m_valueMap = new HashMap<>(initialCapacity);
    }

    public ResolverMap() {
        m_keyMap = new HashMap<>();
        m_valueMap = new HashMap<>();
    }

    @Override
    public int size() {
        return m_keyMap.size();
    }

    @Override
    public boolean isEmpty() {
        return m_keyMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return m_keyMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return m_valueMap.containsKey(value);
    }

    @Override
    public V get(Object key) {
        return m_keyMap.get(key);
    }

    public K getKey(V value) {
        return m_valueMap.get(value);
    }

    @Override
    public V put(K key, V value) {
        m_valueMap.put(value, key);
        return m_keyMap.put(key, value);
    }

    @Override
    public V remove(Object key) {
        V val = m_keyMap.remove(key);
        if (val != null) {
            m_valueMap.remove(val);
        }
        return val;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> e : m.entrySet()) {
            m_keyMap.put(e.getKey(), e.getValue());
            m_valueMap.put(e.getValue(), e.getKey());
        }
    }

    @Override
    public void clear() {
        m_keyMap.clear();
        m_valueMap.clear();
    }

    @Override
    public Set<K> keySet() {
        return m_keyMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return m_keyMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return m_keyMap.entrySet();
    }

    public Map<K, V> getKeyMap() {
        return m_keyMap;
    }

    public Map<V, K> getValueMap() {
        return m_valueMap;
    }
}
