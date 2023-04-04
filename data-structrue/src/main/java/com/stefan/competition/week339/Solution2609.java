package com.stefan.competition.week339;

/**
 * @description: 2609. 最长平衡子字符串
 * @author: stefanyang
 * @date: 2023/4/4 15:45
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution2609 {
    public int findTheLongestBalancedSubstring(String s) {
        int res = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int left = i, right = i + 1;
            while (left >= 0 && right < len) {
                if (s.charAt(left) != '0' || s.charAt(right) != '1') {
                    break;
                }
                left--;
                right++;
            }
            res = Math.max(res, right - left - 1);
        }
        return res;
    }
}
