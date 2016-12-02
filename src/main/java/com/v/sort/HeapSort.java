package com.v.sort;

import com.v.heap.BinaryHeap;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by v on 2016/8/25.
 * 最小堆
 * 升序
 */
public class HeapSort {

    private static BinaryHeap<Comparable> binaryHeap = new BinaryHeap<Comparable>();

    public static void heapSort(Comparable [] arr,int startIndex,int length){
        if (arr == null)
            throw new NullPointerException();
        int size = length + startIndex;
        for (int i = (startIndex) ; i < size ; i++){
            binaryHeap.insert(arr[i]);
        }
        for (int i = (startIndex) ; i < size ; i++){
            arr[i] = binaryHeap.deleteMin();
        }
    }

    @Test
    public void testSort() {
        Comparable[] arr = new Comparable[1000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000000000);
        }
        long startTime = System.currentTimeMillis();
        heapSort(arr, 0, arr.length);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时" + (endTime - startTime) + "ms");
       /* for (Comparable comparable : arr){
            System.out.println(comparable);
        }*/
        assert (endTime - startTime) < 1000;
    }
}
