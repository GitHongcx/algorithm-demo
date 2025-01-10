package com.hcx.algorithm.queue;

import java.util.Iterator;

/**
 * @Title: CircularArrayQueue.java
 * @Package com.hcx.algorithm.queue
 * @Description: 使用环形数组实现队列 head和tail持续递增，需要取模计算出索引值
 * 1.对比起普通数组，当出队的时候，所有元素都要移动；环形数组可以不需要移动元素，通过指针来改变头。
 * 2.环不会存在越界问题
 * 3.数组性能更好
 * @Author: hongcaixia
 * @Date: 2025/1/10 19:16
 * @Version V1.0
 */
public class CircularArrayQueue3<E> implements Queue<E>,Iterable<E> {

    private E[] arr;

    private int head = 0;

    private int tail = 0;

    public CircularArrayQueue3(int capacity) {
        arr = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E e) {
        if (isFull()) {
            return false;
        }
        int index = (int) (Integer.toUnsignedLong(tail) % arr.length);
        arr[index] = e;
        //arr[tail % arr.length] = e;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int index = (int) (Integer.toUnsignedLong(head) % arr.length);
        E e = arr[index];
        head++;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        int index = (int) (Integer.toUnsignedLong(head) % arr.length);
        return arr[index];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return tail - head == arr.length;
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
                int index = (int) (Integer.toUnsignedLong(pointer) % arr.length);
                E e = arr[index];
                pointer++;
                return e;
            }
        };
    }
}
