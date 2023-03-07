package com.stefan.bst1.practice;

import com.stefan.bst1.TrieMap;

/**
 * @description: 前缀树
 * @author: stefanyang
 * @date: 2023/3/7 16:08
 * @version: 1.0
 */
public class Trie {

    private TrieMap<Object> map;

    public Trie() {
        this.map = new TrieMap<>();
    }

    public void insert(String word) {
        map.put(word, new Object());
    }

    public boolean search(String word) {
        return map.get(word) != null;
    }

    public boolean startsWith(String prefix) {
        return map.hasKeyWithPrefix(prefix);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");   // 返回 True
        trie.search("app");     // 返回 False
        trie.startsWith("app"); // 返回 True
        trie.insert("app");
        trie.search("app");     // 返回 True

    }
}