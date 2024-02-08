package com.hcx.algorithm.base;

/**
 * @Title: BinaryCalculate.java
 * @Package com.hcx.algorithm.base
 * @Description: 二进制的加减乘除
 * @Author: hongcaixia
 * @Date: 2024/2/7 19:33
 * @Version V1.0
 */
public class BinaryCalculate {

    /**
     * 加法
     *     0 1 1 1
     *   + 1 1 1 0
     *   -------------
     *   1 0 1 0 1
     *
     *  1+0=1
     *  1+1=2 转为二进制 10 填0,往前进一位
     *  1+1+1(前面进的位)=3 转为二进制 11 填1,往前进一位
     *  0+1+1=2 转为二进制 10 填0,往前进一位
     */

    /**
     * 加法结果：
     * 使用^运算(^ : 两个位相同为0，相异为1)的到的结果就是无进位相加的结果
     *
     * 进位信息：
     * 如果两个都为1，那么就会有进位信息，用&运算，得到的就是1，再往左移动一位，结果就是进位信息
     *
     * 最终的结果就是让^运算的结果加上进位信息
     *
     * 得到的 ^运算结果 和 &运算结果左移 相加就是和，依次执行，直到进位信息没有了，就通过位运算实现了加法
     *
     */
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

    /**
     * 减法
     *     1 0 1 0 0 1
     *   - 0 1 1 0 1 0
     *   ----------------
     *     0 0 1 1 1 1
     *
     *  1-0=1
     *  0-1 不够减,往前借1(借到的是2), 2-1=1
     *  0-0 因为被借走了一位，所以0变成-1：-1-0，不够减，往前借1，2-1-0=1
     *  1-1 因为被借走了一位，所以1变成0：0-1，不够减，往前借1，2-1=1
     *  0-1 因为被借走了一位，所以0变成-1，-1-1，不够减，往前借1，2-1-1=0
     *  1-0 因为被借走了一位，所以1变成0，0-0=0
     */

    /**
     * 减法
     * a-b => a+(-b)
     * -b => ~b+1
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a, int b) {
        return add(a,add(~b,1));
    }


    /**
     * 乘法
     *          1 0 0 1
     *        * 1 1 0 1
     *    ---------------------
     *          1 0 0 1
     *        0 0 0 0
     *      1 0 0 1
     *    1 0 0 1
     *   -----------------------
     *    1 1 1 0 1 0 1
     */

    /**
     * 乘法
     * 当乘的位数是1的时候，还是原来的数字
     * 当乘的位数是0的时候，全部是0
     * 把全部的结果相加
     * 思路：判断乘数b的位数是不是1，通过让他和1进行&运算，如果是1，说明当前位是1。（当乘数是0的时候不用考虑，因为乘出来要加的还是0）
     * &完的结果如果是1，把被乘数a作为要相加的和，左移一位（下一次如果是1的和）。
     * b右移一位，让他来到下一位去乘。
     *
     * @param a
     * @param b
     * @return
     */
    public static int multiply(int a, int b) {
        int lastSum = 0;
        while (b != 0) {
            //b和1进行&运算，判断除要乘的这位是不是1
            if ((b & 1) == 1) {
                lastSum = add(lastSum, a);
            }
            // 111
            b = (b >>> 1);
            a = (a << 1);
        }
        return lastSum;
    }

    /**
     * 除法 100110/110
     *              0 0 0 1 1    商
     *             -------------
     * 除数b 1 1 0 ) 1 0 0 1 1 0           被除数a
     *                1 1 0                // 相当于b向左移动到不会超过a的位置b<=a 此位移动的次数i的位置上为1  a右移a>=b
     *              -----------            // b<<i  <= a
     *              0 0 1 1 1              // a = a-(b<<i)
     *                  1 1 0              // b向左移动到不会超过a的位置 此位商为1
     *                 ------------
     *                  0 0 1 0  余数      //
     *
     */
    /**
     *        0 0 0 0 0 0 0 0      0 0 0 0 0 0 0 0     0 0 0 0 0 0 0 0   0 0 1 0 0 1 1 0
     *        0 0 0 0 0 0 0 0      0 0 0 0 0 0 0 0     0 0 0 0 0 0 0 0   0 0 0 0 0 1 1 0
     *
     *        b左移两位 有小于a的 说明第2位上是1
     *        0 0 0 0 0 0 0 0      0 0 0 0 0 0 0 0     0 0 0 0 0 0 0 0   0 0 1 0 0 1 1 0
     *        0 0 0 0 0 0 0 0      0 0 0 0 0 0 0 0     0 0 0 0 0 0 0 0   0 0 0 1 1 0 0 0
     *
     *
     *
     */

    /**
     * 除法
     * @param a
     * @param b
     * @return
     */
    public static int divide(int a, int b) {
        int result = 0;
        for (int i = 30; i >= 0; i = minus(i, 1)) {
            if ((a >> i) >= b) {
                //说明第i位是1
                result = result | (1 << i);
                a = minus(a, b << i); //a = a-(b<<i);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int add = add(-6, 4);
        System.out.println(add);

        int minus = minus(-6, 4);
        System.out.println(minus);

        int a = -8;
        int b = 1;
        int multiply = multiply(a, b);
        System.out.println(multiply);

        int divide = divide(20, 2);
        System.out.println(divide);
    }











}
