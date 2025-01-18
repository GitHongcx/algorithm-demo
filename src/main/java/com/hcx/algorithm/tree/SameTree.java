package com.hcx.algorithm.tree;

/**
 * @Title: SameTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: 相同的树
 *  https://leetcode.cn/problems/same-tree/description/
 *  给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *  如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * @Author: hongcaixia
 * @Date: 2024/3/30 12:07
 * @Version V1.0
 */
public class SameTree {

    /**
     * 判读两棵二叉树是否相同
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null) {
            return true;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);

        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);

        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(7);

        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = new TreeNode(2);
        treeNode1.right = new TreeNode(3);

        treeNode1.left.left = new TreeNode(4);
        treeNode1.left.right = new TreeNode(5);
//
//        treeNode1.right.left = new BSTTreeNode(6);
//        treeNode1.right.right = new BSTTreeNode(7);

        boolean sameTree = isSameTree(treeNode, treeNode1);
        System.out.println(sameTree);
    }

}
