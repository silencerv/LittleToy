package com.v.sort;

import org.testng.annotations.Test;

/**
 * Created by v on 2016/8/24.
 * 冒泡排序
 * 升序
 */
public class BubbleSort {

    public static void bubbleSort(Comparable[] arr, int startIndex, int length) {
        int size = arr.length;
        if (startIndex < 0 || length < 0 || (startIndex + length) > size)
            throw new IndexOutOfBoundsException();
        int endIndex = startIndex + length;
        for (int i = 0; i < endIndex - 1; i++) {
            for (int j = i + 1; j < endIndex; j++) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    Comparable temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

        }
    }
}
