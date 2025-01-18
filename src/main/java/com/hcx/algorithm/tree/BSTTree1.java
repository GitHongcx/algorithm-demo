package com.hcx.algorithm.tree;

/**
 * @Title: BSTTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: 二叉搜索树-泛型
 * @Author: hongcaixia
 * @Date: 2025/1/18 09:55
 * @Version V1.0
 */
public class BSTTree1<K extends Comparable<K>,V> {

    /**
     * 根节点
     */
    BSTTreeNode<K,V> root;

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public V get(K key) {
        BSTTreeNode<K, V> node = root;
        while (node != null) {
            int res = key.compareTo(node.key);
            if (res < 0) {
                node = node.left;
            } else if (res > 0) {
                node = node.right;
            } else {
                return (V) node.value;
            }

        }
        return null;
    }



    static class BSTTreeNode<K,V> {
        // 作为排序的属性
        K key;
        V value;
        BSTTreeNode<K,V> left;
        BSTTreeNode<K,V> right;

        public BSTTreeNode(K key) {
            this.key = key;
        }

        public BSTTreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTTreeNode(K key, V value, BSTTreeNode<K,V> left, BSTTreeNode<K,V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
