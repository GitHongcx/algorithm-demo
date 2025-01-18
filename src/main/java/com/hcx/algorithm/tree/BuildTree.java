package com.hcx.algorithm.tree;

import java.util.Arrays;

/**
 * @Title: BuildTree.java
 * @Package com.hcx.algorithm.tree
 * @Description:
 * Leetcode105.从前序与中序遍历序列构造二叉树
 * Leetcode106.从中序与后序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，
 * 其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * @Author: hongcaixia
 * @Date: 2024/4/6 8:57
 * @Version V1.0
 */
public class BuildTree {

    /**
     * 从中序与后序遍历序列构造二叉树
     * @param inOrder   左根右
     * @param postOrder 左右根
     * @return
     */
    public static TreeNode buildTreeForInAndPost(int[] inOrder, int[] postOrder) {
        if (inOrder.length == 0 || postOrder.length == 0) {
            return null;
        }
        // 找到根节点
        int rootVal = postOrder[postOrder.length - 1];
        TreeNode root = new TreeNode(rootVal);
        // 从中序遍历中找到根节点，切分左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == rootVal) {
                // 中序遍历的
                // 左子树
                int[] inLeftTree = Arrays.copyOfRange(inOrder, 0, i);
                // 右子树
                int[] inRightTree = Arrays.copyOfRange(inOrder, i + 1, inOrder.length);

                // 后序遍历的 左 右 根
                int[] postLeftTree = Arrays.copyOfRange(postOrder, 0, i);
                int[] postRightTree = Arrays.copyOfRange(postOrder, i, postOrder.length - 1);

                root.left = buildTreeForInAndPost(inLeftTree, postLeftTree);
                root.right = buildTreeForInAndPost(inRightTree, postRightTree);

                break;
            }
        }
        return root;
    }

    /**
     * 从前序与中序遍历序列构造二叉树
     * 1.通过前序遍历找到根节点
     * 2.通过中序遍历切分成左右子树
     *
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * @param preOrder
     * @param inOrder
     * @return
     */
    public static TreeNode buildTreeForPreAndIn(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0 || inOrder.length == 0) {
            return null;
        }
        int val = preOrder[0];
        // 根节点
        TreeNode root = new TreeNode(val);
        // 在中序遍历中找到根节点，切分成左右子树
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == val) {
                // 左子树
                // 包头不包尾
                // 中序 ：左根右
                int[] inLeftTree = Arrays.copyOfRange(inOrder, 0, i); // 不包括i
                int[] inRightTree = Arrays.copyOfRange(inOrder, i + 1, inOrder.length); // 根节点是i，右子树i+1

                // 前序：根左右
                int[] preLeftTree = Arrays.copyOfRange(preOrder, 1, i + 1);
                int[] preRightTree = Arrays.copyOfRange(preOrder, i + 1, preOrder.length);

                root.left = buildTreeForPreAndIn(preLeftTree, inLeftTree);
                root.right = buildTreeForPreAndIn(preRightTree, inRightTree);

                break;
            }
        }
        return root;
    }









    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length == 1) {
            return root;
        }
        // 从中序数组中找到根节点的下标
        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                rootIndex = i;
            }
        }
        // 中序的左子树
        int[] inLeftArr = subArr(inorder, 0, rootIndex - 1);
        // 中序的右子树
        int[] inRightArr = subArr(inorder, rootIndex + 1, inorder.length - 1);

        // 前序的左子树
        int[] preLeftArr = subArr(preorder, 1, inLeftArr.length);
        // 前序的右子树
        int[] preRightArr = subArr(preorder, inLeftArr.length + 1, preorder.length - 1);

        root.left = buildTree(preLeftArr, inLeftArr);
        root.right = buildTree(preRightArr, inRightArr);
        return root;
    }

    /**
     * 切割数组
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static int[] subArr(int[] arr, int start, int end) {
        int[] result = new int[end - start + 1];

        for (int i = 0; i < arr.length; i++) {
            if (i >= start && i <= end) {
                result[i - start] = arr[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{0,1,2,3,4,5,6};
//        int[] ints = subArr(arr, 4,6);
//        for (int i = 0; i < ints.length; i++) {
//            System.out.println(ints[i]);
//        }

        int[] preOrder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        TreeNode treeNode = buildTree(preOrder, inorder);
        printTree(treeNode,3);
    }

    private static void printTree(TreeNode node, int level) {
        if (node == null)
            return;

        printTree(node.right, level + 1);

        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                System.out.print("|\t");
            System.out.println("|-------" + node.val);
        } else
            System.out.println(node.val);

        printTree(node.left, level + 1);
    }

}
