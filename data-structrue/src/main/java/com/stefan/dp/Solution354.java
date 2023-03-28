package com.stefan.dp;

import java.util.Arrays;

/**
 * @description: 354.俄罗斯套娃信封问题
 * @author: stefanyang
 * @date: 2023/3/28 11:36
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution354 {
    public int maxEnvelopes(int[][] envelopes) {
        // 对envelopes进行排序, 先按宽度升序, 后按高度降序
        // 因为宽度或高度相同, 都不可嵌套, 所以相同宽度的信封, 必不可装, 按高度降序可以避免这个
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int[] heights = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }
        return longestIncSeq(heights);
    }
    private int longestIncSeq(int[] heights) {
        int len = heights.length;
        int[] tops = new int[len];
        int piles = 0;
        for (int height : heights) {
            int left = 0;
            int right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tops[mid] > height) {
                    right = mid;
                } else if (tops[mid] < height) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left == piles) {
                piles++;
            }
            tops[left] = height;
        }
        return piles;
    }
}
