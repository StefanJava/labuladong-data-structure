package com.stefan.map.practice;

/**
 * @description: 节点
 * @author: stefanyang
 * @date: 2023/2/17 10:39
 * @version: 1.0
 */
public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
