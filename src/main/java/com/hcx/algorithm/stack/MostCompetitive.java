package com.hcx.algorithm.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: MostCompetitive.java
 * @Package com.hcx.algorithm.stack
 * @Description: Leetcode1673.找出最具竞争力的子序列
 * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
 * 在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
 * @Author: hongcaixia
 * @Date: 2024/5/24 15:56
 * @Version V1.0
 */
public class MostCompetitive {

    public static int[] mostCompetitive(int[] nums, int k) {
        List<Integer> resultNums = new ArrayList<Integer>();

        int del = nums.length - k; // 可以删除的元素个数 3
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
            while (del>0 && resultNums.size()>0 && resultNums.get(resultNums.size()-1) > nums[i] ){
                resultNums.remove(resultNums.size()-1);
                del = del -1; // 2 1 0
            }
            resultNums.add(nums[i]);
        }

        while (resultNums.size() > k) {
            resultNums.remove(resultNums.size() - 1);
        }

        int[] result = new int[k];
        for (int i = 0; i < resultNums.size(); i++) {
            result[i] =  resultNums.get(i);
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums = {2,4,3,3,5,4,9,6};
        int[] ints = mostCompetitive(nums, 4);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }

    }
}
