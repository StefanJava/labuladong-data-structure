package com.stefan.bst1.practice;

import java.util.PriorityQueue;

/**
 * @description: 295. 数据流的中位数
 * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * <p>
 * 例如 arr = [2,3,4]的中位数是 3。
 * 例如arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * <p>
 * MedianFinder() 初始化 MedianFinder对象。
 * <p>
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * <p>
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差10-5以内的答案将被接受。
 * @author: stefanyang
 * @date: 2023/2/23 20:17
 * @version: 1.0
 */
class MedianFinder {

    private PriorityQueue<Integer> large;

    private PriorityQueue<Integer> small;

    public MedianFinder() {
        // 越小优先级越高  小顶堆
        large = new PriorityQueue<>();
        // 越大优先级越高  大顶堆
        small = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
    }

    public void addNum(int num) {
        if (large.size() >= small.size()) {
            large.offer(num);
            small.offer(large.poll());
        } else {
            small.offer(num);
            large.offer(small.poll());
        }
    }

    public double findMedian() {
        if (large.size() > small.size()) {
            return large.peek();
        } else if (small.size() > large.size()) {
            return small.peek();
        } else {
            return (double) (large.peek() + small.peek()) / 2;
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // 返回 1.5 ((1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }
}

