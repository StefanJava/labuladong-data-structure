package com.stefan.arraylinklist.practice;

/**
 * @Description: 1094. 拼车
 * 车上最初有capacity个空座位。车只能向一个方向行驶（也就是说，不允许掉头或改变方向）
 *
 * 给定整数capacity和一个数组 trips , trip[i] = [numPassengersi, fromi, toi]表示第 i 次旅行有numPassengersi乘客，
 * 接他们和放他们的位置分别是fromi和toi。这些位置是从汽车的初始位置向东的公里数。
 *
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回true，否则请返回 false。
 *
 * @Date: 2023/1/15 14:14
 * @Author: stefanyang
 */
public class Solution1094 {

    public boolean carPooling(int[][] trips, int capacity) {
        int[] nums = new int[1001];
        int[] diffNums = different(nums);
        for (int i = 0; i < trips.length; i++) {
            increase(diffNums, trips[i][1], trips[i][2] - 1, trips[i][0]);
        }
        int[] res = result(diffNums);
        for (int i = 0; i < res.length; i++) {
            if (res[i] > capacity) {
                return false;
            }
        }
        return true;
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
