package com.stefan.bst1.practice;

import java.util.*;

/**
 * @description: 692. 前K个高频单词
 * 给定一个单词列表words和一个整数 k ，返回前k个出现次数最多的单词。
 *
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率， 按字典顺序 排序。
 * @author: stefanyang
 * @date: 2023/3/3 19:11
 * @version: 1.0
 */
public class Solution692 {
    public List<String> topKFrequent(String[] words, int k) {
        // 存储单词-单词出现的频率
        Map<String, Integer> map = new HashMap<>(16);
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // 优先队列存储字符串  出现次数最低的排队头 出现次数相同 按字典倒序
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((entry1, entry2) -> {
            if (entry1.getValue().equals(entry2.getValue())) {
                // 出现频率相同
                return entry2.getKey().compareTo(entry1.getKey());
            }
            return entry1.getValue() - entry2.getValue();
        });
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        LinkedList<String> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.addFirst(pq.poll().getKey());
        }
        return res;
    }
}
