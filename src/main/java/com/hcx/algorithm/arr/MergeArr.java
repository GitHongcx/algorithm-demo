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
     * 递归合并数组数组中两个有序区间的元素，把两个有序区间的元素合并为一个有序的数组
     * @param arr1   本来的数组
     * @param start1 第一个有序区间的起点
     * @param end1   第一个有序区间的终点
     * @param start2 第二个有序区间的起点
     * @param end2   第二个有序区间的终点
     * @param arr2   新的数组
     */
    public static void mergeArrRecursion(int[] arr1, int start1,int end1,int start2,int end2,int[] arr2,int index) {
        if (start1 > end1 || start2 > end2) {
            if (start1 <= end1) {
                while (start1 <= end1) {
                    arr2[index] = arr1[start1++];
                    index++;
                }
            }
            if (start2 <= end2) {
                while (start2 <= end2) {
                    arr2[index] = arr1[start2++];
                    index++;
                }
            }
            return;
        }

        if (arr1[start1] < arr1[start2]) {
            arr2[index] = arr1[start1];
            mergeArrRecursion(arr1, start1 + 1, end1, start2, end2, arr2, index + 1);
        } else {
            arr2[index] = arr1[start2];
            mergeArrRecursion(arr1, start1, end1, start2 + 1, end2, arr2, index + 1);
        }
    }


    /**
     * 合并数组数组中两个有序区间的元素，把两个有序区间的元素合并为一个有序的数组
     * @param arr1
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @param arr2
     */
    public static void mergeArr(int[] arr1, int start1,int end1,int start2,int end2,int[] arr2){
        int index = 0;
        while (start1 <= end1 && start2 <= end2) {
            if(arr1[start1]<arr1[start2]){
                arr2[index] = arr1[start1++];
                index++;
            }else{
                arr2[index] = arr1[start2++];
                index++;
            }
        }
        if(start1 <= end1){
            // 第一个区间没遍历完
            while (start1 <= end1) {
                arr2[index] = arr1[start1++];
                index++;
            }
        }
        if(start2 <= end2){
            // 第二个区间没遍历完
            while (start2 <= end2) {
                arr2[index] = arr1[start2++];
                index++;
            }
        }
    }

    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int index1 = m - 1;
        int index2 = n - 1;
        while (index1 >= 0 && index2 >= 0) {
            int num1 = nums1[index1];
            int num2 = nums2[index2];
            if (num2 > num1) {
                nums1[index] = num2;
                index2--;
            } else {
                nums1[index] = num1;
                index1--;
            }
            index--;
        }

        // 针对index1 剩余的 小的不用管 因为本身就是要放在num1中
        while(index2>=0){
            nums1[index] = nums2[index2];
            index2--;
            index--;
        }
    }

    public static void main(String[] args) {
//        int[] nums1 = new int[]{1,2,3,0,0,0};
//        int[] nums2 = new int[]{2,5,6};
//
//        merge1(nums1,3,nums2,3);

        int[] nums1 = new int[]{1,2,3,2,5,6,10,14};
        int[] nums2 = new int[nums1.length];

        //mergeArrRecursion(nums1,0,2,3,7,nums2,0);
        mergeArr(nums1,0,2,3,7,nums2);


        System.out.println(nums2);

    }

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
