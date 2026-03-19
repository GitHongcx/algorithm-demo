package com.hcx.algorithm.kmp;

/**
 * @Title: StrStrSolution.java
 * @Package com.hcx.algorithm.kmp
 * @Description: leetcode28.找出字符串中第一个匹配项的下标
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 * @Author: hongcaixia
 * @Date: 2026/3/18 15:47
 * @Version V1.0
 */
public class StrStrSolution {


    public int strStrKMP(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();

        if (m == 0) {
            return 0;
        }

        int[] nextArr = getNextArr(needle);

        int j = 0;
        for (int i = 0; i < m ; i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = nextArr[j - 1];
            }
            if(haystack.charAt(i) == needle.charAt(j)){
                j++;
            }
            if (j == n) {
                return i - n + 1;
            }
        }
        return -1;
    }

    public static int[] getNextArr(String needle) {
        int[] next = new int[needle.length()];
        // 只有一个元素 没有相同前后缀
        next[0] = 0;
        // 前缀与后缀有的相同字符的长度   相等前缀的长度  j
        int j = 0;
        // i 一直前进 遍历模式串
        for (int i = 1; i < needle.length(); i++) {
            if (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            // 当前长度为j的前缀无法扩展，但可能存在一个更短的前缀长度与当前后缀的末尾部分相等
            // j 回到上一次有相等前缀的位置
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public int strStr(String haystack, String needle) {

        int m = haystack.length();
        int n = needle.length();

        if(m == 0){
            return 0;
        }

        for (int i = 0; i <= m - n; i++) {
            // 记录匹配的长度
            int j = 0;
            while(j < n && needle.charAt(j) == haystack.charAt(i + j)){
                j++;
            }
            if(j == n){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        StrStrSolution strStrSolution = new StrStrSolution();
        String haystack = "hello";
        String needle = "ll";
        int i = strStrSolution.strStrKMP(haystack, needle);
        System.out.println(i);
        System.out.println("==========");
        int[] aabaas = getNextArr("aaabaaa");
        for (int i1 : aabaas) {
            System.out.println(i1);
        }
    }

}
