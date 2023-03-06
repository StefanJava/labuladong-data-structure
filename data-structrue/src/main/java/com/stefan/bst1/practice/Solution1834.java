package com.stefan.bst1.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: 1834. 单线程 CPU
 * @author: stefanyang
 * @date: 2023/3/6 15:21
 * @version: 1.0
 */
public class Solution1834 {
    public static int[] getOrder(int[][] tasks) {
        // 任务个数
        int taskNumber = tasks.length;
        // list按入队时间从小到大排序
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < taskNumber; i++) {
            list.add(new int[]{tasks[i][0], tasks[i][1], i});
        }
        list.sort((a, b) -> {
            return a[0] - b[0];
        });
        // 保存结果
        List<Integer> res = new ArrayList<>();
        // 如果 CPU 空闲，但任务队列中有需要执行的任务，则 CPU 将会选择 执行时间最短 的任务开始执行。如果多个任务具有同样的最短执行时间，则选择下标最小的任务开始执行。=== 用优先队列  先按运行时间从小到大排序，再按索引排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        // 保存当前时刻
        int now = 0;
        int count = 0;
        while (res.size() < taskNumber) {
            // 如果队列不为空,则取出用时最少的任务，并把now+任务所用时间
            if (!pq.isEmpty()) {
                int[] task = pq.poll();
                now += task[1];
                res.add(task[2]);
            } else if (count < taskNumber && list.get(count)[0] > now) {
                // 如果队列为空
                // 把now推进到入队时间最近的任务
                now = list.get(count)[0];
            }

            // 在任务执行过程中，可能有其他任务会入队，执行结束后，如果入队时间<=now, 则加入进去
            for (; count < taskNumber && list.get(count)[0] <= now; count++) {
                pq.offer(list.get(count));
            }
        }
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] tasks = {{1, 2}, {2, 4}, {3, 2}, {4, 1}};
        System.out.println(Arrays.toString(getOrder(tasks)));
    }
}
