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

    public static ListNode reverseBetweenEle(ListNode head, int leftEle, int rightEle) {
        if(leftEle==rightEle){
            return head;
        }
        ListNode countLeftHead = head;
        ListNode countRightHead = head;
        if(head.next==null){
            return head;
        }
        int leftIndex = 0;
        int rightIndex = 0;

        while(countLeftHead.val != leftEle){
            countLeftHead = countLeftHead.next;
            if(countLeftHead==null){
                return head;
            }
            leftIndex++;
        }
        while(countRightHead.val != rightEle){
            countRightHead = countRightHead.next;
            if(countRightHead==null){
                return head;
            }
            rightIndex++;
        }

        if(leftIndex==rightIndex){
            return head;
        }


        //创建一个哨兵节点保存一开始的头节点
        ListNode dummy = new ListNode(-1,head);

        //要开始反转的节点的前一个节点
        ListNode preHead;

        //找到要翻转的起点 1->2->3->4->5->6
        for (int i = 0; i < leftIndex-1; i++) {
            head = head.next;
        }
        preHead = head;

        //一开始要反转的节点
        ListNode pre = preHead.next;
        ListNode cur = preHead.next.next;

        //要翻转的次数
        for (int j = 0; j < rightIndex-leftIndex; j++) {
            ListNode newHead = preHead.next;
            //暂存
            ListNode nextCur = cur.next;
            pre.next = nextCur;
            preHead.next = cur;
            cur.next = newHead;
            cur = nextCur;
        }
        return dummy.next;
    }


    public static ListNode reverseBetween1(ListNode head, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return head;
        }
        //创建一个哨兵节点保存一开始的头节点
        ListNode dummy = new ListNode(-1,head);

        //要开始反转的节点的前一个节点
        ListNode preHead;

        //找到要翻转的起点 1->2->3->4->5->6

        for (int i = 0; i < leftIndex-1; i++) {
            head = head.next;
        }
        preHead = head;

        //一开始要反转的节点
        ListNode pre = preHead.next;
        ListNode cur = preHead.next.next;

        //要翻转的次数
        for (int j = 0; j < rightIndex-leftIndex; j++) {
            ListNode newHead = preHead.next;
            //暂存
            ListNode nextCur = cur.next;
            pre.next = nextCur;
            preHead.next = cur;
            cur.next = newHead;

            cur = nextCur;

        }
        return dummy.next;
    }


    //                                                 2          4
    public static ListNode reverseBetween(ListNode head, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return head;
        }

        //创建一个哨兵节点保存一开始的头节点
        ListNode dummy = new ListNode(-1,head);

        //要开始反转的节点的前一个节点
        ListNode preHead;

        head = dummy;
        //找到要翻转的起点的前一个点 1->2->3->4->5->6
        for (int i = 0; i < leftIndex; i++) {
            head = head.next;
        }
        //要翻转的起点的前一个点
        preHead = head;

        ListNode pre;
        ListNode cur;

//        if (leftIndex == 0) {
//            //一开始的节点就是要翻转的节点
//            pre = preHead;
//            cur = preHead.next;
//        } else {

            //一开始要反转的节点
            pre = preHead.next;
            cur = preHead.next.next;
//        }

        if (cur == null) {
            //链表只有两个节点
            ListNode next = preHead.next;
            preHead.next = null;
            next.next = preHead;
            return next;
        }
        //要翻转的次数
        for (int j = 0; j < rightIndex-leftIndex; j++) {

            ListNode newHead = preHead.next;

            //暂存
            ListNode nextCur = cur.next;

            pre.next = nextCur;
            preHead.next = cur;
            cur.next = newHead;

            cur = nextCur;

//
//            //当前节点的后一个节点
//            ListNode next = cur.next;
//            //交换pre和cur
//            cur.next = pre;
//            pre.next = next;
//            preHead.next = cur;
//
//            //cur指针向后移动
//            cur = next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {

//        ListNode listNode8 = new ListNode(8,null);
//        ListNode listNode7 = new ListNode(7,listNode8);
//        ListNode listNode6 = new ListNode(6,listNode7);

        ListNode listNode5 = new ListNode(5,null);
        ListNode listNode4 = new ListNode(4,listNode5);
        ListNode listNode3 = new ListNode(3,listNode4);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);

//        ListNode listNode5 = new ListNode(5,null);
//        ListNode listNode3 = new ListNode(3,listNode5);

        System.out.println(listNode1);
//        reverseBetween(listNode1,2,4);
        ListNode listNode = reverseBetween(listNode1, 1, 3);
        //1 2 6 5 4 3 7 8
        System.out.println(listNode);
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


    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
