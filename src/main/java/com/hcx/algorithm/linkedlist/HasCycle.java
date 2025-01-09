package com.hcx.algorithm.linkedlist;

/**
 * @Title: HasCycle.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: Leetcode141.环形链表
 * @Author: hongcaixia
 * @Date: 2025/1/9 18:43
 * @Version V1.0
 */
public class HasCycle {

    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (true) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
            if (fast == null || fast.next == null) {
                return false;
            }
        }
    }


}
