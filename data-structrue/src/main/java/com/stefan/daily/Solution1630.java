package com.stefan.daily;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 1630. 等差子数组
 * @author: stefanyang
 * @date: 2023/3/23 16:28
 * @version: 1.0
 */
public class Solution1630 {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        // 暴力破解
        int n = l.length;
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(check(nums, l[i], r[i]));
        }
        return res;
    }
    private Boolean check(int[] nums, int start, int end) {
        // 找出子数组中的最大值和最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // Set集合保存 子数组的值  后序进行判断
        Set<Integer> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            set.add(nums[i]);
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        // 最大值和最小值相等 说明数组中所有元素都相等
        if (max == min) {
            return true;
        }
        // 数组中有相同元素  且不都相同
        int len = (end - start + 1);
        if (set.size() != len) {
            return false;
        }
        // 求出差值
        int d = (max - min) / (len - 1);
        for (int i = 1; i <= len; i++) {
            int val = (i - 1) * d + min;
            if (!set.contains(val)) {
                return false;
            }
        }
        return true;
    }
}
