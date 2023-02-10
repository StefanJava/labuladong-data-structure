package com.stefan.arraylinklist.practice;

import java.util.Stack;

/**
 * @Description: 1944. 队列中可以看到的人数
 * @Date: 2023/2/10 15:25
 * @Author: stefanyang
 */
public class Solution1944 {
    public int[] canSeePersonsCount(int[] heights) {
        int len = heights.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            int count = 0;
            int height = heights[i];
            while (!stack.isEmpty() && height > stack.peek()) {
                stack.pop();
                count++;
            }
            res[i] = stack.isEmpty() ? count : count + 1;
            stack.push(height);
        }
        return res;
    }
}
