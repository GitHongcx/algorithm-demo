package com.hcx.algorithm.linkedlist;

/**
 * @Title: RemoveElements.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: Leetcode203.移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * @Author: hongcaixia
 * @Date: 2025/1/3 19:37
 * @Version V1.0
 */
public class RemoveElements {

    /**
     * 移除链表元素
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(-1, head);

        /**
         * 因为不是双向链表，当指针指向了要删除的元素的时候，需要把上一个元素的next指向当前元素的next 所以需要两个指针
         * point2指向的元素是实际用来比较的，point1指向的元素是用来存储删除的上一个元素，让他指向删除的元素的后一个
         */
        ListNode pointer1 = sentinel;
        ListNode pointer2 = pointer1.next;

        // p1 p2
        // 1 2 2 3 4
        while (pointer2 != null) {
            if (pointer2.val == val) {
                // 要移除的节点
                pointer1.next = pointer2.next;
                // pointer2 往后移动 pointer1不需要变
                pointer2 = pointer2.next;
            } else {
                // 没找到 pointer1和pointer2同时后移
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
            }
        }
        return sentinel.next;
    }


    public static ListNode removeElements1(ListNode head, int val) {
        ListNode sentinel = new ListNode(-1, head);

        ListNode pointer1 = sentinel;
        ListNode pointer2 ;

        while ((pointer2= pointer1.next) != null) {
            if (pointer2.val == val) {
                // 要移除的节点
                pointer1.next = pointer2.next;
                // pointer2 往后移动 pointer1不需要变
            } else {
                // 没找到 pointer1和pointer2同时后移
                pointer1 = pointer1.next;
            }
        }
        return sentinel.next;
    }

    public static ListNode removeElementsRec(ListNode sentinel, int val, ListNode pointer1, ListNode pointer2) {
        if(pointer2==null){
            return sentinel.next;
        }
        if(pointer2.val==val){
            //要移除的元素
            pointer2 = pointer2.next;
            pointer1.next = pointer2;
        }else{
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        removeElementsRec(sentinel,val,pointer1,pointer2);
        return sentinel.next;
    }

    /**
     * 递归删除链表元素
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElementsRecurison(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode listNode = removeElementsRecurison(head.next, val);
        if (head.val == val) {
            // 不返回当前元素，返回上一个元素，就相当于跳过了当前元素
            // 这里的返回值是上一层的返回值，即不返回当前值（当前值为head指向的元素），返回上一层的值（上一层递归结束时返回的）
            return listNode;
        } else {
            // 更新当前节点的next listNode为上一层的结果 当前的元素为head指向的元素，让head指向上一层返回的结果，把链表连起来
            head.next = listNode;
            // 当前元素不需要删除，返回当前元素
            return head;
        }
    }

    public static void main(String[] args) {

        ListNode node5 = new ListNode(4, null);
        ListNode node4 = new ListNode(3, node5);
        ListNode node3 = new ListNode(2, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        System.out.println(node1.val);
        System.out.println(node1.next.val);
        System.out.println(node1.next.next.val);
        System.out.println(node1.next.next.next.val);
        System.out.println(node1.next.next.next.next.val);

//        ListNode node = removeElements1(node1, 2);
        ListNode sentinel = new ListNode(-1, node1);
        ListNode pointer1 = sentinel;
        ListNode pointer2 = pointer1.next;
        //ListNode node = removeElementsRec(sentinel, 2, pointer1, pointer2);
        ListNode node = removeElementsRecurison(node1,2);
        System.out.println("-===============-");

        System.out.println(node.val);
        System.out.println(node.next.val);
        System.out.println(node.next.next.val);
        //System.out.println(node.next.next.next.val);

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
