package com.v.string;

/**
 * Created by v on 2016/12/8.
 */
public class StringAllArranged {


    public static void swap(char [] arr,int src,int pos){
        char temp = arr[src];
        arr[src] = arr[pos];
        arr[pos] = temp;
    }

    public static void allArrangedString(char [] string,int form,int to){
        if (form == to)
            System.out.println(new String(string));
        for (int i = form ; i <= to ; i++){
            swap(string,form,i);
            allArrangedString(string,form + 1,to);
            swap(string,form,i);
        }
    }

    public static void main(String [] args){
        allArrangedString("abcde".toCharArray(),0,3);
    }
}
