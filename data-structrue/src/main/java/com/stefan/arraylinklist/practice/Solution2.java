package com.stefan.arraylinklist.practice;

import com.stefan.arraylinklist.ListNode;

/**
 * @Description: 2. 两数相加
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * @Date: 2023/2/2 21:47
 * @Author: stefanyang
 */
public class Solution2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        int inc = 0;
        while (l1 != null || l2 != null) {

            int newVal = 0;
            if (l1 == null) {
                newVal = l2.val + inc;
                l2 = l2.next;
            } else if (l2 == null) {
                newVal = l1.val + inc;
                l1 = l1.next;
            } else if (l1 != null && l2 != null) {
                newVal = l1.val + l2.val + inc;
                l1 = l1.next;
                l2 = l2.next;
            }

            inc = newVal / 10;
            newVal = newVal % 10;
            ListNode newNode = new ListNode(newVal);
            current.next = newNode;
            current = current.next;
        }
        if (inc != 0) {
            ListNode newNode = new ListNode(inc);
            current.next = newNode;
            current = current.next;
        }
        return dummy.next;
    }

}
