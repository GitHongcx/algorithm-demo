package com.hcx.algorithm.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: LetterCombinationsSolution.java
 * @Package com.hcx.algorithm.hash
 * @Description: leetcode 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * @Author: hongcaixia
 * @Date: 2026/3/16 09:45
 * @Version V1.0
 */
public class LetterCombinationsSolution {

    List<String> resList = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> letterCombinations(String digits) {

        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        List<String> sourceList = new ArrayList<>();
        // 遍历digits
        for (int i = 0; i < digits.length(); i++) {
            String str = map.get(digits.charAt(i) - '0');
            sourceList.add(str);
        }
        backtrack(digits, sourceList, 0);
        return resList;
    }

    public void backtrack(String digits, List<String> sourceList, int start) {
        // 取出digits第一位数字
        if (sb.length() == digits.length()) {
            resList.add(sb.toString());
            return;
        }
        // 控制取出数字对应的字符
        String str = sourceList.get(start);
        // abc  def  ghi
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            sb.append(c);
            backtrack(digits, sourceList, start + 1);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public static void main(String[] args) {
        LetterCombinationsSolution letterCombinationsSolution = new LetterCombinationsSolution();
        List<String> strings = letterCombinationsSolution.letterCombinations("23");
        System.out.println(strings);

    }

}
