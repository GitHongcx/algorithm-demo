package com.hcx.algorithm.recursion;

/**
 * @Title: Fibonacci.java
 * @Package com.hcx.algorithm.recursion
 * @Description: Leetcode509.斐波那契数
 * |1|1|2|3|5|8|13|21|34|55|89|144|
 * 这个数列从第3项开始，每一项都等于前两项之和。
 * @Author: hongcaixia
 * @Date: 2024/5/19 14:25
 * @Version V1.0
 */
public class Fibonacci {

    public static void main(String[] args) {
//        int[] fibonacci = fibonacci(12);
//        int[] fibonacci = fibonacciForRecursion(2,new int[12]);
//        for (int i = 0; i < fibonacci.length; i++) {
//            System.out.println(fibonacci[i]);
//        }
        int fibonacci = fib(30);
        System.out.println(fibonacci);

        //优化后的斐波那契

        int i = fibonacciImprove(30);
        System.out.println("优化之后的"+i);
    }

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] arr = new int[n+1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }

    /**
     * 迭代方式 生成斐波那契数列
     * @param n
     * @return
     */
    public static int[] fibonacci(int n) {
        int[] arr = new int[n];
        if (n == 0) {
            arr[0] = 0;
            return arr;
        }
        if (n == 1) {
            arr[0] = 0;
            arr[1] = 1;
            return arr;
        }
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    /**
     * 求第n项的值
     * @param n
     * @return
     */
    public static int f(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return f(n - 1) + f(n - 2);
    }

    /**
     * 递归方式
     * @param i
     * @param arr
     * @return
     */
    public static int[] fibonacciForRecursion(int i,int[] arr) {
        if (i == arr.length) {
            return arr;
        }
        arr[0] = 0;
        arr[1] = 1;
        arr[i] = arr[i - 1] + arr[i - 2];
        return fibonacciForRecursion(i + 1, arr);
    }


    public static int fibonacciImprove(int n) {
        int[] cache = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            cache[i] = -1;
        }
        cache[0] = 0;
        cache[1] = 1;
        return fibonacciRecursion(n, cache);
    }

    public static int fibonacciRecursion(int n, int[] cache) {
        //用数组缓存已经计算好的结果
        if (cache[n] != -1) {
            return cache[n];
        }
        int lastOne = fibonacciRecursion(n - 1, cache);
        int lastTwo = fibonacciRecursion(n - 2, cache);
        cache[n] = lastOne + lastTwo;
        return cache[n];
    }

}
