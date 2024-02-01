package com.hcx.algorithm.linkedlist;

/**
 * @Title: DoubleLinkedListToDeque.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 用双向链表实现双端队列
 * @Author: hongcaixia
 * @Date: 2024/2/1 15:11
 * @Version V1.0
 */
public class DoubleLinkedListToDeque<T> {

    public DNode<T> head;
    public DNode<T> tail;
    public int size;

    public DoubleLinkedListToDeque(DNode<T> head, DNode<T> tail, int size) {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    /**
     * 从队列头部加入元素
     * @param value
     */
    public void pushHead(T value) {
        DNode<T> node = new DNode<>(value);
        if (head == null || tail == null) {
            //之前没有元素，头和尾都指向当前元素
            head = node;
            tail = node;
        } else {
            //先操作指针，再移动头
            node.next = head;
            head.pre = node;
            head = node;
        }
        size++;
    }

    /**
     * 从队列尾部加入元素
     * @param value
     */
    public void pushTail(T value) {
        DNode<T> node = new DNode<>(value);
        if (head == null || tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
        size++;
    }

    /**
     * 从队列头部弹出元素
     * @return
     */
    public T pollHead() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        if (head == tail) {
            //刚好只剩一个元素的时候
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.pre = null;
        }
        size--;
        return value;
    }

    /**
     * 从队列尾部弹出元素
     * @return
     */
    public T pollTail() {
        if (tail == null) {
            return null;
        }
        T value = tail.value;
        if (tail == head) {
            tail = null;
            head = null;
        } else {
            tail = tail.pre;
            tail.next = null;
        }
        size--;
        return value;
    }

    /**
     * 获取队列头部的元素
     * @return
     */
    public T peekHead() {
        return head == null ? null : head.value;
    }

    /**
     * 获取队列尾部的元素
     * @return
     */
    public T peekTail(){
        return tail == null ? null : tail.value;
    }

}

class DNode<T> {
    public T value;
    public DNode<T> pre;
    public DNode<T> next;

    public DNode(T value) {
        this.value = value;
        this.pre = pre;
        this.next = next;
    }
}

