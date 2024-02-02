package com.hcx.algorithm.linkedlist;

/**
 * @Title: ReverseIntervalList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 链表内指定区间反转
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
 * @Author: hongcaixia
 * @Date: 2024/2/1 20:39
 * @Version V1.0
 */
public class ReverseIntervalList {

    public ListNode reverseBetween(ListNode head, int left, int right) {

        return null;
    }


}

class ListNode {
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
