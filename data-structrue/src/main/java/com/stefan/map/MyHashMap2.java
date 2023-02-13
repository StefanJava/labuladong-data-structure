package com.stefan.map;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Date: 2023/2/10 13:48
 * @Author: stefanyang
 */
public class MyHashMap2<K, V> {

    private final static float FACTOR = 0.75F;

    private int size;

    private int cap;

    private Node<K, V>[] table;

    private final static int INIT_CAP = 4;

    public MyHashMap2() {
        this(INIT_CAP);
    }

    public MyHashMap2(int cap) {
        this.table = (Node<K, V>[]) new Node[cap];
        this.cap = cap;
        this.size = 0;
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (size >= table.length * FACTOR) {
            resize(table.length * 2);
        }
        Node<K, V> node = getNode(key);
        if (node != null) {
            // 修改
            V oldVal = node.v;
            node.v = value;
            return oldVal;
        }
        Node<K, V> newNode = new Node<>(key, value);
        int hash = hash(key);
        while (table[hash] != null && table[hash].status == 1) {
            hash = (hash + 1) % table.length;
        }
        table[hash] = newNode;
        size++;
        return null;
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        Node<K, V> node = getNode(key);
        if (node != null) {
            V oldVal = node.v;
            node.status = 0;
            size--;
            return oldVal;
        }
        return null;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        Node<K, V> node = getNode(key);
        if (node != null) {
            return node.v;
        }
        return null;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return getNode(key) != null;
    }

    public Node<K, V> getNode(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        int hash = hash(key);
        while (table[hash] != null && table[hash].status == 1) {
            if (key.equals(table[hash].k)) {
                return table[hash];
            }
            hash = (hash + 1) % cap;
        }
        return null;
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

    private void resize(int newCap) {
        MyHashMap2<K, V> newMap = new MyHashMap2<>(newCap);
        for (Node<K, V> entry : table) {
            if (entry != null) {
                newMap.put(entry.k, entry.v);
            }
        }
        this.table = newMap.table;
        this.cap = newMap.cap;
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        public K k;
        public V v;
        public Node<K, V> next;
        /**
         * 1-有效 0-无效 占位节点
         */
        public int status = 1;

        public Node() {

        }

        public Node(K key, V value) {
            this.k = key;
            this.v = value;
        }

        @Override
        public K getKey() {
            return this.k;
        }

        @Override
        public V getValue() {
            return this.v;
        }

        @Override
        public V setValue(V value) {
            V oldVal = this.v;
            this.v = value;
            return oldVal;
        }
    }

    public static void main(String[] args) {
        MyHashMap2<Integer, String> map2 = new MyHashMap2<>();
        map2.put(1, "a");
        map2.put(2, "b");
        map2.put(3, "c");
        map2.put(4, "d");
        map2.put(5, "e");
        map2.put(6, "f");
        map2.put(7, "g");
        map2.put(8, "h");
        map2.remove(5);
        map2.remove(2);
        map2.put(4, "n");
        map2.put(5, "o");
        System.out.println(map2.size());
        System.out.println(map2.get(2));
    }
}
