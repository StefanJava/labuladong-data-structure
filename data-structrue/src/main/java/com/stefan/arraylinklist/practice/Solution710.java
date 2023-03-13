package com.stefan.arraylinklist.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 710. 黑名单中的随机数
 * 给定一个整数 n 和一个 无重复 黑名单整数数组blacklist。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个未加入黑名单blacklist的整数。
 * 任何在上述范围内且不在黑名单blacklist中的整数都应该有 同等的可能性 被返回。
 *
 * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
 *
 * 实现Solution类:
 *
 * Solution(int n, int[] blacklist)初始化整数 n 和被加入黑名单blacklist的整数
 * int pick()返回一个范围为 [0, n - 1] 且不在黑名单blacklist 中的随机整数
 * @author: stefanyang
 * @date: 2023/3/13 11:20
 * @version: 1.0
 */
public class Solution710 {
    // 保存白名单的长度
    private int whiteSize;

    private Map<Integer, Integer> blackToIndex = new HashMap<>();

    public Solution710(int n, int[] blacklist) {
        this.whiteSize = n - blacklist.length;
        for (int b : blacklist) {
            blackToIndex.put(b, n);
        }
        int last = n - 1;
        // 将[0, whiteSize)的黑名单中的元素，映射到[whiteSize, n)中去
        for (int b : blacklist) {
            if (b >= whiteSize) {
                continue;
            }
            while (blackToIndex.containsKey(last)) {
                last--;
            }
            blackToIndex.put(b, last);
            last--;
        }
    }

    public int pick() {
        int index = (int) (Math.random() * whiteSize);
        if (blackToIndex.containsKey(index)) {
            index = blackToIndex.get(index);
        }
        return index;
    }
}
