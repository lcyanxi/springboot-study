package com.lcyanxi.basics.leetcode.charStr;

/**
 * 给定一个字符串str和长度leftSize，请把字符串leftSize左边的整体部分与右边交换要求额外空间复杂度O(1)
 * @author lichang
 * @date 2021/4/4
 */
public class ReverseStr {
    public static void main(String[] args) {

        String str = "abcdefg";
        System.out.println("之前字符串:" + str);
        char[] chars = str.toCharArray();
        process(chars,3);
        System.out.println("交换之后的字符串:" + String.valueOf(chars));

    }

    private static void process(char [] arr,int size){
        int length = arr.length - 1;
        reverse(arr,0,size);
        reverse(arr,size + 1,length);
        reverse(arr,0,length);
    }
    private static void reverse(char[] arr,int left,int right){
        while (left < right){
            char temp = arr[right];
            arr[right--] = arr[left];
            arr[left++] = temp;
        }
    }
}
