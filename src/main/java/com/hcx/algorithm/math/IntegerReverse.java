package com.hcx.algorithm.math;

/**
 * @Title: IntegerReverse.java
 * @Package com.hcx.algorithm.math
 * @Description: leetcode7.整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * @Author: hongcaixia
 * @Date: 2026/3/12 09:28
 * @Version V1.0
 */
public class IntegerReverse {

    public static int reverse(int x) {
        long res = 0;
        while (x != 0) {
            // 取个位
            int a = x % 10;
            res = res * 10 + a;
            x = x / 10;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) res;
    }

    public static int reverse1(int x) {
        int res = 0;
        while(x!=0){
            // 个位
            int a = x % 10;
            // 正数溢出  32位有符号整数的取值范围是 -2³¹ 到 2³¹-1，即 -2147483648 到 2147483647的最末位数字 8 -7
            if(res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && a > 7)){
                return 0;
            }
            if(res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE/10 && a < -8)){
                return 0;
            }
            res = res * 10 + a;
            // 前进一位
            x = x / 10;
        }
        return res;
    }

    public static void main(String[] args) {
        int reverse = reverse(-832);
        System.out.println(reverse);
    }

}
