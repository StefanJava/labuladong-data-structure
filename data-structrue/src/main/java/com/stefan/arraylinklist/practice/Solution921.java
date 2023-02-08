package com.stefan.arraylinklist.practice;

/**
 * @Description: 921. 使括号有效的最少添加
 * 只有满足下面几点之一，括号字符串才是有效的：
 *
 * 它是一个空字符串，或者
 * 它可以被写成AB（A与B连接）, 其中A 和B都是有效字符串，或者
 * 它可以被写作(A)，其中A是有效字符串。
 * 给定一个括号字符串 s ，在每一次操作中，你都可以在字符串的任何位置插入一个括号
 *
 * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
 * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
 * @Date: 2023/2/8 11:57
 * @Author: stefanyang
 */
public class Solution921 {
    public int minAddToMakeValid(String s) {
        int res = 0;
        int leftCount = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else {
                if (leftCount == 0) {
                    res++;
                } else {
                    leftCount--;
                }
            }
        }
        return res + leftCount;
    }
}
