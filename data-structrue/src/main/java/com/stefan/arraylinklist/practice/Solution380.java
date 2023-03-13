package com.stefan.arraylinklist.practice;

import java.util.*;

/**
 * @description: 380. O(1) 时间插入、删除和获取随机元素
 * @author: stefanyang
 * @date: 2023/3/13 10:33
 * @version: 1.0
 */
class RandomizedSet {

    // O(1)随机等概率返回，采用数组存储数据
    // 正常插入和删除时，数组不为O(1),可以采用在尾部插入和删除，删除时可把待删除的数据与尾部数据交换位置，size--
    // 哈希表记录元素所对应的索引,删除时方便交换位置

    private List<Integer> data;

    private Map<Integer, Integer> valToIndex;

    public RandomizedSet() {
        this.data = new ArrayList<>();
        this.valToIndex = new HashMap<>();
    }

    public boolean insert(int val) {
        // 已存在，返回false
        if (valToIndex.containsKey(val)) {
            return false;
        }
        // 记录数据对应索引
        valToIndex.put(val, data.size());
        // 添加到尾部
        data.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!valToIndex.containsKey(val)) {
            return false;
        }
        // 查询待删除数据对应索引
        int index = valToIndex.get(val);
        // 拿出数组末尾的数据
        int tailVal = data.get(data.size() - 1);
        // 将数组待删除数据的索引的值   置为tailVal
        Collections.swap(data, index, data.size() - 1);
        data.remove(data.size() - 1);
        valToIndex.put(tailVal, index);
        valToIndex.remove(val);
        return true;
    }

    public int getRandom() {
        return data.get((int)(Math.random() * data.size()));
    }
}