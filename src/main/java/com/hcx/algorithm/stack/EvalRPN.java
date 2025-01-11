package com.hcx.algorithm.stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Title: EvalRPN.java
 * @Package com.hcx.algorithm.stack
 * @Description: Leetcode150.逆波兰表达式求值
 * @Author: hongcaixia
 * @Date: 2025/1/11 18:03
 * @Version V1.0
 */
public class EvalRPN {

    /**
     * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
     * 请你计算该表达式。返回一个表示表达式值的整数。
     *
     * 输入：tokens = ["2","1","+","3","*"]
     * 输出：9
     * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                if (token.equals("+")) {
                    stack.push(num2 + num1);
                } else if (token.equals("-")) {
                    stack.push(num2 - num1);
                } else if (token.equals("*")) {
                    stack.push(num2 * num1);
                } else if (token.equals("/")) {
                    stack.push(num2 / num1);
                }
            } else {
                // 操作数 压入栈
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public int evalRPN1(String[] tokens) {
        LinkedList<Integer> numbers = new LinkedList<>();
        for (String t : tokens) {
            switch (t) {
                case "+" -> {
                    Integer b = numbers.pop();
                    Integer a = numbers.pop();
                    numbers.push(a + b);
                }
                case "-" -> {
                    Integer b = numbers.pop();
                    Integer a = numbers.pop();
                    numbers.push(a - b);
                }
                case "*" -> {
                    Integer b = numbers.pop();
                    Integer a = numbers.pop();
                    numbers.push(a * b);
                }
                case "/" -> {
                    Integer b = numbers.pop();
                    Integer a = numbers.pop();
                    numbers.push(a / b);
                }
                default -> numbers.push(Integer.parseInt(t));
            }
        }
        return numbers.pop();
    }
}
