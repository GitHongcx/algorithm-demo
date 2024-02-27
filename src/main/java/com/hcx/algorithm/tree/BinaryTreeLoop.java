package com.hcx.algorithm.tree;

/**
 * @Title: BinaryTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: 遍历二叉树
 * @Author: hongcaixia
 * @Date: 2024/2/16 18:30
 * @Version V1.0
 */
public class BinaryTreeLoop {

    /**
     * 前序遍历: 根左右
     * @param head
     */
    public static void preOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.println(head.val);
        preOrder(head.left);
        preOrder(head.right);
    }

    /**
     * 中序遍历：左根右
     */
    public static void inOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrder(head.left);
        System.out.println(head.val);
        inOrder(head.right);
    }

    /**
     * 后序遍历：左右根
     */
    public static void postOrder(TreeNode head){
        if (head == null) {
            return;
        }
        postOrder(head.left);
        postOrder(head.right);
        System.out.println(head.val);
    }

    public static void main(String[] args) {
        /**
         *         1
         *       2   3
         *     4  5 6  7
         */
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);

        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);


        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(7);

        preOrder(treeNode);
        System.out.println("==========");
        inOrder(treeNode);
        System.out.println("==========");
        postOrder(treeNode);
    }


}
class TreeNode{
    /**
     * 值
     */
    public int val;

    /**
     * 左节点
     */
    public TreeNode left;

    /**
     * 右节点
     */
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
