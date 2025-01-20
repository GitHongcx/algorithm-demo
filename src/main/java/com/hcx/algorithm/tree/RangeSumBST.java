package com.hcx.algorithm.tree;

import java.util.LinkedList;

/**
 * @Title: RangeSumBST.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode938.二叉搜索树的范围和
 * @Author: hongcaixia
 * @Date: 2025/1/20 09:28
 * @Version V1.0
 */
public class RangeSumBST {

    /**
     * 使用中序遍历
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pointer = root;
        int sum = 0;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
            } else {
                TreeNode pop = stack.pop();
                if (pop.val > high) {
                    break;
                }
                if (pop.val >= low) {
                    sum += pop.val;
                }
                pointer = pop.right;
            }
        }
        return sum;
    }

    /**
     * 递归剪枝
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBSTRecursion(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val < low) {
            return rangeSumBSTRecursion(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBSTRecursion(root.left, low, high);
        }
        return root.val + rangeSumBSTRecursion(root.left, low, high) + rangeSumBSTRecursion(root.right, low, high);
    }
}
