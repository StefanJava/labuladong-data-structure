package com.stefan.traverse;

/**
 * @description: 104. 二叉树的最大深度
 * @author: stefanyang
 * @date: 2023/3/16 16:20
 * @version: 1.0
 */
public class Solution104 {
    private int maxDepth = 0;
    public int maxDepth(TreeNode root) {
        traverse(root);
        return maxDepth;
    }

    public int traverse(TreeNode cur) {
        // base case
        if (cur == null) {
            return 0;
        }
        int maxLeftDepth = traverse(cur.left);
        int maxRightDepth = traverse(cur.right);
        int curMaxDepth = Math.max(maxLeftDepth, maxRightDepth) + 1;
        maxDepth = Math.max(maxDepth, curMaxDepth);
        return curMaxDepth;
    }
}
