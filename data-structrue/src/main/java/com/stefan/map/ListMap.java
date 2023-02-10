package com.stefan.map;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @Description:
 * @Date: 2023/2/10 11:29
 * @Author: stefanyang
 */
public class ListMap<K, V> {

    /**
     * 虚拟头尾指针
     */
    private Node<K, V> head, tail;
    private int size;

    public ListMap() {
        head = tail = new Node<>();
        head.next = tail;
        size = 0;
    }

    /**
     * 存储key value数据的节点
     *
     * @param <K> key
     * @param <V> value
     */
    private static class Node<K, V> implements Map.Entry<K, V> {

        K k;

        V v;

        Node<K, V> next;

        public Node() {
        }

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public V setValue(V value) {
            V oldValue = v;
            v = value;
            return oldValue;
        }
    }

    /**
     * 添加一个键值对，如果之前存在该key，则修改对应的value并返回原value，不存在则添加一个节点，返回null
     *
     * @param k key
     * @param v value
     * @return oldValue
     */
    public V put(K k, V v) {
        if (k == null) {
            throw new NoSuchElementException("key can not be null");
        }
        Node<K, V> node = getNode(k);
        if (node != null) {
            V oldValue = node.v;
            node.v = v;
            return oldValue;
        }
        Node<K, V> newNode = new Node<>(k, v);
        Node<K, V> tmp = head.next;
        head.next = newNode;
        newNode.next = tmp;
        size++;
        return null;
    }

    /**
     * 获取键对应的值
     *
     * @param k key
     * @return value
     */
    public V get(K k) {
        if (k == null) {
            throw new NoSuchElementException("key can not be null");
        }
        Node<K, V> node = getNode(k);
        if (node != null) {
            return node.v;
        }
        return null;
    }

    /**
     * 删除key对应的键值对，并返回value
     *
     * @param k key
     * @return value
     */
    public V remove(K k) {
        if (k == null) {
            throw new NoSuchElementException("key can not be null");
        }
        Node<K, V> prev = head;
        for (Node<K, V> p = head.next; p != null; p = p.next) {
            if (p.k.equals(k)) {
                prev.next = p.next;
                p.next = null;
                break;
            }
            prev = p;
        }
        size--;
        return null;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new NoSuchElementException("key can not be null");
        }
        Node<K, V> node = getNode(key);
        return node != null;
    }

    /**
     * 获取key对应的键值对节点
     *
     * @param key 键
     * @return 键值对节点
     */
    private Node<K, V> getNode(K key) {
        if (key == null) {
            throw new NoSuchElementException("key can not be null");
        }
        for (Node<K, V> p = head; p != null; p = p.next) {
            if (p.k.equals(key)) {
                return p;
            }
        }
        return null;
    }

    /**
     * 获取键值对数量
     *
     * @return 键值对数量
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取所有的key
     *
     * @return keys
     */
    public List<K> keys() {
        LinkedList<K> linkedList = new LinkedList<>();
        for (Node<K, V> p = head; p != tail; p = p.next) {
            linkedList.add(p.k);
        }
        return linkedList;
    }

    /**
     * 获取所有的键值对
     *
     * @return 键值对
     */
    public List<Map.Entry<K, V>> entries() {
        LinkedList<Map.Entry<K, V>> linkedList = new LinkedList<>();
        for (Node<K, V> p = head; p != tail; p = p.next) {
            linkedList.add(p);
        }
        return linkedList;
    }

}
