package com.hcx.algorithm.arr;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Title: MergeInterval
 * @Package com.hcx.algorithm.arr
 * @Description: 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * @Author: hongcaixia
 * @Date: 2025-07-11  17:55
 * @Version V1.0
 */
public class MergeInterval {

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int preStart = intervals[0][0];
        int preEnd = intervals[0][1];
        int[][] res = new int[intervals.length][2];
        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            int curStart = intervals[i][0];
            int curEnd = intervals[i][1];
            if(curStart > preEnd){
                // 不重叠
                // 往数组放入元素 curStart curEnd
                res[index][0] = preStart;
                res[index][1] = preEnd;
                index++;
                preStart = curStart;
                preEnd = curEnd;
            }else{
                // 重叠
                preEnd = Math.max(curEnd, preEnd);
            }
        }
        res[index][0] = preStart;
        res[index][1] = preEnd;
        return Arrays.copyOf(res, index + 1);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,4},{0,4}};
        int[][] merge = merge(intervals);


    }
}
