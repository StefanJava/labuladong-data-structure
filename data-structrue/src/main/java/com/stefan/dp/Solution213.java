package com.stefan.dp;

import java.util.Arrays;

/**
 * @description: 213. 打家劫舍 II
 * @author: stefanyang
 * @date: 2023/3/22 16:14
 * @version: 1.0
 */
public class Solution213 {

    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(dp(1, n - 1, nums), dp(0, n - 2, nums));
    }

    private int dp(int start, int end, int[] nums) {
        int pre = 0;
        int cur = 0;
        int temp = 0;
        for (int i = start; i <= end; i++) {
            temp = cur;
            // cur永远保持最大值
            cur = Math.max(cur, pre + nums[i]);
            pre = temp;
        }
        return cur;
    }

    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] memo1 = new int[n];
        int[] memo2 = new int[n];
        Arrays.fill(memo1, -1);
        Arrays.fill(memo2, -1);
        return Math.max(dp(1, n, nums, memo1), dp(0, n - 1, nums, memo2));
    }

    private static int dp(int start, int end, int[] nums, int[] memo) {
        if (start >= end) {
            return 0;
        }
        int res = 0;
        if (memo[start] != -1) {
            return memo[start];
        }
        res = Math.max(dp(start + 1, end, nums, memo), dp(start + 2, end, nums, memo) + nums[start]);
        // 添加进备忘录  可以防止重复计算
        memo[start] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{200, 3, 140, 20, 10};
        System.out.println(rob(nums));
    }
}
