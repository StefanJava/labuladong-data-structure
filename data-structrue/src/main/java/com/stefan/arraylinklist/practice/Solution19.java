package com.stefan.arraylinklist.practice;

import com.stefan.arraylinklist.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) return null;
        ListNode fast = head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy;
        int count = 0;
        while (fast != null) {
            fast = fast.next;
            if (count >= n) {
                slow = slow.next;
            }
            count++;
        }
        ListNode tmp = slow.next;
        slow.next = tmp.next;
        tmp.next = null;
        return dummy.next;
    }
}
