package com.stefan.arraylinklist.practice;

import java.util.Stack;

/**
 * @description: 1081. 不同字符的最小子序列
 * 返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
 * @author: stefanyang
 * @date: 2023/3/10 16:56
 * @version: 1.0
 */
public class Solution1081 {
    public String smallestSubsequence(String s) {
        // 利用单调栈，保证栈中的字符从栈底到栈顶都是满足字典序的
        Stack<Character> stack = new Stack<>();
        // 字符是否在栈中已存在,大小为256,ASCII
        boolean[] inStack = new boolean[256];
        // 保存字符串中各字符出现的次数,保证满足字典序最小的子序列
        int[] count = new int[256];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            count[c]++;
        }
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            count[c]--;
            // 已在栈中,则跳过
            if (inStack[c]) {
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > c) {
                // 如果栈中存在比c大的字符，并且后面还会出现该字符，则可以把该字符从栈中弹出
                if (count[stack.peek()] > 0) {
                    inStack[stack.pop()] = false;
                } else {
                    break;
                }
            }
            // 加入栈中
            stack.push(c);
            inStack[c] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
