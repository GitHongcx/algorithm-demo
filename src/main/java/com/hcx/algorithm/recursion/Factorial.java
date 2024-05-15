package com.hcx.algorithm.recursion;

/**
 * @Title: Recursion.java
 * @Package com.hcx.algorithm.recursion
 * @Description: 求阶乘
 * @Author: hongcaixia
 * @Date: 2024/5/15 21:40
 * @Version V1.0
 */
public class Factorial {

    public static int f(int n) {
        if (n == 1) {
            return 1;
        }
        return n * f(n - 1);
    }

    public static void main(String[] args) {
        int factorial = f(3);
        System.out.println(factorial);
    }

}
