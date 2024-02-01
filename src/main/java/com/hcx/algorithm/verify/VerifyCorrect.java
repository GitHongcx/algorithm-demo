package com.hcx.algorithm.verify;

/**
 * @Title: VerifyCorrect.java
 * @Package com.hcx.algorithm.verify
 * @Description: 对数器
 * @Author: hongcaixia
 * @Date: 2024/1/28 16:46
 * @Version V1.0
 */
public class VerifyCorrect {

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,3,4,5,6,7};
        boolean sorted = isSorted(arr1);
        System.out.println("数组有序嘛："+sorted);

        System.out.println("======");

        for (int i = 0; i < 1000; i++) {
            int[] arr = randomArr(10, 99);
            select(arr);
            isSorted(arr);
//            for (int j = 0; j < arr.length; j++) {
//                System.out.print(arr[j]+" ");
//            }
//            System.out.println();
        }


    }

    /**
     * 生成一个随机大小随机数字的数组
     * @param maxLength
     * @param maxNum
     * @return
     */
    public static int[] randomArr(int maxLength,int maxNum){
        int arrLength = (int)(Math.random()*maxLength);
        int[] arr = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            int arrEle = (int)(Math.random()*maxNum);
            arr[i] = arrEle;
        }
        return arr;
    }

    public static int[] copyArr(int[] arr){
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    public static boolean isSorted(int[] arr){
        if(arr.length == 0){
            return true;
        }
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            /**
             * i = 1：arr[1] < arr[0]
             * i = 2：arr[2] < arr[1]
             * i = 3：arr[3] < arr[2]
             */
            if(arr[i] < min){
                System.out.println("排序出错");
                return false;
            }
            min = arr[i];
        }
        return true;
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
