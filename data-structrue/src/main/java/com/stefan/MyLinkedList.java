package com.stefan;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> implements Iterable<E> {

    // 虚拟头尾节点
    private final Node<E> head, tail;
    private int size;

    // 双链表节点
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    // 构造函数初始化头尾节点
    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }


    /***** 增 *****/

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        // temp tail
        Node<E> temp = tail.prev;
        newNode.next = tail;
        newNode.prev = temp;

        temp.next = newNode;
        tail.prev = newNode;
        size++;
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> temp = head.next;
        // temp head
        newNode.next = temp;
        newNode.prev = head;

        temp.prev = newNode;
        head.next = newNode;
        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == 0) {
            addFirst(element);
            return;
        }

        if (index == size) {
            addLast(element);
            return;
        }

        Node<E> newNode = new Node<>(element);
        Node<E> nextNode = getNode(index);
        Node<E> prevNode = nextNode.prev;

        newNode.next = nextNode;
        newNode.prev = prevNode;

        prevNode.next = newNode;
        newNode.prev = newNode;
        size++;
    }

    /***** 删 *****/

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> delNode = head.next;
        head.next = delNode.next;
        delNode.next.prev = head;

        delNode.next = null;
        delNode.prev = null;
        size--;
        return delNode.val;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> delNode = tail.prev;
        tail.prev = delNode.prev;
        delNode.prev.next = tail;

        delNode.next = null;
        delNode.prev = null;
        size--;
        return delNode.val;
    }

    public E remove(int index) {
        checkElementIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        if (index == size - 1) {
            return removeLast();
        }

        Node<E> delValue = getNode(index);
        delValue.next.prev = delValue.prev;
        delValue.prev.next = delValue.next;

        delValue.next = null;
        delValue.prev = null;
        size--;
        return delValue.val;
    }

    /***** 查 *****/

    public E get(int index) {
        checkElementIndex(index);
        Node<E> current = head.next;
        for (int i = 0; ; i++) {
            if (i == index) {
                return current.val;
            }
            current = current.next;
        }
    }

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.prev.val;
    }

    /***** 改 *****/

    public E set(int index, E val) {
        checkElementIndex(index);
        Node<E> oldNode = getNode(index);
        E oldVal = oldNode.val;
        oldNode.val = val;
        return oldVal;
    }

    /***** 其他工具函数 *****/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> getNode(int index) {
        Node<E> current = head.next;
        for (int i = 0; ; i++) {
            if (i == index) {
                return current;
            }
            current = current.next;
        }
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
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void display() {
        System.out.println("size = " + size);
        for (Node<E> p = head.next; p != tail; p = p.next) {
            System.out.print(p.val + " -> ");
        }
        System.out.println("null");
        System.out.println();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }
        };
    }

    public static void main(String[] args) {
        // 初始容量设置为 3
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        // 添加 5 个元素
        for (int i = 0; i < 5; i++) {
            myLinkedList.addLast(i);
        }

        myLinkedList.set(1, 9);
        myLinkedList.remove(2);
        myLinkedList.add(4, 100);
        myLinkedList.addLast(8);
        myLinkedList.removeFirst();
        myLinkedList.remove(4);
        myLinkedList.removeLast();

        for (int i = 0; i < myLinkedList.size(); i++) {
            System.out.println(myLinkedList.get(i));
        }
    }
}
