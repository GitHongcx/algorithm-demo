package com.hcx.algorithm.queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: SingleLockBlockingQueue.java
 * @Package com.hcx.algorithm.queue
 * @Description: 双锁实现阻塞队列
 * 一把锁保护 tail，一把锁保护 head，提升性能
 * 在唤醒的时候进行了优化，只有当队列处于临界条件的时候才由另一个线程唤醒，否则自己唤醒（级联通知）
 * @Author: hongcaixia
 * @Date: 2025/1/13 15:23
 * @Version V1.0
 */
public class DoubleLockBlockingQueue<E> implements BlockingQueue<E> {

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

    public DoubleLockBlockingQueue(int capacity) {
        array = (E[]) new Object[capacity];
    }


    @Override
    public void offer(E e) throws InterruptedException {

        // 队列当前大小
        int beforeAdd;

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
            // 返回增加之前的值
            beforeAdd = size.getAndIncrement();
            if (beforeAdd + 1 < array.length) {
                // 说明队列还没有满，唤醒阻塞的offer线程
                tailWait.signal();
            }
        } finally {
            tailLock.unlock();
        }

        // 再offer这里加了锁去唤醒非空的poll线程，会导致正常的poll线程也阻塞，抢不到锁，所以需要减少唤醒时的加锁次数
        /**
         * 当队列原本为空，刚好入队了一个元素时，此时执行唤醒操作，对于剩余的其他阻塞了的poll线程，交给poll线程自己来唤醒
         */
        if (beforeAdd == 0) {
            headLock.lock();
            try{
                // 队列从空变化到不空，会唤醒一个等待的 poll 线程，为了避免死锁，需要等tailLock释放了之后才开始加（条件变量的 await()， signal() 等方法需要先获得与之关联的锁）
                headWait.signal();
            }finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public void offer(E e, long timeout) throws InterruptedException {
        int beforeAdd;
        tailLock.lockInterruptibly();
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
            beforeAdd = size.getAndIncrement();
            if (beforeAdd + 1 < array.length) {
                // 如果入队之前+1 还有剩余空间，说明队列中元素还有剩余，可以唤醒阻塞的polll线程
                tailWait.signal();
            }
        } finally {
            tailLock.unlock();
        }

        // 如果入队之前队列时空的，那么此时只有一个元素，就唤醒阻塞的poll线程
        if (beforeAdd == 0) {
            headLock.lock();
            try {
                // 此时入队成功 唤醒在headWait中阻塞的线程（headWait中的线程是因为队列没有元素所以阻塞，此时队列中成功入队了一个元素，唤醒线程去取队列的元素）
                headWait.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public E poll() throws InterruptedException {
        E e;
        // size减之前的值
        int beforeDecrement;

        headLock.lockInterruptibly();
        try {
            // 唤醒后应该重新检查条件
            while (isEmpty()) {
                headWait.await();
            }
            e = array[head];
            array[head] = null;
            head++;
            if (head == array.length) {
                head = 0;
            }
            beforeDecrement = size.getAndDecrement();
            if (beforeDecrement > 1) {
                // 说明队列中还有剩余元素（并非当前poll完了之后就为空了），所以还可以唤醒阻塞的poll线程
                headWait.signal();
            }
        } finally {
            headLock.unlock();
        }

        // 队列刚从满变成不满的时候，去唤醒等待队列中的offer线程，剩余阻塞的offer线程由offer线程自己唤醒
        if (beforeDecrement == array.length) {
            tailLock.lock();
            try {
                // 队列从满变化到不满，唤醒等待队列中的offer线程
                tailWait.signal();
            } finally {
                tailLock.unlock();
            }
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
