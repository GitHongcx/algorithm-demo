package com.hcx.algorithm.daily;

/**
 * @Title: DecimalValue
 * @Package com.hcx.algorithm.daily
 * @Description: 1290. 二进制链表转整数
 * @Author: hongcaixia
 * @Date: 2025-07-14  10:22
 * @Version V1.0
 */
public class DecimalValue {

    public int getDecimalValue(ListNode head) {
        int res = 0;
        // 反转链表
        head = reverseList(head);
        ListNode pointer = head;

        int index = 0;
        while (pointer != null) {
            if (pointer.val == 1) {
                res += Math.pow(2, index);
            }
            index++;
            pointer = pointer.next;
        }
        return res;
    }

    public ListNode reverseList(ListNode head) {
        ListNode node = null;
        while (head != null) {
            node = new ListNode(head.val, node);
            head = head.next;
        }
        return node;
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
    }

    public void main(String[] args) {
        ListNode node9 = new ListNode(1,null);
        ListNode node8 = new ListNode(1,node9);
        ListNode node7 = new ListNode(1,node8);
        ListNode node6 = new ListNode(0,node7);
        ListNode node5 = new ListNode(0,node6);
        ListNode node4 = new ListNode(1,node5);
        ListNode node3 = new ListNode(0,node4);
        ListNode node2 = new ListNode(0,node3);
        ListNode node1 = new ListNode(1,node2);

        DecimalValue decimalValue = new DecimalValue();
        System.out.println(decimalValue.getDecimalValue(node1));
    }
}
