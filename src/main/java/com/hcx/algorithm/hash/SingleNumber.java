package com.hcx.algorithm.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @Title: SingleNumber.java
 * @Package com.hcx.algorithm.hash
 * @Description: Leetcode136.只出现一次的数字
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * nums = [2,2,1]    1
 * nums = [4,1,2,1,2]  4
 * @Author: hongcaixia
 * @Date: 2025/1/26 15:35
 * @Version V1.0
 */
public class SingleNumber {

    /**
     * 异或：相同为0，不同为1
     * 相同数字异或，结果为0
     * 任何数字和0异或，结果为数字本身
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num ^= nums[i];
        }
        return num;
    }

    public int singleNumber1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            boolean res = set.add(num);
            if (!res) {
                set.remove(num);
            }
        }
        return set.iterator().next();
    }


}
