package com.hcx.algorithm.tree;

import java.util.LinkedList;

/**
 * @Title: TreeFlatten.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode114.二叉树展开为链表
 * @Author: hongcaixia
 * @Date: 2025/1/20 15:11
 * @Version V1.0
 */
public class TreeFlatten {

    public static void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }
        // 新链表
        TreeNode newNode = new TreeNode(root.val);
        TreeNode newPointer = newNode;
        LinkedList<TreeNode> stack = new LinkedList();
        TreeNode pointer = root;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
                if (pointer != null) {
                    // 创建出新节点
                    TreeNode node = new TreeNode(pointer.val);
                    newPointer.right = node;
                    newPointer = node;
                }
            } else {
                TreeNode pop = stack.pop();
                pointer = pop.right;
                if (pointer != null) {
                    // 创建出新节点
                    TreeNode node = new TreeNode(pointer.val);
                    newPointer.right = node;
                    newPointer = node;
                }
            }
        }
    }


    /**
     * 先序：根左右
     * 二叉树中通过right指针链接的节点，相对顺序是不变的
     * <p>
     * 从根节点开始，依次遍历右子树的节点，检查节点是否有左孩子，如果没有，不需要合并，继续向下找；
     * 如果有，就该左子树作为当前遍历的左孩子；
     * 按照上述步骤重复执行，直到每一个节点的左子树都移动到了右子树为止。
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        TreeNode pointer = root;
        while (pointer != null) {
            if (pointer.left != null) {
                TreeNode rightPointer = pointer.left;
                // 找到左子树中最右边的孩子
                while (rightPointer.right != null) {
                    rightPointer = rightPointer.right;
                }
                // 把左子树链接到右子树中
                rightPointer.right = pointer.right;
                pointer.right = pointer.left;
                pointer.left = null;
            }
            // 依次遍历节点的右子树
            pointer = pointer.right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6)));
        flatten(root);
    }

    static class TreeNode {
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

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
