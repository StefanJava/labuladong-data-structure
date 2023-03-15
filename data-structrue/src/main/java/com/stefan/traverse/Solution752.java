package com.stefan.traverse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 752. 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为'0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * @author: stefanyang
 * @date: 2023/3/15 17:06
 * @version: 1.0
 */
public class Solution752 {
    public int openLock(String[] deadends, String target) {
        // 死亡数字集合
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        // 已经被访问过的字符串, 避免重复访问
        Set<String> visited = new HashSet<>();
        int count = 0;
        String initStr = "0000";
        if (deads.contains(initStr)) {
            return -1;
        }
        // 存储可以由当前字符串转动一个数字得到的字符串,以开始下一次遍历
        Queue<String> queue = new LinkedBlockingQueue<>();
        queue.add(initStr);
        int len = target.length();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                if (target.equals(cur)) {
                    return count;
                }
                // 看是否已访问过了
                if (visited.contains(cur)) {
                    continue;
                }
                visited.add(cur);
                for (int i = 0; i < len; i++) {
                    // 如果转动后的数字不在死亡数字内, 则加入队列, 进行下一轮访问
                    String upStr = up(cur, i);
                    if (!deads.contains(upStr)) {
                        queue.add(upStr);
                    }
                    String downStr = down(cur, i);
                    if (!deads.contains(downStr)) {
                        queue.add(downStr);
                    }
                }
            }
            // 转动了一次
            count++;
        }
        return -1;
    }

    public String up(String cur, int index) {
        char[] arr = cur.toCharArray();
        if (arr[index] == '9') {
            arr[index] = '0';
        } else {
            arr[index]++;
        }
        return new String(arr);
    }

    public String down(String cur, int index) {
        char[] arr = cur.toCharArray();
        if (arr[index] == '0') {
            arr[index] = '9';
        } else {
            arr[index]--;
        }
        return new String(arr);
    }
}
