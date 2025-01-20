package com.hcx.algorithm.tree;

/**
 * @Title: LowestCommonAncestor.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode235.二叉搜索树的最近公共祖先
 * @Author: hongcaixia
 * @Date: 2025/1/20 12:25
 * @Version V1.0
 */
public class LowestCommonAncestor {

    /**
     * 当节点在p和q的中间时，那么此节点就是他们的最近公共祖先.
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        // p和q在node的同一侧时 不断循环
        while ((p.val < node.val && q.val < node.val) || (p.val > node.val && q.val > node.val)) {
            if (p.val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    /**
     * 236.二叉树的最近公共祖先
     * 在后序遍历（左右根）回溯的过程中进行处理
     * 如果左子树出现了p、q 右子树出现了p、q，按照后序遍历的结果就找到了p和q，此时根节点就是最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        // 左子树出现了p、q
        TreeNode leftTree = lowestCommonAncestor2(root.left, p, q);
        // 右子树出现了p、q
        TreeNode rightTree = lowestCommonAncestor2(root.right, p, q);
        // 当前的root就是公共祖先
        if (leftTree != null && rightTree != null) {
            return root;
        }
        // 左子树有出现pq 右子树没出现 继续将公共祖先网上返回
        if (leftTree != null) {
            return leftTree;
        }
        // 右子树出现了pq，左子树没出现 继续将公共祖先网上返回
        if (rightTree != null) {
            return rightTree;
        }
        return null;
    }
}
