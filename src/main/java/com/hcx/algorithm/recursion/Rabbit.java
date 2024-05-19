package com.hcx.algorithm.recursion;

/**
 * @Title: Rabbit.java
 * @Package com.hcx.algorithm.recursion
 * @Description: 兔子问题
 * 3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，求第n个月的兔子数
 * @Author: hongcaixia
 * @Date: 2024/5/19 16:13
 * @Version V1.0
 */
public class Rabbit {

    public static int sumRabbit(int month) {
        if (month <= 2) {
            return 1;
        }
        return sumRabbit(month - 1) + sumRabbit(month - 1);
    }

    public static void main(String[] args) {
        int i = sumRabbit(5);
        System.out.println(i);
    }
}
