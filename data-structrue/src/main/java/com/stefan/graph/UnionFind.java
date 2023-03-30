package com.stefan.graph;

/**
 * @description: 并查集UNION-FIND
 * @author: stefanyang
 * @date: 2023/3/30 16:44
 * @version: 1.0
 */
public class UnionFind {
    /**
     * 存储每个节点的父节点
     */
    private int[] parent;

    private int count;

    public UnionFind(int n) {
        this.count = n;
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /* 将 p 和 q 连接 */
    public void union(int p, int q) {
        int rootp = findRoot(p);
        int rootq = findRoot(q);
        if (rootq == rootp) {
            return;
        }
        parent[rootp] = rootq;
        this.count--;
    }

    /* 判断 p 和 q 是否连通 */
    public boolean connected(int p, int q) {
        int rootp = findRoot(p);
        int rootq = findRoot(q);
        return rootq == rootp;
    }

    /* 返回图中有多少个连通分量 */
    public int count() {
        return count;
    }

    private int findRoot(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }
}
