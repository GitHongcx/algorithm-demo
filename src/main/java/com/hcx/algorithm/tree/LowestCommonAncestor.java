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
}
