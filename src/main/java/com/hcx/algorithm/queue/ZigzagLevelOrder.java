package com.hcx.algorithm.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Title: ZigzagLevelOrder.java
 * @Package com.hcx.algorithm.queue
 * @Description: Leetcode103.二叉树的锯齿形层序遍历
 * @Author: hongcaixia
 * @Date: 2025/1/12 16:30
 * @Version V1.0
 */
public class ZigzagLevelOrder {

    /**
     * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
     * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList();
        if (root == null) {
            return resultList;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int curLevelCount = 1;
        // 记录第几层
        int levelNum = 0;
        while (!queue.isEmpty()) {
            levelNum = levelNum + 1;
            // 创建双端队列来存储每一层的元素 奇数层顺序（从尾部添加） 偶数层逆序（从头部添加）
            LinkedList<Integer> deque = new LinkedList();
            int nextLevelCount = 0;
            for (int i = 0; i < curLevelCount; i++) {
                TreeNode node = queue.poll();
                // 偶数层 从队列的头部添加进去
                if (levelNum % 2 == 0) {
                    deque.offerFirst(node.val);
                } else {
                    deque.offerLast(node.val);
                }
                if (node.left != null) {
                    nextLevelCount++;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    nextLevelCount++;
                    queue.offer(node.right);
                }
            }
            curLevelCount = nextLevelCount;
            resultList.add(deque);

        }
        return resultList;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(TreeNode left, int val, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        int c1 = 1;
        while (!queue.isEmpty()) {
            int c2 = 0;
            LinkedList<Integer> deque = new LinkedList<>();
            for (int i = 0; i < c1; i++) {
                TreeNode n = queue.poll();
                if (leftToRight) {
                    deque.offerLast(n.val);
                } else {
                    deque.offerFirst(n.val);
                }
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            c1 = c2;
            leftToRight = !leftToRight;
            result.add(deque);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new TreeNode(new TreeNode(4), 2, new TreeNode(5)),
                1, new TreeNode(new TreeNode(6), 3, new TreeNode(7)));

        List<List<Integer>> lists = new ZigzagLevelOrder().zigzagLevelOrder(root);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
