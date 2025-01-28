package com.hcx.algorithm.sort;

/**
 * @Title: HeapSort.java
 * @Package com.hcx.algorithm.sort
 * @Description: 堆排序
 * @Author: hongcaixia
 * @Date: 2025/1/27 19:13
 * @Version V1.0
 */
public class HeapSort {

    /**
     * 堆排序
     *
     * @param arr
     */
    private int[] heapSort(int[] arr) {
        int size = arr.length;
        // 1.建堆 堆顶元素是最大的
        heapify(arr, size);

        while (size > 1) {
            // 2.堆顶元素与堆底交换 升序排列 最大的元素排在最右侧
            swap(arr, 0, size - 1);
            // 缩小堆
            size--;
            // 调整堆
            downToProper(0, arr, size);
        }
        return arr;
    }


    public int[] sort(int[] arr) {
        int size = arr.length;
        // 1.建堆 堆顶元素是最大的
        heapify(arr, size);
        for (int right = arr.length - 1; right > 0; right--) {
            swap(arr, 0, right);
            downToProper(0, arr, right);
        }
        return arr;
    }

    /**
     * 建堆
     */
    public void heapify(int[] arr,int size) {
        int index = size / 2 - 1;
        for (int i = index; i >= 0; i--) {
            downToProper(i, arr, size);
        }
    }


    /**
     * 交换两个元素
     *
     * @param i
     * @param j
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 元素下沉到正确位置
     *
     * @param parentIndex 父节点的索引
     * @param arr         排序数组
     * @param size        数组大小
     */
    private void downToProper(int parentIndex, int[] arr, int size) {
        while (true) {
            // 假设父节点索引的值是最大的
            int maxIndex = parentIndex;
            // 计算出左右孩子节点的索引
            int leftIndex = 2 * parentIndex + 1;
            int rightIndex = leftIndex + 1;
            if (leftIndex < size && arr[leftIndex] > arr[maxIndex]) {
                // 左孩子值比父节点值大，替换为左孩子索引
                maxIndex = leftIndex;
            }
            if (rightIndex < size && arr[rightIndex] > arr[maxIndex]) {
                // 右孩子值比父节点值大，替换为右孩子
                maxIndex = rightIndex;
            }
            if (maxIndex == parentIndex) {
                // 父节点已经是最大的了 没有更大的孩子
                break;
            }
            // 父节点不是最大的，发生了交换
            int temp = arr[maxIndex];
            arr[maxIndex] = arr[parentIndex];
            arr[parentIndex] = temp;

            // 更新parentIndex
            parentIndex = maxIndex;
        }
    }

}
