package com.hcx.algorithm.recursion;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @Title: RecursionDemo.java
 * @Package com.hcx.algorithm.recursion
 * @Description: (用一句话描述该文件做什么)
 * @Author: hongcaixia
 * @Date: 2024/12/29 17:41
 * @Version V1.0
 */
public class RecursionDemo {

    public static void print(String str,int index){
        if(index==str.length()){
            return;
        }
        char c = str.charAt(index);
        print(str,++index);
        System.out.println(c);

    }

    public static void reverseMessage(String[] s, int index) {
        if(index==s.length-1){
            return;
        }
        reverseMessage(s,++index);
        System.out.println(s[index]);
    }


    public static String invoke(String[] source, int index, String target){
        if(index==source.length){
            return target;
        }
        String str = source[index];
        return invoke(source, ++index, target + " " + str);
    }


    public static String invoke1(String[] source, int index) {
        if (index == source.length) {
            return "";
        }
        String str = source[index++];
        String result = invoke1(source, index);
        if (result.isEmpty() || Objects.equals(str, "")) {
            return result + str;
        } else {
            return result + " " + str;
        }
    }

    public static int binarySearch(int[] arr, int target){
        int i = 0;
        int j = arr.length - 1;
        int middle = (i+j) >>> 1;
        while (i <=j) {
            if(arr[middle] > target){
                // 左边找
                j = middle - 1;
            }else if(arr[middle] < target){
                i = middle + 1;
            }else{
                return middle;
            }
        }
        return  -1;
    }

    public static int binarySearchRecur(int[] arr, int target ,int i,int j) {
        int middle = (i + j) >>> 1;
        if (j < i) {
            return -1;
        }
        if (arr[middle] > target) {
            // 左边找
            return binarySearchRecur(arr,target,i,middle - 1);
        } else if (arr[middle] < target) {
            // 右边找
            return binarySearchRecur(arr,target,middle + 1,j);
        } else {
            return middle;
        }
    }

    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-i-1; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    public static void bubbleSortRecur(int[] arr,int j) {
        if(j==0){
            return ;
        }
        // 把最大的元素交换到最右边
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1]) {
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }
        bubbleSortRecur(arr,j-1);
    }

    public static void bubbleSortRecur1(int[] arr,int j) {
        if(j==0){
            return ;
        }
        //边界右边的都是有序的
        int sortBoundaryIndex = 0;
        // 把最大的元素交换到最右边
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1]) {
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                sortBoundaryIndex = i;
            }
        }
        bubbleSortRecur1(arr,sortBoundaryIndex);
    }


    /**
     * 外层：low指针依次往后移动
     * 内层：插入逻辑：low指针指向的元素暂存起来，空出当前位置， 跟low前面的元素比较，
     * 比当前值小的元素 就是要插入的位置。
     * 没有比当前值小，就依次往后移动元素。
     * @param arr
     */
    public void insertSort(int[] arr, int low) {
        if (low == arr.length) {
            return;
        }
        // 内层循环的指针 i
        int i = low-1;
        // 待插入的元素
        int temp = arr[low];

        // 找到第一个比自己小的元素，就是要插入的位置
        while (i>=0 && arr[i] > temp){
            // 没找到，将当前元素往后移动一个，空出位置
            arr[i+1] = arr[i];
            // 继续往前找
            i --;
        }
        // 找到了第一个比自己小的元素，往右一个位置就是要插入的位置
        // 没有进循环，说明当前位置就是要插入的位置
        if (i != low - 1) {
            arr[i + 1] = temp;
        }

        insertSort(arr,low+1);

    }


    public static int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }


    public static int fibonacciCache(int n,int[] cache) {
        if (n == 0) {
            cache[0] = 0;
            return 0;

        }
        if (n == 1) {
            cache[1] = 1;
            return 1;
        }

        if(cache[n]!=-1){
            return cache[n];
        }else{
            cache[n] = fibonacciCache(n-1,cache)+fibonacciCache(n-2,cache);
            return cache[n];
        }

    }

    /**
     * a   b    c：
     * 1：第1根移动到c：a->c
     *
     *    把b和c交换：a->b
     *    把a和b交换：b->c
     *
     * 2：前1根移动到b：a->b   相当于1个的移法
     *    第2根移动到c：a->c
     *    前1根移动到c：b->c   相当于1个的移法
     *
     *    把b和c交换：a->c  a->b  c->b
     *    把a和b交换：b->a  b->c  a->c
     *
     * 3：前2根移动到b：a->c  a->b   c->b   相当于2个的移法
     *    第3根移动到c：a->c
     *    前2根移动到c：b->a  b->c  a->c    相当于2个的移法
     *
     *    把b和c交换：a->b  a->c   b->c  a->b  c->a  c->b  a->b
     *    把a和b交换：b->c  b->a   c->a  b->c  a->b  a->c  b->c
     *
     * 4: 前3根移动到b：a->b  a->c  b->c  a->b   c->a  c->b  a->b  相当于3个的移法
     *    第4根移动到c：a->c
     *    前3根移动到c：b->c  b->a  c->a  b->c   a->b  a->c  b->c  相当于3个的移法
     *
     * @param n
     * @param a
     * @param b
     * @param c
     */
    public void hanota(int n, LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c) {
        if (n == 0) {
            return;
        }
        //a->b
        hanota(n - 1, a, c, b);
        //a->c
        c.addLast(a.removeFirst()); //中间的一步
        //b->c
        hanota(n - 1, b, a, c);

//        //a->b
//        b.addLast(a.removeFirst());
//        //a->c
//        c.add(a.removeFirst());
//        //b->c
//        c.addLast(b.removeFirst());
    }


