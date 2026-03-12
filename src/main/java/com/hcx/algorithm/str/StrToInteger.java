package com.hcx.algorithm.str;

/**
 * @Title: StrToInteger.java
 * @Package com.hcx.algorithm.str
 * @Description: leetcode8.字符串转换整数
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
 * 函数 myAtoi(string s) 的算法如下：
 * 空格：读入字符串并丢弃无用的前导空格（" "）
 * 符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
 * 转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
 * 舍入：如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被舍入为 −231 ，大于 231 − 1 的整数应该被舍入为 231 − 1 。
 * 返回整数作为最终结果。
 * 从第一个非空字符开始，如果是数字或正负号，则连续读取数字直到遇到非数字字符
 * s = "42"   =>   42
 * s = " -042"  =>   -42
 * s = "1337c0d3"  =>   1337
 * s = "0-1"  =>   0
 * s = "words and 987"  =>   0
 * @Author: hongcaixia
 * @Date: 2026/3/12 13:55
 * @Version V1.0
 */
public class StrToInteger {

    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0;
        int index = 0;
        char[] charArray = s.toCharArray();
        // 跳过空格
        while (index < s.length() && charArray[index] == ' ') {
            index++;
        }
        //正负号
        int sign = 1;
        // 正负号处理
        if (index < s.length() && (charArray[index] == '-' || charArray[index] == '+')) {
            sign = charArray[index] == '-' ? -1 : 1;
            index++;
        }
        while (index < s.length() && charArray[index] >= '0' && charArray[index] <= '9') {
            int curNum = charArray[index] - '0';
            // 判断溢出
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && curNum > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            // 属于数字
            res = res * 10 + curNum;
            System.out.println(res);
            index++;
        }
        return res * sign;
    }

    public static void main(String[] args) {
        StrToInteger strToInteger = new StrToInteger();
        System.out.println(strToInteger.myAtoi("-2147483649"));
    }
}
