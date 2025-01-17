package com.hcx.algorithm.tree;

import java.util.LinkedList;

/**
 * @Title: MaximunDepth.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode104.二叉树的最大深度
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


    public static int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftDepth = maxDepth1(root.left);
        int rightDepth = maxDepth1(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 左 右 根
    public static int maxDepth2(TreeNode root) {
        // 栈的最大高度
        int maxDepth = 0;
        // 最新弹栈的节点
        TreeNode lastPop = null;
        // 用来记录去的路
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            // 去的路
            if (root != null) {
                stack.push(root);
                // 判断栈的高度
                if (stack.size() > maxDepth) {
                    maxDepth = stack.size();
                }
                root = root.left;
            }
            // 回的路
            else {
                // 拿到栈顶的元素
                TreeNode top = stack.peek();
                if (top.right == null || top.right == lastPop) {
                    //没有右孩子  可以弹栈
                    // 右孩子已经处理过了 可以弹栈
                    lastPop = stack.pop();
                }
                // 还有右孩子 或者 右孩子还未处理
                else {
                    // 处理右子树
                    root = top.right;
                }
            }
        }
        return maxDepth;
    }


    // 使用层序遍历 ，有几层，深度就是多少
    public void leveIter(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 每一层的元素个数
        int levelNum = 1;
        while (!queue.isEmpty()) {
            int curLevelNum = 0;
            for (int i = 0; i < levelNum; i++) {
                TreeNode node = queue.poll();
                // 出队的元素 把他的孩子加入队列中
                if (node.left != null) {
                    queue.offer(node.left);
                    curLevelNum++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    curLevelNum++;
                }
            }
            levelNum = curLevelNum;
        }
    }


    public int maxDepth4(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 出队的元素 把他的孩子加入队列中
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);

                }
            }
            depth++;
        }
        return depth;
    }
}
