package com.hcx.algorithm.tree;

/**
 * @Title: MaximunDepth.java
 * @Package com.hcx.algorithm.tree
 * @Description: 二叉树的最大深度
 * 给定一个二叉树 root ，返回其最大深度。
 * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
 * @Author: hongcaixia
 * @Date: 2024/4/5 9:48
 * @Version V1.0
 */
public class MaximumDepth {

    /**
     * 最大深度
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);

        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);


        treeNode.right.left = new TreeNode(5);
        treeNode.right.right = new TreeNode(4);
        System.out.println(maxDepth(treeNode));
    }
}
