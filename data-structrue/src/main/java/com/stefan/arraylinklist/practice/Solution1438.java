package com.stefan.arraylinklist.practice;

import java.util.LinkedList;

/**
 * @Description: 1438. 绝对差不超过限制的最长连续子数组
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 *
 * 如果不存在满足条件的子数组，则返回 0 。
 * @Date: 2023/2/9 15:31
 * @Author: stefanyang
 */
public class Solution1438 {
    public int longestSubarray(int[] nums, int limit) {
        int left = 0, right = 0;
        MonotonicQueue<Integer> mq = new MonotonicQueue<>();
        int windowSize = 0;
        int result = 0;
        while (right < nums.length) {

            mq.push(nums[right]);
            right++;
            windowSize++;
            while (mq.max() - mq.min() > limit) {
                mq.pop();
                left++;
                windowSize--;
            }
            result = Math.max(result, windowSize);
        }
        return result;
    }
}

class MonotonicQueue<E extends Comparable<E>> {
    // 存储元素
    private LinkedList<E> data = new LinkedList<>();
    // 单调递减队列
    private LinkedList<E> maxq = new LinkedList<>();
    // 单调递增队列
    private LinkedList<E> minq = new LinkedList<>();

    public void push(E e) {
        // 常规入队
        data.addLast(e);

        while (!maxq.isEmpty() && maxq.getLast().compareTo(e) < 0) {
            maxq.pollLast();
        }
        maxq.addLast(e);

        while (!minq.isEmpty() && minq.getLast().compareTo(e) > 0) {
            minq.pollLast();
        }
        minq.addLast(e);
    }

    public E max() {
        return maxq.getFirst();
    }

    public E min() {
        return minq.getFirst();
    }

    public E pop() {
        E e = data.pollFirst();
        assert e != null;
        if (!maxq.isEmpty() && maxq.getFirst().compareTo(e) == 0) {
            maxq.pollFirst();
        }
        if (!minq.isEmpty() && minq.getFirst().compareTo(e) == 0) {
            minq.pollFirst();
        }
        return e;
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }
}
