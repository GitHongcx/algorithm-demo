package com.hcx.algorithm.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @Title: MostCommonWord.java
 * @Package com.hcx.algorithm.hash
 * @Description: Leetcode819.最常见的单词
 * 给你一个字符串 paragraph 和一个表示禁用词的字符串数组 banned ，返回出现频率最高的非禁用词。
 * 题目数据 保证 至少存在一个非禁用词，且答案 唯一 。
 * @Author: hongcaixia
 * @Date: 2025/1/26 17:09
 * @Version V1.0
 */
public class MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = Set.of(banned);
        // 所有的单词
        String[] split = paragraph.toLowerCase().split("[^a-zA-Z]+");
        // key:单词  value：出现的次数
        HashMap<String, Integer> map = new HashMap<>();

        for (String s : split) {
            /*
            Integer value = map.get(s);
            if (value == null) {
                value = 0;
            }
            map.put(s, value + 1);
             */
            // 不包含禁用词才加入
            if (!bannedSet.contains(s)) {
                map.compute(s, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        Optional<Map.Entry<String, Integer>> max = map.entrySet().stream().max(Map.Entry.comparingByValue());
        return max.map(Map.Entry::getKey).orElse("");
    }

    public static String mostCommonWord1(String paragraph, String[] banned) {
        // key:单词  value：出现的次数
        HashMap<String, Integer> map = new HashMap<>();
        // 禁用词
        Set<String> bannedSet = Set.of(banned);
        // 所有的单词
        char[] charArray = paragraph.toLowerCase().toCharArray();

        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
            } else {
                //遇到了空格或者其他 把拼好的字符串放入map中
                String key = sb.toString();
                // 判断是不是属于禁用词
                if (!bannedSet.contains(key) && !key.isEmpty()) {
                    map.compute(key, (k, v) -> v == null ? 1 : v + 1);
                }
                // 重置sb
                sb.setLength(0);
            }
        }
        // 如果只有一个单词的时候 没有走到上面的else,没有往map里放
        if (!sb.isEmpty()) {
            //遇到了空格或者其他 把拼好的字符串放入map中
            String key = sb.toString();
            if (!bannedSet.contains(key) && !key.isEmpty()) {
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }
        }

        int maxValue = 0;
        String maxKey = null;
        // 统计最多次数的key
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value > maxValue) {
                maxValue = value;
                maxKey = entry.getKey();
            }
        }
        return maxKey;
    }

    public static void main(String[] args) {
        String paragraph = "Bob. hIt, baLl";
        String[] banned = new String[]{"hit","bob"};
        String s = mostCommonWord1(paragraph, banned);
        System.out.println(s);
    }


}
