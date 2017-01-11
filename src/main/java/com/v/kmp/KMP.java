package com.v.kmp;

/**
 * Created by v on 2016/12/29.
 */
public class KMP {

    public static int indexOf(char [] src,char [] subString){
        int index = -1;
        for (int i = 0 ; i < src.length ; i++){
            //长度不足的话查找失败
            if ((src.length - i) < subString.length){
                return -1;
            }
            for (int j = 0,k = i ; j < subString.length ; j++,k++){
                if (src[k] == subString[j]){
                    if (j == 0)
                        index = i;
                }else {

                }
            }
            if (index != -1)
                return index;
        }
        return index;
    }


    public static void main(String [] args){
        System.out.println(indexOf("asdlfjhlk".toCharArray(),"dlfj".toCharArray()));
    }
}
