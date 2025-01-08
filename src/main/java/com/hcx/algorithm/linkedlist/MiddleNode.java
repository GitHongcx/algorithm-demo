package com.hcx.algorithm.linkedlist;

/**
 * @Title: MiddleNode.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: Leetcode876.链表的中间节点
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * @Author: hongcaixia
 * @Date: 2025/1/8 16:09
 * @Version V1.0
 */
public class MiddleNode {

    /**
     * 快慢指针
     * 快指针每次前进2步，慢指针每次前进一步，当快指针走到末尾时，此时慢指针正好指向中间的位置，
     * 因为快指针走的步数是慢指针的两倍
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        if (head.next == null) {
            return head;
        }

        ListNode quick = head;
        ListNode slow = head;

        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        return slow;
    }


    public static void main(String[] args) {

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
