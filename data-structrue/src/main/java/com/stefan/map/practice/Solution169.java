package com.stefan.map.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 169. 多数元素
 * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * @author: stefanyang
 * @date: 2023/2/16 11:38
 * @version: 1.0
 */
public class Solution169 {
    public int majorityElement1(int[] nums) {
        // 用哈希表记录每个数出现的次数，返回出现次数最大的那个元素
        int target = 0;
        int max = 0;
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < len; i++) {
            int count = map.getOrDefault(nums[i], 0) + 1;
            map.put(nums[i], count);
            if (max < count) {
                max = count;
                target = nums[i];
            }
        }
        return target;
    }
    public int majorityElement2(int[] nums) {
        // 依据题意，总存在多数元素
        // 因此，遍历数组，将多数元素与其他元素抵消，剩下的就是多数元素
        // 用count记录多数元素剩余的个数，如果count==0时，则重新赋值多数元素
        int target = 0;
        int count = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (count == 0) {
                target = nums[i];
                count++;
            } else if (nums[i] == target) {
                count++;
            } else if (nums[i] != target) {
                count--;;
            }
        }
        return target;
    }
}
