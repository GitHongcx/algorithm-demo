package com.hcx.algorithm.queue;

/**
 * @Title: Deque.java
 * @Package com.hcx.algorithm.queue
 * @Description: 双端队列：两端都可以删除、添加
 * @Author: hongcaixia
 * @Date: 2025/1/12 12:14
 * @Version V1.0
 */
public interface Deque<E> {

    /**
     * 队首添加元素
     * @param e
     * @return
     */
    boolean offerFirst(E e);

    /**
     * 队尾添加元素
     * @param e
     * @return
     */
    boolean offerLast(E e);

    /**
     * 队首移除元素并返回
     * @return
     */
    E pollFirst();

    /**
     * 队尾移除元素并返回
     * @return
     */
    E pollLast();

    /**
     * 获取队首元素
     * @return
     */
    E peekFirst();

    /**
     * 获取队尾元素
     * @return
     */
    E peekLast();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 判断队列是否已满
     * @return
     */
    boolean isFull();

}
