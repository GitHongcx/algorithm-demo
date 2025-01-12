package com.hcx.algorithm.queue;

import java.util.Iterator;

/**
 * @Title: CircularArrayDeque.java
 * @Package com.hcx.algorithm.queue
 * @Description: 基于循环数组实现双端队列
 * tail指向的位置不存储元素，每次都指向待存储的位置
 * head操作：先往前走一个，然后设置元素
 * tail操作：先设置值，然后往后走一个
 * @Author: hongcaixia
 * @Date: 2025/1/12 15:04
 * @Version V1.0
 */
public class CircularArrayDeque<E> implements Deque<E>,Iterable<E> {

    E[] array;

    int head;

    int tail;

    public CircularArrayDeque(int capacity) {
        array = (E[]) new Object[capacity + 1];
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        // head指针先往前走一个位置，然后设置值
        head = head - 1;
        if (head < 0) {
            head = array.length - 1;
        }
        array[head] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        array[tail] = e;
        tail = tail + 1;
        if (tail >= array.length) {
            tail = 0;
        }
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        E e = array[head];
        // help gc
        array[head] = null;
        head = head + 1;
        if (head >= array.length) {
            head = 0;
        }
        return e;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        tail = tail - 1;
        if (tail < 0) {
            tail = array.length - 1;
        }
        E e = array[tail];
        // help gc
        array[tail] = null;
        return e;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        tail = tail - 1;
        if (tail < 0) {
            tail = array.length - 1;
        }
        return array[tail];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        if (tail > head) {
            return tail - head == array.length - 1;
        } else if (tail < head) {
            return head - tail == 1;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int pointer = head;

            @Override
            public boolean hasNext() {
                return pointer != tail;
            }

            @Override
            public E next() {
                E e = array[pointer];
                pointer = pointer + 1;
                if (pointer >= array.length) {
                    pointer = 0;
                }
                return e;
            }

            @Override
            public void remove() {

            }
        };
    }
}
