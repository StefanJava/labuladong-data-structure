package com.stefan.bst1.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @description: 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * @author: stefanyang
 * @date: 2023/3/2 11:36
 * @version: 1.0
 */
public class Solution347 {

    public int[] topKFrequent(int[] nums, int k) {

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((entry1, entry2) -> entry2.getValue() - entry1.getValue());

        Map<Integer, Integer> map = new HashMap<>(16);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }
        int[] res = new int[k];
        while (k > 0 && !pq.isEmpty()) {
            res[--k] = pq.poll().getKey();
        }
        return res;
    }
}
