package com.hcx.algorithm.linkedlist;

/**
 * @Title: RemoveNthFromEnd.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: Leetcode19.删除链表的倒数第N个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * @Author: hongcaixia
 * @Date: 2025/1/5 15:41
 * @Version V1.0
 */
public class RemoveNthFromEnd {

    /**
     * 快慢指针实现 1 2 2 3 4
     * 让两个指针的间隔为n，那么当快指针结束时，慢指针的位置就是倒数第n的前一个（慢指针从哨兵开始）
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null){
            return null;
        }
        ListNode sentinel = new ListNode(-1,head);
        ListNode fastPointer = sentinel;
        ListNode slowPointer = sentinel;

        // 让快指针先走n+1步
        for (int j = 0; j < n+1; j++) {
            fastPointer = fastPointer.next;
        }
        while (fastPointer != null) {
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }
        slowPointer.next = slowPointer.next.next;
        return sentinel.next;
    }


    /**
     * 递归实现
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEndRec(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1,head);
        if(head.next == null){
            return null;
        }
        removeRec(sentinel,n);
        return sentinel.next;
    }

    /**
     * 4     4
     * 3     3
     * 2     2
     * 2     1  head
     * 1     0  return 1
     * null  return 0
     * @param head
     * @param n
     * @return
     */
    public static int removeRec(ListNode head, int n){
        if(head == null){
            return 0;
        }
        int removeIndex = removeRec(head.next, n);
        // 1 2 2 3 4
        System.out.println("倒数第"+removeIndex+"个结点是"+head.val);
        //
        /**
         * 让index比实际的下标要少1，就删除的是下一个元素，即要删除的是head指向的下一个元素.
         * 如果head指向的是要删除的元素，那么没办法找到上一个元素 让上一个元素指向head的下一个元素，
         * 所以就找到要删除的上一个元素的时候，再去删除当前head指向的下一个元素，即为要删除的元素
         */
        if(removeIndex == n){
            // 要删除的结点
            // 要删除的元素：head.next
            head.next = head.next.next;
        }
        removeIndex = removeIndex + 1;
        return removeIndex;
    }


    public static void main(String[] args) {

        // 1 2 2 3 4
        ListNode node5 = new ListNode(4, null);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        removeNthFromEnd(node1,2);
        System.out.println("------------------");

        System.out.println(node1.val);
        System.out.println(node1.next.val);
        System.out.println(node1.next.next.val);
        System.out.println(node1.next.next.next.val);
        System.out.println(node1.next.next.next.next.val);


    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val,  ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
