package com.hcx.algorithm.base;

/**
 * @Title: Test.java
 * @Package com.hcx.algorithm.base
 * @Description: (用一句话描述该文件做什么)
 * @Author: hongcaixia
 * @Date: 2024/2/8 18:33
 * @Version V1.0
 */
public class Test {

    public static void main(String[] args) {
        int multiply = multiply(-8, -7);
        System.out.println(multiply);
    }

    public static int multiply(int a, int b) {
        int lastSum = 0;
        while (b != 0) {
            //b和1进行&运算，判断除要乘的这位是不是1
            if ((b & 1) == 1) {
                lastSum = add(lastSum, a);
            }
            b = (b >>> 1);
            a = (a << 1);
        }
        return lastSum;
    }

    public static int add(int a, int b) {
        //和
        int sum = 0;
        //进位信息
        int carry;
        while (b != 0) {
            //无进位相加
            sum = a ^ b;
            //进位信息
            carry = (a & b) << 1;
            b = carry;
            a = sum;
        }
        return sum;
    }
}
