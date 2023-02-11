package com.stefan.arraylinklist.practice;

/**
 * @Description: 977. 有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * @Date: 2023/2/11 13:04
 * @Author: stefanyang
 */
public class Solution977 {
    public int[] sortedSquares(int[] nums) {
        // 双指针（左右指针）
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int[] res = new int[n];
        while (left <= right) {
            if (Math.abs(nums[left]) >= Math.abs(nums[right])) {
                res[n - 1] = nums[left] * nums[left];
                left++;
            } else {
                res[n - 1] = nums[right] * nums[right];
                right--;
            }
            n--;
        }
        return res;
    }
}
