package com.hcx.algorithm.sum;

/**
 * @Title: PrefixSum.java
 * @Package main.java.com.hcx.algorithm.base.sum
 * @Description: 前缀和
 * 原始数组arr：[2,5,9,1,3,7,8]
 * 前缀和数组sunArr：[2,7,16,17,20,27,35]
 * 计算下标L到下标R的元素和:
 * L==0:sunArr[R]
 * L!=0:sunArr[R]-sunArr[L-1]
 * @Author: hongcaixia
 * @Date: 2024/1/27 16:14
 * @Version V1.0
 */
public class PrefixSum {

    public static void main(String[] args) {
        int[] arr = {5,8,1,0,3,2};
        int i = rangeSum(arr, 2, 5);
        int j = rangeSum1(arr, 2, 5);
        System.out.println(i+" "+j);
    }

    public static int rangeSum(int[] arr,int left,int right){
        int sum = 0;
        int[] prefixSumArr = new int[arr.length];
        prefixSumArr[0] = arr[0];
        //算出前缀和数组
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
            prefixSumArr[i] = sum;
        }
        if(left==0){
            return prefixSumArr[right];
        }
        return prefixSumArr[right] - prefixSumArr[left-1];
    }

    public static int rangeSum1(int[] arr,int left,int right){
        int[] prefixSumArr = new int[arr.length];
        prefixSumArr[0] = arr[0];
        //算出前缀和数组
        for (int i = 1; i < arr.length; i++) {
            prefixSumArr[i] = prefixSumArr[i-1]+arr[i];
        }
        return left==0? prefixSumArr[right]:prefixSumArr[right] - prefixSumArr[left-1];
    }
}
