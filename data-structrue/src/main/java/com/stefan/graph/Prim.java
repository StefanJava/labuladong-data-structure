package com.stefan.graph;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @description: Prim算法
 * @author: stefanyang
 * @date: 2023/4/1 13:51
 * @version: 1.0
 */
public class Prim {

    /**
     * 优先级队列, 方便找出权重最小的那条边, 小顶堆
     */
    private PriorityQueue<int[]> pq;

    /**
     * 节点是否已经存在于最小生成树中
     */
    private boolean[] inMst;

    /**
     * 最小生成树的权重和
     */
    private int weightSum;

    /**
     * 生成最小生成树的图
     */
    private List<int[]>[] graph;

    public Prim(List<int[]>[] graph) {
        this.weightSum = 0;
        this.graph = graph;
        this.pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        // 节点个数
        int len = graph.length;
        this.inMst = new boolean[len];

        // 开始切分
        inMst[0] = true;
        cut(0);
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int to = edge[1];
            int weight = edge[2];
            if (inMst[to]) {
                // 如果该节点已经遍历过了，则跳过,否则会产生环
                continue;
            }
            weightSum += weight;
            inMst[to] = true;
            cut(to);
        }
    }

    // 把s的边加入队列
    private void cut(int s) {
        // 遍历连接s的边
        for (int[] edge : graph[s]) {
            int to = edge[1];
            if (inMst[to]) {
                // 防止重复遍历
                continue;
            }
            pq.offer(edge);
        }
    }

    // 最小生成树的权重和
    public int weightSum() {
        return weightSum;
    }

    // 判断最小生成树是否包含图中的所有节点
    public boolean allConnected() {
        for (boolean b : inMst) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

}
