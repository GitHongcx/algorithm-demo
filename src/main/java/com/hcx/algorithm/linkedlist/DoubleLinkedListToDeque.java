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

