package com.stefan.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description: 46. 全排列
 * @author: stefanyang
 * @date: 2023/3/14 19:31
 * @version: 1.0
 */
public class Solution46 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        getOrder(list, nums, used);
        return res;
    }

    public void getOrder(List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            getOrder(list, nums, used);
            // 回溯
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }
}
