package com.hcx.algorithm.pointer;

import java.util.Arrays;

/**
 * @Title: ThreeSumClosestSolution.java
 * @Package com.hcx.algorithm.pointer
 * @Description: leetcode16.最接近的三数之和
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个在 不同下标位置 的整数，使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 * @Author: hongcaixia
 * @Date: 2026/3/13 16:11
 * @Version V1.0
 */
public class ThreeSumClosestSolution {

    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int best = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int fixNum = nums[i];
            int leftIndex = i + 1;
            int rightIndex = nums.length - 1;

            while (leftIndex < rightIndex) {
                int sum = fixNum + nums[leftIndex] + nums[rightIndex];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    leftIndex++;
//                    while (leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex - 1]) {
//                        leftIndex++;
//                    }
                } else {
                    rightIndex--;
//                    while (leftIndex < rightIndex && nums[rightIndex] == nums[rightIndex + 1]) {
//                        rightIndex--;
//                    }
                }
                if (Math.abs(best - target) > Math.abs(target - sum)) {
                    best = sum;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        ThreeSumClosestSolution threeSumClosestSolution = new ThreeSumClosestSolution();
        int[] nums = new int[]{-1, 2, 1, -4};
        int target = 1;
        int i = threeSumClosestSolution.threeSumClosest(nums, target);
        System.out.println(i);

    }

}
