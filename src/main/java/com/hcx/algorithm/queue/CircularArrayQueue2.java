package com.hcx.algorithm.queue;

import java.util.Iterator;

/**
 * @Title: CircularArrayQueue.java
 * @Package com.hcx.algorithm.queue
 * @Description: 使用环形数组实现队列 优化不让尾指针空着
 * 1.对比起普通数组，当出队的时候，所有元素都要移动；环形数组可以不需要移动元素，通过指针来改变头。
 * 2.环不会存在越界问题
 * 3.数组性能更好
 * @Author: hongcaixia
 * @Date: 2025/1/10 19:16
 * @Version V1.0
 */
public class CircularArrayQueue2<E> implements Queue<E>,Iterable<E> {

    private E[] arr;

    private int head = 0;

    private int tail = 0;

    // 元素个数
    private int size = 0;

    public CircularArrayQueue2(int capacity) {
        arr = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E e) {
        if (isFull()) {
            return false;
        }
        arr[tail] = e;
        tail = (tail + 1) % arr.length;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = arr[head];
        head = (head + 1) % arr.length;
        size--;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return arr[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == arr.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int pointer = head;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                E e = arr[pointer];
                pointer = (pointer + 1) % arr.length;
                count++;
                return e;
            }
        };
    }
}
