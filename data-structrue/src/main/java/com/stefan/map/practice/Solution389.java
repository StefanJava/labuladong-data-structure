package com.stefan.map.practice;

/**
 * @description: 389. 找不同
 * 给定两个字符串 s 和 t ，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 * @author: stefanyang
 * @date: 2023/2/19 13:10
 * @version: 1.0
 */
public class Solution389 {
    public char findTheDifference(String s, String t) {
        // 利用异或运算的性质 a ^ a = 0, a ^ 0 = a, a ^ b ^ a = b.
        // 遍历字符串t, s, 进行异或运算, 最后剩下的字符即为添加的字符
        int sLen = s.length();
        int res = 0;
        for (int i = 0; i < sLen; i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            res = res ^ sc ^ tc;
        }
        return (char)(res ^ t.charAt(sLen));
    }
}
