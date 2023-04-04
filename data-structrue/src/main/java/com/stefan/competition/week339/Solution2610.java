package com.stefan.competition.week339;

import java.util.*;

/**
 * @description: 2610. 转换二维数组
 * @author: stefanyang
 * @date: 2023/4/4 16:24
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution2610 {
    public List<List<Integer>> findMatrixPro(int[] nums) {
        // 遍历数组
        int size = nums.length;
        // 哈希表存储每个数字出现的次数, map的大小用作二维数组的列数
        Map<Integer, Integer> map = new HashMap<>(16);
        List<List<Integer>> res = new ArrayList<>();
        for (int num : nums) {
            int n = map.getOrDefault(num, 0);
            if (n >= res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(n).add(num);
            map.put(num, ++n);
        }
        return res;
    }
    public List<List<Integer>> findMatrix(int[] nums) {
        // 遍历数组
        int size = nums.length;
        // 哈希表存储每个数字出现的次数, map的大小用作二维数组的列数
        Map<Integer, Integer> map = new HashMap<>();
        // 存储数组中数字出现的最多次数, 用作二维数组的行数
        int row = 0;
        for (int num : nums) {
            int n = map.getOrDefault(num, 0) + 1;
            map.put(num, n);
            row = Math.max(row, n);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            res.add(new ArrayList<>());
        }
        // 索引列
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            while (value > 0) {
                res.get(--value).add(entry.getKey());
            }
            index++;
        }
        return res;
    }
}
