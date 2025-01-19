package com.hcx.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Title: BSTTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: 二叉搜索树
 * 1. 树节点增加 key 属性，用来比较谁大谁小，key 不可以重复
 * 2. 对于任意一个树节点，它的 key 比左子树的 key 都大，同时也比右子树的 key 都小
 * @Author: hongcaixia
 * @Date: 2025/1/18 09:55
 * @Version V1.0
 */
public class BSTTree {

    /**
     * 根节点
     */
    BSTTreeNode root;

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public Object get(int key) {
        BSTTreeNode node = root;
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
     * 获取树中最小的值
     * @return
     */
    public Object min() {
        return min(root);
    }

    /**
     * 获取当前节点子树中的最小值
      * @param node
     * @return
     */
    public Object min(BSTTreeNode node) {
        if (node == null) {
            return null;
        }
        BSTTreeNode pointer = node;
        while (pointer.left != null) {
            pointer = pointer.left;
        }
        return pointer.value;
    }

    /**
     * 获取树中最大的值
     * @return
     */
    public Object max() {
        return max(root);
    }

    /**
     * 获取当前节点下孩子节点的最大值
     * @param node
     * @return
     */
    public Object max(BSTTreeNode node) {
        if (node == null) {
            return null;
        }
        BSTTreeNode pointer = node;
        while (pointer.right != null) {
            pointer = pointer.right;
        }
        return pointer.value;
    }

    /**
     * 新增节点，如果已经存在，则更新
     * @param key
     * @param value
     */
    public void put(int key, Object value) {
        BSTTreeNode pointer = root;
        // pointer在查找的过程中，最后一次之前就是还未找到的叶子节点
        BSTTreeNode parent = null;
        while (pointer != null) {
            parent = pointer;
            if (key < pointer.key) {
                pointer = pointer.left;
            } else if (key > pointer.key) {
                pointer = pointer.right;
            } else {
                // key已经存在，执行更新操作
                pointer.value = value;
                return;
            }
        }
        // key不存在，执行新增操作
        BSTTreeNode newNode = new BSTTreeNode(key, value);
        if (parent == null) {
            //树为空
            root = newNode;
            return;
        }
        // 找到父节点，跟父节点对比，当前节点比父节点小，设置为左孩子，大设置为右孩子
        if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    /**
     * 获取节点的前驱节点值：一个节点的前驱（前任）节点是指比它小的节点中，最大的那个
     * 遍历找到当前节点：
     * - 如果当前节点存在左子树，那么左子树中的最大值就是他的前驱节点
     * - 如果当前节点没有左子树，那么他的祖先节点中自左而来的离他最近的节点就是他的前驱节点
     * @param key
     * @return
     */
    public Object predecessor(int key) {
        // 遍历找到当前节点
        BSTTreeNode pointer = root;

        // 记录最新的自左向右而来的(即左侧的)祖先节点 (从右往左走的在右侧，是比当前节点大的，不是前驱)
        BSTTreeNode lastLeftAncestorNode = null;

        while (pointer != null) {
            if (key < pointer.key) {
                pointer = pointer.left;
            } else if (key > pointer.key) {
                // 这个分支是从左向右走的
                lastLeftAncestorNode = pointer;
                pointer = pointer.right;
            } else {
                // 找到了当前节点
                break;
            }
        }
        // 没找到当前节点
        if (pointer == null) {
            return null;
        }

        // 找到了当前节点,当前节点存在左子树,左子树中最大值就是前驱
        if (pointer.left != null) {
            return max(pointer.left);
        }
        // 当前节点没有左子树，找祖先中离的最近的自左向右而来的节点
        return lastLeftAncestorNode == null ? null : lastLeftAncestorNode.value;
    }

    /**
     * 获取节点的后继节点值：一个节点的后继（后任）节点是指比它大的节点中，最小的那个
     * 遍历找到当前节点：
     * - 当前节点如果有右子树，那么右子树中最小的值就是后继节点
     * - 当前节点没有右子树，那么祖先节点中离他最近的自右向左而来的节点就是后继节点
     * @param key
     * @return
     */
    public Object successor(int key) {
        BSTTreeNode lastRightAncestorNode = null;
        BSTTreeNode pointer = root;
        while (pointer != null) {
            if (key < pointer.key) {
                lastRightAncestorNode = pointer;
                pointer = pointer.left;
            } else if (key > pointer.key) {
                pointer = pointer.right;
            } else {
                break;
            }
        }
        // 没找到当前节点
        if (pointer == null) {
            return null;
        }
        // 找到了当前节点，当前节点有右子树，右子树中的最小值就是后继节点
        if (pointer.right != null) {
            return min(pointer.right);
        }
        // 当前节点 没有右子树，在祖先节点中找到最近的自右向左而来的节点
        return lastRightAncestorNode == null ? null : lastRightAncestorNode.value;
    }

    /**
     * 根据关键字删除节点
     * 1.被删除的节点只有右孩子，将右孩子托付给被删除节点的父亲
     * 2.被删除的节点只有左孩子，将左孩子托付给被删除节点的父亲
     * 3.被删除的节点是叶子节点，符合情况1和2，将null托付给被删除节点的父亲
     * 4.被删除的节点左右孩子都有：
     *   将被删除节点的后继节点托付给被删除节点的父亲：
     *                                    1
     *                                 /    \
     *            7                  2       4
     *          /   \                         \
     *        4      8                         8
     *      /   \                            /   \
     *     2     5                         7      9
     *    / \     \                      /
     *   1   3     6                    5
     *                                   \
     *                                    6
     *   - 被删除节点与后继节点相邻，将后继节点托付给被删除节点的父亲（删除4，将后继5托付给7）
     *   - 被删除节点与后继节点不相邻，先处理后继节点的后代，将后继节点的后代托付给后继节点的父亲，再将后继节点托付给被删除节点的父节点
     *    （删除4，将后继5的后代6托付给7，再将后继5托付给4的父亲1）
     * @param key
     * @return
     */
    public Object delete(int key) {
        // 找到被删除的节点
        BSTTreeNode pointer = root;
        BSTTreeNode deletedParent = null;
        while (pointer != null) {
            if (key < pointer.key) {
                deletedParent = pointer;
                pointer = pointer.left;
            } else if (key > pointer.key) {
                deletedParent = pointer;
                pointer = pointer.right;
            } else {
                break;
            }
        }
        // 被删除节点不存在
        if (pointer == null) {
            return null;
        }
        // 被删除节点只有左孩子
        if (pointer.right == null) {
            // 将这个唯一的孩子托付给父亲
            shift(deletedParent, pointer, pointer.left);
        }
        // 被删除的节点只有右孩子
        else if (pointer.left == null) {
            shift(deletedParent, pointer, pointer.right);
            // deletedParent.right = pointer.right;
        }
        // 被删除的节点左右孩子都有
        else {
            // 找到被删除节点的后继节点（这里后继不可能在祖先里，因为有右子树）：在被删除节点的右子树中找到最小的(一直往左找到null为止)
            // 判断后继节点与被删除节点是否相邻
            BSTTreeNode deletedSuccessor = pointer.right;

            // 保存后继节点的父亲
            BSTTreeNode deletedSuccessorParent = pointer;

            // 找被删除节点的后继
            while (deletedSuccessor.left != null) {
                deletedSuccessorParent = deletedSuccessor;
                deletedSuccessor = deletedSuccessor.left;
            }
            //后继节点与被删除节点相邻  deletedSuccessorParent == pointer
            if (deletedSuccessor == pointer.right) {
                // 让后继替代被删除节点：把后继节点托付给被删除节点的父节点（只改变了父节点的指针）
                shift(deletedParent, pointer, deletedSuccessor);
                // 上位的节点的左指针需要改变:上位节点的左指针指向原本被删除节点的左孩子(右指针不需要改，他本身就带着右孩子上来的)
                deletedSuccessor.left = pointer.left;
            }
            // 后继节点与被删除节点不相邻，处理后继节点的后事：把后继节点的孩子托付给后继节点的父亲
            else {
                // 处理后事：把后继节点的孩子托付给后继节点的父亲 （这里的后继节点不会有左孩子，因为他本身就是最左边的孩子了）
                shift(deletedSuccessorParent, deletedSuccessor, deletedSuccessor.right);
                // 上位节点的左右指针都需要改变：上位节点的右指针指向自己的后代，后代托付给了他的父亲了；他上位之后要把左右指针都指向原本被删除节点的左右指针指向的地方
                deletedSuccessor.right = pointer.right;
                deletedSuccessor.left = pointer.left;
            }
        }
        return deletedParent.value;
    }

    /**
     * 找出比key小的所有值
     * @param key
     * @return
     */
    public List<Object> less(int key) {
        ArrayList<Object> list = new ArrayList<>();
        // 存储走过的路
        LinkedList<BSTTreeNode> stack = new LinkedList<>();
        // 中序遍历就是由小到大 左 根 右
        BSTTreeNode pointer = root;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
            } else {
                BSTTreeNode pop = stack.pop();
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
        LinkedList<BSTTreeNode> stack = new LinkedList<>();
        BSTTreeNode pointer = root;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.right;
            } else {
                BSTTreeNode pop = stack.pop();
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
        LinkedList<BSTTreeNode> stack = new LinkedList<>();
        BSTTreeNode pointer = root;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                stack.push(pointer);
                pointer = pointer.left;
            } else {
                BSTTreeNode pop = stack.pop();
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

    /**
     * 托付方法:把孩子托付给父亲(只改变了父亲的指向)
     * @param parent  被删除节点的父亲
     * @param deleted 被删除节点
     * @param child   被删除节点的孩子
     */
    public void shift(BSTTreeNode parent, BSTTreeNode deleted, BSTTreeNode child) {
        // 没有父亲，孩子直接成为根节点
        if (parent == null) {
            root = child;
        }
        // 如果本身被删除节点是左孩子，就让自己的孩子称为父亲的左孩子
        else if (parent.left == deleted) {
            parent.left = child;
        }
        // 如果本身被删除节点是右孩子，就让自己的孩子称为父亲的右孩子
        else {
            parent.right = child;
        }
    }


    public Object deleteRecursive(int key) {
        // 保存被删除节点的值
        ArrayList<Object> deletedList = new ArrayList<>();
        root = doDeleteRecursive(root, key, deletedList);
        return deletedList.isEmpty() ? null : deletedList.get(0);
    }


    /**
     * 递归删除节点
     * @param node 查找删除节点的起点
     * @param key 被删除的key
     * @return 被删除节点后续节点
     */
    public BSTTreeNode doDeleteRecursive(BSTTreeNode node, int key,ArrayList<Object> deletedList) {
        // 没找到待删除节点
        if (node == null) {
            return null;
        }
        // 在左子树找key
        if (key < node.key) {
            // 父亲指向被删除节点后续的节点
            node.left = doDeleteRecursive(node.left, key,deletedList);
            return node;
        }
        // 在右子树找key
        else if (key > node.key) {
            node.right = doDeleteRecursive(node.right, key,deletedList);
            return node;
        }
        deletedList.add(node.value);
        //找到了被删除的节点 开始删除
        // 1.只有左孩子
        if (node.left != null) {
            // 返回左孩子
            return node.left;
        }
        // 2.只有右孩子
        if (node.right != null) {
            // 返回右孩子
            return node.right;
        }
        // 3.左右孩子都有：找到后继节点 处理后事
        // 3.1 找后继节点：在右子树中找到最小值

        // 如果后继节点与被删除节点相邻：将后继节点返回就是删剩下的

        // 被删除节点与后继节点不相邻，此时以右子树作为查找被删除元素的起点，后继节点作为删除的节点，此时就又符合了被删除节点和后继节点相邻的情况了

        BSTTreeNode deletedSuccessor = node.right;
        while (deletedSuccessor.left != null) {
            deletedSuccessor = deletedSuccessor.left;
        }

        // 处理不相邻的情况下设置后继节点的右指针 在后继节点上位之前，处理后继节点的后代
        deletedSuccessor.right = doDeleteRecursive(node.right, deletedSuccessor.key, new ArrayList<>());

        // 后继节点上位，左孩子指针需要设置，右孩子一起上来的不需要改
        deletedSuccessor.left = node.left;

        return deletedSuccessor;


        // -----------------被删除的节点与后继节点相邻的处理逻辑 start---------------------
        /*
        BSTTreeNode deletedSuccessor = node.right;
        while (deletedSuccessor.left != null) {
            deletedSuccessor = deletedSuccessor.left;
        }
        // 后继节点上位，左孩子指针需要设置，右孩子一起上来的不需要改
        deletedSuccessor.left = node.left;
        return deletedSuccessor;
         */
        // -----------------被删除的节点与后继节点相邻的处理逻辑 end--------------------


    }

    public void putRecursion(int key, Object value) {
        root = doPutRecursion(root,key,value);
    }

    // 有很多不必要的赋值操作
    public BSTTreeNode doPutRecursion(BSTTreeNode node, int key, Object value) {
        if (node == null) {
            // 树为空
            return new BSTTreeNode(key, value);
        }
        if (key < node.key) {
            // 不存在，比key小，设置为左孩子
            node.left = doPutRecursion(node.left, key, value);
        } else if (key > node.key) {
            // 不存在，比key大，设置为右孩子
            node.right = doPutRecursion(node.right, key, value);
        } else {
            // 存在
            node.value = value;
        }
        return node;
    }

    public Object maxRecursion() {
        return doMaxRecursion(root);
    }

    public Object doMaxRecursion(BSTTreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.value;
        }
        return doMaxRecursion(node.right);
    }


    public Object minRecursion() {
        return doMinRecursion(root);
    }

    public Object doMinRecursion(BSTTreeNode node) {
        if (root == null) {
            return null;
        }
        if (node.left == null) {
            return node.value;
        }
        return doMinRecursion(node.left);
    }


    public Object getRecursion(int key) {
        return doGetRecursion(root, key);
    }

    public Object doGetRecursion(BSTTreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key > root.key) {
            // 右子树找
            return doGetRecursion(root.right, key);
        } else if (key < root.key) {
            // 左子树找
            return doGetRecursion(root.left, key);
        } else {
            // 相等直接返回
            return root.value;
        }
    }


    static class BSTTreeNode {
        // 作为排序的属性
        int key;
        Object value;
        BSTTreeNode left;
        BSTTreeNode right;

        public BSTTreeNode(int key) {
            this.key = key;
        }

        public BSTTreeNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTTreeNode(int key, Object value, BSTTreeNode left, BSTTreeNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
