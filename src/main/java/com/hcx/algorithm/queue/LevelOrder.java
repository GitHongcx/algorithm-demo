package com.hcx.algorithm.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Title: LevelOrder.java
 * @Package com.hcx.algorithm.queue
 * @Description: Leetcode102.二叉树的层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * @Author: hongcaixia
 * @Date: 2025/1/11 10:02
 * @Version V1.0
 */
public class LevelOrder {

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> resultList = new ArrayList<>();
        // 当前层级的元素个数
        int curLevelCount = 1;
        // 队列非空，还有待处理的元素
        while (!queue.isEmpty()) {
            // 下一层级的元素个数
            int nextLevel = 0;
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < curLevelCount; i++) {
                TreeNode node = queue.poll();
                levelList.add(node.val);
                // 把该节点的左右孩子加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                    nextLevel++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    nextLevel++;
                }
            }
            curLevelCount = nextLevel;
            resultList.add(levelList);
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

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
