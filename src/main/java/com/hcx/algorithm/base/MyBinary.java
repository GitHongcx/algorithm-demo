package com.hcx.algorithm.base;

/**
 * @Title: MyBinary.java
 * @Package main.java.com.hcx.algorithm.base
 * @Description: 获取一个数的二进制
 * num左移n位 = num*2^n
 * num右移n位 ：num/2^n
 *
 * num%n 等价于 num&(n-1)  这里的n必须是2的次幂
 *
 *
 * & : 两个位都为1时，结果才为1 （有一个为0就是0）
 * | : 两个位都为0时，结果才为0 （有一个为1就是1）
 * ^ : 两个位相同为0，相异为1
 * >>: 带符号右移（用符号位补）
 * >>>:不带符号右移（用0补）
 *
 * 负数：取反+1
 * @Author: hongcaixia
 * @Date: 2024/1/25 09:57
 * @Version V1.0
 */
public class MyBinary {

    public static void main(String[] args) {
        int num1 = 3;
        getNumBinary(num1);
        int a = 5;
        int b = -a;
        int c = (~a + 1); // 负数：取反+1
        System.out.println();
        System.out.println(b);
        System.out.println(c);

        System.out.println("=============");
        int num = 10;
        // num左移n位 = num*2^n
        System.out.println(num<<2);//40
        System.out.println(num*4);//40

        // num右移n位 ：num/2^n
        System.out.println(num>>2);//2
        System.out.println(num/4);//2

        System.out.println("----");
        // num%n 等价于 num&(n-1)
        System.out.println(num % 4);
        System.out.println(num & 3);

        System.out.println(9 % 4);
        System.out.println(9 & 3);

    }

    public static int getNumBinary(int num){
        StringBuilder sb = new StringBuilder();

        for (int i = 31; i >=0; i--) {
            //1000 0000 0000 0000 0000 0000 0000 0000
            //0000 0000 0000 0000 0000 0000 0000 0010
            String result = (num & (1<<i))==0?"0":"1";

            System.out.print(result);
        }
        return 0;
    }

}
