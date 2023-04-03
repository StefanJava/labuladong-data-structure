package com.stefan.graph.practice;

import com.stefan.graph.Dijkstra;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 743. 网络延迟时间
 * @author: stefanyang
 * @date: 2023/4/3 12:28
 * @version: 1.0
 */
@SuppressWarnings("unchecked, unused")
public class Solution743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        int len =  times.length;
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int weight = time[2];
            graph[from].add(new int[]{to, weight});
        }
        Dijkstra dijkstra = new Dijkstra();
        int[] distList = dijkstra.dijkstra(k, graph);
        int res = 0;
        for (int i = 1; i < distList.length; i++) {
            if (distList[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, distList[i]);
        }
        return res;
    }
}
