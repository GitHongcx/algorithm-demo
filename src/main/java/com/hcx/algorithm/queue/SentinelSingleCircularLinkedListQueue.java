package com.hcx.algorithm.queue;

import java.util.Iterator;

/**
 * @Title: SentinelSingleCircularLinkedListQueue.java
 * @Package com.hcx.algorithm.queue
 * @Description: 使用单向带哨兵的环形链表实现队列
 * @Author: hongcaixia
 * @Date: 2025/1/10 16:16
 * @Version V1.0
 */
public class SentinelSingleCircularLinkedListQueue<E> implements Queue<E>, Iterable<E> {

    // 队列大小（元素个数）
    private int size;

    // 队列容量
    private int capacity = Integer.MAX_VALUE;


    public SentinelSingleCircularLinkedListQueue(int capacity) {
        this.capacity = capacity;
    }

    // 哨兵节点
    Node<E> sentinel = new Node<E>(null, null);
    // 头指针
    Node<E> head = sentinel;
    // 尾指针 环形链表尾节点的next需要指向头
    Node<E> tail = sentinel;

    {
        tail.next = head;
    }

    public SentinelSingleCircularLinkedListQueue() {

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> pointer = head.next;

            @Override
            public boolean hasNext() {
                return pointer != head;
            }

            @Override
            public E next() {
                E val = pointer.val;
                pointer = pointer.next;
                return val;
            }
        };
    }

    private static class Node<E> {
        E val;
        Node<E> next;

        public Node(E val, Node<E> next) {
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public boolean offer(E e) {
        // 检查容量
        if (isFull()) {
            return false;
        }
        Node<E> add = new Node<>(e, head);
        tail.next = add;
        tail = add;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        head.next = head.next.next;
        // 如果刚好只剩下一个元素
        if (head.next == tail) {
            tail = head;
        }
        size--;
        return head.next.val;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.next.val;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }
}
