package com.hcx.algorithm.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Title: MergeList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 合并链表
 * @Author: hongcaixia
 * @Date: 2024/2/6 11:56
 * @Version V1.0
 */
public class MergeList {

    /**
     * 合并两个有序链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoSortedLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        //头节点指针
        ListNode head;
        //操作链表1的指针
        ListNode cur1 = list1;
        //操作链表2的指针
        ListNode cur2 = list2;

        //比较两个链表的头节点谁小谁就作为新的头
        if (list1.val < list2.val) {
            head = list1;
            cur1 = list1.next;
        } else {
            head = list2;
            cur2 = list2.next;
        }
        ListNode pre = head;

        while (true) {
            // 遍历完了list1，让pre直接指向list2
            if (cur1 == null) {
                pre.next = cur2;
                break;
            }
            // 遍历完了list2，让pre直接指向list1
            if (cur2 == null) {
                pre.next = cur1;
                break;
            }
            int val1 = cur1.val;
            int val2 = cur2.val;

            if (val1 <= val2) {
                pre.next = cur1;
                pre = pre.next;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                pre = pre.next;
                cur2 = cur2.next;
            }
        }
        return head;
    }

    /**
     * 合并k个升序链表
     * @param lists
     * @return
     */
    public static ListNode mergeKSortedList(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (int i = 0; i < lists.length; i++) {
            ListNode list = lists[i];
            if(list!=null){
                queue.add(list);
            }
        }
        // 链表头
        ListNode head = queue.poll();
        ListNode cur = head;
        if (head != null) {
            ListNode headNext = head.next;
            if(headNext!=null){
                queue.add(headNext);
            }
        }

        while (!queue.isEmpty()) {
            ListNode ele = queue.poll();
            cur.next = ele;
            cur = ele;
            if (ele.next != null) {
                queue.add(ele.next);
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode5 = new ListNode(5, null);
        ListNode listNode3 = new ListNode(3, listNode5);
        ListNode listNode1 = new ListNode(1, listNode3);

        ListNode listNode6 = new ListNode(6, null);
        ListNode listNode4 = new ListNode(4, listNode6);
        ListNode listNode2 = new ListNode(2, listNode4);

        ListNode listNode13 = new ListNode(10, null);
        ListNode listNode12 = new ListNode(7, listNode13);
        ListNode listNode11 = new ListNode(3, listNode12);

//        System.out.println(listNode1);
//        System.out.println(listNode2);
//        ListNode listNode = mergeTwoSortedLists(listNode1, listNode2);
//        System.out.println(listNode);
        ListNode[] lists = new ListNode[]{listNode11,listNode2,listNode1};

        ListNode listNode7 = mergeKSortedList(lists);
        System.out.println("===================");
        System.out.println(listNode7);

    }


}
