package com.stefan.bst1.practice;

/**
 * @description: 节点
 * @author: stefanyang
 * @date: 2023/2/23 10:56
 * @version: 1.0
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
