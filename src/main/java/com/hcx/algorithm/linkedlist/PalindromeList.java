package com.hcx.algorithm.linkedlist;


/**
 * @Title: PalindromeList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: Leetcode234.回文链表
 * @Author: hongcaixia
 * @Date: 2025/1/8 17:03
 * @Version V1.0
 */
public class PalindromeList {

    /**
     * 判断是否是回文链表
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        // 快慢指针找到中间节点，把中间节点往后的链表进行翻转，然后跟原本的链表一一对比
        ListNode mid = getMid(head);
        // 把中间节点往后的链表进行翻转
        ListNode reverse = reverse(mid);

        ListNode pointer1 = head;
        ListNode pointer2 = reverse;

        while (pointer2 != null) {
            if (pointer1.val != pointer2.val) {
                return false;
            }
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return true;
    }


    /**
     * 判断是否是回文链表【优化】
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        // 找到中间节点的过程同时把前半部分的链表翻转
        ListNode slow = head;
        ListNode fast = head;

        ListNode newHead = null;
        ListNode oldHead = head;

        // 寻找中间节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 翻转链表
            ListNode oldNext = oldHead.next;
            oldHead.next = newHead;
            newHead = oldHead;
            oldHead = oldNext;
        }
        // 循环结束时，此时slow指向的是中间节点 newHead为前半个已经翻转好了的链表
        if (fast != null) {
            //奇数个节点
            slow = slow.next;
        }

        ListNode pointer1 = newHead; // 前半截已经翻转好了的链表
        ListNode pointer2 = slow; //后半截链表

        while (pointer2 != null) {
            if (pointer1.val != pointer2.val) {
                return false;
            }
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return true;
    }

    /**
     * 获取中间节点
     * @param head
     * @return
     */
    public static ListNode getMid(ListNode head){
        if (head.next == null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 翻转链表 1 2 3 4 5 null
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if(head.next == null){
            return head;
        }
        //当前head指向了4  每一轮归的都是5
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }


    // 1 2 2 1
    public static ListNode isPalindromeMethod(ListNode head,ListNode start){
        if(head.next==null){
            return head;
        }
        ListNode end = isPalindromeMethod(head.next,start);
        if(end.val == start.val || end.val == head.val){
            start = start.next;
            return start;
        }else{
            return new ListNode(-1,null);
        }
    }

    public static void main(String[] args) {
        // 1 2 2 3 4
//        ListNode node7 = new ListNode(5, null);
//        ListNode node6 = new ListNode(4, node7);
//        ListNode node5 = new ListNode(4, node6);
//        ListNode node4 = new ListNode(3, node5);
//        ListNode node3 = new ListNode(3, node4);
//        ListNode node2 = new ListNode(2, node3);
//        ListNode node1 = new ListNode(1, node2);
//        ListNode node0 = new ListNode(-200, node1);

        ListNode node5 = new ListNode(1, null);
        ListNode node4 = new ListNode(2, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        boolean palindrome = isPalindrome2(node1);
        System.out.println(palindrome);

        //ListNode sentinel = new ListNode(-200,node1);
//        boolean palindrome = isPalindrome(node1);
//        System.out.println(palindrome);
//        System.out.println("------------------");

//        System.out.println(listNode.val);
//        System.out.println(listNode.next.val);
//        System.out.println(listNode.next.next.val);
//        System.out.println(listNode.next.next.next.val);
//        System.out.println(listNode.next.next.next.next.val);
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
