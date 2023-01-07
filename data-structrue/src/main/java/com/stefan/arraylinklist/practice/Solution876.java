package com.stefan.arraylinklist.practice;

import com.stefan.arraylinklist.ListNode;

/**
 * 876. 链表的中间结点
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 */
public class Solution876 {
    public ListNode middleNode(ListNode head) {
        if (head == null) return null;
        ListNode fast = head;  // fast一次走两步
        ListNode slow = head;  // slow一次走一步
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
