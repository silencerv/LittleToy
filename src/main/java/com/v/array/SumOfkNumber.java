package com.v.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by v on 2016/12/13.
 */
public class SumOfkNumber {

    private static LinkedList<Integer> nums = new LinkedList<>();

    /**
     * 注意到取n，和不取n个区别即可，考虑是否取第n个数的策略，可以转化为一个只和前n-1个数相关的问题。
     如果取第n个数，那么问题就转化为“取前n-1个数使得它们的和为sum-n”，对应的代码语句就是sumOfkNumber(sum - n, n - 1)；
     如果不取第n个数，那么问题就转化为“取前n-1个数使得他们的和为sum”，对应的代码语句为sumOfkNumber(sum, n - 1)。
     * @param sum
     * @param n
     */
    public static void sumOfkNumber(int sum,int n){
        if (sum <=0 || n <=0)
            return;
        if (sum == n){//相等标识正好找到了
            nums.push(n);
            System.out.println(Arrays.toString(nums.toArray()));
            nums.pop();
        }
        //加入N（即当前数
        nums.push(n);
        sumOfkNumber(sum - n,n-1);
        //弹出N （不加入
        nums.pop();
        sumOfkNumber(sum,n-1);
    }

    public static void main(String []args){
        sumOfkNumber(11,16);
    }
}
