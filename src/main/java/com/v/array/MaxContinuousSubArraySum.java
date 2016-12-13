package com.v.array;

/**
 * Created by v on 2016/12/13.
 * 最大子数组和
 */
public class MaxContinuousSubArraySum {

    /**
     * 应该丢弃每次计算开头的所有负数
     * 如果计算结果已经为负数了就直接丢弃掉
     * @param arr
     * @return
     */
    public static int maxSubArraySum(int [] arr){
        int max = 0;
        int eachMax = 0;
        for (int i = 0 ; i < arr.length ;i++){
            int res = (eachMax += arr[i]);
            if ( res < 0) {
                eachMax = 0;
                continue;
            }
            if ( res > max)
                max = eachMax;
        }
        return max;
    }

    public static void main(String [] args){
        System.out.println(maxSubArraySum(new int[]{1, -2, 3, 10, -4, 7, 2, -5}));
    }
}
