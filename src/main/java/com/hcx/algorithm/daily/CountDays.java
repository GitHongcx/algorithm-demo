package com.hcx.algorithm.daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * @Title: CountDays
 * @Package com.hcx.algorithm.daily
 * @Description: 3169. 无需开会的工作日
 * @Author: hongcaixia
 * @Date: 2025-07-11  17:05
 * @Version V1.0
 */
public class CountDays {

    public static int countDays(int days, int[][] meetings) {
        if (meetings.length == 0){
            return days;
        }
        Set<Integer> set = new HashSet<>();
        // 遍历数组
        for (int i = 0; i < meetings.length; i++) {
            int num1 = meetings[i][0];
            int num2 = meetings[i][1];
            for (int j = num1; j <= num2; j++) {
                set.add(j);
            }
        }
        return days - set.size();
    }

    public static int countDays1(int days, int[][] meetings) {
        if (meetings.length == 0){
            return days;
        }
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        int preStart = meetings[0][0];
        int preEnd = meetings[0][1];
        int count = 0;
        for (int i = 1; i < meetings.length; i++) {
            int curStart = meetings[i][0];
            int curEnd = meetings[i][1];
            if( curStart > preEnd){
                // 不重叠
                // 前一个的天数
                count += preEnd - preStart + 1;
                preStart = curStart;
                preEnd = curEnd;
            } else {
                // 重叠
                preEnd = Math.max(curEnd, preEnd);
            }
        }
        // 最后的天数
        count += preEnd - preStart + 1;
        return days - count;
    }


    public static void main(String[] args) {
        int i = countDays1(6, new int[][]{{5,7},{1,2},{9,10}});
        System.out.println(i);
    }
}
