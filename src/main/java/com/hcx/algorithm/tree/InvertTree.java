package com.hcx.algorithm.tree;

/**
 * @Title: InvertTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode226.翻转二叉树
 * @Author: hongcaixia
 * @Date: 2025/1/17 14:46
 * @Version V1.0
 */
public class InvertTree {

    public TreeNode invertTree(TreeNode root) {
        changeRecursion(root);
        return root;
    }

    /**
     * 递归交换左右孩子
     * @param root
     */
    public void changeRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        // 交换左右孩子
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 针对下面的孩子节点继续重复同样的操作
        changeRecursion(root.left);
        changeRecursion(root.right);
    }

}
