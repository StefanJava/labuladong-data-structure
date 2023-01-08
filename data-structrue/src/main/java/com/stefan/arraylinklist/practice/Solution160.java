package com.stefan.arraylinklist.practice;

import com.stefan.arraylinklist.ListNode;

/**
 * 160. 相交链表
 */
public class Solution160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode list1 = headA;
        ListNode list2 = headB;
        while (list1 != list2) {
            list1 = list1 == null ? headB : list1.next;
            list2 = list2 == null ? headA : list2.next;
        }
        return list1;
    }
}
