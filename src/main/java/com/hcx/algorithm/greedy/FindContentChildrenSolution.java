package com.hcx.algorithm.greedy;

import java.util.Arrays;

/**
 * @Title: FindContentChildrenSolution.java
 * @Package com.hcx.algorithm.greedy
 * @Description: leetcode 455. 分发饼干
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
 * 并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是满足尽可能多的孩子，并输出这个最大数值。
 * @Author: hongcaixia
 * @Date: 2026/3/20 10:22
 * @Version V1.0
 */
public class FindContentChildrenSolution {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        // 饼干指针
        int i = s.length - 1;
        // 胃口指针
        int j = g.length - 1;
        while (i >= 0 && j >= 0) {
            // 饼干满足胃口
            if (s[i] >= g[j]) {
                res++;
                i--;
                j--;
            } else {
                // 当前饼干无法满足胃口，跳过胃口
                j--;
            }
        }
        return res;
    }

    /**
     * 分发饼干
     * @param g 胃口大小
     * @param s 饼干尺寸
     * @return
     */
    public int findContentChildren1(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        // 孩子指针
        int j = g.length - 1;
        // 尽可能把大的饼干分给胃口大的小孩 饼干
        for (int i = s.length - 1; i >= 0; i--) {
            // 跳过当前饼干不能满足的孩子
            while (j >= 0 && g[j] > s[i]) {
                j--;
            }
            // 当前饼干可以满足该小孩
            if (j >= 0) {
                res++;
                j--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindContentChildrenSolution findContentChildrenSolution = new FindContentChildrenSolution();
        int[] g = {1,2,3};
        int[] s = {1,1};
        System.out.println(findContentChildrenSolution.findContentChildren(g, s));


    }


}
