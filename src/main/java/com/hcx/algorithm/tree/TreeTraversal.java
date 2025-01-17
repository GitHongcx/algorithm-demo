package com.hcx.algorithm.tree;


import com.sun.source.tree.Tree;

import java.util.LinkedList;

/**
 * @Title: TreeTraversal.java
 * @Package com.hcx.algorithm.tree
 * @Description: 树的遍历
 * @Author: hongcaixia
 * @Date: 2025/1/15 16:40
 * @Version V1.0
 */
public class TreeTraversal {

    /**
     * 前序遍历 根左右
     * @param treeNode
     */
    static void preOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.val);
        preOrder(treeNode.left);
        preOrder(treeNode.right);
    }


    /**
     * 中序遍历
     * @param treeNode
     */
    static void inOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrder(treeNode.left);
        System.out.println(treeNode.val);
        inOrder(treeNode.right);
    }

    /**
     * 后序遍历
     * @param treeNode
     */
    static void postOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        postOrder(treeNode.left);
        postOrder(treeNode.right);
        System.out.println(treeNode.val);
    }

    // 用栈记住来时的路，回去的时候就依次弹栈
    static LinkedList<TreeNode> stack = new LinkedList();
    static void preAndInIterate(TreeNode treeNode) {
        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                System.out.println(treeNode.val);
                // 记住去的路
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                // 往回走
                TreeNode pop = stack.pop();
                System.out.println("开始回去："+pop.val);
                treeNode = pop.right;
            }

        }
    }

    static void postIterate(TreeNode treeNode){
        // 记录最新的弹栈的元素 用于父节点判断 右孩子是不是已经处理过了
        TreeNode lastPop = null;
        while (treeNode != null || !stack.isEmpty()) {
            // 处理左右孩子
            if (treeNode != null) {
                System.out.println(treeNode.val);
                // 记住去的路
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                /**
                 * 处理完了左边的，开始处理右边的
                 * 往回走的时候先不能弹栈 因为还要先处理了右边的 如果弹了栈 就找不到回去的路了
                 * 所以在准备弹栈的时候，先判断栈顶的这个元素的右孩子是不是处理完了
                 * - 针对叶子节点，如果右边是空，那么就可以弹栈了。
                 * - 针对非叶子节点，需要记录一下 右边孩子是不是已经弹栈了
                 */
                TreeNode top = stack.peek();
                // 如果右孩子为空 或者 最近弹栈的就是右孩子（说明已经处理过了右孩子）
                if (top.right == null || lastPop == top.right) {
                    // 往回走
                    lastPop = stack.pop();
                    System.out.println("回去的路："+ lastPop.val);
                }else{
                    // 如果还有右孩子 就先处理右孩子的
                    //System.out.println("开始回去："+top.val);
                    treeNode = top.right;
                }
            }
        }
    }

    // 左右根
    public static void post(TreeNode treeNode){
        TreeNode lastPop = null;
        while (treeNode!=null || !stack.isEmpty()){
            // 去时的路 （适用于前序遍历）
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            }else {
                // 去的路已经结束，开始往回走
                TreeNode root = stack.peek();
                /**
                 * 1.如果没有右孩子 可以直接弹栈，否则 需要先处理右孩子
                 * 2.已经处理过了右孩子 也可以弹栈了(每次弹栈的都更新到变量中，如果最新的这个值就是右孩子，那说明已经处理过了)
                 */
                if (root.right == null || lastPop == root.right) {
                    lastPop = stack.pop();
                    System.out.println(lastPop.val);
                } else {
                    // 去时的路 处理右孩子
                    treeNode = root.right;
                    // 处理右孩子
                    System.out.println(root.right.val);
                }
            }
        }
    }

    public static void postorderTraversal(TreeNode root) {
        // 用栈记录去时的路
        LinkedList<TreeNode> stack = new LinkedList();
        // 记录最新的弹栈元素
        TreeNode lastPop = null;
        while(root!=null || !stack.isEmpty()){
            // 去的路
            if(root!=null){
                stack.push(root);
                root = root.left;
            }
            // 回的路
            else{
                TreeNode peek = stack.peek();
                // 如果没有右孩子 可以直接弹栈 叶子节点 或者是 已经处理完了右孩子
                if(peek.right==null){
                    // 回的路
                    lastPop = stack.pop();
                    // 左 右 孩子 4 7 5 6
                    System.out.println(lastPop.val);
                }else if(lastPop == peek.right){
                    // 回的路
                    lastPop = stack.pop();
                    // 左 右 孩子 2 3 1
                    System.out.println(lastPop.val);
                }
                // 有右孩子要处理
                else {
                    //有右孩子要处理
                    root = peek.right;
                    //System.out.println(root.val);
                }
            }
        }
    }

    public static void iter(TreeNode treeNode) {
        TreeNode lastPop = null;
        while (treeNode != null || !stack.isEmpty()) {
            // 去的路
            if (treeNode != null) {
                // 前序  根 左 右
                // System.out.println(treeNode.val);
                stack.push(treeNode);
                // 处理左子树
                treeNode = treeNode.left;
            } else {
                //回的路
                TreeNode root = stack.peek();
                // 右孩子为空 这里处理的是叶子节点
                if (root.right == null ) {
                    lastPop = stack.pop();
                    // 后序1 ：针对后序遍历 这里的是叶子节点
                    // 中序1： 针对中序遍历
                    System.out.println(lastPop.val);
                }
                // 右孩子已经处理完
                else if(root.right == lastPop){
                    // 这里弹栈的是根节点
                    lastPop = stack.pop();
                    // 后序2： 左 右 根
                    //System.out.println(lastPop.val);
                }
                // 处理右子树
                else {
                    //中序2： 准备去处理右子树 这里的是根元素
                    System.out.println(root.val);
                    treeNode = root.right;
                }
            }
        }
    }


    /**
     *        1
     *      /   \
     *     2     3
     *    / \   / \
     *   4   7 5   6
     * @param args
     */
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(new TreeNode(new TreeNode(4), 2, new TreeNode(7)),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6)));
//        postIterate(treeNode);
        postorderTraversal(treeNode);

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

