package com.hcx.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Title: AVLTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: AVL树
 * @Author: hongcaixia
 * @Date: 2025/1/21 09:33
 * @Version V1.0
 */
public class AVLTree {

    /**
     * 根节点
     */
    AVLNode root;

    /**
     * LL:
     * L：失衡节点5的 bf > 1，即左边更高
     * L：失衡节点5的左孩子3的 bf >= 0，即左孩子这边也是左边更高或等高
     *         6
     *        / \
     *       3   7
     *      / \
     *     2   4
     *    /
     *   1
     *
     *         3
     *        / \
     *       2   6
     *      /   / \
     *     1   4   7
     *
     */
    /**
     * 右旋
     * @param node 要旋转的节点
     * @return 返回新的根节点
     */
    public AVLNode rightRotate(AVLNode node) {
        // 6为要旋转的节点node

        // 要上位的节点3
        AVLNode upNode = node.left;  //
        // 要上位的节点3的孩子4（待换父亲的节点4）
        AVLNode toChangeParent = upNode.right;

        // ①处理后事：上位节点的右孩子换父亲 换位退位节点（旋转）的左孩子
        node.left = toChangeParent;

        // ②上位：要旋转的节点6退位作为要上位节点3的右孩子
        upNode.right = node;

        // 节点旋转完之后需要更新高度
        updateHeight(node);
        // 上位节点的高度也会改变
        updateHeight(upNode);

        return upNode;
    }

    /**
     * RR：
     *
     *     2
     *    / \
     *   1   4
     *      / \
     *     3   5
     *          \
     *           6
     *
     *       4
     *      / \
     *     2   5
     *    / \    \
     *   1   3    6
     */
    /**
     * 左旋
     * @param node 要旋转的节点
     * @return 新的根节点
     */
    public AVLNode leftRotate(AVLNode node) {
        // 待上位的节点
        AVLNode upNode = node.right;
        // 待换父亲的节点
        AVLNode toChangeParent = upNode.left;

        // ①处理上位节点的后事
        node.right = toChangeParent;

        // ②节点4上位：旋转节点作为4的左孩子
        upNode.left = node;

        // 节点旋转完之后需要更新高度
        updateHeight(node);
        // 上位节点的高度也会改变
        updateHeight(upNode);

        return upNode;
    }

    /**
     *
     *         6
     *        / \
     *       2   7
     *      / \
     *     1   4
     *        / \
     *       3   5
     *
     *  左旋左子树:
     *          6
     *        / \
     *       4   7
     *      / \
     *     2   5
     *    / \
     *   1   3
     *
     *  右旋根节点：
     *         4
     *        / \
     *       2   6
     *      / \ / \
     *     1  3 5  7
     */
    /**
     * 先左旋失衡节点的左子树，再右旋失衡节点
     * 针对LR失衡
     * @param node
     * @return
     */
    public AVLNode leftRightRotate(AVLNode node) {
        // 失衡节点node的左子树左旋
        AVLNode avlNode = leftRotate(node.left);
        // 失衡节点6的左孩子更新
        node.left = avlNode;
        // 对失衡节点进行右旋操作
        return rightRotate(node);
    }


    /**
     *     2
     *    / \
     *   1   6
     *      / \
     *     4   7
     *    / \
     *   3   5
     *
     * 1.右子树向右旋转
     *         2
     *        / \
     *       1   4
     *          / \
     *         3   6
     *            / \
     *           5   7
     *
     *  2.整棵树向左旋转
     *          4
     *        / \
     *       2   6
     *      / \  / \
     *     1   3 5  7
     */
    /**
     * 先右旋失衡节点的右子树，再左旋失衡节点
     * 针对RL失衡
     * @param node
     * @return
     */
    public AVLNode rightLeftRotate(AVLNode node) {
        // 失衡节点2的右子树进行右旋，并更新失衡节点的右子树
        node.right = rightRotate(node.right);
        // 对失衡节点进行左旋
        return leftRotate(node);
    }

