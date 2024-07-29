package com.hcx.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: Hanota.java
 * @Package com.hcx.algorithm.recursion
 * @Description: Leetcode面试题08.06.汉诺塔问题
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 *
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。你需要原地修改栈。
 *
 * 示例1:
 *  输入：A = [2, 1, 0], B = [], C = []
 *  输出：C = [2, 1, 0]
 *
 *  示例2:
 *  输入：A = [1, 0], B = [], C = []
 *  输出：C = [1, 0]
 *
 *  A中盘子的数目不大于14个。
 *
 * @Author: hongcaixia
 * @Date: 2024/5/20 20:55
 * @Version V1.0
 */
public class Hanota {

    /**
     * 分析：
     *
     * @param A
     * @param B
     * @param C
     */
    private static void remove(List<Integer> A, List<Integer> B, List<Integer> C){
        //A中只有一个 A直接移动到C
        /**
         * A有1个：
         * A->C
         */
        C.add(A.remove(A.size()-1));

        /**
         * A有2个：A移动到C
         * A->B
         * A->C
         * B->C
         */
        remove(A,C,B);
        remove(A,B,C);
        remove(B,A,C);

        /**
         * A有3个：
         * A移动到B : A->B
         * A->C
         * A->B
         * C->B
         * ==== 前两个已经从A移动到了B（2个的情况）
         * A->C
         * ==== 第三个从A移动到了C（1个的情况）
         * B移动到C : B->C
         * B->A
         * B->C
         * A->C
         * ==== 前两个从B移动到了C（2个的情况）
         *
         * 把3个的情况简化成两个：
         * 前两个圆盘看做是一个：
         * A->B
         * A->C
         * B->C
         * 所以这个操作就是2个圆盘的操作
         *
         */


    }

    public static void honata(int count,List<Integer> A, List<Integer> B, List<Integer> C) {
        if (count == 0) {
            return;
        }
        honata(count - 1, A, C, B);
        C.add(A.remove(A.size() - 1));
        honata(count - 1, B, A, C);

    }

    /**
     * //        //A->B (前n-1个)
     * //        B.add(A.remove(A.size() - 1));
     * //
     * //        //A->C (第n个)
     * //        C.add(A.remove(A.size() - 1));
     * //
     * //        //B->C (前n-1个)
     * //        C.add(B.remove(B.size() - 1));
     * //
     * //
     * //        honata(count - 1, A, C, B);
     * //        honata(count - 1, A, B, C);
     * //        honata(count - 1, B, A, C);
     */



    /**
     * A:[1,0]
     * @param A
     * @param B
     * @param C
     */
    public static void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        remove(A.size(),A,B,C);
    }

    /**
     * 1个圆盘以上的操作
     * 规律：a->b  a->c  b->c
     * @param count
     * @param A
     * @param B
     * @param C
     */
    public static void remove1(int count, List<Integer> A, List<Integer> B, List<Integer> C) {

        // 3个圆盘的操作
        //前两个移动到B A->B  第n-1个圆盘的操作
//        C.add(A.remove(A.size() - 1));
//        B.add(A.remove(A.size() - 1));
//        B.add(C.remove(C.size() - 1));

        //第一步:A->B
        remove1(count - 1, A, B, C);
        remove1(count - 1, A, C, B);
        remove1(count - 1, C, A, B);

        //第3个移动到C A->C  第n个圆盘的操作
        C.add(A.remove(A.size() - 1));

        // 中间两个移动到C B->C 第n-1个圆盘的操作
//        A.add(B.remove(B.size()-1));
//        C.add(B.remove(B.size()-1));
//        C.add(A.remove(A.size()-1));

        remove1(count - 1, B, C, A);
        remove1(count - 1, B, A, C);
        remove1(count - 1, A, B, C);


    }

    public static void remove(int count, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (count == 0) {
            return;
        }
        // A->B  前n-1个
        // B.add(A.remove(A.size()));
        System.out.println("前面第n-1个递归开始");
        remove(count - 1, A, C, B);
        // A->C 第n个

        System.out.println("第n个递归开始");
        C.add(A.remove(A.size() - 1));

        // B->C 前n-1个
        //C.add(B.remove(B.size()-1));
        System.out.println("后面第n-1个递归开始");
        remove(count - 1, B, A, C);
        System.out.println("后面第n-1个递归结束");
    }

    public static void main(String[] args) {
        List<Integer> A  = new ArrayList<>(3);
        A.add(3);
        A.add(2);
        A.add(1);
        List<Integer> B  = new ArrayList<>(3);
        List<Integer> C  = new ArrayList<>(3);

//        List<Integer> A  = new ArrayList<>(2);
//        A.add(2);
//        A.add(1);
//        List<Integer> B  = new ArrayList<>(2);
//        List<Integer> C  = new ArrayList<>(2);


        //hanota(A,B,C);

        //hanota(A,B,C);
        honata(3,A,B,C);

        System.out.println(C);
    }
}
