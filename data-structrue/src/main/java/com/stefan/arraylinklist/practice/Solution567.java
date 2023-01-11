package com.stefan.arraylinklist.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 567. 字符串的排列
 * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。如果是，返回 true ；否则，返回 false 。
 *
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 * @Date: 2023/1/11 14:01
 * @Author: stefanyang
 */
public class Solution567 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> windows = new HashMap<>(16);
        Map<Character, Integer> needs = new HashMap<>(16);
        char[] arr1 = s1.toCharArray();
        for (char c1 : arr1) {
            if (needs.containsKey(c1)) {
                needs.put(c1, needs.get(c1) + 1);
            } else {
                needs.put(c1, 1);
                windows.put(c1, 0);
            }
        }
        char[] arr2 = s2.toCharArray();
        int left = 0, right = 0;
        int valid = 0;
        while (right < arr2.length) {
            char c1 = arr2[right];
            right++;
            if (needs.containsKey(c1)) {
                windows.put(c1, windows.get(c1) + 1);
                if (windows.get(c1).equals(needs.get(c1))) {
                    valid++;
                }
            }

            while (valid == needs.size()) {
                if (right - left == s1.length()) {
                    return true;
                }
                char c2 = arr2[left];
                left++;
                if (needs.containsKey(c2)) {
                    if (windows.get(c2).equals(needs.get(c2))) {
                        valid--;
                    }
                    windows.put(c2, windows.get(c2) - 1);
                }
            }
        }
        return false;
    }
}
