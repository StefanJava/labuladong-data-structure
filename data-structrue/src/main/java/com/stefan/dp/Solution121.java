package com.stefan.dp;

/**
 * @description: 121. 买卖股票的最佳时机
 * @author: stefanyang
 * @date: 2023/3/17 17:09
 * @version: 1.0
 */
public class Solution121 {
    public int maxProfit1(int[] prices) {
        // 动态规划
        // 每天的状态有持有股票和未持有股票两种状态
        int len = prices.length;
        int[][] dp = new int[len + 1][2];
        dp[0][0] = 0;
        // 第0天持有股票，不存在这种情况，可以初始化为最小值（因为要求最大利润）
        dp[0][1] = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i - 1]);
            dp[i][1] = Math.max(- prices[i - 1], dp[i - 1][1]);
        }
        return dp[len][0];
    }
    public int maxProfit2(int[] prices) {
        // 动态规划
        // 每天的状态有持有股票和未持有股票两种状态
        int len = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i - 1]);
            dp_i_1 = Math.max(dp_i_1, -prices[i - 1]);
        }
        return dp_i_0;
    }

    public int maxProfit3(int[] prices) {
        // 假设第i天卖股票,则第i天之前的最小值买入股票利润最大, 依次比较每天卖出股票的利润，取最大值,
        // 记录第i天之前的最小值
        int minValue = Integer.MAX_VALUE;
        int maxBenefit = 0;
        for (int price : prices) {
            if (price < minValue) {
                minValue = price;
            } else {
                maxBenefit = Math.max(maxBenefit, price - minValue);
            }
        }
        return maxBenefit;
    }
}
