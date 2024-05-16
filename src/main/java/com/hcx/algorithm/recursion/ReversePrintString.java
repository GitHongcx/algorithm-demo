package com.hcx.algorithm.recursion;

/**
 * @Title: ReversePrintString.java
 * @Package com.hcx.algorithm.recursion
 * @Description: 逆序打印字符串
 * @Author: hongcaixia
 * @Date: 2024/5/16 09:03
 * @Version V1.0
 */
public class ReversePrintString {

    /**
     * 逆序打印
     * @param str
     * @param index
     */
    public static void reversePrint(String str,int index) {
        if (index == str.length()) {
            return;
        }
        reversePrint(str, index + 1);
        System.out.println(str.charAt(index));
    }

//    reversePrint(String str,0) {
//        reversePrint(str, 1) {
//            reversePrint(str, 2) {
//                reversePrint(str, 3) {
//                    reversePrint(str, 4) {
//                        if (3 == str.length()) {
//                            return;
//                        }
//                    }
//                    System.out.println(str.charAt(3));
//                }
//                System.out.println(str.charAt(2));
//            }
//            System.out.println(str.charAt(1));
//        }
//        System.out.println(str.charAt(0));
//    }

    public static void main(String[] args) {
        reversePrint("abcd",0);
    }
}
