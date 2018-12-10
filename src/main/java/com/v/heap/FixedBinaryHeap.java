package com.v.heap;

import com.v.exception.ErrorEnumValueException;

import java.util.Arrays;
import java.util.Objects;

/**
 * @anthor v
 * Create on 2018/12/6
 * 将指定数组初始化为堆
 */
public class FixedBinaryHeap<E extends Comparable> {

    public static int MAX_HEAP = 1;

    public static int MIN_HEAP = -1;

    public static <T extends Comparable> FixedBinaryHeap<T> createHeap(T [] heap, int heapType) {
        FixedBinaryHeap<T> fixedBinaryHeap = new FixedBinaryHeap<>(heap, heapType);
        fixedBinaryHeap.checkHeap();
        fixedBinaryHeap.init();
        return fixedBinaryHeap;
    }

    /**
     * 数据，必须是完全二叉树
     */
    private E [] heap;

    /**
     * 最大堆还是最小堆
     */
    private int heapType;

    private FixedBinaryHeap(E[] heap, int heapType) {
        this.heap = heap;
        this.heapType = heapType;
    }

    private void checkHeap(){
        if (heapType != MAX_HEAP && heapType != MIN_HEAP)
            throw new ErrorEnumValueException("heap type not in enum value");
        int length = heap.length;
        if (Objects.isNull(heap) || length == 0)
            return;
    }

    /**
     * 初始化这个堆
     */
    private void init() {
        int index = heap.length - 1;
        while (index > 0) {
            int currentNodeIndex = index;
            int brotherNodeIndex = index;
            int fatherNodeIndex = index /2;
            if (currentNodeIndex % 2 == 0) {
                brotherNodeIndex--;
                fatherNodeIndex--;
            }
            int targetIndex = getTargetIndex(currentNodeIndex, brotherNodeIndex);
            int result = compareWith(fatherNodeIndex, targetIndex);
            if (heapType == MAX_HEAP && result < 0) {
                swap(fatherNodeIndex, targetIndex);
            }else if (heapType == MIN_HEAP && result > 0){
                swap(fatherNodeIndex, targetIndex);
            }
            index--;
            if (currentNodeIndex % 2 == 0)
                index--;
        }
    }


    private int getTargetIndex(int xIndex, int yIndex) {
        int result = compareWith(xIndex, yIndex);
        if (heapType == MAX_HEAP) {
            return result >= 0 ? xIndex : yIndex;
        }else {
            return result <= 0 ? xIndex : yIndex;
        }
    }

    private int compareWith(int xIndex, int yIndex) {
        E x = heap[xIndex];
        E y = heap[yIndex];
        return x.compareTo(y);
    }

    private void swap(int xIndex, int yIndex) {
        E x = heap[xIndex];
        E temp = x;
        heap[xIndex] = heap[yIndex];
        heap[yIndex] = temp;
    }

    @Override
    public String toString() {
        return "FixedBinaryHeap{" +
                "heap=" + Arrays.toString(heap) +
                ", heapType=" + heapType +
                '}';
    }
}
