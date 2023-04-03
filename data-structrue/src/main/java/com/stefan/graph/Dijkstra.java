package com.stefan.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: 迪杰斯特拉算法
 * @author: stefanyang
 * @date: 2023/4/3 11:35
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Dijkstra {
    /**
     * 输入一个start起点, 计算其他节点到start的最短距离
     * @param start 起点
     * @param graph 图
     * @return 节点到起点的最短距离
     */
    public int[] dijkstra(int start, List<int[]>[] graph) {
        int len = graph.length;
        int[] distList = new int[len];
        Arrays.fill(distList, Integer.MAX_VALUE);
        // 优先级队列, 按距离排序
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(state -> state.distFromStart));
        distList[start] = 0;
        pq.offer(new State(start, 0));
        while (!pq.isEmpty()) {
            State state = pq.poll();
            // 当前节点距起点的距离
            int id = state.id;
            int curDistFromStart = state.distFromStart;
            if (curDistFromStart > distList[id]) {
                continue;
            }
            for (int[] adj : graph[id]) {
                int nextId = adj[0];
                int nextDistFromCur = adj[1];
                int nextDistFromStart = curDistFromStart + nextDistFromCur;
                if (nextDistFromStart < distList[nextId]) {
                    distList[nextId] = nextDistFromStart;
                    pq.offer(new State(nextId, nextDistFromStart));
                }
            }
        }
        return distList;
    }
}

class State {
    /**
     * 节点id
     */
    int id;

    /**
     * 从起始节点到该节点的距离
     */
    int distFromStart;

    public State(int id, int distFromStart) {
        this.id = id;
        this.distFromStart = distFromStart;
    }
}
