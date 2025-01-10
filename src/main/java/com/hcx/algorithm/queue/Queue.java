package com.hcx.algorithm.queue;

/**
 * @Title: Queue.java
 * @Package com.hcx.algorithm.queue
 * @Description: 队列接口
 * @Author: hongcaixia
 * @Date: 2025/1/10 16:10
 * @Version V1.0
 */
public interface Queue<E> {

    /**
     * 入队：向队尾插入值
     * @param e
     * @return
     */
    boolean offer(E e);

    /**
     * 出队：从队头获取值并移除
     * @return
     */
    E poll();

    /**
     * 从队头获取值不移除
     * @return
     */
    E peek();

    /**
     * 检查队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 检查队列是否已满
     * @return
     */
    boolean isFull();

}
