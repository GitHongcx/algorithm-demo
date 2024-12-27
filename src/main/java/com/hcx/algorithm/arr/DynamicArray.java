package com.hcx.algorithm.arr;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * @Title: DynamicArray.java
 * @Package com.hcx.algorithm.arr
 * @Description: 动态数组
 * @Author: hongcaixia
 * @Date: 2024/12/26 17:02
 * @Version V1.0
 */
public class DynamicArray implements Iterable<Integer>{

    // 逻辑大小
    private int size = 0;

    // 容量
    private int capacity = 8;

    private int[] arr = {};

    /**
     * 往最后位置插入元素
     * @param element
     */
    public void addLast(int element) {
//        arr[size] = element;
//        size++;
        addIndex(size, element);
    }

    /**
     * 按照索引添加元素
     * @param index
     * @param element
     */
    public void addIndex(int index, int element) {

        //检查容量
        checkAndGrow();

        if(index>0 && index<size){
            System.arraycopy(arr,index,arr,index+1,size-index);
        }
        arr[index]=element;
        size++;
    }

    /**
     * 检查容量并扩容
     */
    private void checkAndGrow() {
        //第一次添加 创建初始容量的数组
        if (size == 0) {
            arr = new int[capacity];
        } else if (size == capacity) {
            //扩容为原来的1.5倍
            capacity += capacity >> 1;
            int[] newArr = new int[capacity];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }
    }

    /**
     * 根据索引移除元素
     * @param index
     * @return
     */
    public int remove(int index) {
        int removed = arr[index];

        // 不是最后一个元素才需要移动
        if (index < size - 1) {
            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        }
        size--;
        return removed;
    }



    /**
     * 遍历数组
     * @param consumer
     */
    public void foreach(Consumer<Integer> consumer){
        for (int i = 0; i < size; i++) {
            consumer.accept(arr[i]);
        }
    }

    /**
     * 迭代器遍历
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int i = 0;

            //有没有下一个元素
            @Override
            public boolean hasNext() {
                return i < size;
            }

            //返回当前元素，指针移动到下一个元素
            @Override
            public Integer next() {
                return arr[i++];
            }
        };
    }

    /**
     * stream 遍历
     * @return
     */
    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(arr, 0, size));
    }
}
