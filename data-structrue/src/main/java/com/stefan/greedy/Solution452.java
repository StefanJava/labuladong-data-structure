package com.stefan.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description: 452. 用最少数量的箭引爆气球
 * @author: stefanyang
 * @date: 2023/4/10 11:38
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution452 {
    public int findMinArrowShots(int[][] points) {
        // 根据题意，即是求无重叠区间
        // 将二维数组按x-end从大到小排序
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int count = 1;
        int xEnd = points[0][1];
        int len = points.length;
        for (int i = 1; i < len; i++) {
            if (points[i][0] > xEnd) {
                count++;
                xEnd = points[i][1];
            }
        }
        return count;
    }
}
