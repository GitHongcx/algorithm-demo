package com.hcx.algorithm.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Title: ContainsDuplicate.java
 * @Package com.hcx.algorithm.hash
 * @Description: Leetcode217.存在重复元素
 * 给你一个整数数组 nums 。
 * 如果任一值在数组中出现 至少两次 ，返回 true ；
 * 如果数组中每个元素互不相同，返回 false 。
 * @Author: hongcaixia
 * @Date: 2025/1/26 15:23
 * @Version V1.0
 */
public class ContainsDuplicate {


    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int key : nums) {
            if (!set.add(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate1(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int key : nums) {
            if (map.containsKey(key)) {
                return true;
            }
            map.put(key,key);
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int key : nums) {
            // 返回put之前的旧值
            Integer put = map.put(key, key);
            if (put != null) {
                return true;
            }
        }
        return false;
    }
}
