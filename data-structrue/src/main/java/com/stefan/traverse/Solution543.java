package com.stefan.traverse;

/**
 * @description: 543. 二叉树的直径
 * @author: stefanyang
 * @date: 2023/3/16 14:34
 * @version: 1.0
 */
public class Solution543 {
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        traverse(root);
        return maxDiameter;
    }
    public int traverse(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        int left = traverse(cur.left);
        int right = traverse(cur.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return 1 + Math.max(left, right);
    }
}
