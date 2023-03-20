package com.stefan.traverse;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 105. 从前序与中序遍历序列构造二叉树
 * @author: stefanyang
 * @date: 2023/3/20 12:00
 * @version: 1.0
 */
public class Solution105 {
    private Map<Integer, Integer> inorderMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = inorder.length;
        for (int i = 0; i < len; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
                               int[] inorder, int inStart, int inEnd) {

        if (preStart > preEnd) {
            return null;
        }

        // 根节点的值
        int nodeVal = preorder[preStart];
        // 根节点在中序遍历中对应的索引
        int index = inorderMap.get(nodeVal);
        // 左子树节点的个数
        int leftSize = index - inStart;
        // 前序遍历左子树end
        int preLeftEnd = preStart + leftSize;
        // 构建根节点
        TreeNode root = new TreeNode(nodeVal);
        // 构建左子树
        root.left = buildTree(preorder, preStart + 1, preLeftEnd, inorder, inStart, index - 1);
        // 构建右子树
        root.right = buildTree(preorder, preLeftEnd + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }
}
