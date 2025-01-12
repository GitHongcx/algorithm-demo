package com.hcx.algorithm.queue;

/**
 * @Title: PriorityQueue1.java
 * @Package com.hcx.algorithm.queue
 * @Description: 优先级队列：有序数组实现
 * 入队：跟普通队列一样
 * 出队：优先级最高的出队
 * @Author: hongcaixia
 * @Date: 2025/1/12 17:33
 * @Version V1.0
 */
public class PriorityQueue2<E extends Priority> implements Queue<E> {

    Priority[] array;

    // 数组当前大小
    int size;

    public PriorityQueue2(int capacity) {
        array = new Priority[capacity];
    }

    @Override
    public boolean offer(E e) {
        if (isFull()) {
            return false;
        }
        // 按照优先级，插入到正确的位置
        int index = size - 1;
        // 从数组末尾开始往前找，如果数组中元素的优先级比当前的高，就继续往前，同时把数组的元素往后移（空出位置给当前元素）
        while (index >= 0 && array[index].getPriority() > e.getPriority()) {
            array[index + 1] = array[index];
            index--;
        }
        // 找到了比当前优先级小的则退出了循环，那么index所指向的上一个就是要插入的位置
        array[index + 1] = e;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) array[size - 1];
        // help gc
        array[--size] = null;
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[size - 1];
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
