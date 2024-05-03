package com.hcx.algorithm.arr;

/**
 * @Title: SortedSquares.java
 * @Package com.hcx.algorithm.arr
 * @Description: Leetcode977.有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * @Author: hongcaixia
 * @Date: 2024/5/3 14:41
 * @Version V1.0
 */
public class SortedSquares {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 10};
        int[] result = sortedSquares(nums);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    /**
     * 双指针
     *
     * @param nums
     * @return
     */
    public static int[] sortedSquares(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int[] sortedSquareNums = new int[nums.length];
        int index = nums.length - 1;
        while (start <= end) {
            int startSquare = nums[start] * nums[start];
            int endSquare = nums[end] * nums[end];
            if (startSquare > endSquare) {
                sortedSquareNums[index] = startSquare;
                start++;
            } else {
                sortedSquareNums[index] = endSquare;
                end--;
            }
            index--;
        }
        return sortedSquareNums;
    }

    /**
     * 暴力
     *
     * @param nums
     * @return
     */
    public static int[] sortedSquares1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        return insertSort(nums);
    }

    /**
     * 选择排序
     *
     * @param nums
     * @return
     */
    private static int[] selectSort(int[] nums) {
        for (int j = 0; j < nums.length; j++) {
            int minValueIndex = j;
            for (int k = j + 1; k < nums.length; k++) {
                // 找出索引最小的
                minValueIndex = nums[k] < nums[minValueIndex] ? k : minValueIndex;
            }
            //把找到的下标和第j个元素交换 nums[j] nums[minValueIndex]
            int temp = nums[j];
            nums[j] = nums[minValueIndex];
            nums[minValueIndex] = temp;
        }
        return nums;
    }

    /**
     * 插入排序
     *
     * @param nums
     * @return
     */
    private static int[] insertSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return nums;
    }

}
