package com.stefan.bst1.practice;

/**
 * @description: 211. 添加与搜索单词 - 数据结构设计
 * @author: stefanyang
 * @date: 2023/3/9 15:18
 * @version: 1.0
 */
public class WordDictionary {

    private TrieSet trieSet;

    public WordDictionary() {
        trieSet = new TrieSet();
    }

    public void addWord(String word) {
        trieSet.add(word);
    }

    public boolean search(String word) {
        return trieSet.hasKeyWithPattern(word);
    }
}

class TrieSet {

    private static final int R = 26;

    private class Node {
        boolean isEnd;
        Node[] children = new Node[R];
    }

    private Node root = null;

    public void add(String word) {
        if (word == null) {
            throw new IllegalArgumentException("参数异常");
        }
        root = add(root, word, 0);
    }

    private Node add(Node node, String word, int i) {
        if (node == null) {
            node = new Node();
        }
        if (i == word.length()) {
            node.isEnd = true;
            return node;
        }
        char c = word.charAt(i);
        node.children[c - 'a'] = add(node.children[c - 'a'], word, i + 1);
        return node;
    }

    public boolean hasKeyWithPattern(String word) {
        if (root == null) {
            return false;
        }
        return hasKeyWithPattern(root, word, 0);
    }

    private boolean hasKeyWithPattern(Node node, String word, int i) {
        if (node == null) {
            return false;
        }
        if (i == word.length()) {
            return node.isEnd;
        }
        char c = word.charAt(i);
        if ('.' == c) {
            for (int j = 0; j < R; j++) {
                if (hasKeyWithPattern(node.children[j], word, i + 1)) {
                    return true;
                }
            }
        } else {
            return hasKeyWithPattern(node.children[c - 'a'], word, i + 1);
        }
        return false;
    }
}
