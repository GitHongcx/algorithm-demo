package com.hcx.algorithm.recursion;

/**
 * @Title: ClimbStairs.java
 * @Package com.hcx.algorithm.recursion
 * @Description: Leetcode70.爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * @Author: hongcaixia
 * @Date: 2024/5/19 16:36
 * @Version V1.0
 */
public class ClimbStairs {

    /**
     * 超出时间限制
     *
     * @param n
     * @return
     */
    public int climbStairsForRecursion(int n) {
        if (n <= 2) {
            return n;
        }
        return climbStairs(n - 2) + climbStairs(n - 1);
    }

    /**
     * 迭代
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }


}
