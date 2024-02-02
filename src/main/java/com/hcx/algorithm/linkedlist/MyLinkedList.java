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
        this.value = value;
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
