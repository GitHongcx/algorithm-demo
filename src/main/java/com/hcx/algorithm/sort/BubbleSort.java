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
        myBubbleSort(arr);
        System.out.println(arr);
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
