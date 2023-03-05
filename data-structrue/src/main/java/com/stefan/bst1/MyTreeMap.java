package com.stefan.bst1;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @description: TreeMap实现
 * @author: stefanyang
 * @date: 2023/3/3 19:16
 * @version: 1.0
 */
public class MyTreeMap<K extends Comparable<K>, V> {

    private static class TreeNode<K, V> {
        K key;
        V val;
        TreeNode<K, V> left, right;
        // 记录，以该节点为根的 BST 有多少个节点
        int size;

        TreeNode(K key, V val) {
            this.key = key;
            this.val = val;
            left = right = null;
            this.size = 1;
        }
    }

    private TreeNode<K, V> root = null;

    public MyTreeMap() {
    }

    /***** 增/改 *****/

    // 添加 key -> val 键值对，如果键 key 已存在，则将值修改为 val
    public V put(K key, V val) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        V oldVal = get(key);
        root = put(root, key, val);
        return oldVal;
    }

    private TreeNode<K, V> put(TreeNode<K, V> node, K key, V val) {
        if (node == null) {
            return new TreeNode<>(key, val);
        }

        int i = key.compareTo(node.key);

        // key < node.key 往左子树添加
        if (i < 0) {
            node.left = put(node.left, key, val);
        }
        // key > node.key 往右子树添加
        if (i > 0) {
            node.right = put(node.right, key, val);
        }
        if (i == 0) {
            node.val = val;
        }
        // 维护每个节点的 size 变量
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /***** 删 *****/

    // 删除 key 并返回对应的 val
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("map is empty");
        }
        if (!containsKey(key)) {
            return null;
        }
        V oldVal = get(key);
        root = remove(root, key);
        return oldVal;
    }

    private TreeNode<K, V> remove(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int i = key.compareTo(node.key);
        // key < node.key 往左子树查找
        if (i < 0) {
            node.left = remove(node.left, key);
        }
        // key > node.key 往右子树查找
        if (i > 0) {
            node.right = remove(node.right, key);
        }
        //  key === node.key node即是要删除的节点
        if (i == 0) {
            // node最多有一个子节点
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }

            // 删除左子树最大的那个节点，然后以该节点代替被删除节点
            TreeNode<K, V> leftMax = maxNode(node.left);
            node.left = removeMax(node.left);
            leftMax.left = node.left;
            leftMax.right = node.right;
            node = leftMax;
        }
        // 维护每个节点的 size 变量
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    // 删除并返回 BST 中最小的那个 key
    public void removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = removeMin(root);
    }

    // 删除并返回以 node 为根的 BST 中最小的那个节点
    private TreeNode<K, V> removeMin(TreeNode<K, V> node) {
        if (node.left == null) {
            return null;
        }
        // 往左子树查找，直到叶子节点
        node.left = removeMin(node.left);
        // 维护每个节点的 size 变量
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    // 删除并返回 BST 中最大的那个 key
    public void removeMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = removeMax(root);
    }

    // 删除并返回以 node 为根的 BST 中最大的那个节点
    private TreeNode<K, V> removeMax(TreeNode<K, V> node) {
        // 往右子树查找，直到叶子结点
        if (node.right == null) {
            return null;
        }
        node.right = removeMax(node.right);
        // 维护每个节点的 size 变量
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /***** 查 *****/

    // 返回 key 对应的 val，如果 key 不存在，则返回 null
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        TreeNode<K, V> node = get(root, key);
        return node == null ? null : node.val;
    }

    // 在以 node 为根的 BST 中查找 key
    private TreeNode<K, V> get(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int i = key.compareTo(node.key);
        if (i == 0) {
            return node;
        }
        // 往节点左子树找  key < node.key
        if (i < 0) {
            return get(node.left, key);
        }
        return get(node.right, key);
    }

    // 返回小于等于 key 的最大的键
    public K floorKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("map is empty");
        }
        TreeNode<K, V> node = floorKey(root, key);
        return node == null ? null : node.key;
    }

    // important
    private TreeNode<K, V> floorKey(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int i = key.compareTo(node.key);
        // 如果 key < node.key ， 往左子树找
        if (i < 0) {
            return floorKey(node.left, key);
        }

        // key > node.key 看右子树还有没有小于等于key的节点，有则返回，无则返回本节点
        if (i > 0) {
            TreeNode<K, V> rightNode = floorKey(node.right, key);
            if (rightNode == null) {
                return node;
            }
            return rightNode;
        }

        return node;
    }

    // 返回大于等于 key 的最小的键
    public K ceilingKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("map is empty");
        }
        TreeNode<K, V> node = ceilingKey(root, key);
        return node == null ? null : node.key;
    }

    private TreeNode<K, V> ceilingKey(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int i = key.compareTo(node.key);
        // key < node.key 往左子树找，还有没有大于等于key的节点,有则返回，无则返回本节点
        if (i < 0) {
            TreeNode<K, V> leftNode = ceilingKey(node.left, key);
            if (leftNode == null) {
                return node;
            }
            return leftNode;
        }
        // key > node.key 往右子树找
        if (i > 0) {
            return ceilingKey(node.right, key);
        }
        // key = node.key 相等则返回
        return node;
    }

    // 返回小于 key 的键的个数
    public int rank(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return rank(root, key);
    }

    // 返回以 node 为根的 BST 中小于 key 的键的个数
    private int rank(TreeNode<K, V> node, K key) {
        if (node == null) {
            return 0;
        }
        int i = key.compareTo(node.key);
        // key < node.key 往左边找
        if (i < 0) {
            return rank(node.left, key);
        } else if (i > 0) {
            // 左子树节点个数+自己+右子树符合条件的节点个数
            return 1 + size(node.left) + rank(node.right, key);
        } else {
            return size(node.left);
        }
    }

    // 返回索引为 i 的键，i 从 0 开始计算
    public K select(int i) {
        if (i < 0 || i >= size(root)) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        return select(root, i).key;
    }

    // 返回以 node 为根的 BST 中索引为 i 的那个节点
    private TreeNode<K, V> select(TreeNode<K, V> node, int i) {
        int size = size(node.left);
        // 左子树节点 == i node就是对应索引i的节点
        if (size == i) {
            return node;
        }
        if (size > i) {
            return select(node.left, i);
        }
        // node左子树节点个数<i，说明i对应的节点在node的右子树，往右子树找 i要减去左子树节点个数+1
        return select(node.right, i - size - 1);
    }

    // 返回 BST 中最大的键
    public K maxKey() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // 从根节点开始找  根据bst的性质，节点的左子树的所有节点的值都小于它，右子树节点的值都大于它，因此bst中最小值肯定在根节点的右子树中
        TreeNode<K, V> maxNode = maxNode(root);
        return maxNode.key;
    }

    private TreeNode<K, V> maxNode(TreeNode<K, V> p) {
        if (p.right == null) {
            return p;
        }
        // 如果节点还有右节点，则继续向右子树查询
        return maxNode(p.right);
    }

    // 返回 BST 中最小的键
    public K minKey() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // 从根节点开始找  bst中最小值肯定在根节点的左子树中
        TreeNode<K, V> minNode = minNode(root);
        return minNode.key;
    }

    private TreeNode<K, V> minNode(TreeNode<K, V> p) {
        if (p.left == null) {
            return p;
        }
        // 以节点的左子结点为根节点继续寻找
        return minNode(p.left);
    }

    // 判断 key 是否存在 Map 中
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return get(key) != null;
    }

    /***** 工具函数 *****/

    // 从小到大返回所有键
    public Iterable<K> keys() {
        LinkedList<K> list = new LinkedList<>();
        traverse(root, list);

        return list;
    }

    // 中序遍历 BST
    private void traverse(TreeNode<K, V> node, LinkedList<K> list) {
        if (node == null) {
            return;
        }
        // 如果node不为空，往左子树找
        traverse(node.left, list);
        // 添加完左子树后，添加自己
        list.addLast(node.key);
        // 添加自己后，添加右子树
        traverse(node.right, list);
    }

    // 从小到大返回闭区间 [min, max] 中的键
    public Iterable<K> keys(K min, K max) {
        if (min == null) {
            throw new IllegalArgumentException("min is null");
        }
        if (max == null) {
            throw new IllegalArgumentException("max is null");
        }
        LinkedList<K> list = new LinkedList<>();
        traverse(root, list, min, max);
        return list;
    }

    // 中序遍历 BST
    private void traverse(TreeNode<K, V> node, LinkedList<K> list, K min, K max) {
        if (node == null) {
            return;
        }
        int minCmp = min.compareTo(node.key);
        int maxCmp = max.compareTo(node.key);
        // node.key > min 如果节点值比最小值min小，则左子树不需要遍历
        if (minCmp < 0) {
            traverse(node.left, list, min, max);
        }
        if (minCmp <= 0 && maxCmp >= 0) {
            list.addLast(node.key);
        }
        // 如果节点值比最大值max大，则右子树不遍历
        if (maxCmp > 0) {
            traverse(node.right, list, min, max);
        }
    }

    public int size() {
        return size(root);
    }

    // 返回以 node 节点为根的 BST 有多少节点
    private int size(TreeNode<K, V> node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {
        MyTreeMap<Integer, String> treeMap = new MyTreeMap<>();
        treeMap.put(5, "a");
        treeMap.put(9, "b");
        treeMap.put(4, "c");
        treeMap.put(3, "d");
        treeMap.put(1, "j");
        treeMap.put(7, "e");
        treeMap.put(10, "f");
        treeMap.put(6, "g");
        treeMap.put(8, "h");
        treeMap.put(2, "i");
        System.out.println(treeMap.keys());
        System.out.println(treeMap.keys(3, 9));
        System.out.println(treeMap.remove(4));
        System.out.println(treeMap.keys());
        System.out.println(treeMap.select(7));
        System.out.println(treeMap.rank(6));
        System.out.println(treeMap.minKey());
        System.out.println(treeMap.maxKey());
    }

}
