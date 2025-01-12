package com.hcx.algorithm.stack;

/**
 * @Title: MyQueue.java
 * @Package com.hcx.algorithm.stack
 * @Description: Leetcode232.用栈实现队列
 * @Author: hongcaixia
 * @Date: 2025/1/12 10:23
 * @Version V1.0
 */
public class MyQueue {

    java.util.Stack<Integer> stack = new java.util.Stack();
    java.util.Stack<Integer> helpStack = new java.util.Stack();

    // 将元素 x 推到队列的末尾
    public void push(int x) {
        stack.push(x);
    }

    // 从队列的开头移除并返回元素
    public int pop() {
        if(helpStack.empty()){
            // 先从栈中弹出到辅助栈，再从辅助栈中弹出
            while(!stack.empty()){
                helpStack.push(stack.pop());
            }
        }
        return helpStack.pop();
    }

    // 返回队列开头的元素
    public int peek() {
        if(helpStack.empty()){
            // 先从栈中弹出到辅助栈，再从辅助栈中弹出
            while(!stack.empty()){
                helpStack.push(stack.pop());
            }
        }
        return helpStack.peek();
    }

    public boolean empty() {
        return stack.empty() && helpStack.empty();
    }
}