    /**
     * 检查节点是否失衡，如果失衡，则调整，返回平衡后的节点；否则直接返回
     * @param node
     * @return
     */
    public AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        // 平衡因子
        int bf = bf(node);
        // 等于发生在删除操作时，也符合LL/RR的失衡情况，直接右/左旋
        if (bf > 1 && bf(node.left) >= 0) { // LL
            //直接右旋
            return rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) { // LR
            // 左旋再右旋
            return leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) > 0) { // RL
            // 右旋再左旋
            return rightLeftRotate(node);
        } else if (bf < -1 && bf(node.right) <= 0) { // RR
            // 直接左旋
            return leftRotate(node);
        }
        return node;
    }

    /**
     * 新增节点
     * @param key
     * @param value
     */
    public void put(int key,Object value) {
        root = doPut(root, key, value);
    }

    /**
     * 递归插入元素
     * @param node 开始查找插入位置的节点
     * @param key  插入节点的key
     * @param value 插入节点的value
     * @return 插入完成之后的根节点
     */
    public AVLNode doPut(AVLNode node,int key, Object value) {
        if (node == null) {
            return new AVLNode(key, value);
        }
        // 如果找到了 就执行更新操作 直接返回（不需要调整高度和平衡树）
        if (key == node.key) {
            node.value = value;
            return node;
        }
        // 没找到就继续找到要插入的位置
        if (key < node.key) {
            // 往左找 找到了空位，返回了创建的新节点，设置给当前节点的左孩子（因为是往左找的）
            node.left = doPut(node.left, key, value);
        } else {
            // 如果key比当前的node的key要大，往右找空位;找到后拿到了创建的新节点，设置给当前的右孩子。
            node.right = doPut(node.right, key, value);
        }
        // 插入了新的元素之后，高度需要更新，更新了高度，可能会失衡，需要调整树的平衡
        updateHeight(node);
        return balance(node);
    }


    public void remove(int key) {

    }

    /**
     * 递归删除节点
     * @param node 查找删除节点的起点
     * @param key  要删除的key
     * @return 被删除节点后续节点
     */
    public AVLNode doRemove(AVLNode node,int key) {
        // 寻找被删除的节点
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            // 往左找 找到了之后，返回的是删剩下的，设置为当前节点的左孩子
            node.left = doRemove(node.left, key);
        } else if (key > node.key) {
            // 往右找
            node.right = doRemove(node.right, key);
        } else {
            // 找到了要删除的节点
            // 要删除的节点没有孩子
            if (node.left == null && node.right == null) {
                // 删除之后 没有要处理的后事 直接返回
                return null;
            } else if (node.left == null) {
                // 删除的节点只有右孩子, 返回右孩子，但是这里涉及到高度及平衡的处理，先暂存到删除节点node中，后续处理高度和平衡
                node = node.right;
            } else if (node.right == null) {
                // 删除的节点只有左孩子，返回左孩子，这里涉及高度及树平衡的处理，暂存到删除节点
                node = node.left;
            } else {
                // 删除的节点左右孩子都有
                // 找到要删除节点的后继节点（右子树中找到最小的），让该后继节点上位
                AVLNode deletedSuccessor = node.right;
                while (deletedSuccessor.left != null) {
                    deletedSuccessor = deletedSuccessor.left;
                }
                //后继节点上位之前，先处理完后继节点的后事（此时该后继节点只可能存在右孩子）
                /**
                 * 处理后事：把要删除节点的右子树作为删除的起点再调用doRemove()，删除的节点是该后继节点，此种情况符合只有右孩子的分支判断；
                 * 删完之后就是把该后继节点的孩子给到了后继节点的父亲，即处理完了后事
                 * 此时返回的是原本要删除节点的右子树，此时后继节点要上位: 即后继节点的右子树就是返回的这个处理完了后事的右子树
                 */
                deletedSuccessor.right = doRemove(node.right, deletedSuccessor.key);
                deletedSuccessor.left = node.left;
                // 复制给node 用来更新高度和平衡树
                node = deletedSuccessor;
            }
        }
        // 更新树的高度
        updateHeight(node);
        // 平衡树
        return balance(node);
    }

    /**
     * 求节点的高度
     * @param node
     * @return
     */
    public int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 更新节点的高度（在新增，删除，旋转节点的时候需要执行）
     * @param node
     */
    public void updateHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 获取平衡因子：左子树的高度 - 右子树的高度
     * @param node
     * @return
     */
    public int bf(AVLNode node) {
        return height(node.left) - height(node.right);
    }


    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public Object get(int key) {
        AVLNode node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /**
     * 找出比key小的所有值
     * @param key
     * @return
     */
    public List<Object> less(int key) {
        ArrayList<Object> list = new ArrayList<>();
        // 存储走过的路
        LinkedList<AVLNode> stack = new LinkedList<>();
        // 中序遍历就是由小到大 左 根 右
        AVLNode pointer = root;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
            } else {
                AVLNode pop = stack.pop();
                if (pop.key < key) {
                    list.add(pop.value);
                } else {
                    break;
                }
                pointer = pop.right;
            }
        }
        return list;
    }

    /**
     * 找出比key大的所有值
     * 这里为了提高效率，采用反向中序遍历：右根左
     * @param key
     * @return
     */
    public List<Object> greater(int key) {
        ArrayList<Object> list = new ArrayList<>();
        LinkedList<AVLNode> stack = new LinkedList<>();
        AVLNode pointer = root;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.right;
            } else {
                AVLNode pop = stack.pop();
                if (pop.key > key) {
                    list.add(pop.value);
                } else {
                    break;
                }
                pointer = pop.left;
            }
        }
        return list;
    }

    /**
     * 范围查询
     * @param leftKey
     * @param rightKey
     * @return
     */
    public List<Object> between(int leftKey, int rightKey) {
        ArrayList<Object> list = new ArrayList<>();
        LinkedList<AVLNode> stack = new LinkedList<>();
        AVLNode pointer = root;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
            } else {
                AVLNode pop = stack.pop();
                if (pop.key >= leftKey && pop.key <= rightKey) {
                    list.add(pop.value);
                } else if (pop.key > rightKey) {
                    break;
                }
                pointer = pop.right;
            }
        }
        return list;
    }

    static class AVLNode {

        int key;

        Object value;

        // 节点高度,刚创建出来默认为1（按照力扣的规定）
        int height = 1;
        AVLNode left;
        AVLNode right;
        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

}
