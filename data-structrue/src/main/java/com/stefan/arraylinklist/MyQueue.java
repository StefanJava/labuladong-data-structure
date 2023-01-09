package com.stefan.arraylinklist;

public class MyQueue<E> {

    private final MyLinkedList<E> myLinkedList = new MyLinkedList<>();

    public void enqueue(E e) {
        myLinkedList.addLast(e);
    }

    public E dequeue() {
        return myLinkedList.removeFirst();
    }

    public E peek() {
        return myLinkedList.getFirst();
    }

}
