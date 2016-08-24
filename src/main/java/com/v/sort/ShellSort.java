package com.v.sort;

import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by v on 2016/8/24.
 */
public class ShellSort {

    public static void shellSort(Comparable[] arr, int startIndex, int length) {
        int size = arr.length;
        if (startIndex < 0 || length < 0 || (startIndex + length) > size)
            throw new IndexOutOfBoundsException();
        int intervalSize = startIndex + length;
        int step = intervalSize / 2;

        while (step > 0) {
            //do something
            int interval = intervalSize / step;
            int j;
            for (int i = startIndex + step; i < intervalSize; i++) {//组内插入排序
                Comparable temp = arr[i];
                for (j = i - step; j >= 0 && arr[j].compareTo(temp) > 0; j -= step) {
                    arr[j + step] = arr[j];
                }
                arr[j + step] = temp;
            }
            step /= 2;
        }

    }


    @Test
    public void testBubbleSort() {
        Comparable[] arr = new Comparable[50];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000);
        }
        long startTime = System.currentTimeMillis();
        shellSort(arr, 0, arr.length);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时" + (endTime - startTime) + "ms");
        for (Comparable comparable : arr){
            System.out.println(comparable);
        }
    }
}
