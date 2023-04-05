package com.stefan.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 22. 括号生成
 * @author: stefanyang
 * @date: 2023/4/5 16:31
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution22 {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        generateParenthesis(n, n, sb, result);
        return result;
    }
    private void generateParenthesis(int left, int right, StringBuilder sb, List<String> res) {
        // 剩下的左括号数大于右括号数  说明不可能组成有效括号
        if (left > right) {
            return;
        }
        // 小于0 也是一样
        if (left < 0 || right < 0) {
            return;
        }
        if (left == 0 && right == 0) {
            res.add(sb.toString());
            return;
        }
        // 添加左括号
        sb.append("(");
        generateParenthesis(left - 1, right, sb, res);
        sb.deleteCharAt(sb.length() - 1);
        // 添加右括号
        sb.append(")");
        generateParenthesis(left, right - 1, sb, res);
        sb.deleteCharAt(sb.length() - 1);
    }
}
