package com.stefan.bst1.practice;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * @description: 1845. 座位预约管理系统
 * 请你设计一个管理 n个座位预约的系统，座位编号从1到n。
 *
 * 请你实现SeatManager类：
 *
 * SeatManager(int n)初始化一个SeatManager对象，它管理从 1到 n编号的n个座位。所有座位初始都是可预约的。
 * int reserve()返回可以预约座位的最小编号，此座位变为不可预约。
 * void unreserve(int seatNumber)将给定编号seatNumber对应的座位变成可以预约
 * @author: stefanyang
 * @date: 2023/3/5 14:28
 * @version: 1.0
 */
public class Solution1845 {
    public static void main(String[] args) {
        SeatManager seatManager = new SeatManager(10);
        System.out.println(seatManager.reserve());
        System.out.println(seatManager.reserve());
        System.out.println(seatManager.reserve());
        System.out.println(seatManager.reserve());
        System.out.println(seatManager.reserve());
        System.out.println(seatManager.reserve());
        seatManager.unreserve(1);
        System.out.println(seatManager.reserve());
    }
}

class SeatManager {

    private PriorityQueue<Integer> pq = new PriorityQueue<>();

    public SeatManager(int n) {
        while (n > 0) {
            pq.offer(n);
            n--;
        }
    }

    public int reserve() {
        if (pq.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return pq.poll();
    }

    public void unreserve(int seatNumber) {
        pq.offer(seatNumber);
    }
}