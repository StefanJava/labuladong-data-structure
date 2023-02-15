package com.stefan.map;

import java.util.Iterator;

/**
 * @description: 有序的哈希集合
 * @author: stefanyang
 * @date: 2023/2/15 11:17
 * @version: 1.0
 */
public class MyLinedHashSet<K> {
    private MyLinkedHashMap<K, Object> map = new MyLinkedHashMap<>();

    private static final Object PRESENT = new Object();

    public boolean add(K k) {
        return map.put(k, PRESENT) == null;
    }

    public boolean remove(K k) {
        return map.remove(k) != null;
    }

    public boolean contains(K k) {
        return map.containsKey(k);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public Iterator<K> iterator() {
        return map.keys().iterator();
    }
}
