package com.stefan.dp;

import java.util.Arrays;

/**
 * @description: 198. 打家劫舍
 * @author: stefanyang
 * @date: 2023/3/21 21:10
 * @version: 1.0
 */
public class Solution198 {

    public int rob3(int[] nums) {
        int n = nums.length;
        int next = 0;
        int nextnext = 0;
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            res = Math.max(next, nextnext + nums[i]);
            nextnext = next;
            next = res;
        }
        return res;
    }

    public int rob2(int[] nums) {
        int n = nums.length;
        // dp[i]代表从第i个房子开始抢
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i + 2] + nums[i]);
        }
        return dp[0];
    }

    private int res = 0;
    private int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(0, nums);
    }

    private int dp(int start, int[] nums) {
        if (start >= nums.length) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        res = Math.max(dp(start + 1, nums), dp(start + 2, nums) + nums[start]);
        memo[start] = res;
        return res;
    }
}
