package com.hcx.algorithm.linkedlist;

import java.util.Iterator;

/**
 * @Title: SentinelDoubleLinkedList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 带哨兵的双向链表
 * @Author: hongcaixia
 * @Date: 2024/5/10 10:09
 * @Version V1.0
 */
public class SentinelDoubleLinkedList implements Iterable<Integer> {

    static class DoubleNode {
        DoubleNode pre;
        int value;
        DoubleNode next;

        public DoubleNode(DoubleNode pre, int value, DoubleNode next) {
            this.pre = pre;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 头哨兵
     */
    private static DoubleNode head;

    /**
     * 尾哨兵
     */
    private static DoubleNode tail;

    public SentinelDoubleLinkedList() {
        head = new DoubleNode(null, -1, null);
        tail = new DoubleNode(null, -2, null);
        head.next = tail;
        tail.pre = head;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            DoubleNode pointer = head.next;

            @Override
            public boolean hasNext() {
                return pointer != tail;
            }

            @Override
            public Integer next() {
                Integer value = pointer.value;
                pointer = pointer.next;
                return value;
            }
        };
    }


    /**
     * 根据索引查找节点
     *
     * @param index
     * @return
     */
    private static DoubleNode findNodeByIndex(int index) {
        int i = -1;
        for (DoubleNode pointer = head; pointer != tail; i++, pointer = pointer.next) {
            if (i == index) {
                return pointer;
            }
        }
        return null;
    }

    /**
     * 根据索引位置插入值
     *
     * @param index
     * @param value
     */
    private static void addByIndex(int index, int value) {
        //找到要插入的上一个节点
        DoubleNode pre = findNodeByIndex(index - 1);
        if (pre == null) {
            throw new IllegalArgumentException();
        }
        DoubleNode next = pre.next;
        //要插入的新节点
        DoubleNode node = new DoubleNode(pre, value, next);
        pre.next = node;
        next.pre = node;
    }

    /**
     * 从头部添加节点
     *
     * @param value
     */
    private static void addFirst(int value) {
        addByIndex(0, value);
    }

    /**
     * 从尾部添加节点
     *
     * @param value
     */
    private static void addLast(int value) {
        // 最后一个节点
        DoubleNode last = tail.pre;
        DoubleNode node = new DoubleNode(last, value, tail);
        last.next = node;
        tail.pre = node;
    }

    /**
     * 删除第一个节点
     */
    public void removeFirst() {
        removeByIndex(0);
    }

    /**
     * 删除最后一个节点
     */
    public void removeLast() {
        if (tail.pre == head) {
            throw new IllegalArgumentException();
        }
        // 最后一个节点的前一个节点
        DoubleNode node = tail.pre.pre;
        node.next = tail;
        tail.pre = node;
    }

    /**
     * 按索引删除节点
     *
     * @param index
     */
    public void removeByIndex(int index) {
        // 要删除的上一个
        DoubleNode pre = findNodeByIndex(index - 1);
        // 找不到pre或者要删除的是尾哨兵的时候
        if (pre == null || pre.next == tail) {
            throw new IllegalArgumentException();
        }
        // 要删除的下一个
        DoubleNode next = pre.next.next;

        pre.next = next;
        next.pre = pre;
    }
}
