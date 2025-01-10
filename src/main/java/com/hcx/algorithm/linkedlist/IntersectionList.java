package com.hcx.algorithm.linkedlist;

/**
 * @Title: IntersectionList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: Leetcode160.相交链表(共尾链表)
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * @Author: hongcaixia
 * @Date: 2025/1/10 12:05
 * @Version V1.0
 */
public class IntersectionList {

    /**
     * pointer1从A链表开始遍历，遍历结束接着遍历B链表
     * pointer2从B链表开始遍历，遍历结束接着遍历A链表
     * 在此过程中，如果遇到相同的节点，即为目标，返回即可
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pointer1 = headA;
        ListNode pointer2 = headB;

        while (true) {
            if (pointer1 == pointer2) {
                return pointer1;
            }
            if (pointer1 == null) {
                pointer1 = headB;
            } else {
                pointer1 = pointer1.next;
            }
            if (pointer2 == null) {
                pointer2 = headA;
            } else {
                pointer2 = pointer2.next;
            }
        }
    }
}
