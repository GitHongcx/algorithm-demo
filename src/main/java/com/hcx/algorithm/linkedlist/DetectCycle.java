package com.hcx.algorithm.linkedlist;

/**
 * @Title: DetectCycle.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: Leetcode142.环形链表II
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * @Author: hongcaixia
 * @Date: 2025/1/9 18:47
 * @Version V1.0
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (true) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                // 相遇 让slow回到起点
                slow = head;
                while (fast != slow) {
                    //分别前进一步
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
            if (fast == null || fast.next == null) {
                return null;
            }
        }
    }
}
