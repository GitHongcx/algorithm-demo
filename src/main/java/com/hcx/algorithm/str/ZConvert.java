package com.hcx.algorithm.str;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: ZConvert.java
 * @Package com.hcx.algorithm.str
 * @Description: leetcode6.Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * @Author: hongcaixia
 * @Date: 2026/3/12 08:47
 * @Version V1.0
 */
public class ZConvert {

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> rowsList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            StringBuilder sb = new StringBuilder();
            rowsList.add(sb);
        }
        int curRow = 0;
        boolean down = true;
        // 遍历s
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            StringBuilder stringBuilder = rowsList.get(curRow);
            stringBuilder.append(c);
            if (curRow == numRows - 1) {
                down = false;
            } else if (curRow == 0) {
                down = true;
            }
            if (down) {
                curRow++;
            } else {
                curRow--;
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder stringBuilder : rowsList) {
            res.append(stringBuilder);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        ZConvert zConvert = new ZConvert();
        String s = zConvert.convert("PAYPALISHIRING", 3);
        System.out.println(s);
    }
}
