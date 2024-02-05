package com.hcx.algorithm.linkedlist;

/**
 * @Title: AddTwoLinkedList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 链表求和
 * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 * 输出：2 -> 1 -> 9，即912
 * @Author: hongcaixia
 * @Date: 2024/2/5 13:58
 * @Version V1.0
 */
public class AddTwoLinkedList {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //进位标志
        int carry = 0;

        //开头的节点
        ListNode head = null;

        //指向上一个节点
        ListNode last = null;

        while (l1 != null || l2 != null){
            int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            //该位置上的数
            int cur = val + carry;
            Integer remainder = null;
            if ((cur / 10) > 0) {
                //有进位 余数
                remainder = cur % 10;
            }
            //求和之后的新节点
            ListNode newNode = new ListNode(remainder != null ? remainder : cur);
            if (head == null) {
                //一开始的头节点
                head = newNode;
            } else {
                last.next = newNode;
            }
            last = newNode;
            //存储进位信息 给下一次相加使用
            carry = cur / 10;

            l1 = (l1 == null ? null : l1.next);
            l2 = (l2 == null ? null : l2.next);
        }

//        for (; l1 != null || l2 != null; l1 = (l1 == null ? null : l1.next), l2 = (l2 == null ? null : l2.next)) {
//            int val = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
//            //该位置上的数
//            int cur = val + carry;
//            Integer remainder = null;
//            if ((cur / 10) > 0) {
//                //有进位 余数
//                remainder = cur % 10;
//            }
//            //求和之后的新节点
//            ListNode newNode = new ListNode(remainder != null ? remainder : cur);
//            if (head == null) {
//                //一开始的头节点
//                head = newNode;
//            } else {
//                last.next = newNode;
//            }
//            last = newNode;
//            //存储进位信息 给下一次相加使用
//            carry = cur / 10;
//        }
        //循环结束了如果carry还有值，说明最后还进了一位，再增加一个节点
        if (carry == 1) {
            last.next = new ListNode(carry, null);
        }
        return head;
    }

    public static void main(String[] args) {

        //                             7->1->6
        ListNode listNode3 = new ListNode(6,null);
        ListNode listNode2 = new ListNode(1,listNode3);
        ListNode listNode1 = new ListNode(7,listNode2);

        // 5->9->2    295   312        5->9
        /**
         * 617
         *  95
         *------
         * 712
         */
//        ListNode listNode13 = new ListNode(2,null);
        ListNode listNode12 = new ListNode(9,null);
        ListNode listNode11 = new ListNode(5,listNode12);

        ListNode listNodeB = new ListNode(5,null);
        ListNode listNodeA = new ListNode(5,null);


        // 1+99
        /**
         *   99
         * +  1
         * --------
         *  100
         */
        ListNode listNodeThree = new ListNode(9,null);
        ListNode listNodeTwo = new ListNode(9,listNodeThree);

        ListNode listNodeOne = new ListNode(1,null);

        ListNode listNode = addTwoNumbers(listNodeOne, listNodeTwo);
        System.out.println(listNode);

    }


}
