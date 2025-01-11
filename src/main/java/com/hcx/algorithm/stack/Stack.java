package com.hcx.algorithm.stack;

/**
 * @Title: Stack.java
 * @Package com.hcx.algorithm.stack
 * @Description: 栈
 * @Author: hongcaixia
 * @Date: 2025/1/11 15:03
 * @Version V1.0
 */
public interface Stack<E> {

    /**
     * 压栈：向栈顶压入元素
     * @param value
     * @return
     */
    boolean push(E value);

    /**
     * 弹栈：从栈顶弹出元素
     * @return
     */
    E pop();

    /**
     * 获取栈顶元素（不弹出）
     * @return
     */
    E peek();

    /**
     * 判断栈是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 判断栈是否已满
     * @return
     */
    boolean isFull();
}
