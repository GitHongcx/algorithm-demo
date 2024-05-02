package com.hcx.algorithm.search;

/**
 * @Title: BinarySearch.java
 * @Package com.hcx.algorithm.search
 * @Description: 从有序数组中找到num
 * @Author: hongcaixia
 * @Date: 2024/1/28 19:48
 * @Version V1.0
 */
public class BinarySearch {

    public static void main(String[] args) {


        int[] arr11 = new int[]{1, 2, 4, 4, 4, 7, 8};
        int i5 = binarySearchLeftMost1(arr11, 4);
        System.out.println(i5);


        int[] arr = new int[]{1, 4, 4, 4, 7, 8, 10, 15};
        int i3 = binarySearchLeftMost(arr, 4);
        int i4 = binarySearchRightMost(arr,4);
        System.out.println("最左侧："+ i3+" 最右侧："+i4);


        int index = binarySearchBasic(arr, 4);
        System.out.println("基础版二分查找索引：" + index);

        int i1 = 0;
        int j1 = Integer.MAX_VALUE;
        int mid1 = (i1 + j1) / 2;

        int i2 = mid1 + 1;
        int mid2 = (i2 + j1) / 2; //-536870912
        int mid22 = ((i2 + (j1 - i2))+i2) / 2; //1073741823
        int mid33 =  (i2 + j1) >>> 1;
        System.out.println("最大值问题" + mid2 + " " + mid22 + " " + mid33);//最大值问题 -536870912   1073741823


        int resultIndex = searchNum(arr, 15);

        int[] arr1 = new int[]{1, 2, 4, 4, 4, 6, 6, 7, 8, 10, 15};
        int i = searchLeftNum(arr1, 4);
        System.out.println("大于等于4的最左边的元素下标是：" + i);
        System.out.println(arr1);

        int j = searchRightNum(arr1, 6);
        System.out.println("小于等于4的最右边的元素下标是：" + j);

        int[] arr2 = new int[]{3, 2, 3, 2, 3};
        int k = partMinIndex(arr2);
        System.out.println("局部最小的小标是：" + k);

        int[] arr3 = new int[]{3, 4, 3, 2, 1};
        int peakElement = findPeakElement(arr3);
        System.out.println("局部最大的下标是：" + peakElement);
    }


    /**
     * 二分查找基础版 左闭右闭
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearchBasic(int[] arr,int target) {
        int i = 0;
        int j = arr.length - 1;

        while (i <= j) {
            // int mid = (i + j) / 2;
            int mid = (i + j) >>> 1;
            if (target < arr[mid]) {
                // 去到左区间查找
                j = mid - 1;
            } else if (target > arr[mid]) {
                // 去到右区间查找
                i = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找基础版 左闭右开
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearch(int[] arr,int target) {
        int i = 0;
        int j = arr.length;  // 第一处不同

        while (i < j) {     // 第二处不同
            int mid = (i + j) >>> 1;
            if (target < arr[mid]) {
                // 去到左区间查找
                j = mid;   // 第三处不同
            } else if (target > arr[mid]) {
                // 去到右区间查找
                i = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 平衡二分查找
     * @param arr
     * @param target
     * @return
     */
    public static int balanceBinarySearch(int[] arr, int target) {
        int i = 0;
        int j = arr.length;
        while (j - i > 1) {
            //当i和j中间还有元素的时候就继续
            int mid = (i + j) >>> 1;
            if (target < arr[mid]) {
                j = mid;
            } else {
                i = mid;
            }
        }
        if (arr[i] == target) {
            return i;
        } else {
            return -1;
        }
    }

