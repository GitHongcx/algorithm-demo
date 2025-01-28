package com.hcx.algorithm.sort;

/**
 * @Title: InsertSort.java
 * @Package main.java.com.hcx.algorithm.base.sort
 * @Description: 插入排序
 * [8,3,1,0,2,9,7,4,6]
 * 0~1:保证有序，1往前找，小就往前进
 * 0～2：保证有序，2一直往前找，小就往前交换，否则就停止
 * 0～3：保证有序，3一直往前找，小就往前交换，否则就停止
 * ···
 * @Author: hongcaixia
 * @Date: 2024/1/27 11:58
 * @Version V1.0
 */
public class InsertSort {

    public static void main(String[] args) {
        //int[] arr = {5,8,1,0,3,2};
        int[] arr = {9, 3, 7, 2, 5, 8, 1, 4, 6};
        insertSort(arr);
        System.out.println(arr);
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertSort(int[] arr) {
        // low为未排序区域的左边界，依次递增 ，取出来，插入到已排序区域
        for (int low = 1; low < arr.length; low++) {
            // 存入到临时变量中
            int t = arr[low];
            // 依次与左侧的已排序区域的元素比较，如果比左侧区域的小，就把左侧的区域往右移动，空出位置给t插入
            int i = low - 1;
            // i还在有效范围内， i所在元素值比t大，就把元素依次往右移动，空出位置
            while (i >= 0 && arr[i] > t) {
                arr[i + 1] = arr[i];
                i--;
            }
            // 如果左侧的都比当前的t要小 说明t已经是在正确的位置了
            if(i != low-1){
                //i+1的位置就是空出的位置
                arr[i + 1] = t;
            }
        }
    }


    /**
     * 插入排序
     * @param arr
     */
    public static void insert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //要插入的元素
            int current = arr[i];
            //要比较的索引
            int j = i - 1;
            //寻找比当前元素小的元素的位置
            while (j >= 0 && arr[j] > current) {
                //依次空出一个位置
                arr[j + 1] = arr[j];
                j--;
            }
            //要插入的位置
            arr[j + 1] = current;
        }
    }

    /**
     * 递归插入排序
     * @param arr
     * @param insertEleIndex 要开始准备插入的元素的索引
     */
    public static void insertRecursion(int[] arr,int insertEleIndex) {
        if (insertEleIndex == arr.length) {
            return;
        }
        //要插入的元素
        int current = arr[insertEleIndex];
        //要比较的索引
        int j = insertEleIndex - 1;
        //寻找比当前元素小的元素的位置
        while (j >= 0 && arr[j] > current) {
            //依次空出一个位置
            arr[j + 1] = arr[j];
            j--;
        }
        //要插入的位置
        arr[j + 1] = current;
        insertRecursion(arr, insertEleIndex + 1);
    }


    public static void myInsertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j>=0 && arr[j]>arr[j+1]; j--) {
                int temp = arr[j+1];
                arr[j+1] = arr[j];
                arr[j] = temp;
            }
        }
    }

    public static void myInsertSort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            while (i>0 && arr[i-1]>arr[i]){
                int temp = arr[i-1];
                arr[i-1] = arr[i];
                arr[i] = temp;
                i--;
            }
        }
    }

    public static void myInsertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j-1] > arr[j]) {
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                } else {
                    break;
                }
            }
        }
    }


}
