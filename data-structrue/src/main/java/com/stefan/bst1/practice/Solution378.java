package com.stefan.bst1.practice;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @description: 378. 有序矩阵中第 K 小的元素
 * 给你一个n x n矩阵matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 *
 * 你必须找到一个内存复杂度优于O(n2) 的解决方案。
 * @author: stefanyang
 * @date: 2023/2/25 19:41
 * @version: 1.0
 */
public class Solution378 {
    public int kthSmallest(int[][] matrix, int k) {
        // 小顶堆
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // 把每条链的第一个节点加入队列
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});  // 存储值，第几行，第几列  为了弹出时，可以加入后一个节点
        }

        int res = -1;

        while (!pq.isEmpty() && k > 0) {
            int[] min = pq.poll();
            res = min[0];
            k--;
            int i = min[1];
            int j = min[2];
            if (j + 1 < matrix[i].length) {
                pq.offer(new int[]{matrix[i][j + 1], i, j + 1});
            }
        }

        return res;
    }
}
