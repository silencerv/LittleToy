package com.v.array;

import java.util.Arrays;

/**
 * Created by v on 2016/12/12.
 */
public class TwoNumberSum {

    /**
     * 寻找和为定值的两个数

     题目描述

     输入一个数组和一个数字，在数组中查找两个数，使得它们的和正好是输入的那个数字。
     要求时间复杂度是O(N)。如果有多对数字的和等于输入的数字，输出任意一对即可。
     例如输入数组1、2、4、7、11、15和数字15。由于4+11=15，因此输出4和11。
     */
    public static int [] twoSumOne(int [] arr,int sum){
        Arrays.sort(arr);
        int [] reverseArr = new int[arr.length];
        for (int i = 0 ; i < arr.length ; i++){
            reverseArr[i] = sum - arr[i];
        }
        for (int i = 0, j = reverseArr.length -1  ; i < arr.length ;){
            if (arr[i] == reverseArr[j] )
                return new int[]{arr[i],arr[j]};
            else if (arr[i] > reverseArr[j]){
                j--;
            }else {
                i++;
            }
        }
        return null;
    }

    public static void main(String [] args){
        System.out.println(Arrays.toString(twoSumOne(new int []{1,2,4,7,11,15},15)));
    }
}
