package com.stefan.daily;

/**
 * @description: 1032. 字符流
 * @author: stefanyang
 * @date: 2023/3/24 13:10
 * @version: 1.0
 */
public class StreamChecker {
    private final Trie trie = new Trie();
    private final StringBuilder bd = new StringBuilder();
    public StreamChecker(String[] words) {
        for (String word : words) {
            StringBuilder sb = new StringBuilder(word);
            trie.add(sb.reverse().toString());
        }
    }

    public boolean query(char letter) {
        bd.append(letter);
        String prefix = bd.reverse().toString();
        bd.reverse();
        return trie.exit(prefix);
    }
}

class Trie {
    private static final int R = 26;
    private Node root;
    private static class Node {
        boolean isEnd;
        Node[] children = new Node[R];
    }
    public void add(String word) {
        root = add(root, word, 0);
    }
    private Node add(Node root, String word, int index) {
        if (root == null) {
            root = new Node();
        }
        if (index == word.length()) {
            root.isEnd = true;
            return root;
        }
        char c = word.charAt(index);
        root.children[c - 'a'] = add(root.children[c - 'a'], word, index + 1);
        return root;
    }
    public boolean exit(String word) {
        return exit(root, word, 0);
    }
    private boolean exit(Node root, String word, int index) {
        if (root == null) {
            return false;
        }
        if (root.isEnd) {
            return true;
        }
        if (index == word.length()) {
            return false;
        }
        char c = word.charAt(index);
        return exit(root.children[c - 'a'], word, index + 1);
    }
}

class StreamChecker2 {

    String s = "";
    String[] words = null;

    public StreamChecker2(String[] words) {
        this.words = words;
    }

    public boolean query(char letter) {
        s += letter;
        for (String word : words) {
            if (s.endsWith(word)) {
                return true;
            }
        }
        return false;
    }
}
