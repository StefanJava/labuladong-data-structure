package com.stefan.bst1;

/**
 * @description: recursion
 * @author: stefanyang
 * @date: 2023/2/22 17:02
 * @version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 2};
        int target = 2;
        print(arr);
        System.out.println(search(arr, target));
    }

    private static int search(int[] arr, int target) {
        return search(arr, 0, target);
    }

/*    private static int search(int[] arr, int index, int target) {
        // 第一个
        if (index == arr.length) {
            return -1;
        }
        if (arr[index] == target) {
            return index;
        }
        return search(arr, index + 1, target);
    }*/


    private static int search(int[] arr, int index, int target) {
        // 最后一个
        // base
        if (index == arr.length) {
            return -1;
        }

        // 先查找arr[i + 1]及后面
        int result = search(arr, index + 1, target);

        // base
        if (arr[index] == target && result == -1) {
            return index;
        }
        return result;
    }

    private static void print(int[] arr) {
        print(arr, 0);
    }

/*    private static void print(int[] arr, int index) {
        // 正序
        if (index == arr.length) {
            return;
        }

        System.out.println(arr[index]);

        print(arr, index + 1);
    }*/

    private static void print(int[] arr, int index) {
        // 倒序
        if (index == arr.length) {
            return;
        }

        print(arr, index + 1);

        System.out.println(arr[index]);
    }
}
