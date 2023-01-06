package com.stefan.arraylinklist.practice;

import com.stefan.arraylinklist.ListNode;

/**
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 */
public class Solution86 {
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode();
        ListNode p1 = dummy1;
        ListNode dummy2 = new ListNode();
        ListNode p2 = dummy2;
        ListNode p = head;
        while (p != null) {
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }
            p = p.next;
        }

        // 清除dummy1和dummy2的连接，避免出现环
        p1.next = null;
        p2.next = null;

        // 拼接
        p1.next = dummy2.next;
        return dummy1.next;
    }
}
