package com.hcx.algorithm.stack;

import java.util.Iterator;

/**
 * @Title: ArrayStack.java
 * @Package com.hcx.algorithm.stack
 * @Description: 数组实现栈
 * 0  1  2  3  4  5
 * 底             顶
 *
 * @Author: hongcaixia
 * @Date: 2025/1/11 15:41
 * @Version V1.0
 */
public class ArrayStack<E> implements Stack<E>,Iterable<E> {

    E[] array;

    // 栈顶指针
    int top = 0;

    public ArrayStack(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        array[top] = value;
        top++;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        // top 指向的是待压栈的位置 ，前一个位置是栈顶元素
        int index = top - 1;
        E value = array[index];
        // 弹栈之后，要把元素移除，所以栈顶指针需要往后移动一位
        top = top - 1;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        // top 指向的是待压栈的位置 ，前一个位置是栈顶元素
        int index = top - 1;
        return array[index];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == array.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int pointer = top;

            @Override
            public boolean hasNext() {
                return top > 0;
            }

            @Override
            public E next() {
                return array[--pointer];
            }
        };
    }
}
