package com.hcx.algorithm.str;

import java.util.ArrayList;
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
        //String[] strings = new String[]{"mass", "as", "hero", "superhero"};
        String[] strings = new String[]{"leetcoder","leetcode","od","hamlet","am"};
        boolean match = match("mass", "as");
        //System.out.println(match);

        List<String> stringList = stringMatching(strings);
        for (int i = 0; i <stringList.size(); i++) {
            System.out.println(stringList.get(i));
        }
    }

    /**
     * 暴力破解
     * @param words
     * @return
     */
    public static List<String> stringMatching(String[] words) {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String word = words[i];
                String word1 = words[j];
                boolean result = word.length() > word1.length();
                if (result) {
                    if(match(word, word1)){
                        if(!resultList.contains(word1)){
                            resultList.add(word1);
                        }
                    }
                } else {
                    if(match(word1, word)){
                        if(!resultList.contains(word)){
                            resultList.add(word);
                        }
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * 判断subString是否是string的子串
     * @param string
     * @param subString
     * @return
     */
    public static boolean match(String string, String subString) {
        int strLength = string.length();
        int subStrLength = subString.length();

        for (int i = 0; i <= strLength - subStrLength; i++) { // mass
            boolean flag = true;
            for (int j = 0; j < subStrLength; j++) { // as
                if (string.charAt(j + i) != subString.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }


}
