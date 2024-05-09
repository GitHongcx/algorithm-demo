package com.hcx.algorithm.linkedlist;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @Title: SingleLinkedList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 单向链表的基本操作
 * @Author: hongcaixia
 * @Date: 2024/5/9 16:46
 * @Version V1.0
 */
public class SingleLinkedList implements Iterable<Integer>{

    private static SingleNode head = null;

    @Override
    public Iterator<Integer> iterator() {
        return new IntegerIterator();
    }

    static class SingleNode {

        private int value;

        private SingleNode next;

        public SingleNode(int value, SingleNode next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 头插
     *
     * @param value
     */
    private static void addFirst(int value) {
        //链表为空
        // head = new SingleNode(value, null);
        //链表非空
        head = new SingleNode(value, head);
    }

    /**
     * 找到最后一个元素
     * @return
     */
    private SingleNode findLast() {
        if (head == null) {
            return null;
        }
        SingleNode pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        return pointer;
    }

    /**
     * 尾插
     * @param value
     */
    private static void addLast(int value) {

    }

    /**
     * 遍历while
     */
    private static void loopWhile(Consumer<Integer> consumer) {
        SingleNode pointer = head;
        while (pointer != null) {
            consumer.accept(pointer.value);
            pointer = pointer.next;
        }
    }

    /**
     * 遍历for
     * @param consumer
     */
    private static void loopFor(Consumer<Integer> consumer) {
        for (SingleNode pointer = head; pointer != null; pointer = pointer.next) {
            consumer.accept(pointer.value);
        }
    }


    public static void main(String[] args) {
        SingleLinkedList.addFirst(1);
        SingleLinkedList.addFirst(2);
        SingleLinkedList.addFirst(3);
        SingleLinkedList.addFirst(4);
        SingleLinkedList.addFirst(5);
        SingleLinkedList.loopWhile(value -> System.out.println(value));
        System.out.println("==================");
        SingleLinkedList.loopFor(value -> System.out.println(value));
        System.out.println("==================");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        for (Integer value : singleLinkedList) {
            System.out.println(value);
        }
    }

    private static class IntegerIterator implements Iterator<Integer> {
        // 指针
        SingleNode pointer = head;

        /**
         * 是否有下一个元素
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            return pointer != null;
        }

        /**
         * 返回当前元素，并指向下一个元素
         *
         * @return
         */
        @Override
        public Integer next() {
            int value = pointer.value;
            pointer = pointer.next;
            return value;
        }
    }
}


