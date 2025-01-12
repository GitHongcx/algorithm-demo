package com.hcx.algorithm.queue;

import java.util.ArrayDeque;

/**
 * @Title: MyStack.java
 * @Package com.hcx.algorithm.queue
 * @Description: Leetcode225.用队列实现栈 只使用一个队列实现
 * @Author: hongcaixia
 * @Date: 2025/1/12 11:01
 * @Version V1.0
 */
public class MyStack2 {

    ArrayDeque<Integer> queue = new ArrayDeque(); // a  b  c     d
    // a b
    // b a

    // 当前元素入队之后，为了保证该元素先出，就把之前的的元素全部出队，然后再入队，就实现当前加入的元素x在队头了，就可以实现先弹出了
    public void push(int x) {
        int size = queue.size();
        // 当前元素入队
        queue.offer(x);
        //之前的元素都出队 再加入到队尾
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }

    // 哪个队列非空 就从对应的队列出队到剩下的队列，直到为空的最后一个元素就是栈顶元素
    public int pop() {
        return queue.poll();
    }

    // 返回栈顶元素。
    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

}
