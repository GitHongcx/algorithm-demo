package com.hcx.algorithm.queue;

import java.util.Iterator;

/**
 * @Title: CircularArrayQueue.java
 * @Package com.hcx.algorithm.queue
 * @Description: 使用环形数组实现队列 优化取余运算
 * 求模运算：
 * 如果除数是2的n次方，被除数的后n位即为余数（模）
 * 求被除数后n位：被除数 和 2^n-1 进行 按位与 运算
 * @Author: hongcaixia
 * @Date: 2025/1/10 19:16
 * @Version V1.0
 */
public class CircularArrayQueue4<E> implements Queue<E>,Iterable<E> {

    private E[] arr;

    private int head = 0;

    private int tail = 0;

    public CircularArrayQueue4(int capacity) {
        // 需要传入 2^n 长度
//        if((capacity & (capacity-1)) != 0){
//            // 不是2的幂次
//            throw new IllegalArgumentException("数组容量非法,需设置为2的幂次");
//        }

        // 修改为最近的2的幂次
        // 方法一：
        /*
        int n = (int) (Math.log10(capacity - 1) / Math.log10(2)) + 1;
        arr = (E[]) new Object[1 << n];
         */

        // 方法二：
        capacity -= 1;
        capacity |= capacity >> 1;
        capacity |= capacity >> 2;
        capacity |= capacity >> 4;
        capacity |= capacity >> 8;
        capacity |= capacity >> 16;
        arr = (E[]) new Object[capacity];
    }

    @Override
    public boolean offer(E e) {
        if (isFull()) {
            return false;
        }
        int index = tail & (arr.length - 1);
        arr[index] = e;
        tail++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        int index = head & (arr.length - 1);
        E e = arr[index];
        head++;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        int index = head & (arr.length - 1);
        return arr[index];
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        // 运算结果没有超过范围，就不会出现问题
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
                int index = pointer & (arr.length - 1);
                E e = arr[index];
                pointer++;
                return e;
            }
        };
    }
}
