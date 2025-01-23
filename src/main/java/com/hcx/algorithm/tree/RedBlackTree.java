package com.hcx.algorithm.tree;

/**
 * @Title: RedBlackTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: 实现红黑树
 * @Author: hongcaixia
 * @Date: 2025/1/21 17:17
 * @Version V1.0
 */
public class RedBlackTree {

    /**
     * 根节点
     */
    private Node root;

    /**
     * 颜色枚举
     */
    enum Color {
        RED, BLACK;
    }

    /**
     * 判断节点是否是红色
     *
     * @param node
     * @return
     */
    boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    /**
     * 判断节点是否是黑色
     * @param node
     * @return
     */
    boolean isBlack(Node node) {
        return !isRed(node);
        //return node == null || node.color == Color.BLACK;
    }


    /**
     * 右旋：
     * 1.旋转本身的逻辑：失衡节点左孩子上位 ，处理失衡节点的后事
     * 2.处理 待处理后事节点，上位节点，失衡节点 的父亲
     * 3.处理旋转节点父亲的孩子
     * @param node 失衡的节点
     */
    private void rightRotate(Node node) {
        // 失衡的节点 node
        // 失衡节点的左孩子：要上位的节点
        Node upNode = node.left;
        // 待处理的上位节点的后代：上位节点的右孩子
        Node toChangeParent = upNode.right;

        // 1.正常的右旋处理：
        // 1.1上位
        upNode.right = node;
        // 1.2处理后事
        node.left = toChangeParent;

        // 2.更新相关移动节点的父亲
        // 2.1 更新上位节点后代的父亲 为 失衡的节点
        if (toChangeParent != null) {
            toChangeParent.parent = node;
        }
        // 2.2 更新上位节点的父亲：为 失衡节点的父亲
        upNode.parent = node.parent;
        // 2.3 更新失衡节点的父亲
        node.parent = upNode;

        // 3.处理上位节点父亲的孩子（因为是双向的，步骤二只处理了节点的父亲，针对父亲还要更新父亲的孩子）
        // 判断失衡节点原本是属于左还是还是右孩子
        if (node.parent == null) {
            // 只需要把上位节点更新为根节点
            root = upNode;
        }
        // 失衡节点原本是属于左孩子
        else if (node.parent.left == node) {
            node.parent.left = upNode;
        }
        // 失衡节点原本是属于右孩子
        else {
            node.parent.right = upNode;
        }
    }

    /**
     * 左旋：
     * 1.旋转本身的逻辑：失衡节点右孩子上位 ，处理失衡节点的后事
     * 2.处理 待处理后事节点，上位节点，失衡节点 的父亲
     * 3.处理失衡节点父亲的孩子
     * @param node 失衡的节点
     */
    private void leftRotate(Node node) {
        // 失衡节点node
        // 上位节点:失衡节点的右孩子
        Node upNode = node.right;
        // 待处理后事的节点：上位节点的左孩子
        Node toChangeParent = upNode.left;

        // 1.正常的左旋处理
        // 1.1 节点上位
        upNode.left = node;
        // 1.2 处理后事: 后代toChangeParent父亲更换为原本的失衡节点
        node.right = toChangeParent;

        // 2. 更新相关移动节点的父亲
        // 2.1 上位节点后代toChangeParent的父亲 更新为：失衡的节点
        if (toChangeParent != null) {
            toChangeParent.parent = node;
        }
        // 2.2 更新上位节点的父亲 为 失衡节点的父亲
        upNode.parent = node.parent;
        // 2.3 更新失衡节点的父亲 为 上位节点
        node.parent = upNode;

        // 3.处理失衡节点的父亲的孩子
        // 如果失衡节点原本是根节点
        if (node.parent == null) {
            root = upNode;
        }
        // 失衡节点原本是属于左孩子
        else if (node.parent.left == node) {
            node.parent.left = upNode;
        } else {
            node.parent.right = upNode;
        }
    }

    /**
     * 插入/更新节点
     * @param key
     * @param value
     */
    public void put(int key,Object value) {
        // 找到插入的位置
        Node pointer = root;
        // 插入位置的父亲
        Node parent = null;
        while (pointer != null) {
            parent = pointer;
            if (key < pointer.key) {
                // 往左找
                pointer = pointer.left;
            } else if (key > pointer.key) {
                // 往右找
                pointer = pointer.right;
            } else {
                // 找到了 直接更新值
                pointer.value = value;
                return;
            }
        }
        // 没有找到，当前指针pointer指向的是要插入的位置
        Node added = new Node(key, value);
        // 树为空，新增节点作为根节点
        if (parent == null) {
            root = added;
        } else if (key < parent.key) {
            // 新增节点作为左孩子
            parent.left = added;
            // 设置新增节点的父亲
            added.parent = parent;
        } else {
            // 新增节点作为右孩子
            parent.right = added;
            added.parent = parent;
        }

        // 插入结束后，不平衡的情况下需要对树进行旋转和变色
        fixRedRed(added);
    }

