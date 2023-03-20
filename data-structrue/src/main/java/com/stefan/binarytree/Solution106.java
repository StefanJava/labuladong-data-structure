package com.stefan.binarytree;

import com.stefan.traverse.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 106. 从中序与后序遍历序列构造二叉树
 * @author: stefanyang
 * @date: 2023/3/20 21:15
 * @version: 1.0
 */
public class Solution106 {
    private final Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 通过后序遍历，很容易找到根节点
        // 通过根节点，到中序遍历中很容易计算出左右子树的节点数
        // 然后再从子树中找根节点，重复以上操作
        // 用哈希表存储中序遍历的值和对应数组中的索引
        int len = inorder.length;
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(inorder, 0, len - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                               int[] postorder, int postStart, int postEnd) {
        // base case
        if (postStart > postEnd) {
            return null;
        }
        // 根节点的值
        int rootVal = postorder[postEnd];
        // 根节点在中序遍历中对应的索引值
        int index = map.get(rootVal);
        // 计算左子树的长度
        int leftSize = index - inStart;
        // 构建根节点
        TreeNode root = new TreeNode(rootVal);
        // 构建左子树
        root.left = buildTree(inorder, inStart, index - 1,
                postorder, postStart, postStart + leftSize - 1);
        // 构建右子树
        root.right = buildTree(inorder, index + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1);
        return root;
    }
}
