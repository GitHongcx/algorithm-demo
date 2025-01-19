package com.hcx.algorithm.tree;

/**
 * @Title: BSTTreeDeleteNode.java
 * @Package com.hcx.algorithm.tree
 * @Description: Leetcode450.删除二叉搜索树中的节点
 * @Author: hongcaixia
 * @Date: 2025/1/19 15:30
 * @Version V1.0
 */
public class BSTTreeDeleteNode {

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        TreeNode pointer = root;
        TreeNode deletedParent = null;
        while (pointer != null) {
            if (key < pointer.val) {
                deletedParent = pointer;
                pointer = pointer.left; // 左子树中找
            } else if (key > pointer.val) {
                deletedParent = pointer;
                pointer = pointer.right; // 右子树中找
            } else {
                break; // 找到了
            }
        }
        // 被删除节点不存在
        if (pointer == null) {
            return root;
        }
        // 如果被删除的节点只有左子树 把左孩子托付给父亲
        if (pointer.right == null) {
            if (deletedParent == null) {
                root = pointer.left;
            } else if (deletedParent.left == pointer) {
                deletedParent.left = pointer.left;
            } else {
                deletedParent.right = pointer.left;
            }
            //shift(deletedParent, pointer, pointer.left);
        }
        // 被删除节点只有右子树，把右孩子托付给父亲
        else if (pointer.left == null) {
            if (deletedParent == null) {
                root = pointer.right;
            } else if (deletedParent.left == pointer) {
                deletedParent.left = pointer.right;
            } else {
                deletedParent.right = pointer.right;
            }
            //shift(deletedParent, pointer, pointer.right);
        }
        // 被删除节点左右孩子都有
        else {
            // 找到被删除节点的后继节点（右子树中的最小值），让后继节点上位
            TreeNode deletedSuccessor = pointer.right;
            TreeNode deletedSuccessorParent = pointer;// 记录后继节点的父亲，用来处理后事的
            // 往左找
            while (deletedSuccessor.left != null) {
                deletedSuccessorParent = deletedSuccessor;
                deletedSuccessor = deletedSuccessor.left;
            }
            // 说明后继节点与被删除节点是相邻的 后继节点直接上位 改变左指针
            if (deletedSuccessor == pointer.right) {
                if (deletedParent == null) {
                    root = deletedSuccessor;
                } else if (deletedParent.left == pointer) {
                    deletedParent.left = deletedSuccessor;
                } else {
                    deletedParent.right = deletedSuccessor;
                }
                //shift(deletedParent, pointer, deletedSuccessor);
                deletedSuccessor.left = pointer.left;
            } else {
                // 后继节点与被删除的节点不相邻，先处理后事：把后继节点的孩子托付给后继节点的父亲
                if (deletedSuccessorParent == null) {
                    root = deletedSuccessor.right;
                } else if (deletedSuccessorParent.left == deletedSuccessor) {
                    deletedSuccessorParent.left = deletedSuccessor.right;
                } else {
                    deletedSuccessorParent.right = deletedSuccessor.right;
                }
                //shift(deletedSuccessorParent, deletedSuccessor, deletedSuccessor.right); // 处理后事
                // 后继节点上位

                if (deletedParent == null) {
                    root = deletedSuccessor;
                } else if (deletedParent.left == pointer) {
                    deletedParent.left = deletedSuccessor;
                } else {
                    deletedParent.right = deletedSuccessor;
                }

                //shift(deletedParent, pointer, deletedSuccessor);
                // 更新上位节点的左右指针
                deletedSuccessor.left = pointer.left;
                deletedSuccessor.right = pointer.right;
            }
        }
        return root;
    }


    // 托付方法 把child托付给parent
    public static void shift(TreeNode parent, TreeNode deleted, TreeNode child) {
        if (parent == null) {
            parent = child;
        } else if (parent.left == deleted) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    // [50,30,70,null,40,60,80]

    /**
     *         50
     *        /  \
     *      30    70
     *        \   / \
     *        40 60  80
     * @param args
     */
    /**
     * 4
     * \
     * 7
     * / \
     * 6   8
     * /     \
     * 5       9
     *
     * @param args
     */
    public static void main(String[] args) {
//        TreeNode treeNode = new TreeNode(
//                4,
//                null,
//                new TreeNode(7,new TreeNode(6,new TreeNode(5),null),new TreeNode(8,null,new TreeNode(9))));
//
        TreeNode treeNode = new TreeNode(
                50,
                new TreeNode(30),
                new TreeNode(70, new TreeNode(60), new TreeNode(80)));

        //TreeNode treeNode = new TreeNode(0);

        TreeNode bstNode = deleteNode(treeNode, 50);
        System.out.println(bstNode);
    }

    static class TreeNode {
        // 作为排序的属性
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.val = value;
        }

        public TreeNode(int value, TreeNode left, TreeNode right) {
            this.val = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 递归实现
     * @param node
     * @param key
     * @return
     */
    public TreeNode deleteNodeRecursion(TreeNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.val) {
            node.left = deleteNodeRecursion(node.left, key);
            return node;
        }
        if (node.val < key) {
            node.right = deleteNodeRecursion(node.right, key);
            return node;
        }
        if (node.left == null) { // 情况1 - 只有右孩子
            return node.right;
        }
        if (node.right == null) { // 情况2 - 只有左孩子
            return node.left;
        }
        TreeNode s = node.right; // 情况3 - 有两个孩子
        while (s.left != null) {
            s = s.left;
        }
        s.right = deleteNodeRecursion(node.right, s.val);
        s.left = node.left;
        return s;
    }
}
