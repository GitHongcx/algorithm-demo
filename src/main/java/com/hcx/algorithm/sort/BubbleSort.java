package com.hcx.algorithm.sort;

/**
 * @Title: BubbleSort.java
 * @Package main.java.com.hcx.algorithm.base.sort
 * @Description: 冒泡排序 每一次都进行交换
 * [8,5,1,9,2,0,4,5]
 * 0～7：索引0和索引1比较，如果前者大，则交换；索引1和索引2比较，大的交换放到后面，最终最大值去到索引7的位置
 * 0～6：同样的操作，第二大的去到索引6的位置
 * ···
 * @Author: hongcaixia
 * @Date: 2024/1/27 11:16
 * @Version V1.0
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5,8,1,0,3,2};
        bubbleSortRecursion(arr,0);
        System.out.println(arr);
        System.out.println("******");

        myBubbleSort(arr);
        System.out.println(arr);
    }

    /**
     * 递归冒泡
     * @param arr
     * @param right
     */
    public static void bubbleRecursion(int[] arr,int right) {
        if (right == 0) {
            return;
        }
        for (int i = 0; i < right; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
            }
        }
        bubbleRecursion(arr, right - 1);
    }

    /**
     * 递归冒泡优化
     * @param arr
     * @param right
     */
    public static void bubbleRecursion1(int[] arr,int right) {
        if (right == 0) {
            return;
        }
        int flag = 0;
        for (int i = 0; i < right; i++) {
            if (arr[i] > arr[i + 1]) {
                swap(arr, i, i + 1);
                flag = i;
            }
        }
        bubbleRecursion1(arr, flag);
    }

    public static void bubbleSortRecursion(int[] arr,int i) {
        if (i == arr.length) {
            return;
        }
        for (int j = 0; j < arr.length - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                swap(arr, j, j + 1);
            }
        }
        bubbleSortRecursion(arr,i+1);
    }

    public static void bubbleSort1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1-i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void myBubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length-1; j >0; j--) {
                if(arr[j]<arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

    public static void swap(int[] arr,int a ,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
