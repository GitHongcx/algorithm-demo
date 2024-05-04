package com.hcx.algorithm.arr;

/**
 * @Title: MinSubArrayLen.java
 * @Package com.hcx.algorithm.arr
 * @Description: Leetcode209.长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组
 * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * @Author: hongcaixia
 * @Date: 2024/5/3 16:23
 * @Version V1.0
 */
public class MinSubArrayLen {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] sunInts = preSumArr(nums1);
//        for (int i = 0; i < sunInts.length; i++) {
//            System.out.println(sunInts[i]);
//        }
        int target = 15;
        int[] nums = {1,2,3,4,5};
        int i = minSubArrayLen2(target, nums);
        System.out.println(i);
    }


    /**
     * 滑动窗口
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen2(int target, int[] nums) {
        int left = 0;
        int result = Integer.MAX_VALUE;
        int sum = 0;
        // 窗口右边界right
        for (int right = 0; right < nums.length; right++) {
            sum = sum + nums[right];
            while (sum >= target) {
                if (right - left + 1 < result) {
                    result = right - left + 1;
                }
                //更新sum
                sum = sum - nums[left];
                //缩小窗口 左边界往前移
                left++;
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }


    /**
     * 前缀和+二分
     * 跟暴力破解思想一致，
     * 从第一个开始往后找，找到一个满足的
     * 从第二个开始往后找，找到一个满足的
     * ...
     * 只是每一次寻找的过程，使用了二分加快了搜索效率，降低了时间复杂度
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen1(int target, int[] nums) {
        // 前缀和数组 i和j的差就是i到j的和
        int[] sums = preSumArr(nums);
        int result = Integer.MAX_VALUE;

        for (int k = 0; k < sums.length; k++) {
            // 二分查找
            int i = k;
            int j = sums.length - 1;
            while (i <= j) {
                int mid = (i + j) >>> 1;
                if (sums[mid] - sums[k] >= target) {
                    j = mid - 1;
                    if ((mid - k) < result) {
                        result = mid - k;
                    }
                } else {
                    i = mid + 1;
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    /**
     * 前缀和数组
     *
     * @param nums
     * @return
     */
    public static int[] preSumArr(int[] nums) {
        int[] sum = new int[nums.length+1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i-1];
        }
        return sum;
    }


    /**
     * 暴力破解
     * 第一层循环：控制从第几个开始
     * 第二层循环：外层的第一个和  内层的第一个是否满足/和内层的第二个是否满足/和内层的第三个是否满足 。。。
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum = sum + nums[j];
                if (sum >= target) {
                    int length = j - i + 1;
                    if (result > length) {
                        result = length;
                    }
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
