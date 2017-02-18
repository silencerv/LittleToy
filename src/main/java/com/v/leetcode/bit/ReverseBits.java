package com.v.leetcode.bit;

/**
 * Created by v
 * Date: 2017/2/3
 * Time: 11:24
 * For example
 * given input 43261596 (represented in binary as 00000010100101000001111010011100)
 * return 964176192 (represented in binary as 00111001011110000010100101000000).
 * @author: v
 */
public class ReverseBits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int reverse = 0;
        for (int i = 0 ; i < 32 ; i++){
            reverse = reverse << 1 | (n & 1) ;
            n = n >> 1;
        }
        return reverse;
    }

    public static void main(String [] args){
        int i = 43261596;
        System.out.println(new ReverseBits().reverseBits(i));
    }
}
