package com.stefan.bst1.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @description: 451. 根据字符出现频率排序
 * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
 *
 * 返回 已排序的字符串。如果有多个答案，返回其中任何一个。
 * @author: stefanyang
 * @date: 2023/2/26 13:09
 * @version: 1.0
 */
public class Solution451 {
    public String frequencySort(String s) {
        // 用哈希表存储每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>(16);
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // 利用大顶堆，每次弹出的元素皆是最大的频率
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((entry1, entry2) -> {
            return entry2.getValue().compareTo(entry1.getValue());
        });

        // 按字符频率从大到小排序
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(entry);
        }

        StringBuilder result = new StringBuilder();

        // 每次把频率最大的字符弹出，并重复次数，拼接起来
        while (!pq.isEmpty()) {

            Map.Entry<Character, Integer> entry = pq.poll();
            result.append(String.valueOf(entry.getKey()).repeat(entry.getValue()));
        }
        return result.toString();
    }
}
