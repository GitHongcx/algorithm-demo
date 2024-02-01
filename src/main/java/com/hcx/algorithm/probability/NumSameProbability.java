package com.hcx.algorithm.probability;

/**
 * @Title: NumSameProbability.java
 * @Package com.hcx.algorithm.base.probability
 * @Description: 编写一个可以等概率得到1～7的函数g
 * 前提：f()可以等概率得到 1 2 3 4 5
 * 只利用f()等概率得到 1 2 3 4 5 6 7
 * @Author: hongcaixia
 * @Date: 2024/1/28 14:00
 * @Version V1.0
 */
public class NumSameProbability {

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println((int)(Math.random()*10));
//        }

        System.out.println("=========");
        int count = 0;
        int[] result = new int[10];
        for (int i = 0; i < 1000000; i++) {
            int f = f();
            result[f]++;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("数组的第"+i+"位是："+result[i]);
        }
        System.out.println(count);

        System.out.println("~~~~~~~~~~~~");

        int[] result1 = new int[10];
        for (int i = 0; i < 1000000; i++) {
            int h = h();
            result1[h]++;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("数组的第"+i+"位是："+result1[i]);
        }

        System.out.println("###############");

        int[] result2 = new int[8];
        for (int i = 0; i < 1000000; i++) {
            int g = g();
            result2[g]++;
        }
        for (int i = 1; i < 8; i++) {
            System.out.println("数组的第"+i+"位是："+result2[i]);
        }

    }

    /**
     * 等概率的到1～7的数字
     * 000 = 0
     * 111 = 7
     * @return
     */
    public static int g(){
        //等概率的到0～7
        int res;
        do{
            res = (h()<<2)+(h()<<1)+h();
        }while (res == 0);
        return res;
    }

    /**
     * 等概率的到0和1
     * 1 2  0
     * 4 5  1
     * 3    重来
     * @return
     */
    public static int h() {
        int result;
        do {
            result = f();
        } while (result == 3);
        return result > 3?1:0;
    }

    /**
     * 等概率得到1～5的函数
     * @return
     */
    public static int f(){
        //Math.random():[0,1)
        //[0,5)
        //[1,6) [1,5]
        return (int)((Math.random()*5)+1);
    }


}
