package com.hcx.algorithm.bitmap;


/**
 * @Title: MyBitMap.java
 * @Package com.hcx.algorithm.bitmap
 * @Description: 实现一个位图
 * @Author: hongcaixia
 * @Date: 2024/2/7 11:43
 * @Version V1.0
 */
public class MyBitMap {

    private long[] bit;

    /**
     * 有参构造器
     *
     * @param max 想要表示的最大值
     */
    public MyBitMap(int max) {
        //要加上64的原因：小于64的时候，也需要有一位里来存数据
        // bit = new long[(max+64)/64];
        //使用位运算：
        bit = new long[(max + 64) >> 6];
    }

    /**
     * 添加元素
     * 让对应的位置变为1
     *
     * @param num
     */
    public void add(int num) {
        /** num = 200
         * 1.找到num在数组中的下标：num/64 即 num >> 6  200/64=3 bit[3]
         * 2.找到对应下标中的哪一位(一个long占64位)：num%64 即 num & 63  200%64=8 第8位
         * 3.让这一位的数字变为1：
         *   用1左移 num%64 位 （对应要改的下标是1）
         * ｜原本的值
         * --------------------
         * 运算结果就可以让对应的下标变为1了
         * | : 两个位都为0时，结果才为0 （有一个为1就是1）
         */
        bit[num >> 6] |= (1L << (num & 63));
    }


    /**
     * 删除元素
     * 让对应的位置变为0
     *
     * @param num
     */
    public void delete(int num) {
        /**
         *  1.找到num在数组中的下标：num/64 即 num >> 6  200/64=3 bit[3]
         *  2.找到对应下标中的哪一位(一个long占64位)：num%64 即 num & 63  200%64=8 第8位
         *  3.让这一位的数字变为0
         *   用1左移 num%64 位
         * ～对应位置变成了0
         * & 原本的值
         * ----------------------
         * 运算结果就可以让对应的下标变为0了
         */
        bit[num >> 6] &= ~(1L << (num & 63));
    }

    /**
     * 查询是否包含该元素
     * 查看对应位置是否为1
     *
     * @param num
     * @return
     */
    public boolean contains(int num) {
        /**
         * 1.找到num在数组中的下标：num/64 即 num >> 6  200/64=3  bit[3]
         * 2.找到对应下标中的哪一位(一个long占64位)：num%64 即 num & 63  200%64=8  第8位
         * 3.检查下标3中的数字的第8位是0还是1
         * 用原本的数字&上一个第8位是1的(1L<< (num & 63))，如果结果还是1，说明原本是1，否则是0 (& : 两个位都为1时，结果才为1)
         *   原本的值
         * & 1L左移8(num & 63)位
         * --------------------
         * 如果原本是0 那么&完结果就是0，否则就存在
         */
        return (bit[num >> 6] & (1L << (num & 63))) != 0;
    }


}
