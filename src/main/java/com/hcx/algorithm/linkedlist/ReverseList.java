package com.hcx.algorithm.linkedlist;

import com.hcx.algorithm.recursion.RecursionDemo;

import java.util.List;

/**
 * @Title: ReverseList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 反转链表
 * @Author: hongcaixia
 * @Date: 2024/2/1 20:07
 * @Version V1.0
 */
public class ReverseList {

    /**
     * 反转单向链表：头插法
     * @param head
     * @return
     */
    public static Node reverseList1(Node head) {
        //新链表的头节点
        Node node = null;
        //遍历旧链表
        while (head != null) {
            //每次都创建一个新节点，放在新链表的头部
            node = new Node(head.value, node);
            //让旧链表的头指针往下移动一位
            head = head.next;
        }
        return node;
    }

    /**
     * 创建新的链表实现翻转
     * @param head
     * @return
     */
    public static ListNode reverseList0(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        ListNode pointer = head;

        while (pointer.next != null) {
            newHead = new ListNode(pointer.val,newHead);
            pointer = pointer.next;
        }
        return newHead;
    }

    /**
     * 不创建新节点，把旧链表的节点移动到新的链表
     * @param head
     * @return
     */
    public static ListNode reverseList01(ListNode head) {
        if (head == null) {
            return null;
        }
        // 新链表
        ListNode newHead = head;
        while (head.next != null) {
            // 当前元素的下一个元素 即 要移动的元素
            ListNode temp = head.next;
            head.next = temp.next;

            // 移动的元素的next指向新链表的头
            temp.next = newHead;
            // 新链表的头重新指向到当前移动的元素
            newHead = temp;
        }
        return newHead;
    }

    public static ListNode reverseList02(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //递归最后一层结束后，来到倒数第二调用的下一行 此时为倒数第二个元素
        ListNode last = reverseList02(head.next);
        // last.next.next: 最后一个元素的next
        // last：倒数第二个元素
        head.next.next = head;
        head.next = null;
        return last;
    }


    /**
     * 反转单向链表：双指针法
     * @param head
     * @return
     */
    public static Node reverseList2(Node head) {
        if(head==null || head.next==null){
            return head;
        }
        Node pre = null;
        while(head!=null){
            //把当前节点的后一个节点暂存到tempNext
            Node tempNext = head.next;
            head.next = pre;
            pre = head;
//            tempNext.next = pre;
            head = tempNext;
        }
        return pre;
    }

    /**
     * 反转单向链表：递归法
     * @param head
     * @return
     */
    public static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 反转双向链表
     * @param head
     * @return
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        while (head != null) {
            DoubleNode temp = head.next;

            head.next = pre;
            head.pre = temp;

            pre = head;
            head = temp;
        }
        return pre;
    }




    public static void main(String[] args) {

        ListNode node5 = new ListNode(4,null);
        ListNode node4 = new ListNode(3,node5);
        ListNode node3 = new ListNode(2,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);

        System.out.println(node1.val);
        System.out.println(node1.next.val);
        System.out.println(node1.next.next.val);
        System.out.println(node1.next.next.next.val);
        System.out.println(node1.next.next.next.next.val);

        ListNode node = null;
                //removeElements(node1, 2);
        System.out.println("-===============-");

        System.out.println(node.val);
        System.out.println(node.next.val);
        System.out.println(node.next.next.val);
        System.out.println(node.next.next.next.val);

        System.out.println("||||||||||||||||||||");

//        Node node1 = new Node(1);
//        node1.next = new Node(2);
//        node1.next.next = new Node(3);
//        Node node = reverseList(node1);
//        System.out.println(node.value);
        //System.out.println(node.value+" "+node.next.value+" "+node.next.next.value);
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
