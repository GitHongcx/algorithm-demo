package com.hcx.algorithm.queue;

/**
 * @Title: DoubleCircularLinkedListDeque.java
 * @Package com.hcx.algorithm.queue
 * @Description: 双向环形链表实现双端队列 (Leetcode641.设计循环双端队列)
 * @Author: hongcaixia
 * @Date: 2025/1/12 12:20
 * @Version V1.0
 */
public class DoubleCircularLinkedListDeque<E> implements Deque<E> {

    private Node<E> sentinel = new Node<>(null, null, null);

    // 容量
    int capacity;

    // 当前大小
    int size = 0;

    public DoubleCircularLinkedListDeque(int capacity) {
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        this.capacity = capacity;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        // sentinel e sentinel.next
        Node<E> pre = sentinel;
        Node<E> next = sentinel.next;
        Node<E> offer = new Node<>(pre, e, next);
        sentinel.next = offer;
        next.pre = offer;
        size++;
        return true;
    }

    // sentinel a b c
    //
    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        // sentinel  a b c   e
        Node<E> pre = sentinel.pre;
        Node<E> next = sentinel;
        Node<E> offer = new Node<>(pre, e, next);
        pre.next = offer;
        next.pre = offer;
        size++;
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        // 要移除的元素
        Node<E> poll = sentinel.next;
        sentinel.next = poll.next;
        sentinel.next.pre = sentinel;
        size--;
        return poll.value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        // 要移除的元素
        Node<E> poll = sentinel.pre;
        poll.pre.next = sentinel;
        sentinel.pre = poll.pre;
        size--;
        return poll.value;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.next.value;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return sentinel.pre.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    static class Node<E> {

        Node<E> pre;

        E value;

        Node<E> next;

        public Node(Node<E> pre, E value, Node<E> next) {
            this.pre = pre;
            this.value = value;
            this.next = next;
        }
    }
}
