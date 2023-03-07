package com.stefan.bst1;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 字典树、前缀树
 * @author: stefanyang
 * @date: 2023/3/7 13:49
 * @version: 1.0
 */
public class TrieMap<V> {
    // ASCII 码个数
    private static final int R = 256;
    // 当前存在 Map 中的键值对个数
    private int size = 0;
    // Trie 树的根节点
    private TrieNode<V> root = null;

    private static class TrieNode<V> {
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];
    }

    /***** 增/改 *****/

    // 在 map 中添加或修改键值对
    public void put(String key, V val) {
        if (!containsKey(key)) {
            // 新增键值对
            size++;
        }
        // 需要一个额外的辅助函数，并接收其返回值
        root = put(root, key, val, 0);
    }

    private TrieNode<V> put(TrieNode<V> node, String key, V val, int i) {
        // 不存在，新建节点
        if (node == null) {
            node = new TrieNode<>();
        }

        // key插入完成
        if (i == key.length()) {
            node.val = val;
            return node;
        }

        char c = key.charAt(i);

        node.children[c] = put(node.children[c], key, val, i + 1);
        return node;
    }


    /***** 删 *****/

    // 在 Map 中删除 key
    public void remove(String key) {
        if (!containsKey(key)) {
            return;
        }
        root = remove(root, key, 0);
        size--;
    }

    private TrieNode<V> remove(TrieNode<V> node, String key, int i) {

        if (node == null) {
            return null;
        }
        if (i == key.length()) {
            // 找到了 key 对应的 TrieNode，删除 val
            node.val = null;
        } else {
            char c = key.charAt(i);
            // 递归去子树进行删除
            node.children[c] = remove(node.children[c], key, i + 1);
        }
        // 后序位置，递归路径上的节点可能需要被清理
        if (node.val != null) {
            // 如果该 TireNode 存储着 val，不需要被清理
            return node;
        }
        // 检查该 TrieNode 是否还有后缀
        for (int c = 0; c < R; c++) {
            if (node.children[c] != null) {
                // 只要存在一个子节点（后缀树枝），就不需要被清理
                return node;
            }
        }
        // 既没有存储 val，也没有后缀树枝，则该节点需要被清理
        return null;
    }


    /***** 查 *****/

    // 搜索 key 对应的值，不存在则返回 null
    public V get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        // 前缀
        TrieNode<V> node = getNode(root, key);
        if (node == null || node.val == null) {
            // node为空说明 不存在该前缀  node.val为空 说明不存在该key对应的值为空  只是一个前缀
            return null;
        }
        return node.val;
    }

    // 判断 key 是否存在在 Map 中
    public boolean containsKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return get(key) != null;
    }

    // 判断是和否存在前缀为 prefix 的键
    public boolean hasKeyWithPrefix(String prefix) {
        // 只要是前缀就行
        return getNode(root, prefix) != null;
    }

    // 在所有键中寻找 query 的最短前缀
    public String shortestPrefixOf(String query) {

        return shortestPrefixOf(root, query);
    }

    private String shortestPrefixOf(TrieNode<V> node, String query) {
        TrieNode<V> p = node;
        int length = query.length();
        for (int i = 0; i < length; i++) {
            if (p == null) {
                return "";
            }
            if (p.val != null) {
                return query.substring(0, i);
            }
            char c = query.charAt(i);
            p = p.children[c];
        }

        if (p != null && p.val != null) {
            return query;
        }

        return "";
    }

    // 在所有键中寻找 query 的最长前缀
    public String longestPrefixOf(String query) {
        return longestPrefixOf(root, query);
    }

    private String longestPrefixOf(TrieNode<V> node, String query) {

        // 记录前缀的最大长度
        int maxLen = 0;
        TrieNode<V> p = node;
        int length = query.length();
        for (int i = 0; i < length; i++) {
            if (p == null) {
                break;
            }
            if (p.val != null) {
                // 找到一个键是query的前缀，更新最长前缀键的索引
                maxLen = i;
            }
            char c = query.charAt(i);
            p = p.children[c];
        }
        if (p != null && p.val != null) {
            // 还要判断本身是不是key
            return query;
        }
        return query.substring(0, maxLen);
    }

    // 搜索前缀为 prefix 的所有键
    public List<String> keysWithPrefix(String prefix) {
        // 找到匹配 prefix 在 Trie 树中的那个节点
        TrieNode<V> x = getNode(root, prefix);
        List<String> res = new ArrayList<>();
        if (x == null) {
            return res;
        }
        // DFS 遍历以 x 为根的这棵 Trie 树
        traverse(x, new StringBuilder(prefix), res);
        return res;
    }

    private void traverse(TrieNode<V> x, StringBuilder sb, List<String> res) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            res.add(sb.toString());
        }

        for (int i = 0; i < R; i++) {
            // 先将该字符加入 在进行判断
            sb.append(i);
            traverse(x.children[i], sb, res);
            // i分支遍历完后 进行删除 回溯
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    // 通配符 . 匹配任意字符
    public List<String> keysWithPattern(String pattern) {
        List<String> res = new ArrayList<>();
        traverse(root, new StringBuilder(), pattern, 0, res);
        return res;
    }

    private void traverse(TrieNode<V> node, StringBuilder sb, String pattern, int i, List<String> res) {
        if (node == null) {
            return;
        }
        if (i == pattern.length()) {
            if (node.val != null) {
                res.add(sb.toString());
            }
            return;
        }
        char c = pattern.charAt(i);
        if (c == '.') {
            // 所有值都要遍历
            for (int j = 0; j < R; j++) {
                sb.append(j);
                traverse(node.children[j], sb, pattern, i + 1, res);
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            // 遍历c这一条就行
            sb.append(c);
            traverse(node.children[c], sb, pattern, i + 1, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // 判断是和否存在前缀为 prefix 的键
    public boolean hasKeyWithPattern(String pattern) {
        return hasKeyWithPattern(root, pattern, 0);
    }

    private boolean hasKeyWithPattern(TrieNode<V> node, String pattern, int i) {
        if (node == null) {
            return false;
        }
        if (i == pattern.length()) {
            return node.val != null;
        }
        char c = pattern.charAt(i);
        if (c == '.') {
            // 继续遍历
            for (int j = 0; j < R; j++) {
                if (hasKeyWithPattern(node.children[j], pattern, i + 1)) {
                    return true;
                }
            }
        } else {
            return hasKeyWithPattern(node.children[c], pattern, i + 1);
        }
        return false;
    }

    // 从节点 node 开始搜索 key，如果存在返回对应节点，否则返回 null
    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        TrieNode<V> p = node;
        for (int i = 0; i < key.length(); i++) {
            if (p == null) {
                // 搜索不到
                return null;
            }
            char c = key.charAt(i);
            // 向下搜索
            p = p.children[c];
        }
        return p;
    }

    public int size() {
        return size;
    }
}
