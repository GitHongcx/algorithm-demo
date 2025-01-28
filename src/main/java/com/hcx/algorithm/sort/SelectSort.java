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

        selectSort(arr);

        System.out.println(arr);
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr) {
        // 选择的轮数
        for (int right = arr.length - 1; right > 0; right--) {
            // 假设最后一个元素是最大的 从开头往后遍历，如果遇到比这个数大的，就重新把大的复制给max
            // 选出了最大的元素，把元素交换到a.length依次递减
            int max = right;
            // 每一轮里遍历数组，找出最大值
            for (int i = 0; i < right; i++) {
                if (arr[i] > arr[max]) {
                    max = i;
                }
            }
            // 如果max等于了right 就不需要交换了 说明本身max就是这一轮最大的
            if (max != right) {
                // 找出了最大的元素为max索引位置 把最大的元素交换到最后(随着循环一直递减)第二个交换到的位置为倒数第二个
                int temp = arr[max];
                arr[max] = arr[right];
                arr[right] = temp;
            }
        }
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
