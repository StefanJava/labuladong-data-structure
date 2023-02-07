package com.stefan.arraylinklist.practice;

import com.stefan.arraylinklist.ListNode;

import java.util.Stack;

/**
 * @Description: 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * @Date: 2023/2/7 15:36
 * @Author: stefanyang
 */
public class Solution143 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode tmp = head;
        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
        }
        tmp = head;
        while (tmp != null) {
            ListNode lastNode = stack.pop();
            ListNode next = tmp.next;
            if (lastNode == next || lastNode.next == next) {
                lastNode.next = null;
                break;
            }
            tmp.next = lastNode;
            lastNode.next = next;
            tmp = next;
        }
    }
}