/**
    public void hanota(3, a, b, c){
        // 开始第一行的递归流程，即左侧的树
        hanota(3-1=2, a, c, b){

            hanota(2-1=1, a, b, c) {
                hanota(1 - 1 = 0, a, c, b) {
                    if (n == 0) {
                        return;
                    }
                }
                c.addLast(a.removeFirst());  // 1.a->c
                hanota(1 - 1 = 0, b, a, c){
                    if (n == 0) {
                        return;
                    }
                }
            }

            b.addLast(a.removeFirst()); //  2.a->b

            hanota(2 - 1 = 1, c, a, b) {
                hanota(1 - 1 = 0, c, b, a){
                    if (count == 0) {
                        return;
                    }
                }
                b.addLast(c.removeFirst()); // 3.c->b
                hanota(1 - 1 = 0, a, c, b){
                    if (count == 0) {
                        return;
                    }
                }
            }
            // 左侧的树执行完毕 即第一行的递归执行完毕
            //======================================

            c.addLast(a.removeFirst()); //  4.a->c

            // 开始第三行的递归流程 即右侧的树
            //======================================
            hanota(3-1=2, b, a, c){

                hanota(2-1=1, b, c, a){
                    hanota(1-1=0, b, a, c){
                        if (n == 0) {
                            return;
                        }
                    }
                    a.addLast(b.removeFirst()); // 5.b->a
                    hanota(1-1=0, c, b, a){
                        if (n == 0) {
                            return;
                        }
                    }
                }

                c.addLast(b.removeFirst()); // 6.b->c

                hanota(2-1=1, a, b, c){
                    hanota(1-1=0, a, c, b){
                        if (n == 0) {
                            return;
                        }
                    }
                    c.addLast(a.removeFirst()); // 7.a->c
                    hanota(1-1=0, b, a, c){
                        if (n == 0) {
                            return;
                        }
                    }
                }
            }
        }
**/

    public static int fib(int n){
        if(n<=1){
            return n;
        }
        int prePre = 0;
        int pre= 1;
        //|1|1|2|3|5|8|13|21|34|55|89|144|
        for(int i = 2;i<=n;i++){
            // 2
            int cur = prePre+pre;
            prePre = pre;
            pre = cur;
        }
        return pre;
    }


    /**
     获取第i行第j列的元素
     */
    public static int getEleRec(int i, int j){
        if(i==0 || j==0 || i==j){
            return 1;
        }
        return getEleRec(i-1,j-1)+getEleRec(i-1,j);
    }





    /**
        0: 1  0  0  0  0
        1: 1  1  0  0  0
        2: 1  2  1  0  0
        3: 1  3  [3]  1  0  ******* 第3行第2列  要取第i行第j列的元素  就是把第i-1行和第j列的元素相加
        4: 1  4  6  4  1
     */
    public static int getEle(int row, int col){
        int lastBefore = 1;
        int lastAfter = 1;

        if(row==0 || col==0 || row==col){
            return 1;
        }

        int cur = 0;
        int temp = 0;

        //从第2行第2列开始  值为 【第i-1行+第j列】
        for(int i = 2;i<=row;i++){  // 2
            for (int j = 1; j <=col ; j++) {
                //第2行第1列 值为第1行第1列相加
                cur = lastBefore+lastAfter;
                // 更新 上一行的变量为当前行的 给下一行用
                lastAfter =  cur;
                //lastBefore = 1;
            }
        }
        return cur;
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList();
        if(rowIndex==0){
            list.add(1);
            return list;
        }
        for(int i = 0;i<rowIndex;i++){
            list.add(getEleRec(rowIndex-1,i));
        }
        return list;
    }


    public static int[] getRowArr(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 1;
        if (n == 0) {
            return arr;
        }
        for (int i = 1; i <= n; i++) { // 要生成多少行
            for (int j = i; j > 0; j--) {
                //实际生成的行的元素（即列元素）
                // 生成每一行
                arr[j] = arr[j] + arr[j - 1];
            }
        }
        return arr;
    }


    public static void createRow(int[] arr,int rowIndex){
        if (rowIndex == 0) {
            arr[0] = 1;
            return;
        }
        for (int i = rowIndex; i >0; i--) {
            arr[i] = arr[i] + arr[i-1];
        }
    }

    // 1->2->3
    // 1<-2->3
    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;
        ListNode third = head.next.next;

        second.next = first;
        first.next = null;

        head = second;
        head.next = third;
        reverseList(head);
        return first;
    }


    public static ListNode reverseList10(ListNode head) {
        while(head.next!=null){
            // 指针方向改变
            head.next.next = head;
            ListNode headTemp = head.next;
            head.next = null;
            // 头指针向前移动
            head = headTemp;
        }
        return head;
    }

    public static ListNode reverseList11(ListNode head) {
        ListNode node = null;
        ListNode newHead = new ListNode(head.val,null);
        while(head.next!=null){
            node = new ListNode(head.next.val);
            node.next = newHead;
            // 指针方向改变
            head = head.next;
            newHead = node;
        }
        return newHead;
    }




    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }



    public static void main(String[] args) {

        ListNode node3 = new ListNode(3,null);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);

        System.out.println(node1.val);
        System.out.println(node1.next.val);
        System.out.println(node1.next.next.val);

        ListNode node = reverseList(node1);
        System.out.println("-===============-");

        System.out.println(node.val);
        System.out.println(node.next.val);
        System.out.println(node.next.next.val);