    /**
     * 当出现两个相邻红色节点的时候对树的调整
     * 1.插入的是根节点直接变黑
     * 2.插入节点的父亲就是黑色，无需做任何操作
     * 3.插入节点的父亲是红色，本身也是红色，触发红红相连
     *  - 叔叔是红色：父亲和叔叔一起变红，爷爷变黑；递归直到根节点
     *  - 叔叔是黑色：此种情况，因为变色时叔叔跟父亲颜色不同，所以变色做不到平衡，需要旋转
     *      - 父亲是左孩子，插入节点也是左孩子 LL
     *      - 父亲是左孩子，插入节点是右孩子  LR
     *      - 父亲是右孩子，插入节点是右孩子  RR
     *      - 父亲是右孩子，插入节点是左孩子  RL
     */
    public void fixRedRed(Node node) {
        // 1.插入的是根节点直接变黑
        if (node == root) {
            node.color = Color.BLACK;
            return;
        }
        // 2.插入节点的父亲就是黑色，无需做任何操作
        else if (isBlack(node.parent)) {
            return;
        }
        //3. 父亲和叔叔都是红色
        // 代码执行到这里 父亲一定是红色
        // 父亲
        Node parent = node.parent;
        // 叔叔
        Node uncle = node.getUncle();
        // 爷爷
        Node grandpa = parent.parent;
        // 叔叔是红色（此时父亲是红色）：
        if (isRed(uncle)) {
            // 父亲和叔叔变黑 （路径多了黑，所以爷爷要变红）
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            // 爷爷变红 （又可能触发红红相连）
            grandpa.color = Color.RED;
            // 递归执行
            fixRedRed(grandpa);
        }
        // 叔叔是黑色（此时父亲是红色）叔叔和父亲颜色不同，通过变色做不到平衡，需要旋转
        // 父亲是左孩子，插入节点也是左孩子 LL不平衡
        if (parent.isLeftChild() && node.isLeftChild()) {
            // 1.变色：父亲变黑，爷爷变红
            parent.color = Color.BLACK;
            grandpa.color = Color.RED;
            // 2.右旋：爷爷右旋
            rightRotate(grandpa);
        }
        // 父亲是左孩子，插入节点是右孩子 LR不平衡
        else if (parent.isLeftChild() && !node.isLeftChild()) {
            // 1.父亲左旋 变成了LL
            leftRotate(parent);
            // LL:变色+旋转
            // 变色：父亲变黑（在父亲左旋之后，此时父亲是插入的节点），爷爷变红
            node.color = Color.BLACK;
            grandpa.color = Color.RED;
            rightRotate(grandpa);
        }
        // 父亲是右孩子，插入节点也是右孩子 RR不平衡
        else if (!parent.isLeftChild() && !node.isLeftChild()) {
            // 1.变色：父亲变黑，爷爷变红
            parent.color = Color.BLACK;
            grandpa.color = Color.RED;
            // 2.爷爷左旋
            leftRotate(grandpa);
        }
        // 父亲是右孩子，插入节点是左孩子，RL不平衡
        else {
            // 父亲右旋，变成RR场景 此时父亲变成了node
            rightRotate(parent);
            // 1.变色：父亲变黑，爷爷变红
            node.color = Color.BLACK;
            grandpa.color = Color.RED;
            // 2.旋转：爷爷左旋
            leftRotate(grandpa);
        }
    }

    /**
     * 根据key查询节点
     * @param key
     * @return
     */
    public Node find(int key) {
        Node pointer = root;
        while (pointer != null) {
            if (key < pointer.key) {
                pointer = pointer.left;
            } else if (key > pointer.key) {
                pointer = pointer.right;
            } else {
                return pointer;
            }
        }
        return null;
    }

