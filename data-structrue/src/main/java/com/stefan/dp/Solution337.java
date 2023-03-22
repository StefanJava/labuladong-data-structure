package com.stefan.dp;

import com.stefan.traverse.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 337. 打家劫舍 III
 * @author: stefanyang
 * @date: 2023/3/22 17:43
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution337 {

    public int rob2(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    private int[] dp(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        // 左子节点抢或不抢的值
        int[] left = dp(root.left);
        // 右子节点抢或不抢的值
        int[] right = dp(root.right);
        // 当前抢 则 子节点不可抢
        int rob = root.val + left[0] + right[0];
        // 当前不抢  子节点可抢可不抢  看哪个收益大
        int noRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{noRob, rob};
    }

    private final Map<TreeNode, Integer> map = new HashMap<>();
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }
        // 当前节点抢
        int rob = root.val
                + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));
        // 当前节点不抢
        int noRob = rob(root.left) + rob(root.right);
        int max = Math.max(rob, noRob);
        map.put(root, max);
        return max;
    }
}
