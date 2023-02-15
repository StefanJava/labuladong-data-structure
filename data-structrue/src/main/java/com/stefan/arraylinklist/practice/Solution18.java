package com.stefan.arraylinklist.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 18. 四数之和
 * 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]
 * （若两个四元组元素一一对应，则认为两个四元组重复）：
 * 0 <= a, b, c, d< n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * @author: stefanyang
 * @date: 2023/2/15 10:28
 * @version: 1.0
 */
public class Solution18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return compteNSum(nums, 4, 0, target);
    }

    public List<List<Integer>> compteNSum(int[] nums, int n, int start, long target) {

        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;

        if (len <= 0 || len < n) {
            return result;
        }

        int left = start;
        int right = len - 1;

        if (n == 2) {
            while (left < right) {
                long sum = nums[left] + nums[right];
                int leftVal = nums[left];
                int rightVal = nums[right];
                if (sum < target) {
                    while (left < right && nums[left] == leftVal) {
                        left++;
                    }
                } else if (sum > target) {
                    while (left < right && nums[right] == rightVal) {
                        right--;
                    }
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(leftVal);
                    list.add(rightVal);
                    result.add(list);
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
                List<List<Integer>> prevList = compteNSum(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> tmpList : prevList) {
                    tmpList.add(nums[i]);
                    result.add(tmpList);
                }
                while (i < len - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }

        return result;

    }

}
