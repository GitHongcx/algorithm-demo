package com.hcx.algorithm.stack;

/**
 * @Title: ValidCharacter.java
 * @Package com.hcx.algorithm.stack
 * @Description: Leetcode20.有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 1.左括号必须用相同类型的右括号闭合。
 * 2.左括号必须以正确的顺序闭合。
 * 3.每个右括号都有一个对应的相同类型的左括号。
 * @Author: hongcaixia
 * @Date: 2025/1/11 16:24
 * @Version V1.0
 */
public class ValidCharacter {

    public boolean isValid(String s) {
        java.util.Stack stack = new java.util.Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') {
                stack.push('}');
            }
            if (c == '[') {
                stack.push(']');
            }
            if (c == '(') {
                stack.push(')');
            }
            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = (char) stack.pop();
                if (c != pop) {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    public boolean isValid1(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0 || s.length() == 1) {
            return false;
        }
        java.util.Stack stack = new java.util.Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else { // 右括号
                // 当第一个就是右括号的时候，栈是空
                if (!stack.isEmpty() && c == (char) stack.peek()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0 || s.length() == 1) {
            return false;
        }
        java.util.Stack stack = new java.util.Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else { // 右括号
                // 当第一个就是右括号的时候，栈是空
                if (stack.isEmpty()) {
                    return false;
                }
                char s1 = (char) stack.pop();
                if (c != s1) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
