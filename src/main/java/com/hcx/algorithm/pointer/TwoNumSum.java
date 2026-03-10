package com.hcx.algorithm.pointer;

/**
 * @Title: TwoNumSum.java
 * @Package com.hcx.algorithm.pointer
 * @Description: 两数之和 输入有序数组
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 * 你所设计的解决方案必须只使用常量级的额外空间。
 * @Author: hongcaixia
 * @Date: 2026/3/10 16:49
 * @Version V1.0
 */
public class TwoNumSum {

    public static int[] twoSum(int[] numbers, int target) {
        int leftIndex = 0;
        int rightIndex = numbers.length-1;
        while(leftIndex<rightIndex){
            if(numbers[leftIndex]+numbers[rightIndex]>target){
                rightIndex--;
            }else if(numbers[leftIndex]+numbers[rightIndex]<target){
                leftIndex++;
            }else{
                break;
            }
        }
        return new int[] { leftIndex + 1, rightIndex + 1 };
    }

    public static void main(String[] args) {
        int[] numbers = new int[] {2,7,11,15};
        int target = 9;
        int[] ints = twoSum(numbers, target);
        System.out.println(ints.toString());


    }

}
