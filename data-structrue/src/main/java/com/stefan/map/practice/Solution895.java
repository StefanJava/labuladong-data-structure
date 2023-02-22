package com.stefan.map.practice;

import java.util.HashMap;
import java.util.Stack;

/**
 * @description: 895. 最大频率栈
 * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 *
 * 实现 FreqStack类:
 *
 * FreqStack()构造一个空的堆栈。
 * void push(int val)将一个整数val压入栈顶。
 * int pop()删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 * @author: stefanyang
 * @date: 2023/2/22 16:11
 * @version: 1.0
 */
public class Solution895 {
    /**
     * 记录出现最高的频次
     */
    int maxFreq;

    /**
     * 每个值对应的频次
     */
    HashMap<Integer, Integer> valToFreq;

    /**
     * 相同频次对应的值
     */
    HashMap<Integer, Stack<Integer>> freqToVals;

    public Solution895() {
        this.maxFreq = 0;
        this.valToFreq = new HashMap<>();
        this.freqToVals = new HashMap<>();
    }

    public void push(int val) {
        // 第一次进栈
        int freq = valToFreq.getOrDefault(val, 0) + 1;
        valToFreq.put(val, freq);
        freqToVals.putIfAbsent(freq, new Stack<>());
        freqToVals.get(freq).push(val);
        maxFreq = Math.max(maxFreq, freq);
    }

    public int pop() {
        Stack<Integer> vals = freqToVals.get(maxFreq);
        Integer v = vals.pop();
        int freq = valToFreq.get(v) - 1;
        if (freq == 0) {
            valToFreq.remove(v);
        } else {
            valToFreq.put(v, freq);
        }
        if (vals.isEmpty()) {
            maxFreq--;
            freqToVals.remove(freq + 1);
        }
        return v;
    }
}
