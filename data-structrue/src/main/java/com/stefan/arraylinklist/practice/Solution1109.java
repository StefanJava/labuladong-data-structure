package com.stefan.arraylinklist.practice;

/**
 * @Description: 1109. 航班预订统计
 * 这里有n个航班，它们分别从 1 到 n 进行编号。
 *
 * 有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]
 * 意味着在从 firsti到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。
 *
 * 请你返回一个长度为 n 的数组answer，里面的元素是每个航班预定的座位总数。
 *
 * @Date: 2023/1/15 13:34
 * @Author: stefanyang
 */
public class Solution1109 {

    public int[] corpFlightBookings(int[][] bookings, int n) {

        int[] nums = new int[n];
        int[] diffNums = different(nums);
        for (int i = 0; i < bookings.length; i++) {
            increase(diffNums, bookings[i][0], bookings[i][1], bookings[i][2]);
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
        diffNums[left - 1] += inc;
        if (right < diffNums.length) {
            diffNums[right] -= inc;
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
