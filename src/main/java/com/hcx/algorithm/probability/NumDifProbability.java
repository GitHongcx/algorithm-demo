package com.hcx.algorithm.probability;

/**
 * @Title: NumDifProbability.java
 * @Package com.hcx.algorithm.probability
 * @Description: 函数f()固定但是不等概率返回0和1  0：p  1：1-p
 * 写出一个函数g()等概率返回0和1
 * @Author: hongcaixia
 * @Date: 2024/1/28 15:10
 * @Version V1.0
 */
public class NumDifProbability {

    public static void main(String[] args) {
        int[] arr = new int[2];
        for (int i = 0; i < 1000000; i++) {
            int g = g(); // 0 1
            arr[g] ++;
        }
        for (int i = 0; i < 2; i++) {
            System.out.println(i+":"+arr[i]);
        }

        System.out.println("-------------------");

        int[] arr1 = new int[2];
        for (int i = 0; i < 1000000; i++) {
            int g = g1(); // 0 1
            arr1[g] ++;
        }
        for (int i = 0; i < 2; i++) {
            System.out.println(i+":"+arr1[i]);
        }
    }

    /**
     * 固定不等概率返回0和1
     * @return
     */
    public static int f() {
        return Math.random() > 0.7 ? 1 : 0;
    }

    /**
     * 等概率返回0和1
     * @return
     */
    public static int g(){
        int result  = 0;
        do{
            //01 10 等概率 1 3
            //00 11 重来
            result = (f()<<1) + f();
        }while (result == 0 || result == 3);
        return result == 1 ? 1:0;
    }

    public static int g1() {
        int result = 0;
        do {
            result = f();
        } while (result == f());
        return result;
    }


}
