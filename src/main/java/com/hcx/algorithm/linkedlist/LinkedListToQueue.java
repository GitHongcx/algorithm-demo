package com.hcx.algorithm.linkedlist;

/**
 * @Title: LinkedListToQueue.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 用链表实现队列
 * offer(): 添加元素到队尾
 * poll(): 获取队头元素并删除
 * peek(): 获取队头元素但不删除
 * @Author: hongcaixia
 * @Date: 2024/2/1 11:17
 * @Version V1.0
 */
public class LinkedListToQueue {



}



class MyQueue<V>{

    public MyNode<V> head;

    public MyNode<V> tail;

    public int size;

    public MyQueue(MyNode<V> head, MyNode<V> tail, int size) {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int getSize(){
        return size;
    }

    /**
     * 添加元素到队尾
     * @param value
     */
    public void offer(V value) {
        MyNode<V> myNode = new MyNode<V>(value);
        if (tail == null) {
            head = myNode;
            tail = myNode;
        } else {
            tail.next = myNode;
            tail = myNode;
        }
        size++;
    }

    /**
     * 获取队头元素并删除
     * @return
     */
    public V poll() {
        V result = null;
        if (head != null) {
            result = head.value;
            head = head.next;
            size -- ;
        }
        if (head == null) {
            tail = null;
        }
        return result;
    }

    /**
     * 获取队头元素
     * @return
     */
    public V peek() {
        if (head != null) {
            return head.value;
        }
        return null;
    }
}

class MyNode<T> {
    public T value;
    public MyNode<T> next;

    public MyNode(T value) {
        this.value = value;
    }

    public MyNode(T value, MyNode<T> next) {
        this.next = next;
    }
}




