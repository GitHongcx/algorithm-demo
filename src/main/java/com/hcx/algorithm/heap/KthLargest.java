package com.hcx.algorithm.heap;

/**
 * @Title: KthLargest.java
 * @Package com.hcx.algorithm.heap
 * @Description: Leetcode703.数据流中的第K大元素
 * @Author: hongcaixia
 * @Date: 2025/1/14 16:42
 * @Version V1.0
 */
public class KthLargest {

    MinHeap minHeap;

    public KthLargest(int k, int[] nums) {
        minHeap = new MinHeap(k);
        for (int num : nums) {
            add(num);
        }
    }

    /**
     * 插入数据流后，返回当前第k大元素
     * @param val
     * @return
     */
    public int add(int val) {
        // 堆没满
        if(!minHeap.isFull()){
            minHeap.offer(val);
        }else if (val > minHeap.peek()) {
            minHeap.replaceTop(val);
        }
        return minHeap.peek();
    }
}
