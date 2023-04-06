package com.stefan.sort;

import java.util.Arrays;

/**
 * @description: 归并排序
 * @author: stefanyang
 * @date: 2023/4/6 20:04
 * @version: 1.0
 */
public class MergeSort {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 将给定数组排序
     *
     * @param arr int整型一维数组 待排序的数组
     * @return int整型一维数组
     */
    public static int[] MySort(int[] arr) {
        // write code here
        int n = arr.length;
        temp = new int[n];
        MySort(arr, 0, n - 1);
        return arr;
    }

    private static int[] temp;

    private static void MySort(int[] arr, int left, int right) {
        if (right == left) {
            return;
        }
        int mid = (right - left) / 2 + left;
        MySort(arr, left, mid);
        MySort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int p = left;
        int q = mid + 1;
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        while (p <= mid && q <= right) {
            if (temp[p] < temp[q]) {
                arr[left] = temp[p++];
            } else {
                arr[left] = temp[q++];
            }
            left++;
        }
        if (p > mid) {
            while (q <= right) {
                arr[left++] = temp[q++];
            }
        }
        if (q > right) {
            while (p <= mid) {
                arr[left++] = temp[p++];
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 5};
        System.out.println(Arrays.toString(MySort(arr)));
    }
}
