package com.stefan.arraylinklist.practice;

/**
 * @Description: 303. 区域和检索 - 数组不可变
 * 给定一个整数数组 nums，处理以下类型的多个查询:
 *
 * 计算索引left和right（包含 left 和 right）之间的 nums 元素的 和 ，其中left <= right
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums中索引left和right之间的元素的 总和 ，
 * 包含left和right两点（也就是nums[left] + nums[left + 1] + ... + nums[right])
 *
 * @Date: 2023/1/14 14:46
 * @Author: stefanyang
 */
public class Solution303 {

    class NumArray {
        // 前缀
        private int[] preNums;

        public NumArray(int[] nums) {
            // preNums[0] = 0，便于计算前缀和
            preNums = new int[nums.length + 1];
            for (int i = 1; i < preNums.length; i++) {
                preNums[i] = preNums[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            return preNums[right + 1] - preNums[left];
        }
    }

}
