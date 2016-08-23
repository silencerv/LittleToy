package com.v.heap;

import java.lang.reflect.Array;

/**
 * Created by v on 2016/8/21.
 * 最小堆
 */
public class BinaryHeap<E extends Comparable> {

    private static final int DEFAULT_CAPACITY = 128;

    private E [] heap;

    private int size;

//    private int position;

    private int capacity;

    public BinaryHeap(int capacity) {
        this.capacity = capacity;
        heap = (E[]) new Comparable[this.capacity];
    }

    public BinaryHeap() {
        capacity = DEFAULT_CAPACITY;
        heap = (E[]) new Comparable[this.capacity];
    }

    private void checkSize(){
        if (size >= capacity){
            capacity = capacity * 2;
            E [] newHeap = (E[]) new Comparable[capacity];
            System.arraycopy(heap,0,newHeap,0,size + 1);
            heap = newHeap;
        }
    }

    public void insert(E element){
        checkSize();
        if (element == null)
            throw new NullPointerException();
        int targetIndex = size++;
        heap[targetIndex] = element;
        while(targetIndex > 0){
            int half = targetIndex / 2;
            if (heap[half].compareTo(heap[targetIndex]) > 0){
                E temp = heap[half];
                heap[half] = heap[targetIndex];
                heap[targetIndex] = temp;
            }
            targetIndex = half;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){return size;}

    public E deleteMin(){
        if (isEmpty())
            return null;
        E minElement = heap[0];
        heap[0] = heap[--size];
        int currentIndex = 0;
        while((currentIndex * 2 + 1 ) < size){
            int tempIndex = currentIndex * 2;
            int leftChild = tempIndex + 1;
            int rightChild = tempIndex + 2;
            if (rightChild >= size){
                tempIndex = leftChild;
            }else {
                tempIndex = heap[leftChild].compareTo(heap[rightChild]) > 0 ? rightChild : leftChild;
            }
            if (heap[currentIndex].compareTo(heap[tempIndex]) > 0){
                E tempElement = heap[tempIndex];
                heap[tempIndex] = heap[currentIndex];
                heap[currentIndex] = tempElement;
            }else {
                return minElement;
            }
            currentIndex =tempIndex;
        }
        return minElement;
    }

}
