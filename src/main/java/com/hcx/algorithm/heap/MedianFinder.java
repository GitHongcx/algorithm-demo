package com.hcx.algorithm.heap;

import java.util.PriorityQueue;

/**
 * @Title: MedianFinder.java
 * @Package com.hcx.algorithm.heap
 * @Description: Leetcode295.数据流的中位数
 * 分成两部分：一部分是较小的，一部分是较大的
 * 较小数据中让最大的在堆顶，较大的数据中让最小的在堆顶
 * 堆顶的两个元素就是中间的两个
 * 左边是大顶堆 右边是小顶堆
 * @Author: hongcaixia
 * @Date: 2025/1/15 09:26
 * @Version V1.0
 */
public class MedianFinder {

    // 大顶堆，存储前半部分元素；
    static Heap maxHeap = new Heap(10, true);

    // 小顶堆，存储后半部分元素
    static Heap minHeap = new Heap(10, false);

    public static void addNum(int num) {
        if (maxHeap.size == minHeap.size) {
            //元素加入左边 加入左边之前 要保证元素是最小的 先加入右边，再从右边的堆顶取出最小的加入左边
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            // 元素加入右边，加入右边之前，要保证元素是大的，先加入左边，再从左边的堆顶取出最大的加入右边
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
    }

    public static double findMedian() {
        if (maxHeap.size == minHeap.size) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    // 使用优先级队列 默认是小顶堆

    // 存储前半部分元素
    static PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

    // 存储后半部分元素
    static PriorityQueue<Integer> minQueue = new PriorityQueue<>();


    public static void addNum1(int num) {
        if (minQueue.size() == maxQueue.size()) {
            minQueue.offer(num);
            maxQueue.offer(minQueue.poll());
        } else {
            // 往右边加 加在右边的要保证是大的
            maxQueue.offer(num);
            minQueue.offer(maxQueue.poll());
        }
    }

    public static double findMedian1() {
        if (maxQueue.size() == minQueue.size()) {
            return (maxQueue.peek() + minQueue.peek()) / 2.0;
        } else {
            return maxQueue.peek();
        }
    }


    public static void main(String[] args) {

        addNum1(1);
        //addNum1(2);
        System.out.println(findMedian1());
        //addNum1(3);
        //System.out.println(findMedian1());
    }
}
