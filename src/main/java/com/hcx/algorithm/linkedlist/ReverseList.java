package com.hcx.algorithm.linkedlist;

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
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);
        Node node = reverseList(node1);
        System.out.println(node.value);
        //System.out.println(node.value+" "+node.next.value+" "+node.next.next.value);
    }


}
