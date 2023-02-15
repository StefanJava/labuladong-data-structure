package com.stefan.map;

import com.stefan.arraylinklist.MyArrayList;

import java.util.HashMap;
import java.util.Random;

/**
 * @description: 有序且可以已等概率取k-v
 * @author: stefanyang
 * @date: 2023/2/15 11:19
 * @version: 1.0
 */
public class MyArrayHashMap<K, V> {

    private static class Node<K, V> {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private final HashMap<K, Integer> map = new HashMap<>();

    private final MyArrayList<Node<K, V>> list = new MyArrayList<>();

    private Random random = new Random();

    public MyArrayHashMap() {
    }

    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        Integer index = map.get(key);
        Node<K, V> node = list.get(index);
        return node.val;
    }

    public V put(K key, V val) {
        Integer index = map.get(key);
        if (index == null) {
            Node<K, V> newNode = new Node<>(key, val);
            list.addLast(newNode);
            map.put(key, list.size() - 1);
            return null;
        }
        Node<K, V> node = list.get(index);
        V oldVal = node.val;
        node.val = val;
        return oldVal;
    }

    public V remove(K key) {
        Integer index = map.get(key);
        if (index == null) {
            return null;
        }
        Node<K, V> delNode = list.get(index);
        Node<K, V> tailNode = list.get(list.size() - 1);
        list.set(index, tailNode);
        map.put(tailNode.key, index);
        list.removeLast();
        map.remove(key);
        return delNode.val;
    }

    // 随机弹出一个键
    public K pop() {
        int rand = random.nextInt(list.size());
        return list.get(rand).key;
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public static void main(String[] args) {
        MyArrayHashMap<Integer, Integer> map = new MyArrayHashMap<>();
        map.put(2, 1);
        map.put(2, 2);
        map.put(1, 4);
        map.put(5, 2);
        map.put(3, 4);
        map.put(4, 6);
        map.remove(5);
        System.out.println(map.pop());
        System.out.println(map.size());
        System.out.println(map.get(2));
    }
}
