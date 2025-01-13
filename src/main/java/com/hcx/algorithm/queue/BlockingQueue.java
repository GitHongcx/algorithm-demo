package com.hcx.algorithm.queue;

/**
 * @Title: BlockingQueue.java
 * @Package com.hcx.algorithm.queue
 * @Description: 阻塞队列接口
 * @Author: hongcaixia
 * @Date: 2025/1/13 15:16
 * @Version V1.0
 */
public interface BlockingQueue<E> {

    /**
     * 阻塞入队元素
     * 当队列满时，阻塞，直到队列不满时，把元素入队
     *
     * @param e
     * @throws InterruptedException
     */
    void offer(E e) throws InterruptedException;

    /**
     * 超时阻塞入队元素
     * 当队列满时，阻塞，直到队列不满时，把元素入队，如果中间超时时间达到，则放弃入队，返回false
     *
     * @param e
     * @param timeout
     * @return
     * @throws InterruptedException
     */
    void offer(E e, long timeout) throws InterruptedException;

    /**
     * 元素出队
     *
     * @return
     * @throws InterruptedException
     */
    E poll() throws InterruptedException;

    /**
     * 判断队列是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 判断队列是否已满
     *
     * @return
     */
    boolean isFull();


}
