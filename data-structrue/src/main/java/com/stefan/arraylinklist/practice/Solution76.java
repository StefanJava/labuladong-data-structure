package com.stefan.arraylinklist.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * @Date: 2023/1/11 11:03
 * @Author: stefanyang
 */
public class Solution76 {
    public static String minWindow(String s, String t) {
        if (s == null || t == null || (s.length() < t.length())) return "";
        // 初始化
        Map<Character, Integer> windows = new HashMap<>(16);
        Map<Character, Integer> needs = new HashMap<>(16);
        char[] tArr = t.toCharArray();
        for (char c : tArr) {
            if (needs.containsKey(c)) {
                needs.put(c, needs.get(c) + 1);
            } else {
                needs.put(c, 1);
                windows.put(c, 0);
            }
        }
        char[] sArr = s.toCharArray();
        int left = 0;
        int right = 0;
        int valid = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        while (right < sArr.length) {
            char scRight = sArr[right];
            right++;
            if (needs.containsKey(scRight)) {
                windows.put(scRight, windows.get(scRight) + 1);
                if (windows.get(scRight).equals(needs.get(scRight))) {
                    valid++;
                }
            }

            while (valid == needs.size()) {
                // 改变结果
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char scLeft = sArr[left];
                left++;
                if (needs.containsKey(scLeft)) {
                    if (windows.get(scLeft).equals(needs.get(scLeft))) {
                        valid--;
                    }
                    windows.put(scLeft, windows.get(scLeft) - 1);
                }
            }
        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
}
