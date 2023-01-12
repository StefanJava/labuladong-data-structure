package com.stefan.arraylinklist.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @Date: 2023/1/12 11:39
 * @Author: stefanyang
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> resultMap = new HashMap<>(16);
        char[] sArr = s.toCharArray();
        int left = 0;
        int right = 0;
        int len = 0;
        while (right < sArr.length) {
            char src = sArr[right];
            right++;
            resultMap.put(src, resultMap.getOrDefault(src, 0) + 1);
            while (resultMap.getOrDefault(src, 0) > 1) {
                char slc = sArr[left];
                left++;
                if (src == slc) {
                    resultMap.put(slc, resultMap.get(slc) - 1);
                } else {
                    resultMap.remove(slc);
                }
            }
            if (resultMap.size() > len) {
                len = resultMap.size();
            }
        }
        return len;
    }
}
