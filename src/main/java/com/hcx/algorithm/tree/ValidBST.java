package com.hcx.algorithm.tree;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Title: ValidBST.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode98.验证二叉搜索树
 * @Author: hongcaixia
 * @Date: 2025/1/19 20:06
 * @Version V1.0
 */
public class ValidBST {

    long prev = Long.MIN_VALUE;

    /**
     * 解法一：使用中序遍历【迭代】
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        TreeNode pointer = root;
        // 记住去时的路
        LinkedList<TreeNode> stack = new LinkedList<>();
        long prev = Long.MIN_VALUE;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
            } else {
                TreeNode pop = stack.pop();
                if (prev >= pop.val) {
                    return false;
                }
                prev = pop.val;
                pointer = pop.right;
            }
        }
        return true;
    }

    /**
     * 解法二：使用中序遍历【递归】
     * @param root
     * @return
     */
    public boolean isValidBSTRecursion(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftRes = isValidBSTRecursion(root.left);
        if (prev >= root.val) {
            return false;
        }
        prev = root.val;
        boolean rightRes = isValidBSTRecursion(root.right);
        return leftRes && rightRes;
    }

    /**
     * 解法三：使用中序遍历【递归优化】：剪枝
     * @param root
     * @return
     */
    public boolean isValidBSTRecursion1(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftRes = isValidBSTRecursion1(root.left);
        if (!leftRes) {
            return false;
        }
        if (prev >= root.val) {
            return false;
        }
        prev = root.val;
        return isValidBSTRecursion1(root.right);
    }

    /**
     * 解法四：使用中序遍历【递归】：使用局部变量
     * 注意这里的参数不能使用普通的整形，因为方法参数属于局部变量，每次都只在对应的递归的方法中生效。
     * @param root
     * @return
     */
    public boolean isValidBSTRecursion2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return doValidBSTRecursion(root, new AtomicLong(Long.MIN_VALUE));
    }


    /**
     * 解法五：上下限递归
     * @param root
     * @return
     */
    public boolean isValidBSTRecursion3(TreeNode root,long leftBoundary,long rightBoundary) {
        if (root == null) {
            return true;
        }
        if (root.val <= leftBoundary || root.val >= rightBoundary) {
            return false;
        }
        boolean leftRes = isValidBSTRecursion3(root.left, leftBoundary, root.val);
        if (!leftRes) {
            return false;
        }
        return isValidBSTRecursion3(root.right, root.val, rightBoundary);
    }

    /**
     * 递归
     * @param root
     * @param prev
     * @return
     */
    public boolean doValidBSTRecursion(TreeNode root, AtomicLong prev) {
        if (root == null) {
            return true;
        }
        boolean leftRes = doValidBSTRecursion(root.left, prev);
        if (!leftRes) {
            return false;
        }
        if (prev.get() >= root.val) {
            return false;
        }
        prev.set(root.val);
        return doValidBSTRecursion(root.right, prev);
    }


    class TreeNode {
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
}
