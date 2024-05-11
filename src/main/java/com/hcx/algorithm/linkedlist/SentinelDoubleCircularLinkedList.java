package com.hcx.algorithm.linkedlist;

import java.util.Iterator;

/**
 * @Title: SentinelDoubleCircularLinkedList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 带哨兵的双向环形链表
 * @Author: hongcaixia
 * @Date: 2024/5/11 11:04
 * @Version V1.0
 */
public class SentinelDoubleCircularLinkedList implements Iterable<Integer> {

    // 哨兵节点
    private static DoubleCircularNode sentinel = new DoubleCircularNode(null, -1, null);

    public SentinelDoubleCircularLinkedList() {
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            DoubleCircularNode pointer = sentinel.next;

            @Override
            public boolean hasNext() {
                return pointer != sentinel;
            }

            @Override
            public Integer next() {
                Integer value = pointer.value;
                pointer = pointer.next;
                return value;
            }
        };
    }

    private static class DoubleCircularNode {
        DoubleCircularNode pre;
        int value;
        DoubleCircularNode next;

        public DoubleCircularNode(DoubleCircularNode pre, int value, DoubleCircularNode next) {
            this.pre = pre;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 从头部添加节点
     */
    private static void addFirst(int value) {
        //前驱
        DoubleCircularNode pre = sentinel;
        // 后继
        DoubleCircularNode next = sentinel.next;
        DoubleCircularNode node = new DoubleCircularNode(pre, value, next);

        pre.next = node;
        next.pre = node;
    }

    /**
     * 从尾部添加节点
     *
     * @param value
     */
    private static void addLast(int value) {
        DoubleCircularNode node = new DoubleCircularNode(sentinel.pre, value, sentinel);
        sentinel.pre.next = node;
        sentinel.pre = node;
    }

    /**
     * 从头部删除节点
     */
    private static void removeFirst() {
        //要删除的元素
        DoubleCircularNode remove = sentinel.next;
        if (remove == sentinel) {
            throw new IllegalArgumentException();
        }
        sentinel.next = remove.next;
        remove.next.pre = sentinel;
    }

    /**
     * 从尾部删除节点
     */
    private static void removeLast() {
        //要删除的节点
        DoubleCircularNode remove = sentinel.pre;
        if (remove == sentinel) {
            throw new IllegalArgumentException();
        }
        remove.pre.next = sentinel;
        sentinel.pre = remove.pre;
    }

    /**
     * 根据值删除对应的节点
     */
    private static void removeByValue(int value) {
        //要删除的节点
        DoubleCircularNode remove = findByValue(value);
        if (remove == null) {
            return;
        }
        remove.pre.next = remove.next;
        remove.next.pre = remove.pre;
    }

    /**
     * 根据值查找节点
     *
     * @return
     */
    private static DoubleCircularNode findByValue(int value) {
        DoubleCircularNode pointer = sentinel.next;
        while (pointer != sentinel) {
            if (pointer.value == value) {
                return pointer;
            }
            pointer = pointer.next;
        }
        return null;
    }

}
