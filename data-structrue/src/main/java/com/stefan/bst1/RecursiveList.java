package com.stefan.bst1;

import com.stefan.bst1.practice.ListNode;

import java.util.NoSuchElementException;

/**
 * @description: 单向链表递归实现
 * @author: stefanyang
 * @date: 2023/2/23 12:12
 * @version: 1.0
 */
public class RecursiveList<E> {
    // 单链表链表节点
    private static class Node<E> {
        E val;
        Node<E> next;

        Node(E val) {
            this.val = val;
        }
    }

    private Node<E> first = null;

    private int size = 0;

    public RecursiveList() {
    }

    /***** 增 *****/

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> next = first;
        first = newNode;
        newNode.next = next;
        size++;
    }

    public void addLast(E e) {
        first = addLast(first, e);
        size++;
    }

    private Node<E> addLast(Node<E> first, E e) {
        if (first == null) {
            return new Node<>(e);
        }
        first.next = addLast(first.next, e);
        return first;
    }

    public void add(int index, E e) {
        checkPositionIndex(index);
        if (index == 0) {
            addFirst(e);
            return;
        }

        if (index == size) {
            addLast(e);
            return;
        }

        first = add(first, index, e);
        size++;
    }

    private Node<E> add(Node<E> node, int index, E e) {
        if (index == 0) {
            Node<E> newNode = new Node<>(e);
            newNode.next = node;
            return newNode;
        }
        node.next = add(node.next, index - 1, e);
        return node;
    }

    /***** 删 *****/

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        E val = first.val;
        first = first.next;
        size--;
        return val;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        first = removeLast(first);
        size--;
    }

    // x -> y -> null
    private Node<E> removeLast(Node<E> node) {
        if (node.next == null) {
            return null;
        }
        node.next = removeLast(node.next);
        return node;
    }

    public void remove(int index) {
        checkElementIndex(index);
        first = remove(first, index);
        size--;
    }

    private Node<E> remove(Node<E> node, int index) {
        if (index == 0) {
            return node.next;
        }
        node.next = remove(node.next, index - 1);
        return node;
    }

    /***** 查 *****/

    public E getFirst() {
        return first.val;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("list is empty");
        }
        return getLast(first);
    }

    private E getLast(Node<E> node) {
        if (node.next == null) {
            return node.val;
        }
        return getLast(node.next);
    }

    public E IndexOf(int index) {
        checkElementIndex(index);
        return getNode(index).val;
    }

    /***** 改 *****/

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> node = getNode(index);
        E oldVal = node.val;
        node.val = element;
        return oldVal;
    }

    /***** 其他工具函数 *****/
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 返回 index 对应的 Node
    // 注意：请保证传入的 index 是合法的
    private Node<E> getNode(int index) {
        checkElementIndex(index);
        return getNode(first, index);
    }

    private Node<E> getNode(Node<E> first, int index) {
        if (index == 0) {
            return first;
        }
        return getNode(first.next, index - 1);
    }

    public static void main(String[] args) {
        RecursiveList<Integer> list = new RecursiveList<>();
        list.addFirst(1);
        list.addLast(2);
        list.addFirst(3);
        list.add(1, 4);
        list.removeFirst();
        list.remove(2);
        list.add(2, 5);
        list.set(1, 6);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.IndexOf(1));
    }

}
