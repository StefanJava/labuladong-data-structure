package com.stefan.map;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Date: 2023/2/10 13:48
 * @Author: stefanyang
 */
public class MyHashMap1<K, V> {

    private final static float FACTOR = 0.75F;

    private int size;

    private ListMap<K, V>[] table;

    private final static int INIT_CAP = 4;

    public MyHashMap1() {
        this(INIT_CAP);
    }

    public MyHashMap1(int cap) {
        this.table = new ListMap<>[cap];
        this.size = 0;
        for (int i = 0; i < cap; i++) {
            table[i] = new ListMap<>();
        }
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (size >= table.length * FACTOR) {
            resize(table.length * 2);
        }
        ListMap<K, V> kvListMap = table[hash(key)];
        if (!kvListMap.containsKey(key)) {
            size++;
        }
        return kvListMap.put(key, value);
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        ListMap<K, V> kvListMap = table[hash(key)];
        if (kvListMap.containsKey(key)) {
            size--;
            return kvListMap.remove(key);
        }
        return null;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        ListMap<K, V> listMap = table[hash(key)];
        return listMap.get(key);
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return get(key) != null;
    }

    public int hash(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void resize(int newCap) {
        ListMap<K, V>[] newTable = new ListMap<>[newCap];
        for (int i = 0; i < table.length; i++) {
            List<Map.Entry<K, V>> entries = table[i].entries();
            for (Map.Entry<K, V> entry : entries) {
                ListMap<K, V> listMap = new ListMap<>();
                listMap.put(entry.getKey(), entry.getValue());
                table[hash(entry.getKey())] = listMap;
            }
        }
        this.table = newTable;
    }

}
