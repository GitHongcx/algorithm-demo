package com.hcx.algorithm.sort;

import java.util.Arrays;

/**
 * @Title: ShellSort.java
 * @Package com.hcx.algorithm.sort
 * @Description: 希尔排序
 * 插入排序的改进版：让元素更快速地交换到最终位置
 * 分组实现插入，每组元素间隙称为 gap
 * 每轮排序后 gap 逐渐变小，直至 gap 为 1 完成排序
 * @Author: hongcaixia
 * @Date: 2025/1/28 16:08
 * @Version V1.0
 */
public class ShellSort {

    /**
     * 希尔排序
     * @param arr
     */
    public static void shellSort(int[] arr) {
        // 按照gap分组的元素进行插入排序  逐渐缩小gap 直至为1
        for (int gap = arr.length >> 1; gap > 0; gap = gap >> 2) {
            // 实现插入排序
            for (int low = gap; low < arr.length; low++) {
                // 准备要插入的元素暂存到temp
                int temp = arr[low];
                // 已排序区域的右边界
                int i = low - gap;
                while (i >= 0 && arr[i] > temp) {
                    // 空出插入位置
                    arr[i + gap] = arr[i];
                    i = i - gap;
                }
                if (i != low - gap) {
                    // 往空位插入
                    arr[i + gap] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 7, 2, 5, 8, 1, 4, 6};
        System.out.println(Arrays.toString(a));
        shellSort(a);
        System.out.println(Arrays.toString(a));
    }

}
