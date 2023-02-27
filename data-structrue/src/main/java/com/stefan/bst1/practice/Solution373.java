package com.stefan.bst1.practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: 373. 查找和最小的 K 对数字
 * 给定两个以 升序排列 的整数数组 nums1 和 nums2,以及一个整数 k。
 *
 * 定义一对值(u,v)，其中第一个元素来自nums1，第二个元素来自 nums2。
 *
 * 请找到和最小的 k个数对(u1,v1), (u2,v2) ... (uk,vk)。
 *
 * @author: stefanyang
 * @date: 2023/2/27 13:11
 * @version: 1.0
 */
public class Solution373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        // 两个数组都是以升序排列
        // 以nuns1[i]为基点，遍历num2[j],可以形成num1.length条  num[i] + nums[j]的和从小到大的链条
        // 这样可以每次把链表的第一个节点放入优先队列(小顶堆),弹出的都是和最小的数字对
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (a[0] + a[1])));

        // 初始化队列
        for (int i = 0; i < len1; i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }

        while (!pq.isEmpty() && k > 0) {
            int[] minPair = pq.poll();
            int num1 = minPair[0];
            int num2 = minPair[1];
            int j = minPair[2] + 1;
            List<Integer> pair = new ArrayList<>();
            pair.add(num1);
            pair.add(num2);
            res.add(pair);
            k--;
            if (j < len2) {
                pq.offer(new int[]{num1, nums2[j], j});
            }
        }
        return res;
    }
}
