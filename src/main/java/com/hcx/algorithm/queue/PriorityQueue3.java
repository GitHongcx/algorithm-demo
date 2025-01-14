package com.hcx.algorithm.queue;

/**
 * @Title: PriorityQueue1.java
 * @Package com.hcx.algorithm.queue
 * @Description: 优先级队列：使用大顶堆实现
 * @Author: hongcaixia
 * @Date: 2025/1/12 17:33
 * @Version V1.0
 */
public class PriorityQueue3<E extends Priority> implements Queue<E> {

    Priority[] array;

    // 数组当前大小
    int size;

    public PriorityQueue3(int capacity) {
        array = new Priority[capacity];
    }

    /**
     * 1.新元素添加到数组的尾部
     * 2.要符合大顶堆的特性，还需要对堆进行调整：
     *   循环比较新元素与父节点的优先级，如果父节点的优先级低，则往下移动到子节点的位置，直到父节点的优先级更高或者childIndex==0
     * @param e
     * @return
     */
    @Override
    public boolean offer(E e) {
        if (isFull()) {
            return false;
        }
        int childIndex = size;
        int parentIndex = (childIndex - 1) / 2;
        // array[childIndex] = e;
        while (childIndex > 0 && e.getPriority() > array[parentIndex].getPriority()) {
            // 把父节点放到子节点上 上层的元素依次往下一层放
            array[childIndex] = array[parentIndex];
            // 两个指针继续往上找，直到不符合条件为止
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
        array[childIndex] = e;
        size++;
        return true;
    }

    /**
     * 1.移除并返回优先级最高的元素，即根元素
     * 2.要符合堆的特性，还需要对堆进行调整
     *   - 把堆顶元素与最末尾元素进行交换，移除并返回最末尾的元素，大小减1
     *   - 调整堆：对堆顶的根元素依次与孩子节点作比较，如果优先级比孩子节点小，往下移动，直到父节点优先级大于孩子节点优先级或者没有孩子节点为止。
     * @return
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        // 1.堆顶元素和最末尾元素交换
        Priority top = array[0];
        array[0] = array[size - 1];
        array[size - 1] = top;

        // 2.数组大小-1
        size--;

        // 要返回的元素
        Priority e = array[size];
        // help gc
        array[size] = null;

        // 3.调整堆顶元素位置，依次往下找到正确的位置
        downToProper(0);

        return (E) e;
    }

    /**
     * 将父节点元素下沉到正确的位置
     * 从堆顶开始，依次将父元素与孩子节点中较大的进行交换，直到父元素大于两个孩子，或者没有孩子为止。
     * @param parentIndex 父节点索引
     */
    public void downToProper(int parentIndex) {
        // 该节点的左孩子
        int leftChildIndex = parentIndex * 2 + 1;
        // 右孩子
        int rightChildIndex = leftChildIndex + 1;

        // 父节点索引 先假设本身的优先级最高，如果有比他高的 就替换掉他
        int maxIndex = parentIndex;

        if (leftChildIndex < size && array[leftChildIndex].getPriority() > array[maxIndex].getPriority()) {
            // 左孩子节点的优先级比父节点大，将maxIndex 设置为左孩子索引
            maxIndex = leftChildIndex;
        }
        if (rightChildIndex < size && array[rightChildIndex].getPriority() > array[maxIndex].getPriority()) {
            // 右孩子节点的优先级比父节点大，将maxIndex设置为右孩子索引
            maxIndex = rightChildIndex;
        }

        // 说明有更大的孩子节点
        if (maxIndex != parentIndex) {
            // 把父节点和该节点交换
            Priority temp = array[maxIndex];
            array[maxIndex] = array[parentIndex];
            array[parentIndex] = temp;
            // 继续往下找
            downToProper(maxIndex);
        }
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[0];
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
