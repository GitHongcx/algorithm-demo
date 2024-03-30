package com.hcx.algorithm.tree;

/**
 * @Title: SymmetricTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: 对称二叉树
 *  https://leetcode.cn/problems/symmetric-tree/
 *  给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * @Author: hongcaixia
 * @Date: 2024/3/30 13:36
 * @Version V1.0
 */
public class SymmetricTree {

    public static boolean isSymmetric(TreeNode root) {
        return isSame(root, root);
    }


    /**
     * 判断左右是否相等
     * @param left
     * @param right
     * @return
     */
    public static boolean isSame(TreeNode left,TreeNode right) {
        if (left == null ^ right == null) {
            return false;
        }
        if (left == null) {
            return true;
        }
        return left.val == right.val && isSame(left.right, right.left) && isSame(left.left, right.right);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);

        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);


        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(4);
        boolean symmetric = isSymmetric(treeNode);
        System.out.println(symmetric);
    }
}
