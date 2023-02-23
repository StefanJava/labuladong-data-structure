package com.stefan.bst1.practice;

/**
 * @description: 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * @author: stefanyang
 * @date: 2023/2/23 10:55
 * @version: 1.0
 */
public class Solution92 {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        if (left == 1) {
            return reverse(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    private ListNode tail;

    public ListNode reverse(ListNode head, int right) {
        if (right == 1) {
            tail = head.next;
            return head;
        }
        ListNode next = reverse(head.next, right - 1);
        head.next.next = head;
        head.next = tail;
        return next;
    }
}
