package com.hcx.algorithm.linkedlist;

/**
 * @Title: SwapPairsList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 两两交换链表中的节点
 * @Author: hongcaixia
 * @Date: 2024/2/4 12:13
 * @Version V1.0
 */
public class SwapPairsList {

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1,head);

        ListNode cur = dummy;

        while (cur.next!=null && cur.next.next!=null){
            //暂存
            ListNode curNext = cur.next;
            ListNode moveNode = cur.next.next;

            cur.next.next = cur.next.next.next;
            moveNode.next = curNext;
            cur.next = moveNode;

            //cur去到下下个位置
            cur = curNext;

        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode6 = new ListNode(6,null);
        ListNode listNode5 = new ListNode(5,listNode6);
        ListNode listNode4 = new ListNode(4,listNode5);
        ListNode listNode3 = new ListNode(3,listNode4);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);
        System.out.println(listNode1);
        ListNode listNode = swapPairs(listNode1);
        System.out.println(listNode);
    }

}
