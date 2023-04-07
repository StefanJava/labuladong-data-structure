package com.stefan.greedy;

import java.util.Arrays;

/**
 * @description: 435. 无重叠区间
 * @author: stefanyang
 * @date: 2023/4/7 11:46
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        int row = intervals.length;
        // 把二维数组按end升序排序
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        // 区间不重叠的最大数量
        int count = 1;
        int end = intervals[0][1];
        for (int[] x : intervals) {
            int start = x[0];
            if (start >= end) {
                count++;
                end = x[1];
            }
        }
        return row - count;
    }
}
