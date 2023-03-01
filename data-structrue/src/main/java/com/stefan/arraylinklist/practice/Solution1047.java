package com.stefan.arraylinklist.practice;

import java.util.Stack;

/**
 * @description: 1047. 删除字符串中的所有相邻重复项
 * 给出由小写字母组成的字符串S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * @author: stefanyang
 * @date: 2023/3/1 11:37
 * @version: 1.0
 */
public class Solution1047 {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (c == stack.peek()) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = String.valueOf(stack.pop()) + res;
        }
        return res;
    }
    public String removeDuplicates2(String s) {
        char[] arr = s.toCharArray();
        int len = s.length();
        int slow = 0;
        int fast = 0;
        while (fast < len) {
            arr[slow] = arr[fast];
            if (slow > 0 && arr[slow] == arr[slow - 1]) {
                slow--;
            } else {
                slow++;
            }
            fast++;
        }
        return new String(arr, 0, slow);
    }
    public String removeDuplicates3(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int top = -1;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (top >= 0 && sb.charAt(top) == c) {
                sb.deleteCharAt(top);
                top--;
            } else {
                sb.append(c);
                top++;
            }
        }
        return sb.toString();
    }
}
