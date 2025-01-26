package com.hcx.algorithm.hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Title: LongestSubstring.java
 * @Package com.hcx.algorithm.hash
 * @Description: Leetcode3.无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
 * s = "abcabcbb"
 * 输出: 3
 *
 * s = "bbbbb"
 * 输出: 1
 * @Author: hongcaixia
 * @Date: 2025/1/26 12:29
 * @Version V1.0
 */
public class LongestSubstring {

    /**
     * 哈希表
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        // 存储不重复的子串 key：字符  value：索引    为了在遇到重复的时候从里面拿到下次要开始的位置
        HashMap<Character, Integer> map = new HashMap<>();
        int begin = 0;
        int maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            // 当前字符在map中 更新索引
            if (map.containsKey(c)) {
                // 遇到了重复的可能有两种情况：和最开始 或者 和当前的 重复
                // 当前重复的这个元素所在的下标+1的位置重新开始 要保证begin不能往回走
                begin = Math.max(map.get(c) + 1, begin);
                // 更新begin的索引
                map.put(c, end);
            } else { // 当前字符不在map中 加入map
                map.put(c, end);
            }
            //System.out.println(s.substring(begin, end+1));
            maxLength = Math.max(maxLength, end - begin + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int i = lengthOfLongestSubstring1("abcabcbb");

    }

    /**
     * 滑动窗口
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 左指针
        int left = 0;
        // 右指针
        int right = 0;
        // 当前长度
        int curLength = 0;
        // 最大长度
        int maxLength = 0;

        // 存储当前遍历的子串
        Set<Character> set = new HashSet<>();

        // 右指针不断向前遍历
        while (right < s.length()) {
            // 当前的字符串不在集合中
            if (!set.contains(s.charAt(right))) {
                // 加入集合
                set.add(s.charAt(right));
                curLength++;
                if (curLength > maxLength) {
                    maxLength = curLength;
                }
                right++;
            } else {
                // 当前字符包含在集合中，左指针前进,直到没有重复的为止
                while (set.contains(s.charAt(right))) {
                    set.remove(s.charAt(left));
                    left++;
                    curLength--;
                }
                // 左指针移动到了没有重复的为止，右指针指向的元素加入集合中
                set.add(s.charAt(right));
                right++;
                curLength++;
            }
        }
        return maxLength;
    }

    /**
     * 优化版本
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        // 字符 本身作为下标，字符串中的索引 作为数组的元素
        int[] arr = new int[128];
        Arrays.fill(arr, -1);
        int begin = 0;
        int maxLength = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            // 当前的字符在数组中
            if (arr[ch] != -1) { // 重复时调整 begin
                begin = Math.max(begin, arr[ch] + 1);
                // 更新该字符的索引
                arr[ch] = end;
            } else { // 不重复
                arr[ch] = end;
            }
            System.out.println(s.substring(begin, end + 1));
            maxLength = Math.max(maxLength, end - begin + 1);
        }
        return maxLength;
    }
}
