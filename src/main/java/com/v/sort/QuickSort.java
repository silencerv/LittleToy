package com.v.sort;

import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by v on 2016/8/24.
 * 快速排序
 * 升序
 * 包含endIndex
 */
public class QuickSort {

    public static void quickSort(Comparable [] arr,int startIndex,int endIndex){
        if (startIndex >= endIndex)
            return;
        int left = startIndex + 1;
        int right = endIndex;

        Comparable baseValue = arr[startIndex];
        while(left < right){
            while (left < right && arr[left].compareTo(baseValue) <= 0)
                left++;
            while (left < right && arr[right].compareTo(baseValue) > 0)
                right--;
            Comparable temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        if (arr[left].compareTo(baseValue) > 0){
            left--;
        }
        arr[startIndex] = arr[left];
        arr[left] = baseValue;

        //递归
        quickSort(arr,startIndex,left - 1);
        quickSort(arr,left + 1,endIndex);
    }

    @Test
    public void testSort() {
        Comparable[] arr = new Comparable[100];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        long startTime = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时" + (endTime - startTime) + "ms");
        for (Comparable comparable : arr){
            System.out.println(comparable);
        }
    }
}
