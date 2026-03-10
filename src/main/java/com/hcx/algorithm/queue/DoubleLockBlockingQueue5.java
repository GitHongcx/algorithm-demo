package com.hcx.algorithm.queue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: SingleLockBlockingQueue.java
 * @Package com.hcx.algorithm.queue
 * @Description: 双锁实现阻塞队列
 * 一把锁保护 tail，一把锁保护 head，提升性能
 * @Author: hongcaixia
 * @Date: 2025/1/13 15:23
 * @Version V1.0
 */
public class DoubleLockBlockingQueue5<E> implements BlockingQueue<E> {

    E[] array;

    // 队列大小
    AtomicInteger size = new AtomicInteger(0);

    // 头指针
    int head = 0;

    // 尾指针
    int tail = 0;

    // 出队锁
    ReentrantLock headLock = new ReentrantLock();
    // 入队锁
    ReentrantLock tailLock = new ReentrantLock();

    // 当队列满时，线程先进入等待队列阻塞（此时会释放锁），等到有元素出队之后，再被其他线程唤醒，然后重新执行元素入队操作。
    Condition tailWait = tailLock.newCondition();

    // 当队列空时，线程需要进入等待队列（此时会释放锁），需要等到有元素入队之后，再被其他线程唤醒，然后执行元素出队操作。
    Condition headWait = headLock.newCondition();

    public DoubleLockBlockingQueue5(int capacity) {
        array = (E[]) new Object[capacity];
    }


    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        try {
            // tailWait中的线程被唤醒之后，会与其他线程一起争抢锁，如果抢不到，那么就还需要再次进入等待队列，所以需要使用while循环判断，确保队列不是满的才往下执行
            // 唤醒后应该重新检查条件
            while (isFull()) {
                tailWait.await();
            }
            array[tail] = e;
            tail++;
            if (tail == array.length) {
                tail = 0;
            }
            size.getAndIncrement();
        } finally {
            tailLock.unlock();
        }
        // 队列从空到非空，唤醒等待出队的poll线程
        headLock.lock();
        try {
            headWait.signal();
        } finally {
            headLock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        E e;
        headLock.lockInterruptibly();
        try {
            // 队列空等待
            while (isEmpty()) {
                headWait.await();
            }
            // 不空则出队
            e = array[head];
            if (++head == array.length) {
                head = 0;
            }
            // 修改 size
            size.getAndDecrement();
        } finally {
            headLock.unlock();
        }
        // 队列从满到不满，唤醒等待入队的offer线程
        tailLock.lock();
        try {
            tailWait.signal();
        } finally {
            tailLock.unlock();
        }
        return e;
    }

    @Override
    public boolean isEmpty() {
        return size.get() == 0;
    }

    @Override
    public boolean isFull() {
        return size.get() == array.length;
    }
}
