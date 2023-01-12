package com.stefan.arraylinklist.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 438. 找到字符串中所有字母异位词
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * @Date: 2023/1/12 10:28
 * @Author: stefanyang
 */
public class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> windows = new HashMap<>(16);
        Map<Character, Integer> needs = new HashMap<>(16);
        char[] pArr = p.toCharArray();
        for (char c : pArr) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        List<Integer> result = new ArrayList<>();
        char[] sArr = s.toCharArray();
        while (right < sArr.length) {
            char rc = sArr[right];
            right++;
            if (needs.containsKey(rc)) {
                windows.put(rc, windows.getOrDefault(rc, 0) + 1);
                if (windows.get(rc).equals(needs.get(rc))) {
                    valid++;
                }
            }
            while (valid == needs.size()) {
                if (right - left == pArr.length) {
                    result.add(left);
                }
                char lc = sArr[left];
                left++;
                if (needs.containsKey(lc)) {
                    if (windows.get(lc).equals(needs.get(lc))) {
                        valid--;
                    }
                    windows.put(lc, windows.get(lc) - 1);
                }
            }
        }
        return result;
    }
}
