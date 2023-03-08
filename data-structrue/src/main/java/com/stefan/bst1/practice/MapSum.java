package com.stefan.bst1.practice;

import com.stefan.bst1.TrieMap;

import java.util.List;

/**
 * @description: 677. 键值映射
 * @author: stefanyang
 * @date: 2023/3/8 14:42
 * @version: 1.0
 */
public class MapSum {

    private TrieMap<Integer> trieMap;

    public MapSum() {
        trieMap = new TrieMap<>();
    }

    public void insert(String key, int val) {
        trieMap.put(key, val);
    }

    public int sum(String prefix) {
        List<String> list = trieMap.keysWithPrefix(prefix);
        int sum = 0;
        for (String s : list) {
            sum += trieMap.get(s);
        }
        return sum;
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));           // 返回 3 (apple = 3)
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));           // 返回 5 (apple + app = 3 + 2 = 5)
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
