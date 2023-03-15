package com.stefan.traverse;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 111. 二叉树的最小深度
 * @author: stefanyang
 * @date: 2023/3/15 11:07
 * @version: 1.0
 */
public class Solution111 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        int depth = 1;
        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            // 遍历完一层 深度加一
            depth++;
        }
        return depth;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int depth = Integer.MAX_VALUE;
        return traverse(root, depth);
    }
    public int traverse(TreeNode cur, int depth) {
        if (cur.left == null && cur.right == null) {
            return 1;
        }
        if (cur.left != null) {
            depth = Math.min(depth, traverse(cur.left, depth) + 1);
        }
        if (cur.right != null) {
            depth = Math.min(depth, traverse(cur.right, depth) + 1);
        }
        return depth;
    }
}
