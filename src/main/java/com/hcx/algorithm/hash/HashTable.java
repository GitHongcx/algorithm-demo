package com.hcx.algorithm.hash;

/**
 * @Title: HashTable.java
 * @Package com.hcx.algorithm.hash
 * @Description: 哈希表
 * @Author: hongcaixia
 * @Date: 2025/1/25 15:02
 * @Version V1.0
 */
public class HashTable {

    // 存储数据的数组
    Entry[] table = new Entry[16];

    // 哈希表元素个数
    int size = 0;

    // 负载因子
    float loadFactor = 0.75f;

    // 数组阈值 达到这个数量之后 扩容
    int threshold = (int) (loadFactor * table.length);

    /**
     * 根据哈希码获取value
     *
     * @param hash 哈希码定为数组的索引
     * @param key
     * @return
     */
    Object get(int hash, Object key) {
        //根据哈希码计算出元素的位置
        /**
         * 求模运算替换为位运算
         * hash % 数组长度 = hash & (数组长度-1)
         */
        int index = hash & (table.length - 1);
        // 如果该索引处没有值，元素不存在
        if (table[index] == null) {
            return null;
        }
        // 在对应的位置继续查找（有多个元素【链表】）
        Entry pointer = table[index];
        while (pointer != null) {
            if (pointer.key.equals(key)) {
                return pointer.value;
            }
            pointer = pointer.next;
        }
        return null;
    }


    /**
     * 根据key获取元素
     * @param key
     * @return
     */
    Object get(Object key) {
        int hashCode = key.hashCode();
        return get(hashCode, key);
    }

    /**
     * 根据哈希码新增元素
     *
     * @param hash  哈希码（找数组插入位置的下标）
     * @param key   新增key
     * @param value 新增value
     */
    void put(int hash, Object key, Object value) {
        // 计算要插入的数组索引
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            // 对应位置没有元素，直接插入
            table[index] = new Entry(hash, key, value);
        } else {
            // 对应位置不为空，查找是否存在相同的key，如果存在，则执行更新操作;否则遍历到链表尾部插入元素
            Entry pointer = table[index];
            while (true) {
                if (pointer.key.equals(key)) {
                    // 存在相同的key，更新该key的value
                    pointer.value = value;
                    return;
                }
                if (pointer.next == null) {
                    // 遍历到了链表尾部
                    break;
                }
                pointer = pointer.next;
            }
            // 此时指针指向的是链表尾部的位置
            pointer.next = new Entry(hash, key, value);
        }
        // 数组size+1
        size++;
        // 数组大小到达阈值，扩容
        if (size > threshold) {
            // 扩容
            resize();
        }
    }

    /**
     * 新增
     * @param key
     * @param value
     */
    void put(Object key, Object value) {
        int hashCode = key.hashCode();
        put(hashCode, key, value);
    }



    Object remove(int hash, Object key) {
        int index = hash & (table.length - 1);
        // 没找到要删除的元素
        if (table[index] == null) {
            return null;
        }
        Entry pointer = table[index];
        // 从第一个元素开始，pre指针指向null
        Entry pre = null;
        while (pointer != null) {
            if (pointer.key.equals(key)) {
                // 找到了要删除的元素
                if (pre == null) {
                    // 第一个就是要删除的元素
                    table[index] = pointer.next;
                } else {
                    pre.next = pointer.next;
                }
                size--;
                return pointer.value;
            }
            pre = pointer;
            pointer = pointer.next;

        }
        return null;
    }

    /**
     * 移除key
     * @param key
     * @return
     */
    Object remove(Object key) {
        int hashCode = key.hashCode();
        return remove(hashCode, key);
    }

    /**
     * 扩容
     */
    private void resize() {
        // 创建新数组，长度为原来的两倍
        Entry[] newTable = new Entry[table.length << 1];
        // 遍历旧数组 ，依次把数据复制到新数组中
        for (int i = 0; i < table.length; i++) {
            // 每个链表头
            Entry pointer = table[i];
            // 不为空，把元素拷贝到新数组
            if (pointer != null) {
                /**
                 * 旧数组中的链表拆分，移动到新数组
                 * 一个链表最多拆成两个：
                 * hash & table.length == 0 在原来的索引处
                 * hash & table.length != 0 在新的索引处：本身索引位置+数组长度
                 */
                // 遍历第一个链表的指针
                Entry firstListPointer = null;
                // 遍历第二个链表的指针
                Entry secondListPointer = null;

                // 第一个链表的链表头
                Entry firstHead = null;
                // 第二个链表的链表头
                Entry secondHead = null;

                // 把当前元素的链表拆分成两个
                while (pointer != null) {
                    // 把数组中的链表拆分成两个 第一个链表
                    if ((pointer.hashCode & table.length) == 0) {
                        if (firstListPointer != null) {
                            // 把链表链接起来
                            firstListPointer.next = pointer;
                        } else {
                            // 刚进来，给链表头赋值。
                            firstHead = pointer;
                        }
                        // 更新遍历第一个链表的指针 指针后移
                        firstListPointer = pointer;
                    } else {
                        // 第二个链表
                        if (secondListPointer != null) {
                            secondListPointer.next = pointer;
                        } else {
                            secondHead = pointer;
                        }
                        // 更新遍历第二个链表的指针 指针后移
                        secondListPointer = pointer;
                    }
                    // 指针往后移
                    pointer = pointer.next;
                }

                // 两个链表分配完之后，拷贝到新数组中 第一个链表位置不变，第二个链表索引为 当前索引+数组长度
                if (firstListPointer != null) {
                    firstListPointer.next = null;
                    newTable[i] = firstHead;
                }
                if (secondListPointer != null) {
                    secondListPointer.next = null;
                    newTable[i + table.length] = secondHead;
                }
            }
        }
        table = newTable;
        threshold = (int) (loadFactor * table.length);
    }

    /**
     * 获取哈希码优化
     * @param key
     * @return
     */
    int getHashCode(Object key) {
        int hashCode = key.hashCode();
        // 和高16位异或运算 让高位也参与运算 前提：数组长度为2的幂
        return hashCode ^ (hashCode >>> 16);
    }

    /**
     * 节点类
     */
    static class Entry {
        // 哈希码
        int hashCode;
        // 键
        Object key;
        // 值
        Object value;
        // 下一个节点
        Entry next;

        public Entry(int hashCode, Object key, Object value) {
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
        }
    }
}
