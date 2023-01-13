package com.stefan.arraylinklist.practice;

/**
 * @Description: 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * @Date: 2023/1/13 10:39
 * @Author: stefanyang
 */
public class Solution5 {

    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String p1 = palindrome(s, i, i);
            String p2 = palindrome(s, i, i + 1);
            res = p1.length() > res.length() ? p1 : res;
            res = p2.length() > res.length() ? p2 : res;
        }
        return res;
    }

    public String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            } else {
                break;
            }
        }
        return s.substring(l + 1, r);
    }

}
