package com.hcx.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: GenerateParenthesisSolution.java
 * @Package com.hcx.algorithm.backtrack
 * @Description: leetcode 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * @Author: hongcaixia
 * @Date: 2026/3/18 13:29
 * @Version V1.0
 */
public class GenerateParenthesisSolution {

    List<String> resList = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> generateParenthesis(int n) {
        backtrack(0, 0, n);
        return resList;
    }

    public void backtrack(int left, int right, int n) {
        if (left == n && right == n) {
            resList.add(sb.toString());
            return;
        }
        // 穷举左括号
        if (left < n) {
            sb.append("(");
            backtrack(left + 1, right, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if(right < left){
            sb.append(")");
            backtrack(left, right + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParenthesisSolution generateParenthesisSolution = new GenerateParenthesisSolution();
        List<String> list = generateParenthesisSolution.generateParenthesis(3);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
