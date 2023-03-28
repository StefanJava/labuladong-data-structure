package com.stefan.arraylinklist.practice;

import java.util.*;

/**
 * @description: 187. 重复的DNA序列
 * @author: stefanyang
 * @date: 2023/3/28 15:03
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution187 {
    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        if (len < 11) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i + 10 <= len; i++) {
            String subStr = s.substring(i, i + 10);
            map.put(subStr, map.getOrDefault(subStr, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    private final static int L = 10;

    // 滑动窗口
    public List<String> findRepeatedDnaSequences2(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        Set<String> res = new HashSet<>();
        int left = 0;
        int right = 0;
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();
        while (right < len) {
            char c = chars[right];
            // 扩大窗口
            right++;
            sb.append(c);

            if (right - left == L) {
                // 缩小窗口
                String str = sb.toString();
                if (set.contains(str)) {
                    res.add(str);
                } else {
                    set.add(str);
                }
                sb.deleteCharAt(0);
                left++;
            }
        }
        return new ArrayList<>(res);
    }

    private final static int R = 4;

    // 滑动窗口
    public List<String> findRepeatedDnaSequences3(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            switch (chars[i]) {
                case 'A':
                    nums[i] = 0;
                    break;
                case 'C':
                    nums[i] = 1;
                    break;
                case 'G':
                    nums[i] = 2;
                    break;
                case 'T':
                    nums[i] = 3;
                    break;
                default:
                    break;
            }
        }
        int left = 0;
        int right = 0;
        int windowHash = 0;
        List<String> res = new ArrayList<>();
        Map<Integer, Integer> seen = new HashMap<>();
        while (right < len) {
            int n = nums[right];
            // 扩大窗口
            windowHash = windowHash * R + n;
            right++;
            // 缩小窗口
            if (right - left == L) {
                if (seen.getOrDefault(windowHash, 0) == 1) {
                    res.add(s.substring(left, right));
                }
                seen.put(windowHash, seen.getOrDefault(windowHash, 0) + 1);
                windowHash = windowHash - nums[left] * (int) Math.pow(R, L - 1);
                left++;
            }
        }
        return res;
    }
}
