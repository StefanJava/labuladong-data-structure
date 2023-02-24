package com.stefan.bst1.practice;

import java.util.PriorityQueue;

/**
 * @description: 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * @author: stefanyang
 * @date: 2023/2/24 10:51
 * @version: 1.0
 */
public class Solution215 {
    public int findKthLargest(int[] nums, int k) {
        // 小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            pq.offer(nums[i]);
            if (pq.size() > k) {
                // 当队列中元素个数>k时,弹出元素
                pq.poll();
            }
        }
        return pq.peek();
    }
}
