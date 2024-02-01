package com.hcx.algorithm.linkedlist;

/**
 * @Title: LinkedListToStack.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 用链表实现栈
 * push(): 添加元素到栈
 * pop(): 获取栈元素并删除
 * peek(): 获取栈元素但不删除
 * @Author: hongcaixia
 * @Date: 2024/2/1 12:20
 * @Version V1.0
 */
public class LinkedListToStack<T> {

    public MyNode<T> top;

    public int size;

    public LinkedListToStack(MyNode<T> top, int size) {
        this.top = null;
        this.size = 0;
    }

    /**
     * 添加元素到栈顶
     * @param value
     */
    public void push(T value) {
        MyNode<T> myNode = new MyNode<>(value);
        if (top == null) {
            top = myNode;
        } else {
            myNode.next = top;
            // top.next = myNode;
            top = myNode;
        }
        size++;
    }

    /**
     * 获取栈顶元素并删除
     * @return
     */
    public T pop(){
        T value = null;
        if(top!=null){
            value = top.value;
            top = top.next;
            size--;
        }
        return value;
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public T peek() {
        if (top != null) {
            return top.value;
        }
        return null;
    }


}



