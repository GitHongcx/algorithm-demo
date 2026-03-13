package com.hcx.algorithm.str;

/**
 * @Title: LongestCommonPrefixSolution.java
 * @Package com.hcx.algorithm.str
 * @Description: leetcode14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * @Author: hongcaixia
 * @Date: 2026/3/13 15:16
 * @Version V1.0
 */
public class LongestCommonPrefixSolution {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 假设公共前缀为第一个字符串
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            while (!str.startsWith(prefix)) {
                // 缩小公共前缀
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        LongestCommonPrefixSolution solution = new LongestCommonPrefixSolution();
        String s = solution.longestCommonPrefix(strs);
        System.out.println(s);
    }

}
