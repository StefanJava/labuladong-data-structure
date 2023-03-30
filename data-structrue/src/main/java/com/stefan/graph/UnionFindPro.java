package com.stefan.graph;

/**
 * @description: 并查集UNION-FIND
 * @author: stefanyang
 * @date: 2023/3/30 16:44
 * @version: 1.0
 */
public class UnionFindPro {
    /**
     * 存储每个节点的父节点
     */
    private int[] parent;

    /**
     * 以i节点为根的树的个数
     */
    private int[] size;

    private int count;

    public UnionFindPro(int n) {
        this.count = n;
        this.parent = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /* 将 p 和 q 连接 */
    public void union(int p, int q) {
        int rootp = findRoot(p);
        int rootq = findRoot(q);
        if (rootq == rootp) {
            return;
        }
        if (size[rootp] > size[rootq]) {
            // rootq 挂在大的 rootp
            parent[rootq] = rootp;
            size[rootp] += size[rootq];
        } else {
            parent[rootp] = rootq;
            size[rootq] += size[rootp];
        }
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
