package com.stefan.arraylinklist.practice;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 658. 找到 K 个最接近的元素
 * 给定一个 排序好 的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 * @Date: 2023/2/6 15:27
 * @Author: stefanyang
 */
public class Solution658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 首先找在数组中最接近x的位置
        int p = leftSearch(arr, x);
        int left = p - 1;
        int right = p;
        LinkedList<Integer> result = new LinkedList<>();
        while (right - left - 1 < k) {
            if (left == -1) {
                result.addLast(arr[right]);
                right++;
            } else if (right == arr.length) {
                result.addFirst(arr[left]);
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                result.addFirst(arr[left]);
                left--;
            } else if (x - arr[left] > arr[right] - x) {
                result.addLast(arr[right]);
                right++;
            }
        }
        return result;
    }

    public int leftSearch(int[] arr, int x) {
        int left = 0;
        int right = arr.length;
        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == x) {
                right = mid;
            } else if (arr[mid] > x) {
                // 减少arr[mid]的值
                right = mid;
            } else if (arr[mid] < x) {
                // 增大arr[mid]的值
                left = mid + 1;
            }

        }
        return left;
    }
}
