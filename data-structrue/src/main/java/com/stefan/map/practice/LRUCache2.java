package com.stefan.map.practice;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @description: 146. LRU 缓存
 * @author: stefanyang
 * @date: 2023/2/20 12:47
 * @version: 1.0
 */
public class LRUCache2 {
    private int cap;

    private LinkedHashMap<Integer, Integer> cache;

    public LRUCache2(int capacity) {
        this.cap = capacity;
        this.cache = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        // key存在
        if (cache.containsKey(key)) {
            cache.remove(key);
            cache.put(key, value);
        } else {
            // key不存在
            if (cache.size() >= cap) {
                Integer delKey = cache.keySet().iterator().next();
                cache.remove(delKey);
            }
            cache.put(key, value);
        }
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除 key，重新插入到队尾
        cache.remove(key);
        cache.put(key, val);
    }
}