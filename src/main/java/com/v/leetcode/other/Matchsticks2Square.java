package com.v.leetcode.other;

import java.util.*;

/**
 * Created by v
 * Date: 2017/2/3
 * Time: 11:45
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has,
 * please find out a way you can make one square by using up all those matchsticks. You should not break any stick,
 * but you can link them up, and each matchstick must be used exactly one time.
 * Your input will be several matchsticks the girl has, represented with their stick length.
 * Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 * <p>
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 * <p>
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 * Note:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick array will not exceed 15.
 *
 * @author: v
 */
public class Matchsticks2Square {

    public boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4)
            return false;
        int perimeter = 0;
        for (int i = 0; i < nums.length; i++) {
            perimeter += nums[i];
        }
        int sideLength = perimeter / 4;
        if (sideLength * 4 != perimeter) {
            return false;
        } else {
            Arrays.sort(nums);
            if (nums[nums.length - 1] > sideLength)
                return false;
            return dfs(nums,new int[4],0,sideLength);
        }
    }

    public boolean dfs(int[] arr, int[] sums, int index,int target) {
        if (index == arr.length){
            if(sums[0] == target && sums[1] == target && sums[2] == target )
                return true;
            return true;
        }
        for (int i = 0 ; i < 4 ; i++){
            if (sums[i] + arr[index] <= target){
                sums[i] += arr[index];
                if(dfs(arr,sums,index+1,target)) return true;
                sums[i] -= arr[index];
            }
        }
        return false;
    }


    public static void main(String [] args){
        System.out.println(new Matchsticks2Square().makesquare(new int[]{5,5,5,5,4,4,4,4,3,3,3,3}));
    }
}
