package com.hcx.algorithm.sort;

/**
 * @Title: MergeInsertSort.java
 * @Package com.hcx.algorithm.sort
 * @Description: 归并+插入
 * 小数据量且有序度高时，插入排序效果好；大数据量用归并效果更佳。可以结合二者。
 * @Author: hongcaixia
 * @Date: 2025/2/4 11:14
 * @Version V1.0
 */
public class MergeInsertSort {

    public static void main(String[] args) {
        int[] arr = {5,2,3,1};
        merge(arr);
        System.out.println(arr);
    }

    /**
     * 归并+插入
     *
     * @param arr
     */
    public static void merge(int[] arr) {
        splitSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    /**
     * 插入排序
     * 在left和right区间使用插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr, int left, int right) {
        // low为未排序区域的左边界，依次递增取出来，插入到已排序区域
        for (int low = left + 1; low <= right; low++) {
            int temp = arr[low];
            int i = low - 1;
            while (i >= left && arr[i] > temp) {
                arr[i + 1] = arr[i];
                i--;
            }
            if (i != low - 1) {
                // 空出的插入位置
                arr[i + 1] = temp;
            }
        }
    }

    /**
     * 切分数组，递归执行合并两个有序数组
     *
     * @param arr
     * @param left
     * @param right
     * @param result
     */
    public static void splitSort(int[] arr, int left, int right, int[] result) {
        // 只剩下一个元素，有序，返回
//        if (left == right) {
//            return;
//        }
        if (right - left <= 32) {
            // 当元素小于32个时，使用插入排序
            insertSort(arr, left, right);
            return;
        }
        //切分
        int mid = (left + right) >>> 1;
        // 左侧
        splitSort(arr, left, mid, result);
        // 右侧
        splitSort(arr, mid + 1, right, result);
        // 左右侧各自只有一个元素，就是最小的已排序区间 ，将已排序区间进行合并
        mergeSortedArr(arr, left, mid, mid + 1, right, result);
        // 合并完之后拷贝到原来的数组中
        System.arraycopy(result, left, arr, left, right - left + 1);
    }

    /**
     * 合并数组的两个有序区间到新的数组中
     *
     * @param arr
     * @param leftStart
     * @param leftEnd
     * @param rightStart
     * @param rightEnd
     * @param resultArr
     */
    public static void mergeSortedArr(int[] arr, int leftStart, int leftEnd, int rightStart, int rightEnd, int[] resultArr) {
        int newIndex = leftStart;
        while (leftStart <= leftEnd && rightStart <= rightEnd) {
            if (arr[leftStart] < arr[rightStart]) {
                resultArr[newIndex] = arr[leftStart];
                leftStart++;
            } else {
                resultArr[newIndex] = arr[rightStart];
                rightStart++;
            }
            newIndex++;
        }
        // 当某一侧已经遍历完，把另一侧剩下的全部直接复制到新数组
        if (leftStart > leftEnd) {
            // 左侧已经遍历完，把右侧的数据全部拷贝到新数组
            System.arraycopy(arr, rightStart, resultArr, newIndex, rightEnd - rightStart + 1);
        }
        if (rightStart > rightEnd) {
            // 右侧已经遍历完，把左侧的数据全部拷贝到新数组
            System.arraycopy(arr, leftStart, resultArr, newIndex, leftEnd - leftStart + 1);
        }
    }

}
