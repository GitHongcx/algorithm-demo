package com.hcx.algorithm.pointer;

/**
 * @Title: TheLongPalindrome.java
 * @Package com.hcx.algorithm.pointer
 * @Description: leetcode5.最长回文子串
 * @Author: hongcaixia
 * @Date: 2026/3/11 11:16
 * @Version V1.0
 */
public class TheLongPalindrome {

    public static String longestPalindrome(String s) {
        if(s==null || s.length() ==1){
            return s;
        }
        int start = 0;
        int end = 0;
        for(int i = 0;i<s.length();i++){
            int left = i;
            int right = i+1;
            int len1 = expandLength(s,left,left);
            int len2 = expandLength(s,left,right);
            int len = Math.max(len1,len2);
            if(len > end - start){
                start = i - (len-1)/2;
                end = len/2 + i;
            }
        }
        return s.substring(start, end+1);
    }

    /**
     * 获取回文串的长度
     * @param s
     * @param left
     * @param right
     * @return
     */
    public static int expandLength(String s, int left, int right) {
        while(left>=0 && right<s.length()) {
            int leftValue = s.charAt(left);
            int rightValue = s.charAt(right);
            if (leftValue == rightValue) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return right - left - 1;
    }

    public static String longestPalindrome1(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] charArray = s.toCharArray();
        if (s.length() == 2 && charArray[0] == charArray[1]) {
            return s;
        }
        if (s.length() == 2) {
            return s.substring(0, 1);
        }
        int length = 0;
        String res = null;
        // 从第二位开始作为起始位置
        for (int i = 1; i < charArray.length - 1; i++) {
            int left = i - 1;
            int right = i + 1;
            StringBuilder sb = new StringBuilder();
            int tempLength = 0;
            while (left >= 0 && left < right && right < charArray.length) {
                if (charArray[left] == charArray[right]) {
                    sb.append(charArray[left]).append(charArray[i]).append(charArray[right]);
                    left--;
                    right++;
                    tempLength++;
                } else {
                    if (length == 0) {
                        if (charArray[left] == charArray[i]) {
                            sb.append(charArray[left]).append(charArray[i]);
                            tempLength++;
                        }
                        if (charArray[right] == charArray[i]) {
                            sb.append(charArray[i]).append(charArray[right]);
                            tempLength++;
                        }
                    }
                    break;
                }
                length = tempLength;
            }
            if (tempLength >= length) {
                res = sb.toString();
                length = tempLength;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "babad";
        String res = longestPalindrome(s);
        System.out.println(res);
    }

}
