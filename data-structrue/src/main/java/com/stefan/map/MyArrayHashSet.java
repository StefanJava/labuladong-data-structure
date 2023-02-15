package com.stefan.map;

/**
 * @description: 有序且可已均等概率弹出元素的哈希集合
 * @author: stefanyang
 * @date: 2023/2/15 11:42
 * @version: 1.0
 */
public class MyArrayHashSet<K> {

    private final static Object PRESENT = new Object();

    private final MyArrayHashMap<K, Object> map = new MyArrayHashMap<>();

    public boolean add(K key) {
        return map.put(key, PRESENT) == null;
    }

    public boolean remove(K key) {
        return map.remove(key) != null;
    }

    public K pop() {
        return map.pop();
    }

    public boolean contains(K key) {
        return map.containsKey(key);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

}
