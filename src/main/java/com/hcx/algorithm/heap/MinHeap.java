package com.hcx.algorithm.heap;

/**
 * @Title: MaxHeap.java
 * @Package com.hcx.algorithm.heap
 * @Description: 小顶堆
 * @Author: hongcaixia
 * @Date: 2025/1/14 10:29
 * @Version V1.0
 */
public class MinHeap {

    int[] arr;

    int size;

    public MinHeap(int capacity) {
        arr = new int[capacity];
    }

    /**
     * 获取堆顶元素
     * @return
     */
    public int peek(){
        return arr[0];
    }

    /**
     * 移除堆顶元素并返回
     * 1.堆顶元素和末尾元素交换
     * 2.size--
     * 3.堆顶的元素依次下沉到正确的位置
     * @return
     */
    public int poll(){
        int top = arr[0];
        swap(0,top-1);
        size--;

        // 对堆顶元素依次执行下沉操作到正确位置
        downToProper(0);

        return top;
    }

    /**
     * 移除指定索引的元素并返回
     * @param index
     * @return
     */
    public int poll(int index){
        int ele = arr[index];
        swap(index, size-1);
        size--;
        downToProper(index);
        return ele;
    }

    /**
     * 替换堆顶元素
     * @param replaced
     */
    public void replaceTop(int replaced){
        arr[0] = replaced;
        downToProper(0);
    }

    /**
     * 在堆的尾部添加元素
     * @param offered
     * @return
     */
    public boolean offer(int offered) {
        if (size == arr.length) {
            return false;
        }
        upToProper(offered, size);
        size++;
        return true;
    }

    /**
     * 将元素上浮到正确位置
     * @param offered 元素
     * @param index  元素当前下标
     */
    private void upToProper(int offered, int index) {
        int childIndex = index;

        while (childIndex > 0) {
            // 计算他的父节点下标
            int parentIndex = (childIndex - 1) >> 1;
            if (offered < arr[parentIndex]) {
                // 父节点往下移动
                arr[childIndex] = arr[parentIndex];
            } else {
                break;
            }
            // 改变孩子节点为当前的父节点
            childIndex = parentIndex;
        }
        arr[childIndex] = offered;
    }

    /**
     * 将元素下沉到正确的位置
     * @param index 元素当前下标
     */
    private void downToProper(int index) {
        int minIndex = index;

        // 计算左右孩子节点
        int leftChildIndex = 2 * index + 1;
        if (leftChildIndex<size && arr[leftChildIndex] < arr[minIndex]) {
            minIndex = leftChildIndex;
        }
        int rightChildIndex = leftChildIndex + 1;
        if (rightChildIndex<size && arr[rightChildIndex] < arr[minIndex]) {
            minIndex = rightChildIndex;
        }
        // 找到了更大的孩子 交换元素
        if (minIndex != index) {
            swap(index, minIndex);
            downToProper(minIndex);
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
     * 建堆
     * 1.找到最后一个非叶子节点
     * 2.从最后一个非叶子节点从后向前，对每一个节点执行下沉操作（就是所有有孩子的父节点，都下沉到正确的位置（大顶堆：保证父节点是最大的，如果本身就是大的比较之后不需要下沉））
     */
    private void heapify() {
        // 最后一个非叶子节点索引 最后一个元素的父元素 (size-2)/2;
        int index = size / 2 - 1;
        for (int i = index; i >= 0; i--) {
            downToProper(i);
        }
    }

    public boolean isFull() {
        return size == arr.length;
    }
}
