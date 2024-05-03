package com.hcx.algorithm.arr;

/**
 * @Title: RemoveElement.java
 * @Package com.hcx.algorithm.arr
 * @Description: Leetcode27.移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * @Author: hongcaixia
 * @Date: 2024/5/2 18:19
 * @Version V1.0
 */
public class RemoveElement {

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        //int i = removeElement(nums, 3);
        int i1 = removeElementDoublePoint(nums, 3);
        System.out.println(i1);
    }

    /**
     * 暴力破解
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < nums.length; j++) {
                    nums[j - 1] = nums[j];
                }
                size--;
                i--;
            }
        }
        return size;
    }

    /**
     * 双指针
     * @param nums
     * @param val
     * @return
     */
    public static int removeElementDoublePoint(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }


}
