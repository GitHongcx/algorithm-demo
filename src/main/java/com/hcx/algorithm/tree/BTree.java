package com.hcx.algorithm.tree;

import java.util.Arrays;

/**
 * @Title: BTree.java
 * @Package com.hcx.algorithm.tree
 * @Description: B-树
 * @Author: hongcaixia
 * @Date: 2025/1/23 17:03
 * @Version V1.0
 */
public class BTree {

    // 根节点
    private Node root;

    // 树中节点最小度数
    int t;

    // 最小key数量
    final int MIN_KEY_NUM;

    // 最大key数量
    final int MAX_KEY_NUM;

    public BTree() {
        // 默认度数设置为2
        this(2);
    }

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MIN_KEY_NUM = t - 1;
        MAX_KEY_NUM = 2 * t - 1;
    }

    /**
    * 判断key在树中是否存在
    * @param key
    * @return
    */
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    /**
     * 新增key
     *
     * @param key
     */
    public void put(int key) {
        doPut(root, key, 0, null);
    }

    /**
     * 执行新增key
     * 1.查找插入位置：从根节点开始，沿着树向下查找，直到找到一个叶子节点，这个叶子节点包含的键值范围覆盖了要插入的键值。
     * 2.插入键值：在找到的叶子节点中插入新的键值。如果叶子节点中的键值数量没有超过B树的阶数（即每个节点最多可以包含的键值数量），则插入操作完成。
     * 3.分裂节点：如果叶子节点中的键值数量超过了B树的阶数，那么这个节点需要分裂。
     * @param node 待插入元素的节点
     * @param key 插入的key
     * @param nodeIndex  待插入元素节点的索引
     * @param nodeParent 待插入节点的父节点
     */
    public void doPut(Node node, int key, int nodeIndex, Node nodeParent) {
        // 查找插入位置
        int index = 0;
        while (index < node.keyNum) {
            if (node.keys[index] == key ) {
                // 找到了 做更新操作 （因为没有维护value，所以就不用处理了）
                return;
            }
            if (node.keys[index] > key) {
                // 没找到该key, 退出循环，index的值就是要插入的位置
                break;
            }
            index++;
        }
        // 如果是叶子节点，直接插入
        if (node.leafFlag) {
            node.insertKey(key, index);
        } else {
            // 非叶子节点，继续从孩子中找到插入位置 父亲的这个待插入的index正好就是元素要插入的第x个孩子的位置
            doPut(node.children[index], key , index, node);
        }
        // 处理节点分裂逻辑 : keyNum数量达到上限，节点分裂
        if (node.keyNum == MAX_KEY_NUM) {
            split(node, nodeIndex, nodeParent);
        }
    }

    /**
     * 节点分裂
     * 左侧数据：本身左侧的数据留在该节点
     * 中间数据：中间索引2（度-1）的数据6移动到父节点的索引1（被分裂节点的索引）处
     * 右侧数据：从索引3（度）开始的数据，移动到新节点，新节点的索引值为分裂节点的index+1
     * @param node 要分裂的节点
     * @param index 分裂节点的索引
     * @param parent 要分裂节点的父节点
     *
     */
    public void split(Node node, int index, Node parent) {
        // 没有父节点，当前node为根节点
        if (parent == null) {
            // 创建出新的根来存储中间数据
            Node newRoot = new Node(t);
            newRoot.leafFlag = false;
            newRoot.insertChild(node, 0);
            // 更新根节点为新创建的newRoot
            this.root = newRoot;
            parent = newRoot;
        }

        // 1.处理右侧数据：创建新节点存储右侧数据
        Node newNode = new Node(t);
        // 新创建的节点跟原本分裂节点同级
        newNode.leafFlag = node.leafFlag;
        // 新创建节点的数据从 原本节点【度】位置索引开始拷贝 拷贝长度：t-1
        System.arraycopy(node.keys, t, newNode.keys, 0, t - 1);
        // 如果node不是叶子节点，还需要把node的一部分孩子也同时拷贝到新节点的孩子中
        if (!node.leafFlag) {
            System.arraycopy(node.children, t, newNode.children, 0, t);
        }
        // 更新新节点的keyNum
        newNode.keyNum = t - 1;

        // 更新原本节点的keyNum
        node.keyNum = t - 1;

        // 2.处理中间数据：【度-1】索引处的数据 移动到父节点【分裂节点的索引】索引处
        // 要插入父节点的数据：
        int midKey = node.keys[t - 1];
        parent.insertKey(midKey, index);

        // 3. 新创建的节点作为父亲的孩子
        parent.insertChild(newNode, index + 1);

        // parent的keyNum在对应的方法中已经更新了
    }

    /**
     * 删除指定key
     * @param key
     */
    public void remove(int key) {
        doRemove(root, null, 0, key);
    }

    /**
     * 删除指定key
     * @param node 查找待删除key的起点
     * @param parent 待删除key的父亲
     * @param nodeIndex 待删除的key的索引
     * @param key 待删除的key
     */
    public void doRemove(Node node, Node parent, int nodeIndex, int key) {
        // 找到被删除的key
        int index = 0;
        // 循环查找待删除的key
        while (index < node.keyNum) {
            if (node.keys[index] >= key) {
                //找到了或者没找到
                break;
            }
            index++;
        }
        // 如果找到了 index就是要删除的key索引；
        // 如果没找到，index就是要在children的index索引位置继续找

        // 一、是叶子节点
        if (node.leafFlag) {
            // 1.1 没找到
            if (!found(node, key, index)) {
                return;
            }
            // 1.2 找到了
            else {
                // 删除当前节点index处的key
                node.removeKey(index);
            }
        }
        // 二、不是叶子节点
        else {
            // 1.1 没找到
            if (!found(node, key, index)) {
                // 继续在孩子中找 查找的孩子的索引就是当前index
                doRemove(node.children[index], node, index, key);
            }
            // 1.2 找到了
            else {
                // 找到后继节点，把后继节点复制给当前的key，然后删除后继节点。
                // 在索引+1的孩子里开始,一直往左找，直到节点是叶子节点为止，就找到了后继节点
                Node deletedSuccessor = node.children[index + 1];
                while (!deletedSuccessor.leafFlag) {
                    // 更新为最左侧的孩子
                    deletedSuccessor = deletedSuccessor.children[0];
                }
                // 1.2.1 当找到叶子节点之后，最左侧的key就是后继key
                int deletedSuccessorKey = deletedSuccessor.keys[0];
                // 1.2.2 把后继key赋值给待删除的key
                node.keys[index] = deletedSuccessorKey;
                // 1.2.3 删除后继key 再调用该方法，走到情况一，删除掉该后继key： 起点为索引+1的孩子处，删除掉后继key
                doRemove(node.children[index + 1], node, index + 1, deletedSuccessorKey);
            }
        }

        // 树的平衡：
        if (node.keyNum < MIN_KEY_NUM) {
            balance(node, nodeIndex, parent);
        }
    }

    /**
     * 树的平衡
     * @param node 失衡节点
     * @param index 失衡节点索引
     * @param parent 失衡节点父节点
     */
    public void balance(Node node, int index, Node parent) {
        if (node == root) {
            // 如果是根节点 当调整到根节点只剩下一个key时，要替换根节点 (根节点不能为null，要保证右孩子才替换)
            if (root.keyNum == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        // 拿到该节点的左右兄弟，判断节点是不是富裕的,如果富裕，则找兄弟借
        Node leftBrother = parent.childLeftBrother(index);
        Node rightBrother = parent.childRightBrother(index);

        // 左边的兄弟富裕:右旋
        if (leftBrother != null && leftBrother.keyNum > MIN_KEY_NUM) {
            // 1.要旋转下来的key：父节点中【失衡节点索引-1】的key：parent.keys[index-1]；插入到失衡节点索引0位置
            // （这里父亲节点旋转走的不用删除，因为等会左侧的兄弟旋转上来会覆盖掉）
            node.insertKey(parent.keys[index - 1], 0);

            // 2.0 如果左侧节点不是叶子节点，有孩子，当旋转一个时，只需要留下原本孩子数-1 ，把最大的孩子过继给失衡节点的最小索引处(先处理后事)
            if (!leftBrother.leafFlag) {
                node.insertChild(leftBrother.removeRightMostChild(), 0);
            }

            // 2.1 要旋转上去的key：左侧兄弟最大的索引key，删除掉，插入到父节点中【失衡节点索引-1】位置（此位置就是刚才在父节点旋转走的key的位置）
            // 这里要直接覆盖，不能调插入方法，因为这个是当初旋转下去的key。
            parent.keys[index - 1] = leftBrother.removeRightMostKey();

            return;
        }
        // 右边的兄弟富裕：左旋
        if (rightBrother != null && rightBrother.keyNum > MIN_KEY_NUM) {
            // 1.要旋转下来的key：父节点中【失衡节点索引】的key：parent.keys[index]；插入到失衡节点索引最大位置keyNum位置
            // （这里父亲节点旋转走的不用删除，因为等会右侧的兄弟旋转上来会覆盖掉）
            node.insertKey(parent.keys[index], node.keyNum);

            // 2.0 如果右侧节点不是叶子节点，有孩子，当旋转一个时，只需要留下原本孩子数-1 ，把最小的孩子过继给失衡节点的最大索引处（孩子节点的索引比父亲要多1）
            if (!rightBrother.leafFlag) {
                node.insertChild(rightBrother.removeLeftMostChild(), node.keyNum + 1);
            }

            // 2.1 要旋转上去的key：右侧兄弟最小的索引key，删除掉，插入到父节点中【失衡节点索引-1】位置（此位置就是刚才在父节点旋转走的key的位置）
            // 这里要直接覆盖，不能调插入方法，因为这个是当初旋转下去的key。
            parent.keys[index] = rightBrother.removeLeftMostKey();

            return;
        }
        // 左右兄弟都不够，往左合并
        if (leftBrother != null) {
            // 向左兄弟合并
            // 1.把失衡节点从父亲中移除
            parent.removeChild(index);

            // 2.插入父节点的key到左兄弟 将父节点中【失衡节点索引-1】的key移动到左侧
            leftBrother.insertKey(parent.removeKey(index - 1), leftBrother.keyNum);

            // 3.插入失衡节点的key及其孩子到左兄弟
            node.moveToTarget(leftBrother);
        } else {
            // 右兄弟向自己合并
            // 1.把右兄弟从父亲中移除
            parent.removeChild(index + 1);
            // 2.把父亲的【失衡节点索引】 处的key移动到自己这里
            node.insertKey(parent.removeKey(index), node.keyNum);
            // 3.把右兄弟完整移动到自己这里
            rightBrother.moveToTarget(node);
        }
    }

    /**
     * key在当前node的index索引处找到了
     * @param node
     * @param key
     * @param index
     * @return
     */
    private static boolean found(Node node, int key, int index) {
        return index < node.keyNum && node.keys[index] == key;
    }





    static class Node {
        // 关键字
        int[] keys;
        // 关键字数量
        int keyNum;
        // 孩子节点
        Node[] children;
        // 是否是叶子节点
        boolean leafFlag = true;
        // 最小度数：最少孩子数（决定树的高度，度数越大，高度越小）
        int t;

        // ≥2
        public Node(int t) {
            this.t = t;
            // 最多的孩子数（约定）
            this.children = new Node[2 * t];
            this.keys = new int[2 * t -1];
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNum));
        }

        /**
         * 根据key获取节点
         * @param key
         * @return
         */
        Node get(int key) {
            // 先从当前key数组中找
            int i = 0;
            while (i < keyNum) {
                if (keys[i] == key) {
                    // 在当前的keys关键字数组中找到了
                    return this;
                }
                if (keys[i] > key) {
                    // 当数组比当前key大还未找到时，退出循环
                    break;
                }
                i++;
            }
            // 如果是叶子节点，没有孩子了，说明key不存在
            if (leafFlag) {
                return null;
            } else {
                // 非叶子节点，退出时i的值就是对应范围的孩子节点数组的索引，从对应的这个孩子数组中继续找
                return children[i].get(key);
            }
        }

        /**
         * 向keys数组中指定的索引位置插入key
         * @param key
         * @param index
         */
        void insertKey(int key,int index) {
            /**
             * [0,1,2,3]
             * src:源数组
             * srcPos：起始索引
             * dest：目标数组
             * destPos: 目标索引
             * length：拷贝的长度
             */
            System.arraycopy(keys, index, keys, index + 1, keyNum - index);
            keys[index] = key;
            keyNum++;
        }

        /**
         * 向指定索引插入child
         *
         * @param child
         * @param index
         */
        void insertChild(Node child, int index) {
            System.arraycopy(children, index, children, index + 1, keyNum - index);
            children[index] = child;
        }


        /**
         * 移除指定索引处的key
         * @param index
         * @return
         */
        int removeKey(int index) {
            int deleted = keys[index];
            System.arraycopy(keys, index + 1, keys, index, --keyNum - index);
            return deleted;
        }

        /**
         * 移除最左索引处的key
         * @return
         */
        int removeLeftMostKey(){
            return removeKey(0);
        }

        /**
         * 移除最右边索引处的key
         * @return
         */
        int removeRightMostKey() {
            return removeKey(keyNum - 1);
        }

        /**
         * 移除指定索引处的child
         * @param index
         * @return
         */
        Node removeChild(int index) {
            Node deleted = children[index];
            System.arraycopy(children, index + 1, children, index, keyNum - index);
            children[keyNum] = null;
            return deleted;
        }

        /**
         * 移除最左边的child
         * @return
         */
        Node removeLeftMostChild() {
            return removeChild(0);
        }

        /**
         * 移除最右边的child
         * @return
         */
        Node removeRightMostChild() {
            return removeChild(keyNum);
        }

        /**
         * 获取指定children处左边的兄弟
         * @param index
         * @return
         */
        Node childLeftBrother(int index) {
            return index > 0 ? children[index - 1] : null;
        }

        /**
         * 获取指定children处右边的兄弟
         * @param index
         * @return
         */
        Node childRightBrother(int index) {
            return index == keyNum ? null : children[index + 1];
        }

        /**
         * 复制当前节点到目标节点（key和child）
         * @param target
         */
        void moveToTarget(Node target) {
            int start = target.keyNum;
            // 当前节点不是叶子节点 说明有孩子
            if (!leafFlag) {
                // 复制当前节点的孩子到目标节点的孩子中
                for (int i = 0; i <= keyNum; i++) {
                    target.children[start + i] = children[i];
                }
            }
            // 复制key到目标节点的keys中
            for (int i = 0; i < keyNum; i++) {
                target.keys[target.keyNum++] = keys[i];
            }
        }


    }
}
