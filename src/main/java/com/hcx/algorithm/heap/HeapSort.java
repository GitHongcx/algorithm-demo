package com.hcx.algorithm.heap;

/**
 * @Title: HeapSort.java
 * @Package com.hcx.algorithm.heap
 * @Description: 堆排序
 * 1.建立大顶堆
 * 2.让堆顶的元素与堆底的元素交换，缩小堆的大小，调整堆
 * 3.重复步骤二直到堆中仅剩一个元素
 *
 * @Author: hongcaixia
 * @Date: 2025/1/14 11:52
 * @Version V1.0
 */
public class HeapSort {

    static int[] arr;

    int size;

    public HeapSort(int[] array) {
        this.arr = array;
        this.size = array.length;
        heapify();
    }

    private void heapSort() {
        // 1.建堆
        heapify();

        while (size > 1) {
            // 2.堆顶元素与堆底交换
            swap(0, size - 1);
            // 缩小堆
            size--;
            // 重建堆
            downToProper(0);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 9, 3, 2, 6, 8, 7, 5};
        HeapSort heapSort = new HeapSort(array);
        heapSort.heapSort();
        for (int i = 0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }


    public void heapify() {
        int index = (arr.length - 1) / 2 - 1;
        for (int i = index; i >= 0; i--) {
            downToProper(i);
        }
    }


    /**
     * 交换两个元素
     * @param i
     * @param j
     */
    private void swap(int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 将元素下沉到正确的位置
     * @param index 元素当前下标
     */
    private void downToProper(int index) {
        int maxIndex = index;

        // 计算左右孩子节点
        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex < size && arr[leftChildIndex] > arr[maxIndex]) {
            maxIndex = leftChildIndex;
        }
        int rightChildIndex = leftChildIndex + 1;
        if (rightChildIndex < size && arr[rightChildIndex] > arr[maxIndex]) {
            maxIndex = rightChildIndex;
        }
        // 找到了更大的孩子 交换元素
        if (maxIndex != index) {
            swap(index, maxIndex);
            downToProper(maxIndex);
        }
    }

}
