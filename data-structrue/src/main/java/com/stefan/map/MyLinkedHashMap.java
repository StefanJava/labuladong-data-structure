package com.stefan.map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @description: key有序的哈希链表
 * @author: stefanyang
 * @date: 2023/2/15 10:40
 * @version: 1.0
 */
public class MyLinkedHashMap<K, V> {

    private final HashMap<K, Node<K, V>> map = new HashMap<>();

    private final Node<K, V> head;
    private final Node<K, V> tail;

    public MyLinkedHashMap() {
        // 初始化
        head = new Node<>();
        tail = new Node<>();

        head.next = tail;
        tail.prev = head;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        Node<K, V> node = map.get(key);
        if (node == null) {
            return null;
        }
        return node.val;
    }

    public V put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        Node<K, V> oldNode = map.get(key);

        if (oldNode == null) {
            Node<K, V> newNode = new Node<>(key, val);
            map.put(key, newNode);
            addLastNode(newNode);
            return null;
        }
        V oldVal = oldNode.val;
        oldNode.val = val;
        return oldVal;
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (!containsKey(key)) {
            return null;
        }
        Node<K, V> oldNode = map.remove(key);
        removeNode(oldNode);
        return oldNode.val;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public Iterable<K> keys() {
        LinkedList<K> linkedList = new LinkedList<>();
        for (Node<K, V> p = head.next; p != tail; p = p.next) {
            linkedList.add(p.key);
        }
        return linkedList;
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    private static class Node<K, V> implements Map.Entry<K, V> {
        public K key;
        public V val;

        public Node<K, V> prev;
        public Node<K, V> next;

        public Node() {
        }

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }

    private void addLastNode(Node<K,V> newNode) {
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        newNode.next = tail;
        tail.prev = newNode;
    }

    private void removeNode(Node<K,V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

}
