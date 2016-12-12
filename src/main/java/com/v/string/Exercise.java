package com.v.string;

/**
 * Created by v on 2016/12/12.
 */
public class Exercise {

    /**
     * 只出现一次的字符
     * @return
     */
    public static char onceChar(String str){
        char [] string = str.toCharArray();
        int [] charCount = new int[26 * 2];
        for (int i = 0 ; i < string.length ; i++){
            ++charCount[string[i] - 'A'];
        }
        for (int i = 0 ; i < charCount.length ; i++){
            if (charCount[i] == 1)
                return (char)(i + 'A');
        }
        return 1;
    }

    /**
     * 最长的对称字符串的长度
     * @param str
     * @return
     */
    public static int maxSymmetryLength(String str){
        char [] srcArr = str.toCharArray();
        char [] tempArr = new char[2 * srcArr.length + 1];
        tempArr[0] = '#';
        for (int i = 1,j = 0 ; j < srcArr.length ; j++){
            tempArr[i++] = srcArr[j];
            tempArr[i++] = '#';
        }
        int max = 0;
        int pos = 0;
        int [] lens = new int[tempArr.length];
        for (int i = 0 ; i < tempArr.length ; i++){
            int tempLen = 1;
            if (pos + max > i){
                tempLen = Math.min(lens[2 * pos - i],max);
            }
            while( (i + tempLen) < tempArr.length &&(i - tempLen) > -1 && tempArr[i + tempLen] == tempArr[i - tempLen])
                tempLen++;
            if (tempLen >= max){
                max = tempLen;
                pos = i;
            }
            //save
            lens[i] = tempLen;
        }
        return max - 1;
    }


    public static void main(String [] args){
        System.out.println(maxSymmetryLength("g35oo53gle"));
    }
}
