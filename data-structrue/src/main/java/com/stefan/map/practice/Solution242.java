package com.stefan.map.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
 *。
 * @author: stefanyang
 * @date: 2023/2/18 19:06
 * @version: 1.0
 */
public class Solution242 {

    public boolean isAnagram2(String s, String t) {
        // 用哈希表存储各字符的个数
        Map<Character, Integer> map = new HashMap<>();
        int sLen = s.length();

        for (int i = 0; i < sLen; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        int tLen = t.length();

        for (int i = 0; i < tLen; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        for (Integer count : map.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;

    }

    public boolean isAnagram1(String s, String t) {
        int[] sEncode = encodeStr(s);
        int[] tEncode = encodeStr(t);
        int size = sEncode.length;
        for (int i = 0; i < size; i++) {
            if (sEncode[i] != tEncode[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] encodeStr(String str) {
        int len = str.length();
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            count[c - 'a']++;
        }
        return count;
    }
}