//        int[] rowArr = getRowArr(4);
//
//        for (int i = 0; i < rowArr.length; i++) {
//            System.out.print(rowArr[i]+" ");
//        }

//        int ele1 = getEle(3, 2);
//        System.out.println("动态规划获取到的元素："+ele1);


//        int ele = getEleRec(4, 2);
//        System.out.println("杨辉三角获取的元素是："+ele);
//        List<Integer> row = getRow(3);
//        System.out.println("杨辉三角第n行的元素是"+ row);


//        System.out.println("888888");
//
//        int fib = fib(45);
//        System.out.println("ddd"+fib);
//
//        System.out.println("*************8");
//
//        int[] arr11 = new int[]{1, 2, 4, 4, 4, 7, 8};
//
//
//        int i6 = binarySearchRecur(arr11, 7, 0, arr11.length - 1);
//
//        System.out.println(i6);
//
//        System.out.println("0000000");
//
//        int[] arr2 = new int[]{9, 12, 4, 14, 8, 7, 18};
//        int[] arr3 = bubbleSort(arr2);
//        for (int i = 0; i < arr3.length; i++) {
//            System.out.println(arr3[i]);
//        }
//
//        System.out.println("1111111");
//
//
//        int[] arr5 = new int[]{9, 12, 4, 14, 8, 7, 18};
//        bubbleSortRecur1(arr5,arr5.length-1);
//        for (int i = 0; i < arr5.length; i++) {
//            System.out.println(arr5[i]);
//        }
//
//        System.out.println("22222");
//
//        String[] arr = new String[]{"the", "sky", "is", "blue"};
//        reverseMessage(arr,-1);
//
//        System.out.println("====");
//
//
//        String[] arr1 = new String[]{"the", "sky", "is", "blue"};
//        String invoke = invoke1(arr1, 0);
//        String[] s = invoke.split(" ");
//        for (int i = 0; i < s.length; i++) {
//            System.out.println(s[i]);
//        }

    }
}
