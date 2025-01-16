package com.hcx.algorithm.tree;

/**
 * @Title: SymmetricTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode101.对称二叉树
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


    public boolean checkSymmetricTree(TreeNode root) {
        if(root==null){
            return true;
        }
        return recursionCheck(root.left,root.right);
    }

    public boolean recursionCheck(TreeNode left,TreeNode right){
        if(left==null && right == null){
            return true;
        }
        if(left == null || right == null){
            return false;
        }
        if(left.val!=right.val){
            return false;
        }
        boolean leftRes = recursionCheck(left.left,right.right);
        boolean rightRes = recursionCheck(left.right,right.left);
        return leftRes && rightRes;
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
