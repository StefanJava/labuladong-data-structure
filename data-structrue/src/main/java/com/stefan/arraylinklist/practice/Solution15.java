package com.stefan.arraylinklist.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 15. 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 *
 * 你返回所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 * @author: stefanyang
 * @date: 2023/2/14 17:15
 * @version: 1.0
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        return computeNSum(nums, 3, 0, 0);
    }

    public List<List<Integer>> computeNSum(int[] nums, int n, int start, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (n < 2 || len < n) {
            return res;
        }

        if (n == 2) {
            int left = start;
            int right = len - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                int leftVal = nums[left];
                int rightVal = nums[right];
                if (sum > target) {
                    while (left < right && nums[right] == rightVal) {
                        right--;
                    }
                } else if (sum < target) {
                    while (left < right && nums[left] == leftVal) {
                        left++;
                    }
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    while (left < right && nums[left] == leftVal) {
                        left++;
                    }
                    while (left < right && nums[right] == rightVal) {
                        right--;
                    }
                }
            }
        } else {

            for (int i = start; i < len; i++) {
                List<List<Integer>> tmpList = computeNSum(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> list : tmpList) {
                    list.add(nums[i]);
                    res.add(list);
                }
                while (i < len - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }
}
