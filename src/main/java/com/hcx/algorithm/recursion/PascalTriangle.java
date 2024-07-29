package com.hcx.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: PascalTriangle.java
 * @Package com.hcx.algorithm.recursion
 * @Description: 杨辉三角
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * 输入: numRows = 1
 * 输出: [[1]]
 *
 * @Author: hongcaixia
 * @Date: 2024/7/28 13:46
 * @Version V1.0
 */
public class PascalTriangle {

    /**
     * numRows = 5
     * [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        /**
         * 1 [[1]]
         * 2 [[1],[1,1]]
         * 3 [[1],[1,1],[1,2,1]]
         * 4 [[1],[1,1],[1,2,1],[1,3,3,1]]
         * 5 [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
         */
        List<List<Integer>> lists = new ArrayList<>();
        generate(1,numRows,lists);
        return lists;
    }

    /**
     * 求第i行第j列元素
     * @param i
     * @param j
     * @return
     */
    public static int element(int i,int j) {
        //每一行的第一个(第0列)和最后一个都是1
        if (j == 0 || i == j) {
            return 1;
        }
        return element(i - 1, j - 1) + element(i - 1, j);
    }



    /**
     * 递归优化
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generateRecursionCache(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        //行初始化为numRows
        int[][] arr = new int[numRows][];

        for (int i = 0; i < numRows; i++) {
            //i行的列初始化为i+1
            arr[i] = new int[i + 1];

            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                //第i行第j列元素
                int element = elementCache(i, j, arr);
                list.add(element);
            }
            lists.add(list);
        }
        return lists;
    }

    /**
     * 求第i行第j列元素 使用记忆法优化
     * @param i
     * @param j
     * @return
     */
    public static int elementCache(int i,int j,int[][] arr) {
        if (arr[i][j] != 0) {
            return arr[i][j];
        }
        //每一行的第一个(第0列)和最后一个都是1
        if (j == 0 || i == j) {
            arr[i][j] = 1;
            return 1;
        }
        arr[i][j] = elementCache(i - 1, j - 1, arr) + elementCache(i - 1, j, arr);
        return arr[i][j];
    }

    /**
     * 递归
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generateRecursion(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                //第i行第j列元素
                int element = element(i, j);
                list.add(element);
            }
            lists.add(list);
        }
        return lists;
    }



    /**
     * 迭代
     * @param startRow
     * @param numRows
     * @param lists
     */
    public static void generate(int startRow,int numRows,List<List<Integer>> lists) {
        if (startRow == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            lists.add(list);
        }

        if(numRows==1){
            return;
        }

        if (startRow == numRows) {
            return;
        }

        List<Integer> list = new ArrayList<>();
        //每一行的第一个和最后一个元素都是1
        list.add(1);
        int cycle = startRow - 1;
        if (cycle > 0) {
            //当前下标1元素来源于上一行的下标0和下标1的和 这里的循环是循环中间一共有多少个元素
            for (int i = 0; i < cycle; i++) {
                //上一行的元素 [1,2,1]
                List<Integer> list1 = lists.get(lists.size() - 1);
                //依次求和，0+1 1+2 2+3
                list.add(list1.get(i) + list1.get(i + 1));
            }
        }

        //当前下标2元素来源于上一行的下标1和小标2的和。
        list.add(1);
        lists.add(list);

        startRow++;
        generate(startRow, numRows, lists);
    }

    /**
     * 迭代
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generateIterate(int numRows) {
        List<List<Integer>> lists = new ArrayList<>();

        List<Integer> listFirst = new ArrayList<>();
        listFirst.add(1);
        lists.add(listFirst);

        if (numRows == 1) {
            return lists;
        }

        for (int i = 0; i < numRows-1; i++) { //行
            List<Integer> list = new ArrayList<>();
            //每一行的第一个和最后一个元素都是1
            list.add(1);
            if (i > 0) {
                //当前下标1元素来源于上一行的下标0和下标1的和 这里的循环是循环中间一共有多少个元素
                for (int j = 0; j < i; j++) { //列
                    //上一行的元素 [1,2,1]
                    List<Integer> list1 = lists.get(lists.size() - 1);
                    //依次求和，0+1 1+2 2+3
                    list.add(list1.get(j) + list1.get(j + 1));
                }
            }
            //当前下标2元素来源于上一行的下标1和小标2的和。
            list.add(1);
            lists.add(list);
        }
        return lists;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = generateRecursionCache(5);
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> list = lists.get(i);

            for (int j = 0; j < list.size(); j++) {
                System.out.println(list.get(j));
            }
        }
        System.out.println("---------------------");


        List<List<Integer>> generate = generate(5);

        for (int i = 0; i < generate.size(); i++) {
            List<Integer> list = generate.get(i);

            for (int j = 0; j < list.size(); j++) {
                System.out.println(list.get(j));
            }
        }

        System.out.println("====================");

        List<List<Integer>> lists1 = generateIterate(5);

        for (int i = 0; i < lists1.size(); i++) {
            List<Integer> list = lists1.get(i);

            for (int j = 0; j < list.size(); j++) {
                System.out.println(list.get(j));
            }
        }


    }



}
