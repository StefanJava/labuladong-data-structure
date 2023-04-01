package com.stefan.graph.practice;

import com.stefan.graph.Prim;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 1584. 连接所有点的最小费用
 * @author: stefanyang
 * @date: 2023/4/1 14:29
 * @version: 1.0
 */
@SuppressWarnings("unchecked")
public class Solution1584Pro {
    public int minCostConnectPoints(int[][] points) {
        // Prim算法 使用切分定理, 首先选定一个节点进行切分, 并选出权重最小的那条边
        // 然后再对这条边的另一个节点进行切分

        // 两点用i, j代替, 存入List中
        int len = points.length;
        List<int[]>[] sideList = new ArrayList[len];
        for (int i = 0; i < len; i++) {
            sideList[i] = new ArrayList<>();
        }
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int x = Math.abs(points[i][0] - points[j][0]);
                int y = Math.abs(points[i][1] - points[j][1]);
                sideList[i].add(new int[]{i, j, x + y});
                sideList[j].add(new int[]{j, i, x + y});
            }
        }
        Prim prim = new Prim(sideList);
        return prim.weightSum();
    }
}
