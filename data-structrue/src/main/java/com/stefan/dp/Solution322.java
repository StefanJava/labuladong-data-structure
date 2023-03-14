package com.stefan.dp;

import java.util.Arrays;

/**
 * @description: 322. 零钱兑换
 * @author: stefanyang
 * @date: 2023/3/14 11:22
 * @version: 1.0
 */
public class Solution322 {
    public int coinChange2(int[] coins, int amount) {
        // dp[i]表示凑成总金额i所需的最少硬币个数
        int[] dp = new int[amount + 1];
        // 为什么赋值为amount + 1, 因为amount最多就是由amount个1元硬币凑成, 最大不会超过amount
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        // 遍历所有的金额
        for (int i = 0; i < dp.length; i++) {
            // 遍历所有的硬币
            for (int coin : coins) {
                if (i < coin) {
                    continue;
                }
                // 取各子问题的最小值
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public int coinChange1(int[] coins, int amount) {
        // memo[i]表示凑成总金额i所需的最少硬币个数
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        return dp(coins, amount, memo);
    }
    public int dp(int[] coins, int amount, int[] memo) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        // 如果前面已经计算过, 则直接返回
        if (memo[amount] != -2) {
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subCount = dp(coins, amount - coin, memo);
            // 子问题无解
            if (subCount == -1) {
                continue;
            }
            // res取各个子问题的最小值
            res = Math.min(res, 1 + subCount);
        }
        memo[amount] = (res == Integer.MAX_VALUE ? -1 : res);
        return memo[amount];
    }
}
