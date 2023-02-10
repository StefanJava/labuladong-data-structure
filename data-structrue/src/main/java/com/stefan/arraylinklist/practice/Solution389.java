package com.stefan.arraylinklist.practice;

/**
 * @Description: 389. 找不同
 * 给定两个字符串 s 和 t ，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 * @Date: 2023/2/10 10:19
 * @Author: stefanyang
 */
public class Solution389 {
    public char findTheDifference(String s, String t) {
        int slen = s.length();
        for (int i = 0; i < slen; i++) {
            char sc = s.charAt(i);
            if (t.contains(String.valueOf(sc))) {
                int index = t.indexOf(sc);
                if (index == t.length() - 1) {
                    t = t.substring(0, index);
                } else {
                    t = t.substring(0, index) + t.substring(index + 1);
                }
            }
        }
        return t.toCharArray()[0];
    }
}
