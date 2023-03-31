package com.stefan.graph.practice;

import com.stefan.graph.UnionFindProMax;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 1584. 连接所有点的最小费用
 * @author: stefanyang
 * @date: 2023/3/31 16:39
 * @version: 1.0
 */
public class Solution1584 {
    public static int minCostConnectPoints(int[][] points) {
        // 要连通所有点,则想到UNION-FIND
        // 连接的总费用要最小, 可以将所有连接的费用进行排序,依次添加到Union-Find中
        // 如果两个节点已经在Union-Find中连通了, 因为是按从小到大排序的, 则舍弃

        // 两点用i, j代替, 存入List中
        List<int[]> sideList = new ArrayList<>();
        int len = points.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int x = Math.abs(points[i][0] - points[j][0]);
                int y = Math.abs(points[i][1] - points[j][1]);
                sideList.add(new int[]{i, j, x + y});
            }
        }
        // 排序
        sideList.sort((a, b) -> a[2] - b[2]);
        // 遍历添加到 Union-Find
        UnionFindProMax union = new UnionFindProMax(len);
        for (int[] v : sideList) {
            if (!union.connected(v[0], v[1])) {
                sum += v[2];
                union.union(v[0], v[1]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // [[0,0],[2,2],[3,10],[5,2],[7,0]]
        int[][] points = new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println(minCostConnectPoints(points));
    }
}