    /**
     * 获取删除节点删剩下的
     * @param deleted
     * @return
     */
    public Node findReplace(Node deleted) {
        if (deleted.left == null && deleted.right == null) {
            return null;
        } else if (deleted.left == null) {
            return deleted.right;
        } else if (deleted.right == null) {
            return deleted.left;
        } else {
            // 左右孩子都有 返回被删除节点的后继节点
            // 查找后继节点 右子树中的最小值
            Node deletedSuccessor = deleted.right;
            while (deletedSuccessor.left != null) {
                deletedSuccessor = deletedSuccessor.left;
            }
            return deletedSuccessor;
        }
    }


    /**
     * 删除节点
     * @param key
     */
    public void remove(int key) {
        // 根据key找到删除节点
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    /**
     * 删除节点
     * @param deleted
     */
    public void doRemove(Node deleted) {
        // 删除节点的后继节点
        Node replace = findReplace(deleted);
        //分三种大情况：没有孩子 一个孩子 两个孩子

        // 被删除节点的父亲
        Node deletedParent = deleted.parent;

        // 一、没有孩子
        if (replace == null) {
            // 1.1 删除的节点是根节点
            if (deleted == root) {
                root = null;
            }
            // 1.2 删除的节点不是根节点
            else {
                // 变色（注意这里要先变色 再删除）
                if (isBlack(deleted)) { // 被删除的是黑色 因为没有孩子（孩子为黑色），所以也属于下面的删除的是黑，剩下的孩子也是黑
                    // 旋转 + 变色
                    fixBlackBlack(deleted);
                } else { // 被删除的是红色 不需要做处理
                    // do nothing
                }

                // 删除操作
                // 删除节点属于左孩子
                if (deleted.isLeftChild()) {
                    deletedParent.left = null;
                } else { // 删除节点属于右孩子
                    deletedParent.right = null;
                }
                deleted.parent = null;
            }
            return;
        }

        // 二、有一个孩子
        if (deleted.left == null || deleted.right == null) {
            // 2.1 删除的节点是根节点 这个唯一的孩子一定是红色：根节点和孩子互换，删除孩子
            if (deleted == root) {
                // delete和replace交换
                deleted.key = replace.key;
                deleted.value = replace.value;
                deleted.left = null;
                deleted.right = null;
            }
            // 2.2 不是根节点
            else {
                // 删除操作
                // 看被删除的节点是左孩子还是右孩子
                if (deleted.isLeftChild()) {
                    deletedParent.left = replace;
                } else {
                    deletedParent.right = replace;
                }
                replace.parent = deletedParent;
                // 被删除的节点孩子还父亲置空
                deleted.parent = null;
                deleted.left = null;
                deleted.right = null;

                // 变色操作
                // 删除的是黑色 ，删剩下的也是黑色
                if (isBlack(deleted) && isBlack(replace)) {
                    // 旋转 + 变色
                    fixBlackBlack(replace);
                } else { // 删除的是黑色，剩下的是红色：
                    // 少了一个黑色，把剩下的红色变黑即可
                    replace.color = Color.BLACK;
                }
            }
            return;
        }

        // 三、有两个孩子
        /**
         * 这种情况转为只有一个孩子的情况：
         * 将要删除的节点和后继节点的键值交换，那么要删除的节点就变成了后继节点，此时一直递归调用，直到只有一个孩子的情况，进入情况二的分支
         */
        // 交换key
        int tempKey = deleted.key;
        deleted.key = replace.key;
        replace.key = tempKey;

        // 交换value
        Object tempVal = deleted.value;
        deleted.value = replace.value;
        replace.value = tempVal;

        // 要删的就变成了replace 递归直到进入条件二
        doRemove(replace);
    }


    /**
     * 被删除的节点是黑色，删剩下的也是黑色（整体这个路径上少了一个黑色，失衡）
     * - 情况一.被删节点的兄弟是红色，此时侄子一定是黑色：左旋+变色 转为下面两种情况
     * - 情况二.被删节点的兄弟是黑色，侄子（这俩的孩子）都是黑：
     * - 情况三.被删节点的兄弟是黑色，侄子（这俩的孩子）中至少一个红：
     * @param node
     */
    private void fixBlackBlack(Node node) {
        if (node == root) {
            //此时处理结束
            return;
        }
        // 节点的兄弟
        Node brother = node.getBrother();
        // 节点的父亲
        Node parent = node.parent;
        // 情况一.被删节点的兄弟是红色，此时侄子一定是黑色：左旋+变色
        if (isRed(brother)) {
            if (node.isLeftChild()) {
                // node属于左节点  父亲左旋
                // 1.1 父亲左旋
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            // 1.2 调整平衡
            brother.color = Color.BLACK;
            parent.color = Color.RED;

            // 此时把兄弟变成了黑色了 就符合了情况二和情况三，再次调用方法进行二和三情况的处理
            fixBlackBlack(node);
            return;
        }
        // 情况二：删除节点的兄弟为黑色，侄子都是黑：
        assert brother != null;
        if (isBlack(brother.left) && isBlack(brother.right)) {
            // 2.1兄弟变红
            brother.color = Color.RED;
            // 如果此时父亲是红，则让父亲变黑就维持了平衡了
            if (isRed(parent)) {
                parent.color = Color.BLACK;
            }
            // 如果父亲是黑，触发黑黑调整：让兄弟变红;依次递归，让每一条路径都有一个兄弟变红，就都少了一个黑色，实现黑色平衡
            else {
                fixBlackBlack(parent);
            }
        } else {
            // 情况三：删除的节点兄弟是黑色，侄子至少一个红色（一个红色或者两个都是红色）
            /**
             * 	兄弟是左孩子，左侄子是红色：LL ：父亲右旋+变色
             * 	兄弟是左孩子，右侄子是红色：LR
             * 	兄弟是右孩子，右侄子是红色：RR
             * 	兄弟是右孩子，左侄子是红色：RL
             */
            // LL: 兄弟是左孩子，左侄子是红色
            if (brother.isLeftChild() && isRed(brother.left)) {
                // 父亲右旋
                rightRotate(parent);
                /**
                 * 变色逻辑：
                 * 1.通过旋转过来的节点变成黑色补齐该路径上少的黑色
                 * 2.针对上位节点，变成原本父亲的颜色，因为这条路径上本身是平衡的，所以上来的要变成原本父亲的颜色
                 * 3.针对红色的侄子，变成黑色，因为原本该路径上，被删除节点的兄弟被当成了父亲，所以原本作为这个路径的黑色兄弟就走了，少了一个黑色，所以让红色侄子变黑。
                 */
                // 代码变色从后往前，因为从前往后会导致还变色的颜色丢失了
                // 侄子变黑
                brother.left.color = Color.BLACK;
                //上位节点（兄弟节点）变成原来父亲的颜色
                brother.color = parent.color;
                // 原本父亲节点旋转下去了 变成黑色
                parent.color = Color.BLACK;
            }
            // LR：兄弟是左孩子，右侄子是红色
            else if (brother.isLeftChild() && isRed(brother.right)) {
                /**
                 * 这里的代码可以做简化，旋转之前和之后分别做一次变色即可
                 */
                // 侄子上位，变成父亲的颜色（这里要先变色，因为侄子是要旋下去的，就找不到右孩子了）
                brother.right.color = parent.color;
                // 兄弟左旋
                leftRotate(brother);
                // 父亲右旋
                rightRotate(parent);
                // 父亲最后退位，颜色变黑，补齐删除的黑色
                parent.color = Color.BLACK;
            }
            // RR
            else if (!brother.isLeftChild() && !isRed(brother.left)) {
                // 父亲左旋
                leftRotate(parent);
                // 侄子变黑
                brother.right.color = Color.BLACK;
                //上位节点（兄弟节点）变成原来父亲的颜色
                brother.color = parent.color;
                // 原本父亲节点旋转下去了 变成黑色
                parent.color = Color.BLACK;
            }
            // RL
            else {
                // 侄子上位，变成父亲的颜色（这里要先变色，因为侄子是要旋下去的，就找不到右孩子了）
                brother.left.color = parent.color;
                // 兄弟右旋
                rightRotate(brother);
                // 父亲左旋
                leftRotate(parent);
                // 父亲最后退位，颜色变黑，补齐删除的黑色
                parent.color = Color.BLACK;
            }
        }
    }


    private static class Node {
        int key;
        Object value;
        Node left;
        Node right;

        // 父节点
        Node parent;
        // 颜色 默认为红色
        Color color = Color.RED;

        public Node(int key) {
            this.key = key;
        }

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node(int key, Color color, Node left, Node right) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
        }

        /**
         * 判断节点是否是左孩子
         *
         * @return
         */
        boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        /**
         * 获取当前节点的叔叔：爸爸的兄弟（前提必须有爷爷存在）
         *
         * @return
         */
        Node getUncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            // 如果当前节点的父亲是属于左孩子,那么爷爷的右孩子就是叔叔
            if (parent == parent.parent.left) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        /**
         * 获取当前节点的兄弟
         *
         * @return
         */
        Node getBrother() {
            if (parent == null) {
                return null;
            }
            // 当前节点是属于左孩子，那么父亲的右孩子就是兄弟
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }

    }

}
