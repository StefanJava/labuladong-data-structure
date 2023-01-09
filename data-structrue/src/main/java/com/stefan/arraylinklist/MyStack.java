package com.stefan.arraylinklist;

public class MyStack<E> {

    private final MyLinkedList<E> myLinkedList = new MyLinkedList<>();

    public void push(E e) {
        myLinkedList.addLast(e);
    }

    public E pop() {
        return myLinkedList.removeLast();
    }

    public E peek() {
        return myLinkedList.getLast();
    }

}
