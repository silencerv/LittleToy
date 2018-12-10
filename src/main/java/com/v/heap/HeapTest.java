package com.v.heap;

import org.testng.annotations.Test;

/**
 * @anthor v
 * Create on 2018/12/6
 */
public class HeapTest {

    @Test
    public void testFixedBinaryHeap() {
        Integer [] arr = new Integer[]{212,4,1,445,44,123,123,1123,123,4,342,123,123,23,3442,32,3123,234,23,1};
        FixedBinaryHeap<Integer> heap = FixedBinaryHeap.createHeap(arr, FixedBinaryHeap.MAX_HEAP);
        System.out.println(heap);
    }
}
