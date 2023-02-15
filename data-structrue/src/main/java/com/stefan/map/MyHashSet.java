package com.stefan.map;

/**
 * @description: 简单的哈希集合
 * @author: stefanyang
 * @date: 2023/2/15 10:33
 * @version: 1.0
 */
public class MyHashSet<K> {

    private final static Object PRESENT = new Object();

    private MyHashMap2<K, Object> map = new MyHashMap2<>();

    public boolean add(K k) {
        if (k == null) {
            throw new IllegalArgumentException("key is null");
        }
        return map.put(k, PRESENT) == null;
    }

    public boolean remove(K k) {
        if (k == null) {
            throw new IllegalArgumentException("key is null");
        }
        return map.remove(k) != null;
    }

    public boolean contains(K k) {
        if (k == null) {
            throw new IllegalArgumentException("key is null");
        }
        return map.containsKey(k);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

}
