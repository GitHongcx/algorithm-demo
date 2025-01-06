package com.hcx.algorithm.linkedlist;

/**
 * @Title: DeleteDuplicates.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: Leetcode.83 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * <p>
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 * @Author: hongcaixia
 * @Date: 2025/1/5 19:33
 * @Version V1.0
 */
public class DeleteDuplicates {
    // 1 2 2 3 4   -3 -1

    /**
     * 递归
     * @param head
     * @return
     */
    public static ListNode deleteDuplicatesRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode listNode = deleteDuplicatesRecursion(head.next);
        if (head.val == listNode.val) {
            head.next = head.next.next;
            return head;
        } else {
            return head;
        }
    }


    /**
     * 双指针
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pointer1 = head;
        ListNode pointer2 = pointer1.next;
        //pointer2 = pointer2.next;
        while (pointer2 != null) {
            if (pointer2.val == pointer1.val) {
                pointer1.next = pointer2.next;
                pointer2 = pointer2.next;
            } else {
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
            }
        }
        return head;
    }


    /**
     * Leetcode82: 删除排序链表中的重复元素II
     * 删除原始链表中所有重复数字的节点，只留下不同的数字
     * @param head
     * @return
     */
    public static ListNode deleteDuplicateRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            //找到重复的，还需要一直往后找，直到不重复为止，那么head.next就是去掉了当前重复元素的子链表
            ListNode pointer = head.next.next;
            while (pointer != null && pointer.val == head.val) {
                pointer = pointer.next;
            }
            return deleteDuplicateRecursion(pointer);
        } else {
            ListNode listNode = deleteDuplicateRecursion(head.next);
            head.next = listNode;
            return head;
        }
    }







    public static ListNode deleteDuplicates2Recursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 1 1 1 2 3 null
        if(head.val == head.next.val){
            //相等的 继续往后
            while (head.next != null && head.next.next != null){
                head = head.next;
                if(head.val != head.next.val){
                    ListNode listNode = deleteDuplicates2Recursion(head.next);
                    return listNode;
                }
            }
            ListNode listNode = deleteDuplicates2Recursion(head.next.next);
            return listNode;
        }else{
            ListNode listNode = deleteDuplicates2Recursion(head.next);
            head.next = listNode;
            return head;
        }


//        ListNode listNode = deleteDuplicates2Recursion(head.next);
//
//        if (head.val == listNode.val) {
//
//            //ListNode node = listNode.next;
//            head.next = listNode.next;
//            return head;
//        } else {
//            head.next = listNode;
//            return head;
//        }
    }


    /**
     * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode pointer1 = head;
        ListNode pointer2 = head.next;

        // 1 2 2 3 4 5
        while(pointer2 != null){
            if(pointer1.val == pointer2.val){
                //point2 = point2.next;
                while(pointer1.val==pointer2.val){
                    pointer1.next = pointer2.next;
                    pointer2 = pointer2.next;
                }
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
                // pointer1.next = pointer2.next;
                // pointer2 = pointer2.next;
                // pointer3 = pointer1;
            }else{
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        // 1 2 2 3 4
//        ListNode node5 = new ListNode(3, null);
//        ListNode node4 = new ListNode(2, node5);
//        ListNode node3 = new ListNode(1, node4);
//        ListNode node2 = new ListNode(1, node3);
//        ListNode node1 = new ListNode(1, node2);
//        ListNode node0 = new ListNode(-200, node1);

        ListNode node2 = new ListNode(1, null);
        ListNode node1 = new ListNode(1, node2);

        //ListNode sentinel = new ListNode(-200,node1);
        ListNode listNode = deleteDuplicates2Recursion(node1);
        listNode = listNode.next;
        System.out.println("------------------");

        System.out.println(listNode.val);
        System.out.println(listNode.next.val);
        System.out.println(listNode.next.next.val);
        System.out.println(listNode.next.next.next.val);
        System.out.println(listNode.next.next.next.next.val);
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
