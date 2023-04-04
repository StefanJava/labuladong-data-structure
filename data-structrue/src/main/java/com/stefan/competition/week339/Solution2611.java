package com.stefan.competition.week339;

import java.util.Arrays;

/**
 * @description: 2611. 老鼠和奶酪
 * @author: stefanyang
 * @date: 2023/4/4 17:02
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution2611 {
    public static int miceAndCheese(int[] reward1, int[] reward2, int k) {
        // 第一只老鼠吃的k个, 一定是reward1[i]-reward2[i]中差最大的k个
        // 因此可以先计算出差值数组
        // 然后把对应索引按照差值的大小排序
        int n1 = reward1.length;
        Integer[] indexes = new Integer[n1];
        int[] sub = new int[n1];
        for (int i = 0; i < n1; i++) {
            indexes[i] = i;
        }
        for (int i = 0; i < n1; i++) {
            sub[i] = reward1[i] - reward2[i];
        }
        Arrays.sort(indexes, (o1, o2) -> sub[o2] - sub[o1]);
        int res = 0;
        for (int i = 0; i < indexes.length; i++) {
            if (i < k) {
                res += reward1[indexes[i]];
            } else {
                res += reward2[indexes[i]];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] reward1 = new int[]{1, 1};
        int[] reward2 = new int[]{1, 1};
        System.out.println(miceAndCheese(reward1, reward2, 2));
    }
}
