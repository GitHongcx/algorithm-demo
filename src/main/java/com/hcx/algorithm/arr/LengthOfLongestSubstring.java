package com.hcx.algorithm.arr;

import java.util.HashSet;
import java.util.Set;

/**
 * @Title: LengthOfLongestSubstring.java
 * @Package com.hcx.algorithm.arr
 * @Description: Leetcode3.无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
 * @Author: hongcaixia
 * @Date: 2024/5/5 17:13
 * @Version V1.0
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String str = "pwwkew";
        int length = lengthOfLongestSubstring1(str);
        System.out.println(length);
    }

    /**
     * 滑动窗口
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s){
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < chars.length; right++) {
            if(set.add(chars[right])){
                int length = right - left + 1;
                if(length > maxLength){
                    maxLength = length;
                }
            }else{
                while (!set.add(chars[right])){
                    //移除窗口元素
                    set.remove(chars[left]);
                    //左边界前进
                    left++;
                }
            }
        }
        return maxLength;
    }

    /**
     * 错误的== 感觉跟暴力又没区别了
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int longestLength = 0;
        int left = 0;
        for (int right = 0; right < chars.length; right++) {
            if (set.add(chars[right])) {
                //说明当前这个元素不重复
                int length = right - left + 1;
                if (length > longestLength) {
                    longestLength = length;
                }
            } else {
                set.clear();
                right = left++;
            }
        }
        return longestLength;
    }

}
