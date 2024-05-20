package com.hcx.algorithm.recursion;

/**
 * @Title: Sum.java
 * @Package com.hcx.algorithm.recursion
 * @Description: 求和
 * n + (n-1) + (n-2) + ... + 1
 * @Author: hongcaixia
 * @Date: 2024/5/19 17:34
 * @Version V1.0
 */
public class Sum {

    public static void main(String[] args) {
        //long sum = sumRecursion(15000);
        long sum = sum(100000000);
        System.out.println(sum);
    }

    /**
     * 递归 StackOverflowError
     * @param n
     * @return
     */
    public static long sumRecursion(long n) {
        if (n == 1) {
            return n;
        }
        return sumRecursion(n - 1) + n;
    }

    /**
     * 迭代
     * @param n
     * @return
     */
    public static long sum(long n){
        long sum = 0;
        for (long i = 0; i <= n; i++) {
            sum = sum + i;
        }
        return sum;
    }
}
