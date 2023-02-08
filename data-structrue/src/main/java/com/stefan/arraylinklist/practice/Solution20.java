package com.stefan.arraylinklist.practice;

import java.util.Stack;

/**
 * @Description: 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * @Date: 2023/2/8 10:38
 * @Author: stefanyang
 */
public class Solution20 {
    public boolean isValid(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i ++) {
            char c = s.charAt(i);
            if (c == ']' || c == '}' || c == ')') {
                char cp = getPair(c);
                if (stack.isEmpty() || cp != stack.pop()) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public char getPair(char c) {
        if (c == ')') {
            return '(';
        } else if (c == '}') {
            return '{';
        } else {
            return '[';
        }
    }
}
