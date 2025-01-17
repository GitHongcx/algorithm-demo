package com.hcx.algorithm.tree;

import java.util.LinkedList;

/**
 * @Title: MinDepth.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode111.二叉树的最小深度
 * @Author: hongcaixia
 * @Date: 2025/1/17 10:35
 * @Version V1.0
 */
public class MinDepth {


    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // 左子树为空
        if (left == 0) {
            // 返回右子树深度+1
            return right + 1;
        }
        // 右子树为空
        if (right == 0) {
            // 返回左子树深度+1
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }

    /**
     * 使用层序遍历,遇到的第一个叶子节点所在的层数就是最小深度
     * @param root
     * @return
     */
    public static int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        if (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                // 叶子节点
                if (poll.left == null && poll.right == null) {
                    return depth;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return depth;
    }


    /**
     *        1
     *      /   \
     *     2     3
     *    / \
     *   4   7
     * @param args
     */
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(new TreeNode(new TreeNode(4), 2, new TreeNode(7)),
                1,
                new TreeNode(null, 3, null));
        int i = minDepth(treeNode);
        System.out.println(i);
    }

    static class TreeNode{
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

        public TreeNode(TreeNode left, int val , TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
