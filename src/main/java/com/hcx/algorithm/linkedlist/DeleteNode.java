package com.hcx.algorithm.linkedlist;

/**
 * @Title: DeleteNode.java
 * @Package com.hcx.algorithm.linkedlist
 * @Description: Leetcode237.删除链表中的节点
 * 有一个单链表的 head，我们想删除它其中的一个节点 node。
 * 给你一个需要删除的节点 node 。你将 无法访问 第一个节点  head。
 * 链表的所有值都是 唯一的，并且保证给定的节点 node 不是链表中的最后一个节点。
 * @Author: hongcaixia
 * @Date: 2025/1/10 12:01
 * @Version V1.0
 */
public class DeleteNode {

    /**
     * 删除链表中的节点
     * 把要删除的节点和下一个节点值交换（因为是唯一的），那么下一个节点就变成了要删除的节点
     * @param node
     */
    public void deleteNode(ListNode node) {
        // 下一个节点值赋值给待删除节点
        node.val = node.next.val;
        // 把下一个节点删除
        node.next = node.next.next;
    }


}
