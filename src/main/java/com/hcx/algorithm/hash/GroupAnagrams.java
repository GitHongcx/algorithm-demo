package com.hcx.algorithm.hash;

import java.util.*;

/**
 * @Title: GroupAnagrams.java
 * @Package com.hcx.algorithm.hash
 * @Description: Leetcode49.字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * [["bat"],["nat","tan"],["ate","eat","tea"]]
 * @Author: hongcaixia
 * @Date: 2025/1/26 14:29
 * @Version V1.0
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            // 排好序的字符串作为key
            String key = new String(charArray);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);

//            List<String> list = map.get(key);
//            if (list == null || list.isEmpty()) {
//                List<String> strList = new ArrayList<>();
//                strList.add(str);
//                map.put(key, strList);
//            } else {
//                list.add(str);
//            }
        }
        return new ArrayList<>(map.values());
    }


    public List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<ArrayKey, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 获取当前字符串对应的key数组
            ArrayKey arrayKey = new ArrayKey(str);
            // 检查map是否存在该key 加入当前的字符串
            map.computeIfAbsent(arrayKey, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 因为map的key要求重写equals和hashcode方法，所以自定义一个类作为key
     */
    class ArrayKey {

        // 存储26个字母的数组
        int[] key = new int[26];

        /**
         * 把字符串填充到key数组中 26个字母 对应填充到0~25下标中
         *
         * @param str
         */
        public ArrayKey(String str) {
            for (int i = 0; i < str.length(); i++) {
                key[str.charAt(i) - 'a']++;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            ArrayKey arrayKey = (ArrayKey) obj;

            return Arrays.equals(key, arrayKey.key);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(key);
        }
    }
}
