package com.stefan.binarytree;

import com.stefan.traverse.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 889. 根据前序和后序遍历构造二叉树
 * @author: stefanyang
 * @date: 2023/3/21 11:16
 * @version: 1.0
 */
public class Solution889 {
    private final Map<Integer, Integer> map = new HashMap<>();
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // 前序遍历可以知道左子树的根节点
        // 根据左子树的根节点，到后序中找到对应的值，可以计算出左右子树的节点个数
        // 从而递归构建左右子树
        // 保存后序遍历数组  值->索引  的映射
        int postLen = postorder.length;
        for (int i = 0; i < postLen; i++) {
            map.put(postorder[i], i);
        }
        return constructFromPrePost(preorder, 0, preorder.length - 1, postorder, 0, postLen - 1);
    }

    private TreeNode constructFromPrePost(int[] preorder, int preStart, int preEnd,
                                          int[] postorder, int postStart, int postEnd) {
        // base case
        if (preStart > preEnd) {
            return null;
        }
        // 根节点的值
        int rootVal = preorder[preStart];
        // 构建节点
        TreeNode root = new TreeNode(rootVal);
        if (preStart != preEnd) {
            // 左子树根节点的值
            int leftRootVal = preorder[preStart + 1];
            // 左子树根节点的值在后序遍历中对应的索引
            int index = map.get(leftRootVal);
            // 左子树的长度
            int leftSize = index - postStart + 1;
            // 构建左子树
            root.left = constructFromPrePost(preorder, preStart + 1, preStart + leftSize,
                    postorder, postStart, index);
            // 构建左子树
            root.right = constructFromPrePost(preorder, preStart + leftSize + 1, preEnd,
                    postorder, index + 1, postEnd - 1);
        }
        return root;
    }
}
