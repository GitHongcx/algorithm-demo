package com.hcx.algorithm.hash;

import java.util.HashMap;

/**
 * @Title: TheRomanToInt.java
 * @Package com.hcx.algorithm.hash
 * @Description: leetcode13.罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 * @Author: hongcaixia
 * @Date: 2026/3/12 17:15
 * @Version V1.0
 */
public class TheRomanToInt {

    public static int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;

        int length = s.length();
        for (int i = 0; i < length; i++) {
            // 如果当前字符小于下一个字符，则减去当前值；否则加上当前值。
            char c = s.charAt(i);
            if (i < length - 1 && map.get(c) < map.get(s.charAt(i + 1))) {
                res = res - map.get(c);
            } else {
                res = res + map.get(c);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int i = romanToInt("LVIII");
        System.out.println(i);
    }

}
