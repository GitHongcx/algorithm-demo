package com.hcx.algorithm.pointer;

import java.util.*;

/**
 * @Title: ThreeNumSum.java
 * @Package com.hcx.algorithm.pointer
 * @Description: 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * @Author: hongcaixia
 * @Date: 2026/3/10 16:42
 * @Version V1.0
 */
public class ThreeNumSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> resList = new ArrayList<>();
        for (int i = 0; i < nums.length-2; i++) {
            // 固定一个数
            int fixValue = nums[i];
            int leftIndex = i+1;
            int rightIndex = nums.length - 1;
            // 跳过重复
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            while (leftIndex < rightIndex) {
                if (nums[leftIndex] + nums[rightIndex] + fixValue > 0) {
                    rightIndex--;
                } else if (nums[leftIndex] + nums[rightIndex] + fixValue < 0) {
                    leftIndex++;
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add((nums[leftIndex]));
                    list.add(nums[i]);
                    list.add(nums[rightIndex]);
                    resList.add(list);
                    // 跳过重复
                    while (leftIndex < rightIndex && nums[rightIndex] == nums[rightIndex - 1]) {
                        rightIndex--;
                    }
                    while (leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex + 1]) {
                        leftIndex++;
                    }
                    rightIndex--;
                    leftIndex++;
                }
            }
        }
        return resList;
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        List<List<Integer>> resList = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        while (leftIndex + 1 < rightIndex) {
            if (nums[leftIndex] + nums[leftIndex + 1] + nums[rightIndex] > 0) {
                rightIndex--;
            } else if (nums[leftIndex] + nums[leftIndex + 1] + nums[rightIndex] < 0) {
                leftIndex++;
            } else {
                List<Integer> list = new ArrayList<>();
                list.add((nums[leftIndex]));
                list.add(nums[leftIndex + 1]);
                list.add(nums[rightIndex]);
                set.add(list);
                rightIndex--;
                leftIndex++;
            }
        }
        resList.addAll(set);
        return resList;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        ThreeNumSum threeNumSum = new ThreeNumSum();
        List<List<Integer>> lists = threeNumSum.threeSum(nums);
        System.out.println(lists.toString());
    }
}
