package com.hcx.algorithm.tree;

/**
 * @Title: BstFromPreorder.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode1008.前序遍历构造二叉搜索树
 * @Author: hongcaixia
 * @Date: 2025/1/20 10:11
 * @Version V1.0
 */
public class BstFromPreorder {

    /**
     * 解法一：遍历数组依次创建新节点插入树对应的位置中
     * O(nlogn)
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            insertNode(root,preorder[i]);
        }
        return root;
    }

    /**
     * 插入节点
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertNode(root.left, val);
        } else if (val > root.val) {
            root.right = insertNode(root.right, val);
        }
        return root;
    }


    /**
     * 解法二：使用上界递归 根左右  将当前节点创建完整之后（本身的节点和他自己的左右孩子），再将他设置为父亲的左右孩子
     * 1.遍历前序数组中的节点
     * 2.给当前节点设置左孩子的上限和右孩子的上限
     * 3.在后续节点创建的过程中，作为前面节点的孩子，需要满足前面设置的左右限制；如果当前节点不满足，则左右孩子设置为null。
     * O(logn)
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        return bstFromPreorderRecursion(preorder,Integer.MAX_VALUE);
    }

    // 数组中的索引
    int arrIndex = 0;

    /**
     * 满足左边界，可以作为左孩子，满足右边界，可以作为右孩子；否则左右孩子设置为null
     * @param preorder
     * @param boundary
     * @return
     */
    public TreeNode bstFromPreorderRecursion(int[] preorder,int boundary) {
        // 数组遍历结束
        if (arrIndex == preorder.length) {
            return null;
        }
        // 数组中的值
        int val = preorder[arrIndex];
        // 如果该值大于上界 不符合左右孩子，返回空作为左右孩子
        if (val > boundary) {
            return null;
        }
        // 没有大于上界，创建节点
        TreeNode treeNode = new TreeNode(val);
        arrIndex++;
        // 设置节点的左右孩子
        treeNode.left = bstFromPreorderRecursion(preorder, val); // 左孩子边界：要比当前节点小
        treeNode.right = bstFromPreorderRecursion(preorder, boundary); // 右孩子边界：就是当前的边界（每次在设置左孩子上界的时候会被更新，就是父节点的左孩子上限）
        // 把当前已经设置完了左右孩子的节点返回给上一层
        return treeNode;
    }

    /**
     * 解法三：分治：每次划分出根 左子树 右子树；逐渐缩小范围
     * O(nlogn)
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder3(int[] preorder) {
        return partitionRecursion(preorder,0,preorder.length-1);
    }

    /**
     * 递归分治，逐渐缩小树的范围，依次建立每一个棵子树
     * @param preorder
     * @param left
     * @param right
     * @return
     */
    public TreeNode partitionRecursion(int[] preorder,int left,int right) {
        if (left > right) {
            return null;
        }
        // 根节点
        TreeNode root = new TreeNode(preorder[left]);
        // 查找右边界
        int index = left+1;
        while (index <= right) {
            if (preorder[index] > preorder[left]) {
                break;
            }
            index++;
        }
        root.left = partitionRecursion(preorder, left + 1, index - 1);
        root.right = partitionRecursion(preorder, index, right);
        return root;
    }

}
