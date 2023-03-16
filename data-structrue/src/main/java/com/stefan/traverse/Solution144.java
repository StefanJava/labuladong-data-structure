package com.stefan.traverse;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 144. 二叉树的前序遍历
 * @author: stefanyang
 * @date: 2023/3/16 16:25
 * @version: 1.0
 */
public class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res);
        return res;
    }

    public void traverse(TreeNode cur, List<Integer> res) {
        if (cur == null) {
            return;
        }
        // 前序位置做操作
        res.add(cur.val);
        traverse(cur.left, res);
        traverse(cur.right, res);
    }
}
