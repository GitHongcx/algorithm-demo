package com.hcx.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: CombineSolution.java
 * @Package com.hcx.algorithm.backtrack
 * @Description: leetcode 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * @Author: hongcaixia
 * @Date: 2026/3/16 13:15
 * @Version V1.0
 */
public class CombineSolution {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> pathList = new ArrayList<>();
        backtrack(resultList,pathList,n,k,1);
        return resultList;
    }

    /**
     * 回溯
     * @param resultList
     * @param pathList
     * @param n
     * @param k
     * @param start 搜索的起始位置 为了不重复
     */
    public void backtrack(List<List<Integer>> resultList,List<Integer> pathList,int n,int k,int start){
        if(pathList.size() == k){
            resultList.add(new ArrayList<>(pathList));
            return;
        }
        // 要组合的数
        // 剪枝：i <= n - (k - path.size()) + 1
        for (int i = start; i <= n - (k - pathList.size()) + 1; i++) {
            pathList.add(i);
            // 控制一共几位数 控制k个数 遍历剩下的元素
            backtrack(resultList,pathList,n,k,i+1);
            // 回溯
            pathList.remove(pathList.size()-1);
        }
    }

    public static void main(String[] args) {
        CombineSolution combineSolution = new CombineSolution();
        List<List<Integer>> combine = combineSolution.combine(5, 3);
        System.out.println(combine);
    }
}
