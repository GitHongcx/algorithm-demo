package com.hcx.algorithm.sort;

/**
 * @Title: SelectSort.java
 * @Package main.java.com.hcx.algorithm.base.sort
 * @Description: 选择排序 可能跨过了几个数字才做交换
 * 0～n：选择最小的和索引为0的交换
 * 1～n：选择最小的和索引为1的交换
 * 2～n：选择最小的和索引为2的交换
 * ···
 * n-1 ～ n：选择最小的和索引为n的交换
 *
 * @Author: hongcaixia
 * @Date: 2024/1/25 12:56
 * @Version V1.0
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {5,8,1,0,3,2};

        select1(arr);

        System.out.println(arr);
    }

    public static void select1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                //找出最小值索引
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            //最小值索引跟最前面一个交换
            int temp = arr[minValueIndex];
            arr[minValueIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void select(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i]>arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
}
