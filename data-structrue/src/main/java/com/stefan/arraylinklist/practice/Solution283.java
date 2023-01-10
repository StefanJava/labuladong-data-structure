package com.stefan.arraylinklist.practice;

/**
 * @Description: 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * @Date: 2023/1/10 11:33
 * @Author: stefanyang
 */
public class Solution283 {
    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        for (; index < nums.length; index++) {
            nums[index] = 0;
        }
    }
}
