package com.hcx.algorithm.queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: SingleLockBlockingQueue.java
 * @Package com.hcx.algorithm.queue
 * @Description: 单锁实现阻塞队列
 * @Author: hongcaixia
 * @Date: 2025/1/13 15:23
 * @Version V1.0
 */
public class SingleLockBlockingQueue<E> implements BlockingQueue<E> {

    E[] array;

    // 队列大小
    int size = 0;

    // 头指针
    int head = 0;

    // 尾指针
    int tail = 0;

    // 保证线程安全操作队列时需要加锁
    ReentrantLock lock = new ReentrantLock();

    // 当队列满时，线程先进入等待队列阻塞（此时会释放锁），等到有元素出队之后，再被其他线程唤醒，然后重新执行元素入队操作。
    Condition tailWait = lock.newCondition();

    // 当队列空时，线程需要进入等待队列（此时会释放锁），需要等到有元素入队之后，再被其他线程唤醒，然后执行元素出队操作。
    Condition headWait = lock.newCondition();

    public SingleLockBlockingQueue(int capacity) {
        array = (E[]) new Object[capacity];
    }


    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
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
            size++;
            // 此时入队成功 唤醒在headWait中阻塞的线程（headWait中的线程是因为队列没有元素所以阻塞，此时队列中成功入队了一个元素，唤醒线程去取队列的元素）
            headWait.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void offer(E e, long timeout) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            long time = TimeUnit.MILLISECONDS.toNanos(timeout);
            // 唤醒后应该重新检查条件
            while (isFull()) {
                if (time <= 0) {
                    return;
                }
                // 重新更新等待时间
                time = tailWait.awaitNanos(time);
            }
            array[tail] = e;
            tail++;
            if (tail == array.length) {
                tail = 0;
            }
            size++;
            // 此时入队成功 唤醒在headWait中阻塞的线程（headWait中的线程是因为队列没有元素所以阻塞，此时队列中成功入队了一个元素，唤醒线程去取队列的元素）
            headWait.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            // 唤醒后应该重新检查条件
            while (isEmpty()) {
                headWait.await();
            }
            E e = array[head];
            array[head] = null;
            head++;
            if (head == array.length) {
                head = 0;
            }
            size--;
            // 此时队列中已经有元素出队，那么队列中已经有空间可以再入队了，唤醒在入队时因为没空间阻塞的等待队列tailWait
            tailWait.signal();
            return e;
        } finally {
            lock.unlock();
        }
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
