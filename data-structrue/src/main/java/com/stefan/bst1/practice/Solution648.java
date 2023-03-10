package com.stefan.bst1.practice;

import java.util.List;

/**
 * @description: 648、单词替换
 * @author: stefanyang
 * @date: 2023/3/10 15:12
 * @version: 1.0
 */
class Solution648 {
    public String replaceWords(List<String> dictionary, String sentence) {
        MyTrieSet trieSet = new MyTrieSet();
        for (String key : dictionary) {
            trieSet.add(key);
        }
        String[] strArr = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : strArr) {
            String key = trieSet.shortestKeyWithPrefix(word);
            if ("".equals(key)) {
                key = word;
            }
            sb.append(key).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}

class MyTrieSet {

    private final static int R = 26;

    private static class TrieNode {
        boolean isEnd = false;
        TrieNode[] children = new TrieNode[R];
    }

    private TrieNode root = null;

    public void add(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        root = add(root, key, 0);
    }

    private TrieNode add(TrieNode node, String key, int i) {
        if (node == null) {
            node = new TrieNode();
        }
        if (i == key.length()) {
            node.isEnd = true;
            return node;
        }
        char c = key.charAt(i);
        node.children[c - 'a'] = add(node.children[c - 'a'], key, i + 1);
        return node;
    }

    public String shortestKeyWithPrefix(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        return shortestKeyWithPrefix(root, word, 0);
    }

    private String shortestKeyWithPrefix(TrieNode node, String word, int i) {
        if (node == null) {
            return "";
        }
        if (node.isEnd) {
            return word.substring(0, i);
        } else if (i == word.length()) {
            return "";
        }
        char c = word.charAt(i);
        return shortestKeyWithPrefix(node.children[c - 'a'], word, i + 1);
    }

}
