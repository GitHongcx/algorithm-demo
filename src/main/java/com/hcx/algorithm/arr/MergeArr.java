package com.hcx.algorithm.arr;

/**
 * @Title: MergeArr.java
 * @Package com.hcx.algorithm.arr
 * @Description: Leetcode88.合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * @Author: hongcaixia
 * @Date: 2024/12/27 15:45
 * @Version V1.0
 */
public class MergeArr {

    /**
     * 合并两个非递减顺序 排列的整数数组
     * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * 输出：[1,2,2,3,5,6]
     * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
     * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 当前指向的nums1的指针（即新数组）
        int index = m + n - 1;
        // 指向 nums1 数组的尾指针
        int i = m - 1;
        // 指向 nums2 数组的尾指针
        int j = n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[index] = nums1[i];
                i--;
            } else {
                nums1[index] = nums2[j];
                j--;
            }
            index--;
        }

        while (j >= 0) {
            //将剩下的全部加入到第一个数组
            nums1[index] = nums2[j--];
            index--;
        }

    }
}
