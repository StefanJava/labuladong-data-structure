package com.stefan.binarytree;

import com.stefan.traverse.TreeNode;

/**
 * @description: 654. 最大二叉树
 * @author: stefanyang
 * @date: 2023/3/20 11:08
 * @version: 1.0
 */
public class Solution654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        // 计算数组的最大值，构造根节点
        // 接着以上述根节点为中心,构造左右子树,构造左右子树的根节点
        return constructTree(nums, 0, nums.length - 1);
    }

    public TreeNode constructTree(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        // 找数组中的最大值对应的索引
        int j = low;
        for (int i = low; i <= high; i++) {
            if (nums[i] > nums[j]) {
                j = i;
            }
        }
        // 构建根节点
        TreeNode root = new TreeNode(nums[j]);
        // 构建左子树
        root.left = constructTree(nums, low, j - 1);
        // 构建右子树
        root.right = constructTree(nums, j + 1, high);
        return root;
    }
}