    /**
     * 二分查找，返回最左侧的目标元素
     * @return
     */
    public static int binarySearchLeftMost(int[] arr, int target) {
        int i = 0;
        int j = arr.length - 1;
        int candidate = -1 ;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            if (target < arr[mid]) {
                // 去到左区间查找
                j = mid - 1;
            } else if (target > arr[mid]) {
                // 去到右区间查找
                i = mid + 1;
            } else {
                //相等了不立刻返回，而是找到最左侧的，再返回
                candidate = mid;
                //继续向左查找，看看是否还有相同的
                j = mid - 1;
            }
        }
        return candidate;
    }

    /**
     * 二分查找，返回最右侧的目标元素
     * @return
     */
    public static int binarySearchRightMost(int[] arr, int target) {
        int i = 0;
        int j = arr.length - 1;
        int candidate = -1 ;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            if (target < arr[mid]) {
                // 去到左区间查找
                j = mid - 1;
            } else if (target > arr[mid]) {
                // 去到右区间查找
                i = mid + 1;
            } else {
                //相等了不立刻返回，而是找到最右侧的，再返回
                candidate = mid;
                //继续向左查找，看看是否还有相同的
                i = mid + 1;
            }
        }
        return candidate;
    }


    /**
     * 优化最左查找返回值
     * @param arr
     * @param target
     * @return 返回大于等于目标的最靠左的索引
     */
    public static int binarySearchLeftMost1(int[] arr, int target) {
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            if (target <= arr[mid]) {
                // 去到左区间查找
                j = mid - 1;
            } else {
                // 去到右区间查找
                i = mid + 1;
            }
        }
        return i;
    }


    /**
     * 优化最右查找返回值
     * @param arr
     * @param target
     * @return 小于等于目标的最靠右的索引
     */
    public static int binarySearchRightMost1(int[] arr, int target) {
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            if (target < arr[mid]) {
                // 去到左区间查找
                j = mid - 1;
            } else {
                // 去到右区间查找
                i = mid + 1;
            }
        }
        return i - 1;
    }

    /**
     * 从有序数组中找到num
     * [1,4,7,8,10,15]
     * @param arr
     * @param num
     * @return
     */
    public static int searchNum(int[] arr,int num) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            int midEle = arr[mid];
            if (num == midEle) {
                return mid;
            } else if (num < midEle) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 从有序数组中找到第一个大于等于num的数的下标
     * @param arr
     * @param num
     * @return
     */
    public static int searchLeftNum(int[] arr,int num) {
        int left = 0;
        int right = arr.length - 1;
        int leftIndex = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= num) {
                right = mid - 1;
                leftIndex = mid;
            } else {
                left = mid + 1;
            }
        }
        return leftIndex;
    }

    /**
     * 从有序数组中找到最后一个小于等于num的数的下标
     * 1,2,4,4,4,6,7,8,10,15
     * @param arr
     * @param num
     * @return
     */
    public static int searchRightNum(int[] arr,int num) {
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int midIndex = (left + right) / 2;
            if (arr[midIndex] <= num) {
                index = midIndex;
                left = midIndex + 1;
            } else {
                right = midIndex - 1;
            }
        }
        return index;
    }

    /**
     * 求局部最小
     * 在一个无序数组中，相邻的数肯定不相等。求任意一个局部最小数
     * @param arr
     * @return
     */
    public static int partMinIndex(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        int left = 0;
        int right = arr.length - 1;
        if (arr[left] < arr[left + 1]) {
            return left;
        }
        if (arr[right - 1] > arr[right]) {
            return right;
        }
        // 走到这里说明左边呈下降趋势，右边呈上升趋势，中间一定有局部最小
        int resultIndex = -1;
        while (left <= right) {
            int midIndex = (left + right) / 2;
            if (midIndex == 0) {
                return midIndex + 1;
            }
            if (midIndex == arr.length - 1) {
                return arr.length - 2;
            }
            if (arr[midIndex - 1] > arr[midIndex] && arr[midIndex + 1] > arr[midIndex]) {
                resultIndex = midIndex;
                break;
            }
            if (arr[midIndex - 1] < arr[midIndex]) {
                right = midIndex - 1;
            } else {
                left = midIndex + 1;
            }
        }
        return resultIndex;
    }

    /**
     * 局部最大
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        int length = nums.length;
        if(nums[0]>nums[1]){
            return 0;
        }
        if(nums[length-1]>nums[length-2]){
            return length-1;
        }
        int left = 0;
        int right = length-1;
        while(left<=right){
            int midIndex = (left+right)/2;
            if(midIndex==0){
                return 1;
            }
            if(midIndex==length-1){
                return length-2;
            }
            if((midIndex-1)==0 && nums[midIndex]> nums[midIndex+1]){
                return midIndex;
            }
            if((midIndex+1)==length && nums[midIndex] > nums[midIndex -1]){
                return midIndex-1;
            }
            if(nums[midIndex]>nums[midIndex-1] && nums[midIndex]>nums[midIndex+1]){
                return midIndex;
            }
            if(nums[midIndex] < nums[midIndex -1]){
                right = midIndex -1 ;
            }else{
                left = midIndex +1;
            }
        }
        return -1;
    }
}
