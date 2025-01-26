package com.hcx.algorithm.hash;

/**
 * @Title: FirstUniqChar.java
 * @Package com.hcx.algorithm.hash
 * @Description: Leetcode387.字符串中的第一个唯一字符
 * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
 * s = "leetcode"  0
 * s = "loveleetcode"  2
 * s = "aabb"  -1
 * @Author: hongcaixia
 * @Date: 2025/1/26 16:33
 * @Version V1.0
 */
public class FirstUniqChar {

    public int firstUniqChar(String s) {
        // 每个字符出现的次数 下标 26个字母的顺序 值：出现的次数
        int[] count = new int[26];
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            count[c - 'a']++;
        }
        for (int i = 0; i < charArray.length; i++) {
            // 字符串按顺序找到出现的次数
            if (count[charArray[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
