package com.stefan.arraylinklist.practice;

/**
 * @Description: 240. 搜索二维矩阵 II
 *
 * 编写一个高效的算法来搜索mxn矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * @Date: 2023/2/6 14:43
 * @Author: stefanyang
 */
public class Solution240 {
    public boolean searchMatrix(int[][] matrix, int target) {

        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0;
        int j = n - 1;

        while (i < m && j >= 0) {

            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                // 根据题目矩阵的性质，往左减少，往右增加
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            }

        }

        return false;

    }

}
