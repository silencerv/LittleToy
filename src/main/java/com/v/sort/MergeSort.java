package com.v.sort;

import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by v on 2016/8/24.
 */
public class MergeSort {

    public static void mergeSortRecursive(Comparable [] arr,int startIndex,int endIndex,Comparable [] resultArr){
        if (startIndex >= endIndex)
            return;
        int start = startIndex;
        int midIndex = (startIndex + endIndex) / 2;
        int leftStart = startIndex;
        int leftEnd = midIndex;
        int rightStart = midIndex + 1;
        int rightEnd = endIndex;
        mergeSortRecursive(arr,leftStart,leftEnd,resultArr);
        mergeSortRecursive(arr,rightStart,rightEnd,resultArr);
        while (leftStart <= leftEnd && rightStart <= rightEnd){
            resultArr[startIndex++] = arr[leftStart].compareTo(arr[rightStart]) <= 0 ? arr[leftStart++] : arr[rightStart++];
        }
        while (leftStart <= leftEnd){
            resultArr[startIndex++] = arr[leftStart++];
        }
        while (rightStart <= rightEnd){
            resultArr[startIndex++] = arr[rightStart++];
        }
        while(start <= endIndex){
            arr[start] = resultArr[start++];
        }
    }

    public static void mergeSort(Comparable [] arr,int startIndex,int endIndex,Comparable [] resultArr){
        int interval = 1;
        int length = endIndex - startIndex + 1;
        boolean isOriginal = true;
        while (interval < length){
            for (int i = startIndex ; i < endIndex ; i += (interval + interval)) {
                //do something
                int mid = (interval + i) > endIndex ? endIndex : (interval + i);
                int leftStart = i;
                int leftEnd = mid;
                int rightStart = mid;
                int rightEnd = (i + interval + interval) > endIndex ? endIndex : (i + interval + interval);
                int start = i;
                while (leftStart < leftEnd && rightStart < rightEnd) {
                    resultArr[start++] = arr[leftStart].compareTo(arr[rightStart]) <= 0 ? arr[leftStart++] : arr[rightStart++];
                }
                while (leftStart < leftEnd)
                    resultArr[start++] = arr[leftStart++];

                while (rightStart < rightEnd)
                    resultArr[start++] = arr[rightStart++];
            }
            Comparable[] tempArr = arr;
            arr = resultArr;
            resultArr = tempArr;
            interval += interval;
            isOriginal = !isOriginal;
        }
        if (!isOriginal){
            for (int i = startIndex ; i < endIndex ; i++){
                arr[i] = resultArr[i];
            }
        }
    }
    @Test
    public void testSort() {
        Comparable[] arr = new Comparable[1000000];
        Comparable[] resultArr = new Comparable[1000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100000000);
        }
        long startTime = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length,resultArr);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时" + (endTime - startTime) + "ms");
      /*  for (Comparable comparable : arr){
            System.out.println(comparable);
        }*/
    }
}
