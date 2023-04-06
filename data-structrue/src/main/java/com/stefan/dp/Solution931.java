package com.stefan.dp;

import java.util.Arrays;

/**
 * @description: 931. 下降路径最小和
 * @author: stefanyang
 * @date: 2023/4/6 16:37
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution931 {
    private int[][] memo;
    public int minFallingPathSum(int[][] matrix) {
        int res = Integer.MAX_VALUE;
        int n = matrix.length;
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], 100001);
        }
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dp(matrix, n - 1, j));
        }
        return res;
    }
    private int dp(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix.length) {
            return 100001;
        }

        if (i == 0) {
            return matrix[0][j];
        }

        if (memo[i][j] != 100001) {
            return memo[i][j];
        }

        memo[i][j] = matrix[i][j] + Math.min(
                // 正下方
                dp(matrix, i - 1, j),
                Math.min(
                        // 左下方
                        dp(matrix, i - 1, j + 1),
                        // 右下方
                        dp(matrix, i - 1, j - 1)
                )
        );

        return memo[i][j];
    }
}
