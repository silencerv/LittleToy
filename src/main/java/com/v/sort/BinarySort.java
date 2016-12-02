package com.v.sort;

import java.util.Arrays;

/**
 * Created by v on 2016/12/2.
 * 二分插入排序
 */
public class BinarySort {


    public static void binarySort(Comparable[] arr, int startIndex, int length) {
        if (arr == null)
            throw new NullPointerException("src array must be not null!");
        if (startIndex < 0 || startIndex > arr.length || length < 0 || (startIndex + length) > arr.length)
            throw new IndexOutOfBoundsException();
        int len = startIndex + length;
        for (int currentIndex = startIndex + 1; currentIndex < len; currentIndex++) {
            int left = startIndex;
            int right = currentIndex;
            Comparable current = arr[currentIndex];
            while (left < right) {
                int mid = (left + right) >> 1;
                if (arr[mid].compareTo(current) < 0) {//必须是left = mid + 1，因为left 和 right 相邻的话 mid = left
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            int n = currentIndex - left;
            System.arraycopy(arr, left, arr, left + 1, n);

            arr[left] = current;
        }
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{123, 23, 32, 52, 65, 231,564,123,3245,43,5,123123,78,54,35,234 ,4535, 2312, 2123, 76, 432};
        binarySort(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
