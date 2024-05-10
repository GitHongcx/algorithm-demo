package com.hcx.algorithm.linkedlist;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @Title: SingleLinkedList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 带哨兵的单向链表的基本操作
 * @Author: hongcaixia
 * @Date: 2024/5/9 16:46
 * @Version V1.0
 */
public class SentinelSingleLinkedList implements Iterable<Integer> {

    /**
     * 让头指针指向哨兵节点 减少非空判断
     */
    private static SingleNode head = new SingleNode(-1,null);

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
        // head = new SingleNode(value, head);
        addByIndex(0, value);
    }


    /**
     * 尾插
     *
     * @param value
     */
    private static void addLast(int value) {
        SingleNode last = findLast();
//        if (last == null) {
//            addFirst(value);
//        } else {
        last.next = new SingleNode(value, null);
//        }
    }

    /**
     * 根据索引查找节点
     *
     * @param index
     * @return
     */
    private static SingleNode findNode(int index) {
        int i = -1;
        for (SingleNode pointer = head; pointer != null; pointer = pointer.next, i++) {
            if (i == index) {
                return pointer;
            }
        }
        return null;
    }

    /**
     * 往对应位置index插入元素
     *
     * @param index
     * @param val
     */
    private static void addByIndex(int index, int val) {
//        if (index == 0) {
//            addFirst(val);
//        } else {
        //获取当前要插入的索引的上一个元素
        SingleNode pre = findNode(index - 1);
        if (pre == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法 %n", index));
        }
        pre.next = new SingleNode(val, pre.next);
//        }
    }

    /**
     * 根据索引获取节点值
     *
     * @param index
     * @return
     */
    public static int getNodeVal(int index) {
        SingleNode node = findNode(index);
        if (node == null) {
            throw new IllegalArgumentException(String.format("index [%d] 不合法 %n", index));
        }
        return node.value;
    }

    /**
     * 删除第一个节点
     */
    public static void removeFirst() {
//        if (head == null) {
//            throw new IllegalArgumentException("没有可删除的节点");
//        }
//        head = head.next;
        remove(0);
    }

    /**
     * 按照索引删除对应的节点
     * @param index
     */
    public static void remove(int index) {
//        if (index == 0) {
//            removeFirst();
//        } else {
            SingleNode pre = findNode(index - 1);
            if (pre == null || pre.next == null) {
                throw new IllegalArgumentException("没有可删除的节点");
            }
            pre.next = pre.next.next;
//        }
    }

    /**
     * 遍历while
     */
    private static void loopWhile(Consumer<Integer> consumer) {
        SingleNode pointer = head.next;
        while (pointer != null) {
            consumer.accept(pointer.value);
            pointer = pointer.next;
        }
    }

    /**
     * 遍历for
     *
     * @param consumer
     */
    private static void loopFor(Consumer<Integer> consumer) {
        for (SingleNode pointer = head.next; pointer != null; pointer = pointer.next) {
            consumer.accept(pointer.value);
        }
    }

    /**
     * 找到最后一个元素
     *
     * @return
     */
    private static SingleNode findLast() {
        // 因为头指针指向的是哨兵 所以不会为空
//        if (head == null) {
//            return null;
//        }
        SingleNode pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        return pointer;
    }


    public static void main(String[] args) {


        SentinelSingleLinkedList.addLast(1);
        SentinelSingleLinkedList.addLast(2);
        SentinelSingleLinkedList.addLast(3);
        SentinelSingleLinkedList.addLast(4);
        SentinelSingleLinkedList.addLast(5);
        SentinelSingleLinkedList.loopWhile(value -> System.out.println(value));

        System.out.println("-------kkkkkk-----------------");

        SentinelSingleLinkedList.addFirst(1);
        SentinelSingleLinkedList.addFirst(2);
        SentinelSingleLinkedList.addFirst(3);
        SentinelSingleLinkedList.addFirst(4);
        SentinelSingleLinkedList.addFirst(5);
        SentinelSingleLinkedList.loopWhile(value -> System.out.println(value));


        System.out.println("=====================----------");
        SentinelSingleLinkedList.loopFor(value -> System.out.println(value));
        System.out.println("==================");
        SentinelSingleLinkedList singleLinkedList = new SentinelSingleLinkedList();
        for (Integer value : singleLinkedList) {
            System.out.println(value);
        }


        int i = 0;


        int nodeVal = getNodeVal(2);
        System.out.println("找到的元素是：" + nodeVal);


        // addByIndex(0,17);
        //removeFirst();
//        for (SingleNode pointer = head; pointer != null; pointer = pointer.next, i++) {
//            System.out.println(pointer.value + " 索引是：" + i);
//        }
        System.out.println("--------------");
        removeFirst();
        System.out.println("--------------");
        SentinelSingleLinkedList.loopWhile(value -> System.out.println(value));
        System.out.println("--------------");
        removeFirst();
        SentinelSingleLinkedList.loopWhile(value -> System.out.println(value));

        System.out.println("--------------");

        remove(2);
        SentinelSingleLinkedList.loopWhile(value -> System.out.println(value));

        System.out.println("--------------");
        remove(0);
        SentinelSingleLinkedList.loopWhile(value -> System.out.println(value));

        System.out.println("--------------");
        //remove(7);
        SentinelSingleLinkedList.loopWhile(value -> System.out.println(value));

        System.out.println("--------------");
        remove(6);
        SentinelSingleLinkedList.loopWhile(value -> System.out.println(value));
    }

    private static class IntegerIterator implements Iterator<Integer> {
        // 指针
        SingleNode pointer = head.next;

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


