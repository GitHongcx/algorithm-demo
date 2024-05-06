package com.hcx.algorithm.str;

import java.util.List;

/**
 * @Title: StringMatching.java
 * @Package com.hcx.algorithm.str
 * @Description: Leetcode1408.数组中的字符串匹配
 * 给你一个字符串数组 words ，数组中的每个字符串都可以看作是一个单词。请你按 任意 顺序返回 words 中是其他单词的子字符串的所有单词。
 * 如果你可以删除 words[j] 最左侧和/或最右侧的若干字符得到 words[i] ，那么字符串 words[i] 就是 words[j] 的一个子字符串。
 * @Author: hongcaixia
 * @Date: 2024/5/6 11:03
 * @Version V1.0
 */
public class StringMatching {

    public static void main(String[] args) {
        String[] strings = new String[]{"mass", "as", "hero", "superhero"};
        List<String> stringList = stringMatching(strings);
        for (int i = 0; i <stringList.size(); i++) {
            System.out.println(stringList.get(i));
        }
    }


    public static List<String> stringMatching(String[] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {

            }
        }
        return null;
    }


}
