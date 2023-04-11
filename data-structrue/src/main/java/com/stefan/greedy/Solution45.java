package com.stefan.greedy;

import java.util.Arrays;

/**
 * @description: 45. 跳跃游戏 II
 * @author: stefanyang
 * @date: 2023/4/11 10:22
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution45 {
    public int jump(int[] nums) {
        int count = 0;
        int end = 0;
        int fastest = 0;
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            fastest = Math.max(fastest, i + nums[i]);
            if (end == i) {
                count++;
                end = fastest;
            }
        }
        return count;
    }
    public int jump1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 100001);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }
}
