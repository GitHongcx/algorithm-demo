package com.hcx.algorithm.queue;

import java.util.PriorityQueue;

/**
 * @Title: MergeKLists.java
 * @Package com.hcx.algorithm.heap
 * @Description: Leetcode23.合并K个升序链表
 * @Author: hongcaixia
 * @Date: 2025/1/13 10:35
 * @Version V1.0
 */
public class MergeKLists {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, (o1, o2) -> Integer.compare(o1.val, o2.val));

        // 存储排好序的新链表
        ListNode sentinel = new ListNode(-1, null);
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.offer(node);
            }
        }
        // 定义一个指针，指向新链表的末尾
        ListNode endPointer = sentinel;

        // 弹出最小的元素并将弹出的节点的元素的下一个加入队列
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            endPointer.next = node;
            endPointer = node;
            if (node.next != null) {
                priorityQueue.offer(node.next);
            }
        }
        return sentinel.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
