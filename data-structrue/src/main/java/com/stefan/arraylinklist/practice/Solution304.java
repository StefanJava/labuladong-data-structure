package com.stefan.arraylinklist.practice;

/**
 * @Description: 304. 二维区域和检索 - 矩阵不可变
 * 给定一个二维矩阵 matrix，以下类型的多个请求：
 *
 * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1,col1) ，右下角 为 (row2,col2) 。
 * 实现 NumMatrix 类：
 *
 * NumMatrix(int[][] matrix)给定整数矩阵 matrix 进行初始化
 * int sumRegion(int row1, int col1, int row2, int col2)返回
 * 左上角 (row1,col1)、右下角(row2,col2) 所描述的子矩阵的元素 总和 。
 *
 * @Date: 2023/1/14 16:19
 * @Author: stefanyang
 */
public class Solution304 {

    static class NumMatrix {

        private final int[][] preMatrix;

        public NumMatrix(int[][] matrix) {
            // 行
            int n = matrix.length;
            // 列
            int m = matrix[0].length;
            preMatrix = new int[n + 1][m + 1];
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    preMatrix[i][j] = preMatrix[i][j - 1] + preMatrix[i - 1][j] - preMatrix[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preMatrix[row2 + 1][col2 + 1] - preMatrix[row1][col2 + 1] - preMatrix[row2 + 1][col1] + preMatrix[row1][col1];
        }
    }

}
