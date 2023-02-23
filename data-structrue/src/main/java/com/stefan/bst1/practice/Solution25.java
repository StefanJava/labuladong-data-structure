package com.stefan.bst1.practice;

/**
 * @description: 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每k个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * @author: stefanyang
 * @date: 2023/2/23 11:55
 * @version: 1.0
 */
public class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode a = head;
        ListNode b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    // 翻转k个节点
    public ListNode reverse(ListNode a, ListNode b) {
        ListNode prev = null;
        ListNode cur = a;
        while (cur != b) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
