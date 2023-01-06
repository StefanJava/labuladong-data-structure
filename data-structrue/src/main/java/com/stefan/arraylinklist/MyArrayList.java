package com.stefan.arraylinklist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {

    /**
     * 真正存储数据
     */
    private E[] data;

    /**
     * 记录当前数组中元素的个数
     */
    private int size;

    /**
     * 默认初始容量
     */
    private static final int INIT_CAP = 1;

    public MyArrayList() {
        this(INIT_CAP);
    }

    public MyArrayList(int initCapacity) {
        this.data = (E[]) new Object[initCapacity];
        this.size = 0;
    }

    /***** 增 ******/

    /**
     * 在数组尾部添加一个元素
     *
     * @param e 添加的元素
     */
    public void addLast(E e) {
        if (data.length == size) {
            // 扩容
            resize(data.length * 2);
        }

        data[size] = e;
        size++;
    }

    /**
     * 在index索引的位置添加一个元素
     *
     * @param index 索引
     * @param e     元素
     */
    public void add(int index, E e) {
        checkPositionIndex(index);
        // data[index..] -> data[index + 1...]
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = e;
        size++;
    }

    /****** 删 ******/

    /**
     * 删除数组的最后一个元素并返回
     *
     * @return 删除的元素
     */
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // 缩容
        if (size < data.length / 4) {
            resize(data.length / 2);
        }
        E delValue = data[size - 1];
        data[size - 1] = null;
        size--;
        return delValue;
    }

    /**
     * 删除index对应的元素并返回
     *
     * @param index 索引位置
     * @return 删除的元素
     */
    public E remove(int index) {
        checkElementIndex(index);

        // 缩容
        if (size < data.length / 4) {
            resize(data.length / 2);
        }

        // data[index + 1...] -> data[index...]
        E delValue = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[size - 1] = null;
        size--;
        return delValue;
    }

    /****** 查 ******/

    /**
     * 返回索引index对应的元素
     *
     * @param index 索引
     * @return 索引对应的元素
     */
    public E get(int index) {
        checkElementIndex(index);
        return data[index];
    }

    /****** 改 ******/

    /**
     * 将索引index的元素改为e，并返回之前的元素值
     *
     * @param index 索引
     * @param e     新值
     * @return E
     */
    public E set(int index, E e) {
        checkElementIndex(index);
        E oldValue = data[index];
        data[index] = e;
        return oldValue;
    }

    /******* 工具函数 *******/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /******* 私有函数 *******/

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 检查index索引位置是否可以存在元素
     *
     * @param index 位置
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * 检查index索引位置是否可以添加元素
     *
     * @param index 位置
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * 将数组大小扩容或缩容为newCap
     */
    private void resize(int newCap) {
        E[] temp = (E[]) new Object[newCap];
        // copy数据
        System.arraycopy(data, 0, temp, 0, size);
        data = temp;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int p = 0;

            @Override
            public boolean hasNext() {
                return p != size;
            }

            @Override
            public E next() {
                return data[p++];
            }
        };
    }

    private void display() {
        System.out.println("size = " + size + " cap = " + data.length);
        System.out.println(Arrays.toString(data));
    }

    public static void main(String[] args) {
        // 初始容量设置为 3
        MyArrayList<Integer> arr = new MyArrayList<>(3);

        // 添加 5 个元素
        for (int i = 1; i <= 5; i++) {
            arr.addLast(i);
        }

        arr.remove(3);
        arr.add(1, 9);
        arr.addLast(100);
        int val = arr.removeLast();

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
    }
}
