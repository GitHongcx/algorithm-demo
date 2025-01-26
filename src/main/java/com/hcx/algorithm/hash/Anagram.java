package com.hcx.algorithm.hash;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Title: Anagram.java
 * @Package com.hcx.algorithm.hash
 * @Description: Leetcode242.有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词
 * s = "anagram", t = "nagaram"   true
 * s = "rat", t = "car"      false
 * @Author: hongcaixia
 * @Date: 2025/1/26 16:12
 * @Version V1.0
 */
public class Anagram {

    public boolean isAnagram(String s, String t) {
        HashMap<String, String> map = new HashMap<>();
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);

        String str = new String(charArray);
        map.put(str, str);

        char[] charArray1 = t.toCharArray();
        Arrays.sort(charArray1);
        String str1 = new String(charArray1);

        String res = map.put(str1, str1);
        if (res != null) {
            return true;
        }
        return false;
    }


    public boolean isAnagram1(String s, String t) {
        return Arrays.equals(strToIntArray1(s), strToIntArray1(t));
    }

    public int[] strToIntArray(String s) {
        // 存放字符串的数组
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 97]++;
        }
        return arr;
    }

    // 优化
    public int[] strToIntArray1(String s) {
        // 存放字符串的数组
        int[] arr = new int[26];
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            arr[c - 97]++;
        }
        return arr;
    }

}
