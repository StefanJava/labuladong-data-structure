package com.stefan.dp;

/**
 * @description: 509. 斐波那契数
 * @author: stefanyang
 * @date: 2023/3/13 21:10
 * @version: 1.0
 */
public class Solution509 {

    public int fib2(int n) {
        int[] dp = new int[n + 1];
        return reverse(dp, n);
    }
    public int reverse(int[] dp, int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        return reverse(dp, n - 1) + reverse(dp, n - 2);
    }

    public int fib3(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    public int fib4(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prev = 0;
        int cur = 1;
        for (int i = 2; i <= n; i++) {
            // 计算f(i)
            int sum = cur + prev;
            prev = cur;
            cur = sum;
        }
        return cur;
    }
}
