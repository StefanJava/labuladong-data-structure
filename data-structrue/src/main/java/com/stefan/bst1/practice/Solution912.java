package com.stefan.bst1.practice;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description: 912. 排序数组
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * @author: stefanyang
 * @date: 2023/3/2 16:32
 * @version: 1.0
 */
public class Solution912 {

    public int[] sortArray(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }
        int count = 0;
        while (!pq.isEmpty()) {
            nums[count++] = pq.poll();
        }
        return nums;
    }

}
