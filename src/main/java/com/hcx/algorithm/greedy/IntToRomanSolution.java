package com.hcx.algorithm.greedy;

/**
 * @Title: IntToRomanSolution.java
 * @Package com.hcx.algorithm
 * @Description: leetcode12.整数转罗马数字
 * 罗马数字是通过添加从最高到最低的小数位值的转换而形成的。将小数位值转换为罗马数字有以下规则：
 * 如果该值不是以 4 或 9 开头，请选择可以从输入中减去的最大值的符号，将该符号附加到结果，减去其值，然后将其余部分转换为罗马数字。
 * 如果该值以 4 或 9 开头，使用 减法形式，表示从以下符号中减去一个符号，例如 4 是 5 (V) 减 1 (I): IV ，9 是 10 (X) 减 1 (I)：IX。仅使用以下减法形式：4 (IV)，9 (IX)，40 (XL)，90 (XC)，400 (CD) 和 900 (CM)。
 * 只有 10 的次方（I, X, C, M）最多可以连续附加 3 次以代表 10 的倍数。你不能多次附加 5 (V)，50 (L) 或 500 (D)。如果需要将符号附加4次，请使用 减法形式。
 * @Author: hongcaixia
 * @Date: 2026/3/13 14:42
 * @Version V1.0
 */
public class IntToRomanSolution {

    public String intToRoman(int num) {
        int[] valueArr = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanArr = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < valueArr.length && num > 0; i++) {
            // 当前数字
            int value = valueArr[i];
            while (num >= value) {
                num = num - value;
                String romanStr = romanArr[i];
                res.append(romanStr);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = new IntToRomanSolution().intToRoman(3749);
        System.out.println(s);
    }
}
