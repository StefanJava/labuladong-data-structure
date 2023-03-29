package com.stefan.graph.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 207. 课程表
 * @author: stefanyang
 * @date: 2023/3/29 11:21
 * @version: 1.0
 */
@SuppressWarnings("unchecked, unused")
public class Solution207 {
    private boolean[] visited;
    private boolean[] onPath;
    private boolean hasCircle;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 构件图 graph[i] 表示的是只有修完课程i 才可修graph[i].get(j)  j = 0 ... n
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] pair : prerequisites) {
            // 需先修的课程编号
            int prev = pair[1];
            int next = pair[0];
            graph[prev].add(next);
        }
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        return !hasCircle;
    }
    private void traverse(List<Integer>[] graph, int v) {
        if (onPath[v]) {
            hasCircle = true;
        }
        if (visited[v] || hasCircle) {
            return;
        }
        visited[v] = true;
        onPath[v] = true;
        for (int s : graph[v]) {
            traverse(graph, s);
        }
        onPath[v] = false;
    }
}
