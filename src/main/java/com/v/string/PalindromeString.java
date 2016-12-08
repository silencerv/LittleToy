package com.v.string;

/**
 * Created by v on 2016/12/7.
 */
public class PalindromeString {

    /**
     * 回文，英文palindrome，
     * 指一个顺着读和反过来读都一样的字符串，比如madam、我爱我，
     * 这样的短句在智力性、趣味性和艺术性上都颇有特色，中国历史上还有很多有趣的回文诗。
     */

    /**
     * 从两头向中间扫描
     * @param string
     * @return
     */
    public static boolean palindromeString(char [] string){
        int left = 0;
        int right = string.length - 1;
        while(left < right && string[left++] == string[right--]);
        if (left < right )
            return false;
        else if (string[--left] != string[++right])
            return false;
        return true;
    }

    public static void main(String [] args){
        System.out.println(palindromeString("madam".toCharArray()));
    }
}
