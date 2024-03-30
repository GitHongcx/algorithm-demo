package com.hcx.algorithm.base;

/**
 * @Title: MyFactorial.java
 * @Package main.java.com.hcx.algorithm.base
 * @Description: 阶乘
 * 1!+2!+3!+·····+N！
 * @Author: hongcaixia
 * @Date: 2024/1/25 11:03
 * @Version V1.0
 */
public class MyFactorial {

    public static void main(String[] args) {
        int num = 10;
        int factorial = factorial(num);
        System.out.println(factorial);
        System.out.println("==============");
        int mul = mul(5);
        System.out.println(mul);

    }

    public static int factorial(int num){
        int cur = 1;
        int addResult = 0;
        for (int i = 1; i <= num; i++) {
            cur = cur * i;
            addResult = addResult + cur;
            /**
             * 1!=1      1
             * 2!=1!*2   2
             * 3!=2!*3   6
             * 4!=3!*4   24
             * ....
             * N!=(N-1)!*N
             */
        }
        return addResult;
    }

    public static int mul(int n){
        if(n==0){
            return 1;
        }else{
            return mul(n-1)*n;
        }
    }
}
