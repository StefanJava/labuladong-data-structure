package com.stefan.traverse;

import com.stefan.arraylinklist.ListNode;

import java.util.Stack;

/**
 * @description: 234. 回文链表
 * @author: stefanyang
 * @date: 2023/3/24 12:16
 * @version: 1.0
 */
public class Solution234 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(isPalindrome3(head));
    }

    public static boolean isPalindrome3(ListNode head) {
        // 通过快慢找出链表的中点  翻转中点之后的节点
        ListNode fast = head;
        ListNode slow = head;
        ListNode p = head;
        while (fast != null && fast.next != null) {
            p = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            // 节点个数为奇数
            p = slow;
            slow = slow.next;
        }
        ListNode left = head;
        // 从slow开始翻转
        ListNode right = reverse(slow);
        ListNode q = right;
        // 各自从left, right遍历比较
        while (right != null) {
            if (left.val != right.val) {
                p.next = reverse(q);
                return false;
            }
            left = left.next;
            right = right.next;
        }
        p.next = reverse(q);
        return true;
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
    private ListNode left;
    public boolean isPalindrome2(ListNode head) {
        left = head;
        return traverse(left);
    }
    private boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        res = res && (left.val == right.val);
        left = left.next;
        return res;
    }

    public boolean isPalindrome1(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode p = head;
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        p = head;
        while (p != null) {
            if (stack.pop().val != p.val) {
                return false;
            }
            p = p.next;
        }
        return true;
    }
}
