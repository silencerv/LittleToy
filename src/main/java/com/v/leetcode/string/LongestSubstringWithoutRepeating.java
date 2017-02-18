package com.v.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by v
 * Date: 2017/2/3
 * Time: 11:00
 *
 Examples:

 Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * @author: v
 */
public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int currentLen = 0;
        char [] chars = s.toCharArray();
        Map<Character,Integer> existed = new HashMap<>(26);
        for (int i = 0 ; i  < chars.length ; i++){
            if (existed.containsKey(chars[i])){
                i = existed.get(chars[i]);
                if(currentLen > maxLen)
                    maxLen = currentLen;
                currentLen = 0;
                existed.clear();
            }else {
                existed.put(chars[i],i);
                currentLen++;
            }
        }
        if (currentLen > maxLen)
            maxLen = currentLen;
        return maxLen;
    }

    public static void main(String [] args){
        System.out.println(new LongestSubstringWithoutRepeating().lengthOfLongestSubstring("aab"));
    }
}
