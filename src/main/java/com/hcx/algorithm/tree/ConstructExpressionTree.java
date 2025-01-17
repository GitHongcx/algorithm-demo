package com.hcx.algorithm.tree;

import java.util.LinkedList;

/**
 * @Title: ConstructExpressionTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: 后缀表达式转二叉树
 * @Author: hongcaixia
 * @Date: 2025/1/17 15:33
 * @Version V1.0
 */
public class ConstructExpressionTree {

    /**
     * 操作数：压栈
     * 操作符：弹栈
     * 21-3*：表达式树
     *         *
     *        / \
     *       -   3
     *      / \
     *     2   1
     *
     *
     * @param tokens
     * @return
     */
    public TreeNode constructExpressionTree(String[] tokens) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (String token : tokens) {
            switch (token) {
                // 操作符 弹栈
                case "+", "-", "*", "/" -> {
                    // 先是右孩子
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode root = new TreeNode(token);
                    root.left = left;
                    root.right = right;
                    stack.push(root);
                }
                // 操作数
                default -> {
                    stack.push(new TreeNode(token));
                }
            }
        }
        return stack.peek();
    }


    static class TreeNode {
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(TreeNode left, String val, TreeNode right) {
            this.left = left;
            this.val = val;
            this.right = right;
        }

        @Override
        public String toString() {
            return this.val;
        }
    }
}
