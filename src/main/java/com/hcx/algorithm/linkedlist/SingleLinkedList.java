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
public class SingleLinkedList implements Iterable<Integer> {

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
     * 尾插
     *
     * @param value
     */
    private static void addLast(int value) {
        SingleNode last = findLast();
        if (last == null) {
            addFirst(value);
        } else {
            last.next = new SingleNode(value, null);
        }
    }

    /**
     * 根据索引查找节点
     *
     * @param index
     * @return
     */
    private static SingleNode findNode(int index) {
        int i = 0;
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
        if (index == 0) {
            addFirst(val);
        } else {
            //获取当前要插入的索引的上一个元素
            SingleNode pre = findNode(index - 1);
            if (pre == null) {
                throw new IllegalArgumentException(String.format("index [%d] 不合法 %n", index));
            }
            pre.next = new SingleNode(val, pre.next);
        }
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
        if (head == null) {
            throw new IllegalArgumentException("没有可删除的节点");
        }
        head = head.next;
    }

    /**
     * 按照索引删除对应的节点
     * @param index
     */
    public static void remove(int index) {
        if (index == 0) {
            removeFirst();
        } else {
            SingleNode pre = findNode(index - 1);
            if (pre == null || pre.next == null) {
                throw new IllegalArgumentException("没有可删除的节点");
            }
            pre.next = pre.next.next;
        }
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
     *
     * @param consumer
     */
    private static void loopFor(Consumer<Integer> consumer) {
        for (SingleNode pointer = head; pointer != null; pointer = pointer.next) {
            consumer.accept(pointer.value);
        }
    }

    /**
     * 递归遍历
     */
    private static void loopRecursion(Consumer<Integer> before, Consumer<Integer> after) {
        recursion(head, before, after);
    }

    /**
     * 递归调用
     * @param node
     * @param before
     * @param after
     */
    private static void recursion(SingleNode node, Consumer<Integer> before, Consumer<Integer> after) {
        if (node == null) {
            return;
        }
        before.accept(node.value);
        recursion(node.next, before, after);
        after.accept(node.value);
    }

    /**
     * 找到最后一个元素
     *
     * @return
     */
    private static SingleNode findLast() {
        if (head == null) {
            return null;
        }
        SingleNode pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        return pointer;
    }


    public static void main(String[] args) {
        SingleLinkedList.addFirst(1);
        SingleLinkedList.addFirst(2);
        SingleLinkedList.addFirst(3);
        SingleLinkedList.addFirst(4);
        SingleLinkedList.addFirst(5);
        SingleLinkedList.loopWhile(value -> System.out.println(value));
        System.out.println("000000");
        SingleLinkedList.loopRecursion(value -> System.out.println("before: "+value),value -> System.out.println("after: "+value));

        System.out.println("==================");
        SingleLinkedList.loopFor(value -> System.out.println(value));
        System.out.println("==================");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        for (Integer value : singleLinkedList) {
            System.out.println(value);
        }
        System.out.println("------------------------");
        SingleLinkedList.addLast(1);
        SingleLinkedList.addLast(2);
        SingleLinkedList.addLast(3);
        SingleLinkedList.addLast(4);
        SingleLinkedList.addLast(5);
        SingleLinkedList.loopWhile(value -> System.out.println(value));

        int i = 0;


        int nodeVal = getNodeVal(2);
        System.out.println("找到的元素是：" + nodeVal);


        // addByIndex(0,17);
        //removeFirst();
        for (SingleNode pointer = head; pointer != null; pointer = pointer.next, i++) {
            System.out.println(pointer.value + " 索引是：" + i);
        }
        System.out.println("--------------");
        removeFirst();
        System.out.println("--------------");
        SingleLinkedList.loopWhile(value -> System.out.println(value));
        System.out.println("--------------");
        removeFirst();
        SingleLinkedList.loopWhile(value -> System.out.println(value));

        System.out.println("--------------");

        remove(2);
        SingleLinkedList.loopWhile(value -> System.out.println(value));

        System.out.println("--------------");
        remove(0);
        SingleLinkedList.loopWhile(value -> System.out.println(value));

        System.out.println("--------------");
        //remove(7);
        SingleLinkedList.loopWhile(value -> System.out.println(value));

        System.out.println("--------------");
        remove(6);
        SingleLinkedList.loopWhile(value -> System.out.println(value));
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


