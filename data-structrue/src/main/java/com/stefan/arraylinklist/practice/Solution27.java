package com.stefan.arraylinklist.practice;

/**
 * @Description: 27. 移除元素
 * 给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 *
 * @Date: 2023/1/10 11:27
 * @Author: stefanyang
 */
public class Solution27 {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val && nums[right] != val) {
                nums[left] = nums[right];
                left++;
                right--;
            } else if (nums[left] != val && nums[right] != val) {
                left++;
            } else {
                right--;
            }
        }
        return right + 1;
    }
}
