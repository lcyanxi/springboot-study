package com.lcyanxi.leetcode.charStr;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * 打印一个字符串的全部子序列
 * @author lichang
 * @date 2021/4/5
 */
public class SubsequenceStr {
    public static void main(String[] args) {
        String str = "abced";
        System.out.println(process(str.toCharArray()));
    }

    private static List<String> process(char[] arr){
        List<String> res = Lists.newArrayList();
        for (int i = 0; i < arr.length; i++){

            res.add(String.valueOf(arr[i]));

            for(int j = i + 1; j < arr.length; j++){
                res.add(arr[i] + String.valueOf(arr[j]));
            }
        }
        return res;
    }

}
