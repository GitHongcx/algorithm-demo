package com.hcx.algorithm.recursion;

/**
 * @Title: Factorial.java
 * @Package com.hcx.algorithm.recursion
 * @Description: 求阶乘
 * @Author: hongcaixia
 * @Date: 2024/5/15 13:09
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
