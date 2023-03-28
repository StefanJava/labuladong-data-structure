package com.stefan.graph.practice;

import java.util.LinkedList;
import java.util.List;

/**
 * @description: 797. 所有可能的路径
 * @author: stefanyang
 * @date: 2023/3/28 18:05
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution797 {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int len = graph.length;
        dfs(graph, 0, len - 1);
        return res;
    }
    private void dfs(int[][] graph, int v, int n) {
        path.addLast(v);
        if (v == n) {
            res.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }
        for (int x : graph[v]) {
            dfs(graph, x, n);
        }
        path.removeLast();
    }
}
