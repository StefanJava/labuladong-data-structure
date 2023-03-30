package com.stefan.graph.practice;

import com.stefan.graph.UnionFind;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 990. 等式方程的可满足性
 * @author: stefanyang
 * @date: 2023/3/30 18:00
 * @version: 1.0
 */
public class Solution990 {
    public boolean equationsPossible1(String[] equations) {
        Set<String> notEquals = new HashSet<>();
        UnionFind union = new UnionFind(26);
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                union.union(equation.charAt(0) - 'a', equation.charAt(3) - 'a');
            } else {
                notEquals.add(equation);
            }
        }
        for (String notEqual : notEquals) {
            if (union.connected(notEqual.charAt(0) - 'a', notEqual.charAt(3) - 'a')) {
                return false;
            }
        }
        return true;
    }
}
