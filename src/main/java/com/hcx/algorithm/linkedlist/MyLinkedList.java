package com.hcx.algorithm.linkedlist;

/**
 * @Title: MyLinkedList.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: 链表
 * @Author: hongcaixia
 * @Date: 2024/1/31 16:14
 * @Version V1.0
 */
public class MyLinkedList {

    public static void main(String[] args) {
        int a = 3;
        String str = "abc";
        m3(a);
        m4(str);
        //System.out.println(str);
        //System.out.println(a);

        //System.out.println("-----");
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);
        //System.out.println(node1.value);

        m1(node1);
        System.out.println(node1);
        System.out.println("m1"+node1.value);
        node1 = m2(node1);
        System.out.println("m2"+node1.value);
        System.out.println(node1);


    }

    public static void m1(Node node){
        node = node.next;
    }

    public static Node m2(Node node){
        System.out.println("nodeNext: "+node.next);
        return node.next;
    }

    public static void m3(int a){
        a = 4;
    }

    public static void m4(String str){
        str = "abcd";
    }

}

class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.next = next;
    }
}

class DoubleNode {
    public int value;
    public DoubleNode pre;
    public DoubleNode next;

    public DoubleNode(int value) {
        this.value = value;
    }

    public DoubleNode(int value, DoubleNode pre,DoubleNode next) {
        this.pre = pre;
        this.next = next;
    }
}

class Solution {

    /**
     * 反转单向链表
     * @param head
     * @return
     */
    public static Node reverseList(Node head) {
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
        System.out.println(node.value+" "+node.next.value+" "+node.next.next.value);
    }
}
