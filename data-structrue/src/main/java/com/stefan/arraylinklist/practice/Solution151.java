package com.stefan.arraylinklist.practice;

/**
 * @description: 151. 反转字符串中的单词
 * @author: stefanyang
 * @date: 2023/3/27 17:45
 * @version: 1.0
 */
@SuppressWarnings("unused")
public class Solution151 {

    private final static char C = ' ';

    public String reverseWords(String s) {
        // 取出多余的空格  两端以及中间空格个数大于1的空格
        StringBuilder sb = trimSpace(s);
        // 翻转整个字符串
        reverseStr(sb, 0, sb.length() - 1);
        // 翻转每个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    private StringBuilder trimSpace(String s) {
        StringBuilder sb = new StringBuilder();
        // 去除前面的空格
        int start = 0;
        int end = s.length() - 1;
        while (start <= end && s.charAt(start) == C) {
            start++;
        }
        if (start > end) {
            return sb;
        }
        // 去除后面的空格
        while (end >= 0 && s.charAt(end) == C) {
            end--;
        }
        // 去除中间的空格
        int count = 0;
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ') {
                sb.append(c);
                count = 0;
            } else if (count == 0) {
                sb.append(c);
                count++;
            }
            start++;
        }
        return sb;
    }

    private void reverseStr(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    private void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 0;
        int len = sb.length();
        while (end < len) {
            char c = sb.charAt(end);
            if (c != ' ') {
                end++;
            } else {
                reverseStr(sb, start, end - 1);
                start = ++end;
            }
        }
        reverseStr(sb, start, end - 1);
    }
}
