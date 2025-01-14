package com.hcx.algorithm.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Title: FindKthLargest.java
 * @Package com.hcx.algorithm.heap
 * @Description: Leetcode215.数组中的第K个最大元素：使用小顶堆
 * 1.将数组的前k个元素加入小顶堆
 * 2.后续的元素，如果比堆顶元素小，则跳过；如果比堆顶元素大，则将该元素替换掉堆顶元素
 * 3.调整堆
 * 堆中保存的就是前k个大的元素，堆顶元素恰好是前K个最大的
 * @Author: hongcaixia
 * @Date: 2025/1/14 14:05
 * @Version V1.0
 */
public class FindKthLargest {


    public static int findKthLargest(int[] nums, int k) {

        // 数组前k个元素加入小顶堆
        MinHeap minHeap = new MinHeap(k);

        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                // 替换堆顶元素
                minHeap.replaceTop(nums[i]);
            }
        }

        return minHeap.peek();
    }

    public static int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt((Integer c1) -> c1).reversed());
        for (int num : nums) {
            pq.offer(num);
        }
        int res = 0;
        for(int j = k;j>0;j--){
            res = pq.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int kthLargest1 = findKthLargest(nums, 4);
        System.out.println(kthLargest1);
    }


}
