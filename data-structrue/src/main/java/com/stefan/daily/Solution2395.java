package com.stefan.daily;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 2395. 和相等的子数组
 * @author: stefanyang
 * @date: 2023/3/26 12:57
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution2395 {
    public boolean findSubarrays(int[] nums) {
        // 根据题意，可以用哈希表存储子数组的和，遍历数组，判断哈希表中是否存在子数组的和
        Set<Integer> subSums = new HashSet<>();
        int n = nums.length - 1;
        for (int i = 0; i < n; i++) {
            int sum = nums[i] + nums[i + 1];
            if (!subSums.add(sum)) {
                // 如果已存在该和，则返回true
                return true;
            }
        }
        return false;
    }
}
