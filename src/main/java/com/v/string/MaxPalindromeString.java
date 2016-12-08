package com.v.string;

import java.util.Arrays;

/**
 * Created by v on 2016/12/8.
 */
public class MaxPalindromeString {

    public static int maxPalindromeString(char [] string){
        int len = string.length;
        char joinChar = '#';
        char [] joinString = new char[len * 2 + 1];
        joinString[0] = joinChar;
        for (int i = 1 ,j = 0 ; i < joinString.length ; j++){
            joinString[i++] = string[j];
            joinString[i++] = joinChar;
        }
        int [] palindromeCount = new int[joinString.length];
        int pos = 0;
        int maxRightLen = 0;
        for (int i = 0 ; i < joinString.length ; i++){
            palindromeCount[i] = 1;
            if (i < pos){
                palindromeCount[i] = palindromeCount[i] = Math.min(palindromeCount[2 * pos - i],maxRightLen - i);
            }
            while (palindromeCount[i] + i < joinString.length && i - palindromeCount[i] > -1 && joinString[palindromeCount[i] + i] == joinString[i - palindromeCount[i]])
                palindromeCount[i]++;
            if (palindromeCount[i] >= maxRightLen){
                maxRightLen = palindromeCount[i];
                pos = i;
            }
        }
        Arrays.sort(palindromeCount);//其实不需要排序
        return palindromeCount[joinString.length - 1] -1 ;
    }

    public static void main(String [] args){
        System.out.println(maxPalindromeString("abcsdcvasdfasdabcdffdcba".toCharArray()));
    }
}
