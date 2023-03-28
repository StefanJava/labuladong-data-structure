package com.stefan.dp;

import java.util.Arrays;

/**
 * @description: 300. 最长递增子序列
 * @author: stefanyang
 * @date: 2023/3/28 10:28
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution300 {
    public int lengthOfLIS(int[] nums) {
        // dp[i]表示以nums[i]结尾的最长递增子序列
        // dp[i]最小值为1
        // 计算dp[i]时,找到i之前,比nums[i]小的数对应的索引index的dp[index] + 1, 并且取最大值
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
    public int lengthOfLIS2(int[] nums) {
        // 二分法
        int len = nums.length;
        int[] tops = new int[len];
        int piles = 0;

        for (int poker : nums) {
            int left = 0;
            int right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tops[mid] > poker) {
                    right = mid;
                } else if (tops[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left == piles) {
                piles++;
            }
            tops[left] = poker;
        }
        return piles;
    }
}
