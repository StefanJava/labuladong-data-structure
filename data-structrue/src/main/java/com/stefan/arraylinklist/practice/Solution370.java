package com.stefan.arraylinklist.practice;

/**
 * @Description: 370. 区间加法
 * 假设你有一个长度为n的数组，初始情况下所有的数字均为0，你将会被给出k 个更新的操作。
 *
 * 其中，每个操作会被表示为一个三元组：[startIndex, endIndex, inc]，
 * 你需要将子数组A[startIndex ... endIndex]（包括 startIndex 和 endIndex）增加inc。
 *
 * 请你返回k次操作后的数组。
 *
 * @Date: 2023/1/15 12:55
 * @Author: stefanyang
 */
public class Solution370 {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] nums = new int[length];
        int[] diffNums = different(nums);
        for (int i = 0; i < updates.length; i++) {
            increase(diffNums, updates[i][0], updates[i][1], updates[i][2]);
        }
        return result(diffNums);
    }

    // 构建差分数组
    public int[] different(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums;
        }
        int[] diffNums = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            diffNums[i] = nums[i] - nums[i - 1];
        }
        return diffNums;
    }

    // 区间增加
    public void increase(int[] diffNums, int left, int right, int inc) {
        diffNums[left] += inc;
        if (right + 1 < diffNums.length) {
            diffNums[right + 1] -= inc;
        }
    }

    // 由差分数组推导原数组
    public int[] result(int[] diffNums) {
        if (diffNums.length == 0 || diffNums.length == 1) {
            return diffNums;
        }
        int[] res = new int[diffNums.length];
        res[0] = diffNums[0];
        for (int i = 1; i < diffNums.length; i++) {
            res[i] = diffNums[i] + res[i - 1];
        }
        return res;
    }
}
