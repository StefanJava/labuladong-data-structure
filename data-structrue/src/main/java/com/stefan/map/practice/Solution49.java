package com.stefan.map.practice;

import java.util.*;

/**
 * @description: 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * @author: stefanyang
 * @date: 2023/2/16 12:18
 * @version: 1.0
 */
public class Solution49 {

    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            map.putIfAbsent(String.valueOf(chars), new ArrayList<>());
            List<String> list = map.get(String.valueOf(chars));
            list.add(str);
        }

        List<List<String>> res = new ArrayList<>();
        for (String encode : map.keySet()) {
            res.add(map.get(encode));
        }

        return res;

    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        // 因为字符串仅包含小写字母，所有源单词中的字母通常恰好只用一次。
        // 可对字符串中的字符获取编码，然后相加，就可以得到该字符串的编码
        // 而根据字母异位词定义，会有相同的编码
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String encode = encodeStr(str);
            List<String> list = map.get(encode);
            if (list != null) {
                list.add(str);
                map.put(encode, list);
            } else {
                list = new ArrayList<>();
                list.add(str);
                map.put(encode, list);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (String encode : map.keySet()) {
            res.add(map.get(encode));
        }

        return res;

    }

    // 记录每个字符出现的次数
    public String encodeStr(String str) {
        char[] count = new char[26];
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            int index = (c - 'a');
            count[index]++;
        }
        return new String(count);
    }

}
