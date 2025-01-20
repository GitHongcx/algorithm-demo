package com.hcx.algorithm.tree;

/**
 * @Title: SortedArrayToBST.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode108.将有序数组转换为二叉搜索树
 * @Author: hongcaixia
 * @Date: 2025/1/20 17:21
 * @Version V1.0
 */
public class SortedArrayToBST {

    //中序遍历 左根右
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTRecursion(nums, 0, nums.length - 1);
    }

    /**
     * 递归分治
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public TreeNode sortedArrayToBSTRecursion(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        // 找到中间节点作为根节点
        int mid = (start + end) / 2;

        //根节点
        TreeNode root = new TreeNode(nums[mid]);
        // 设置根节点的左孩子
        root.left = sortedArrayToBSTRecursion(nums, start, mid - 1);
        // 设置根节点的右孩子
        root.right = sortedArrayToBSTRecursion(nums, mid + 1, end);
        return root;
    }


}
