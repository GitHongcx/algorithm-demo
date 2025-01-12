package com.hcx.algorithm.queue;

/**
 * @Title: PriorityQueue1.java
 * @Package com.hcx.algorithm.queue
 * @Description: 优先级队列：无序数组实现
 * 入队：跟普通队列一样
 * 出队：优先级最高的出队
 * @Author: hongcaixia
 * @Date: 2025/1/12 17:33
 * @Version V1.0
 */
public class PriorityQueue1<E extends Priority> implements Queue<E> {

    Priority[] array;

    // 数组当前大小
    int size;

    public PriorityQueue1(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E e) {
        if(isFull()){
            return false;
        }
        array[size++] = e;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        // 找到优先级最高的元素出队
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].getPriority() > array[maxIndex].getPriority()) {
                maxIndex = i;
            }
        }
        E e = (E) array[maxIndex];
        // 出队后，该位置后的元素往前移动
        if (maxIndex < size - 1) {
            System.arraycopy(array, maxIndex + 1, array, maxIndex, size - maxIndex - 1);
        }
        // help gc
        array[--size] = null;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        // 找到优先级最高的元素
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].getPriority() > array[maxIndex].getPriority()) {
                maxIndex = i;
            }
        }
        E e = (E) array[maxIndex];
        return e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
