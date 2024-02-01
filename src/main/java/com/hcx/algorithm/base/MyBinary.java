package com.hcx.algorithm.base;

/**
 * @Title: MyBinary.java
 * @Package main.java.com.hcx.algorithm.base
 * @Description: 获取一个数的二进制
 * num左移一位 = num*2
 * &：有一个是1 就是1
 * | :
 * ^ :
 * >>:带符号右移（用符号位补）
 * >>>:不带符号右移（用0补）
 * 负数：取反+1
 * @Author: hongcaixia
 * @Date: 2024/1/25 09:57
 * @Version V1.0
 */
public class MyBinary {

    public static void main(String[] args) {
        int num = 3;
        getNumBinary(num);
        int a = 5;
        int b = -a;
        int c = (~a + 1); // 负数：取反+1
        System.out.println();
        System.out.println(b);
        System.out.println(c);
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
