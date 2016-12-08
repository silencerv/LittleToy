package com.v.string;

/**
 * Created by v on 2016/12/7.
 */
public class StringToInteger {
    /**
     * 输入一个由数字组成的字符串，把它转换成整数并输出。例如：输入字符串"123"，输出整数123。
     * 给定函数原型int StrToInt(const char *str) ，实现字符串转换成整数的功能，不能使用库函数atoi。
     */
    public static int stringToInteger(char [] string){
        int res = 0;
        int i = 0;
        for (; i < string.length ; i ++){
            res = res * 10 + (string[i] - '0');
        }
        return res;
    }

    public static void main(String [] args){
        System.out.println(stringToInteger(new char[]{'2','3','1','3','9'}));
    }
}
