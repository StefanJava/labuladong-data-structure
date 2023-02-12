package com.stefan.arraylinklist.practice;

/**
 * @Description: 6354. 找出数组的串联值
 * 给你一个下标从 0 开始的整数数组nums 。
 *
 * 现定义两个数字的 串联是由这两个数值串联起来形成的新数字。
 *
 * 例如，15和49的串联是1549 。
 * nums的 串联值最初等于 0 。执行下述操作直到nums变为空：
 *
 * 如果nums中存在不止一个数字，分别选中 nums 中的第一个元素和最后一个元素，将二者串联得到的值加到nums的 串联值 上，然后从nums中删除第一个和最后一个元素。
 * 如果仅存在一个元素，则将该元素的值加到nums 的串联值上，然后删除这个元素。
 * 返回执行完所有操作后nums 的串联值。
 * @Date: 2023/2/12 14:15
 * @Author: stefanyang
 */
public class Solution6354 {
    public long findTheArrayConcVal(int[] nums) {
        long res = 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            res += Long.parseLong(String.valueOf(nums[left]) + nums[right]);
            left++;
            right--;
        }
        if (left == right) {
            res += nums[left];
        }
        return res;
    }
}
