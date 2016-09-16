package com.v.shuffle;

/**
 * Created by v on 2016/9/16.
 */
public class PerfectShuffle {

    private static int [] arr = new int[]{0,1,2,3,4,5,6,7,8};
    //最终顺序1,5,2,6,3,7,4,8

    public static void stepByStep(int [] arr){
        int half = arr.length >> 1;
        int count = 1;
        int startIndex = half;

        while(startIndex < arr.length){
            int current = startIndex;
            int gap = half - count++;
            while(gap-- > 0){
                int temp = arr[current];
                arr[current] = arr[current - 1];
                arr[current - 1] = temp;
                current--;
            }
            startIndex++;
        }
    }

    public static void perfectShuffleOne(int [] arr,int n ){
        int n2 = n << 1;
        int [] resultArr = new int[arr.length];
        for (int i = 0 ; i <= n2 ; i ++){
            resultArr[(i * 2) % (n2 + 1 )] = arr[i];
        }
        for (int i = 1 ; i < arr.length ; i++){
            arr[i] = resultArr[i];
        }
    }

    /**
     * 跑圈
     * @param arr
     * @param from  其实位置
     * @param mod  2 * n + 1
     */
    public static void cycle(int [] arr,int from,int mod){

        for (int i = 2 * from % mod ; from != i ; i = i * 2 % mod){
            int t = arr[i];
            arr[i] = arr[from];
            arr[from] = t;
        }
    }

    public static void perfect(int [] arr,int n ){
        int n2 = n << 1;
        int k = 1;
        while(n2 > (3 * k + 1)){
            cycle(arr,k,n2 + 1);
            k *= 3;
        }
    }

    public static void main(String [] args){
        perfect(arr,4);
        for (int i : arr){
            System.out.println(i);
        }
    }
}
