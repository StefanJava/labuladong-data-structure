package com.stefan.map.practice;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @description: 460. LFU 缓存
 * @author: stefanyang
 * @date: 2023/2/21 10:36
 * @version: 1.0
 */
public class LFUCache {
    private int capacity;

    /**
     * 记录最小频次
     */
    private int minFreq;

    /**
     * key - val 映射
     */
    private HashMap<Integer, Integer> keyToVal;

    /**
     * key - 使用频次 映射
     */
    private HashMap<Integer, Integer> keyToFreq;

    /**
     * freq - 相同频次的key集合 映射
     */
    private HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyToVal = new HashMap<>();
        this.keyToFreq = new HashMap<>();
        this.freqToKeys = new HashMap<>();
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // key频次+1
        int oldFreq = keyToFreq.get(key);
        int newFreq = oldFreq + 1;
        keyToFreq.put(key, newFreq);

        // 修改频次对应的集合
        freqToKeys.get(oldFreq).remove(key);
        freqToKeys.putIfAbsent(newFreq, new LinkedHashSet<>());
        LinkedHashSet<Integer> freqKeySet = freqToKeys.get(newFreq);
        freqKeySet.add(key);
        // 如果此时频次最少对应的key集合为空则 minFreq = minFreq + 1
        if (null == freqToKeys.get(minFreq) || freqToKeys.get(minFreq).size() == 0) {
            freqToKeys.remove(this.minFreq);
            this.minFreq++;
        }
        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        // 如果key存在
        if (keyToVal.containsKey(key)) {
            int oldVal = keyToVal.get(key);
            // 修改key对应值
            keyToVal.put(key, value);
            // 对应key频次+1
            int oldFreq = keyToFreq.get(key);
            int newFreq = oldFreq + 1;
            keyToFreq.put(key, newFreq);
            // 修改频次对应的集合
            freqToKeys.get(oldFreq).remove(key);
            freqToKeys.putIfAbsent(newFreq, new LinkedHashSet<>());
            LinkedHashSet<Integer> freqKeySet = freqToKeys.get(newFreq);
            freqKeySet.add(key);
            if (null == freqToKeys.get(minFreq) || freqToKeys.get(minFreq).size() == 0) {
                freqToKeys.remove(this.minFreq);
                this.minFreq++;
            }
        } else {
            // 如果key不存在
            if (keyToVal.size() >= this.capacity) {
                // 容量满了
                // 剔除使用频次最少的key-value
                LinkedHashSet<Integer> freqKeys = freqToKeys.get(minFreq);
                Integer delKey = freqKeys.iterator().next();
                freqKeys.remove(delKey);
                keyToVal.remove(delKey);
                keyToFreq.remove(delKey);
                if (freqKeys.size() == 0) {
                    freqToKeys.remove(minFreq);
                }
            }
            keyToVal.put(key, value);
            keyToFreq.put(key, 1);
            freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
            LinkedHashSet<Integer> freqKeySet = freqToKeys.get(1);
            freqKeySet.add(key);
            minFreq = 1;
        }
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.get(2);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2
        lfu.get(1);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
    }
}
