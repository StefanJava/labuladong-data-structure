package com.stefan.arraylinklist.practice;

/**
 * @Description: 74. 搜索二维矩阵
 * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * @Date: 2023/2/3 12:30
 * @Author: stefanyang
 */
public class Solution74 {

    public boolean searchMatrix(int[][] matrix, int target) {
        // 把访问二维数组转变成一维数组 index = i * n + j, i = index / n, j = index % n
        int left = 0;
        int right = matrix.length * matrix[0].length;
        // 左闭右开
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (get(matrix, mid) == target) {
                return true;
            } else if (get(matrix, mid) < target) {
                left = mid + 1;
            } else if (get(matrix, mid) > target) {
                right = mid;
            }
        }
        return false;
    }

    public int get(int[][] matrix, int index) {
        int i = index / matrix[0].length;
        int j = index % matrix[0].length;
        return matrix[i][j];
    }

}
