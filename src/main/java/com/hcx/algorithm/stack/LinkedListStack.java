package com.hcx.algorithm.stack;

import java.util.Iterator;

/**
 * @Title: LinkedListStack.java
 * @Package com.hcx.algorithm.stack
 * @Description: 链表实现栈
 * @Author: hongcaixia
 * @Date: 2025/1/11 15:08
 * @Version V1.0
 */
public class LinkedListStack<E> implements Stack<E>,Iterable<E> {

    private Node<E> sentinel = new Node<>(null,null);

    // 头指针
    private Node<E> head = sentinel;

    // 容量
    private final int capacity;

    // 当前大小
    private int size;


    public LinkedListStack(int capacity) {
        this.capacity = capacity;
    }


    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        Node<E> node = new Node<E>(value, head.next);
        head.next = node;
        size++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        Node<E> node = head.next;
        head.next = node.next;
        size--;
        return node.value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.next.value;
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public boolean isFull() {
        return capacity == size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> pointer = head.next;

            @Override
            public boolean hasNext() {
                return pointer != null;
            }

            @Override
            public E next() {
                E value = pointer.value;
                pointer = pointer.next;
                return value;
            }
        };
    }

    static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
