package com.stefan.arraylinklist.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Description: 496. 下一个更大元素 I
 * nums1中数字x的 下一个更大元素 是指x在nums2 中对应位置 右侧 的 第一个 比x大的元素。
 *
 * 给你两个 没有重复元素 的数组nums1 和nums2 ，下标从 0 开始计数，其中nums1是nums2的子集。
 *
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 *
 * 返回一个长度为nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 * @Date: 2023/2/9 11:58
 * @Author: stefanyang
 */
public class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] tmp = new int[nums2.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            tmp[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums2[i]);
        }

        Map<Integer, Integer> map = new HashMap<>(16);

        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], tmp[i]);
        }

        int[] res = new int[nums1.length];
        for (int j = 0; j < nums1.length; j++) {
            int next = map.get(nums1[j]);
            res[j] = next;
        }

        return res;
    }
}
