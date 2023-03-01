package com.stefan.bst1;

/**
 * @description: 优先级队列-大顶堆
 * @author: stefanyang
 * @date: 2023/3/1 20:10
 * @version: 1.0
 */
public class MaxPQ<Key extends Comparable<Key>> {
    // 存储元素的数组
    private Key[] pq;

    private int size;

    public MaxPQ(int cap) {
        this.pq = (Key[]) new Comparable[cap];
        this.size = 0;
    }

    /* 返回当前队列中最大元素 */
    public Key max() {
        return pq[0];
    }

    /* 插入元素 e */
    public void insert(Key e) {
        pq[size] = e;
        swim(size);
        size++;
    }

    /* 删除并返回当前队列中最大元素 */
    public Key delMax() {
        Key oldVal = pq[0];
        pq[0] = pq[size - 1];
        pq[size - 1] = null;
        size--;
        sink(0);
        return oldVal;
    }

    /* 上浮第 x 个元素，以维护最大堆性质 */
    private void swim(int x) {
        // 比父节点小
        while (x >= 0 && less(parent(x), x)) {
            swap(x, parent(x));
            x = parent(x);
        }
    }

    /* 下沉第 x 个元素，以维护最大堆性质 */
    private void sink(int x) {
        while (left(x) < size) {
            // 找出左右节点最大值
            int max = left(x);
            if (right(x) < size && less(max, right(x))) {
                max = right(x);
            }
            // 如果小于最大值，则与最大值进行交换，否则符合条件，退出循环
            if (less(x, max)) {
                swap(x , max);
            } else {
                break;
            }
            x = max;
        }
    }

    /* 交换数组的两个元素 */
    private void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /* pq[i] 是否比 pq[j] 小？ */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    public static void main(String[] args) {
        MaxPQ<Integer> pq = new MaxPQ<>(10);
        pq.insert(4);
        pq.insert(2);
        pq.insert(1);
        pq.insert(5);
        pq.insert(7);
        pq.insert(3);
        System.out.println(pq.delMax());
        pq.insert(6);
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
        System.out.println(pq.delMax());
    }

}
