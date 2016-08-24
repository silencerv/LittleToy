package com.v.sort;

import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by v on 2016/8/24.
 * 插入排序
 * 升序
 */
public class InsertionSort {

    public static void insertionSort(Comparable[] arr, int startIndex, int length) {
        int size = arr.length;
        if (startIndex < 0 || length < 0 || (startIndex + length) > size)
            throw new IndexOutOfBoundsException();
        int intervalSize = startIndex + length;
        for (int i = startIndex + 1; i < intervalSize; i++) {
            Comparable temp = arr[i];
            for (int j = i - 1; j >= startIndex && temp.compareTo(arr[j]) < 0; j--) {
                arr[j + 1] = arr[j];
                arr[j] = temp;
            }
        }
    }

}
