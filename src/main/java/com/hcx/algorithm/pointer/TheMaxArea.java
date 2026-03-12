package com.hcx.algorithm.pointer;

/**
 * @Title: TheMaxArea.java
 * @Package com.hcx.algorithm.pointer
 * @Description: leetcode 11.盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * @Author: hongcaixia
 * @Date: 2026/3/12 16:39
 * @Version V1.0
 */
public class TheMaxArea {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            // 高度
            int minHeight = Math.min(height[left], height[right]);
            // 长度
            int length = right - left;
            int area = minHeight * length;
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new TheMaxArea().maxArea(height));
    }


}
