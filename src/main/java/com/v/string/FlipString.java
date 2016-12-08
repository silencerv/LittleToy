package com.v.string;

import java.util.Arrays;

/**
 * Created by v on 2016/12/7.
 */
public class FlipString {

    /**
     * 给定一个字符串，要求把字符串前面的若干个字符移动到字符串的尾部，
     * 如把字符串“abcdef”前面的2个字符'a'和'b'移动到字符串的尾部，
     * 使得原字符串变成字符串“cdefab”。请写一个函数完成此功能，
     * 要求对长度为n的字符串操作的时间复杂度为 O(n)，空间复杂度为 O(1)。
     */
    //解法一：暴力移位法
    public static void flipOneCharString(char[] string) {
        char temp = string[0];
        int len = string.length;
        for (int i = 1 ; i < len ;i++){
            string[i-1] = string[i];
        }
        string[len -1 ] = temp;
    }

    public static void flipString(char [] string,int n){
        while (n-- > 0){
            flipOneCharString(string);
        }
    }
    //解法二：三步反转法
    public static void reverseString(char [] string,int from ,int to){
        char temp;
        while(from < to){
            temp = string[from];
            string[from++] = string[--to];
            string[to] = temp;
        }
    }

    public static void reverseString(char [] string,int n){
        int len = string.length;
        reverseString(string,0,n);
        reverseString(string,n,len);;
        reverseString(string,0,len);
    }

    public static void main(String [] args){
        char [] string = new char[]{'a','b','c','d','e','f'};
        reverseString(string,2);
        System.out.println(Arrays.toString(string));
    }
}
