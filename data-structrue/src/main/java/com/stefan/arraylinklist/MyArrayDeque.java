package com.stefan.arraylinklist;

import java.util.NoSuchElementException;

/**
 * @Description: 用数组实现双端队列
 * @Date: 2023/2/7 10:23
 * @Author: stefanyang
 */
public class MyArrayDeque<E> {

    private int size;

    private E[] data;

    private final static int INIT_CAP = 2;

    /**
     * data的索引区间[first, last)存储着元素
     */
    private int first, last;

    public MyArrayDeque(int initCap) {
        this.size = 0;
        this.data = (E[]) new Object[initCap];
        this.first = this.last = 0;
    }

    public MyArrayDeque() {
        this(INIT_CAP);
    }

    /******** 增 ********/
    public void addFirst(E e) {
        if (size == data.length) {
            resize(size * 2);
        }
        first = (first - 1 + data.length) % data.length;
        data[first] = e;
        size++;
    }

    public void addLast(E e) {
        if (size == data.length) {
            resize(size * 2);
        }
        data[last] = e;
        last = (last + 1) % data.length;
        size++;
    }

    /******** 删 ********/
    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("index out of bounds");
        }
        if (size == data.length / 4) {
            resize(data.length / 2);
        }
        E result = data[first];
        data[first] = null;
        first = (first + 1) % data.length;
        size--;
        return result;
    }

    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("index out of bounds");
        }
        if (size == data.length / 4) {
            resize(data.length / 2);
        }
        E result = data[last - 1];
        data[last - 1] = null;
        last = (last - 1 + data.length) % data.length;
        size--;
        return result;
    }

    /******** 查 ********/
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("index out of bounds");
        }
        return data[first];
    }

    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException("index out of bounds");

        }
        return data[(last - 1 + data.length) % data.length];
    }

    /******** 工具函数 ********/
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void resize(int newCap) {
        E[] temp = (E[]) new Object[newCap];
        for (int i = 0; i < size; i++) {
            temp[i] = data[(this.first + i) % data.length];
        }
        this.first = 0;
        this.last = size;
        this.data = temp;
    }
}
