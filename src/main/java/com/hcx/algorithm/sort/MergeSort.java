package com.hcx.algorithm.sort;

import java.util.Arrays;

/**
 * @Title: MergeSort.java
 * @Package com.hcx.algorithm.sort
 * @Description: 归并排序
 * 分 - 每次从中间切一刀，处理的数据少一半
 * 治 - 当数据仅剩一个时可以认为有序
 * 合 - 两个有序的结果，可以进行合并排序
 * @Author: hongcaixia
 * @Date: 2025/1/28 19:47
 * @Version V1.0
 */
public class MergeSort {

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        int[] resultArr = new int[arr.length];
        splitSort1(arr);
    }

    public static void main(String[] args) {


        int[] a = {3, -1};
        //int[] resultArr = new int[a.length];
        //splitSort1(a,0,a.length,resultArr);
        System.out.println(Arrays.toString(a));
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * TopDown 自上而下
     * 切分数组 递归执行合并两个有序数组
     *
     * @param arr
     * @param left
     * @param right
     * @param result
     */
    public static void splitSort(int[] arr, int left, int right, int[] result) {
        if (left == right) {
            return;
        }
        // 切分
        int mid = (left + right) >>> 1;

        // 不断切分 直到元素只有一个
        // 左侧
        splitSort(arr, left, mid, result);
        // 右侧
        splitSort(arr, mid + 1, right, result);

        // 对切分完的两个有序数组（最小的时候分别只有一个元素，各自有序）进行合并
        merge(arr, left, mid, mid + 1, right, result);

        System.arraycopy(result, left, arr, left, right - left + 1);
    }

    /**
     * BottomUp 自下而上 非递归
     *
     * @param arr
     */
    public static void splitSort1(int[] arr) {
        int arrLen = arr.length;
        int[] target = new int[arrLen];
        /**
         * 宽度1：0  1   2   3   4  5   6  7
         * 宽度2：01  23  45  67
         * 宽度4：0123  4567
         * 宽度8：01234567
         */
        // 切分数组 宽度依次为 1 2 4  按宽度切分了 对每个宽度的数组进行合并
        for (int width = 1; width < arrLen; width *= 2) {
            // 每一轮对应宽度的区间分别进行合并
            // 合并的左右区间索引
            // 内层循环针对每一轮对应宽度 执行对应次数的两个有序数组合并  startIndex 和 endIndex 为数组的左右边界
            for (int startIndex = 0; startIndex < arr.length; startIndex += 2 * width) {
                // 下一次开始的索引-1
                int endIndex = startIndex + 2 * width - 1;
                if (endIndex >= arrLen) {
                    endIndex = arrLen - 1;
                }
                // startIndex~endIndex 是两个有序区间加在了一起的
                int middleIndex = startIndex + width - 1;
                if (middleIndex >= arrLen) {
                    middleIndex = arrLen - 1;
                }
                merge(arr, startIndex, middleIndex, middleIndex + 1, endIndex, target);
                //System.out.printf("width %d [%d,%d]%n", width, startIndex, endIndex);
            }
            System.arraycopy(target, 0, arr, 0, arrLen);
        }
    }

    /**
     * 合并两个有序数组：源数组 两段有序区间 合并到 目标数组
     *
     * @param arr        源数组
     * @param leftStart  第一段有序区间开始索引
     * @param leftEnd    第一段有序区间结束索引
     * @param rightStart 第二段有序区间开始索引
     * @param rightEnd   第二段有序区间结束索引
     * @param resultArr  结果数组
     */
    public static void merge(int[] arr, int leftStart, int leftEnd, int rightStart, int rightEnd, int[] resultArr) {
        int index = leftStart;
        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (arr[leftStart] < arr[rightStart]) {
                resultArr[index] = arr[leftStart];
                leftStart++;
            } else {
                resultArr[index] = arr[rightStart];
                rightStart++;
            }
            index++;
        }
        // 左侧区域超出了边界，已经遍历完成，把右侧剩余部分拷贝到新数组
        if (leftStart > leftEnd) {
            System.arraycopy(arr, rightStart, resultArr, index, rightEnd - rightStart + 1);
        }
        if (rightStart > rightEnd) {
            System.arraycopy(arr, leftStart, resultArr, index, leftEnd - leftStart + 1);
        }
    }
}
