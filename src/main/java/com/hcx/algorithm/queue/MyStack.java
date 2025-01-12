package com.hcx.algorithm.queue;

import java.util.ArrayDeque;

/**
 * @Title: MyStack.java
 * @Package com.hcx.algorithm.queue
 * @Description: Leetcode225.用队列实现栈 用两个队列实现
 * @Author: hongcaixia
 * @Date: 2025/1/12 11:01
 * @Version V1.0
 */
public class MyStack {

    ArrayDeque<Integer> queue1 = new ArrayDeque(); // a  b  c     d
    ArrayDeque<Integer> queue2 = new ArrayDeque();
    // a b c    出到只剩下一个元素 就是需要弹栈的元素

    public void push(int x) {
        if (queue1.isEmpty()) {
            queue2.offer(x);
        } else {
            queue1.offer(x);
        }
    }

    // 哪个队列非空 就从对应的队列出队到剩下的队列，直到为空的最后一个元素就是栈顶元素
    public int pop() {
        int ele = 0;
        if (!queue1.isEmpty()) {
            while (!queue1.isEmpty()) {
                ele = queue1.poll();
                if (!queue1.isEmpty()) {
                    queue2.offer(ele);
                }
            }
        } else {
            while (!queue2.isEmpty()) {
                ele = queue2.poll();
                if (!queue2.isEmpty()) {
                    queue1.offer(ele);
                }
            }
        }
        return ele;
    }

    public int top() {
        int ele = 0;
        if (!queue1.isEmpty()) {
            while (!queue1.isEmpty()) {
                ele = queue1.poll();
                queue2.offer(ele);
            }
        } else {
            while (!queue2.isEmpty()) {
                ele = queue2.poll();
                queue1.offer(ele);
            }
        }
        return ele;
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

}
