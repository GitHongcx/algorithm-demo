package com.hcx.algorithm.hash;

import java.util.HashMap;

/**
 * @Title: TwoSum.java
 * @Package com.hcx.algorithm.hash
 * @Description: Leetcode1.两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
 * 你可以按任意顺序返回答案。
 * @Author: hongcaixia
 * @Date: 2025/1/26 11:32
 * @Version V1.0
 */
public class TwoSum {

    /**
     * nums = [2,7,11,15], target = 9
     * [0,1]
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        // 存储遍历过的元素 key:数组元素 value：数组下标
        HashMap<Integer, Integer> map = new HashMap<>();

        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            int num2 = target - num1;
            // 从map中找到num2
            if (map.containsKey(num2)) {
                return new int[]{i, map.get(num2)};
            } else {
                map.put(num1, i);
            }
        }
        return null;
    }
}
