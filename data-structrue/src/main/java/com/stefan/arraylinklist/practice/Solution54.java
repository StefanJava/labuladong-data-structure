package com.stefan.arraylinklist.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 54. 螺旋矩阵
 * @author: stefanyang
 * @date: 2023/3/27 19:59
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int size = matrix.length * matrix[0].length;
        while (res.size() < size) {
            // 上
            if (up <= down) {
                for (int i = left; i <= right; i++) {
                    res.add(matrix[up][i]);
                }
                up++;
            }
            // 右
            if (right >= left) {
                for(int i = up; i <= down; i++) {
                    res.add(matrix[i][right]);
                }
                right--;
            }
            // 下
            if (down >= up) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[down][i]);
                }
                down--;
            }
            // 左
            if (left <= right) {
                for (int i = down; i >= up; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;
    }
}
