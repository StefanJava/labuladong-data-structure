package com.stefan.arraylinklist.practice;

import com.stefan.arraylinklist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        ListNode dummy = new ListNode();
        ListNode p = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(l -> l.val));
        // 把各链表的头节点加入优先级队列（最小堆）
        for (ListNode list : lists) {
            if (list != null) queue.add(list);
        }

        while (!queue.isEmpty()) {
            ListNode min = queue.poll();
            p.next = min;
            p = p.next;
            if (min.next != null) {
                queue.add(min.next);
            }
        }
        return dummy.next;
    }

}
