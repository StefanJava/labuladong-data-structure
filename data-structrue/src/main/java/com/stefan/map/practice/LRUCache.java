package com.stefan.map.practice;

import java.util.HashMap;

/**
 * @description: 146. LRU 缓存
 * @author: stefanyang
 * @date: 2023/2/20 12:47
 * @version: 1.0
 */
public class LRUCache {
    private int cap;

    private HashMap<Integer, NodeEntry<Integer, Integer>> map;

    private MyLinkedList<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.cache = new MyLinkedList<>();
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        NodeEntry<Integer, Integer> node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
        return node.val;
    }

    public void put(int key, int value) {
        // key存在
        if (map.containsKey(key)) {
            NodeEntry<Integer, Integer> oldNode = map.get(key);
            oldNode.val = value;
            cache.remove(oldNode);
            cache.addLast(oldNode);
        } else {
            // key不存在
            NodeEntry<Integer, Integer> newNode = new NodeEntry<>(key, value);
            if (cache.size() >= cap) {
                NodeEntry<Integer, Integer> delNode = cache.removeFirst();
                map.remove(delNode.key);
            }
            map.put(key, newNode);
            cache.addLast(newNode);
        }
    }
}

class NodeEntry<K, V> {
    K key;
    V val;
    NodeEntry<K, V> prev, next;
    public NodeEntry() {
    }
    public NodeEntry(K key, V val) {
        this.key = key;
        this.val = val;
    }
}

class MyLinkedList<K, V> {
    int size;
    NodeEntry<K, V> head, tail;

    public MyLinkedList() {
        head = new NodeEntry<>();
        tail = new NodeEntry<>();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void remove(NodeEntry<K, V> delNode) {
        delNode.prev.next = delNode.next;
        delNode.next.prev = delNode.prev;
        delNode.next = null;
        delNode.prev = null;
        size--;
    }

    public NodeEntry<K, V> removeFirst() {
        if (size <= 0) {
            return null;
        }
        NodeEntry<K, V> temp = head.next;
        head.next = temp.next;
        temp.next.prev = head;
        temp.prev = null;
        temp.next = null;
        size--;
        return temp;
    }

    public void addLast(NodeEntry<K, V> node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
        tail.next = null;
        size++;
    }

    public int size() {
        return this.size;
    }
}
