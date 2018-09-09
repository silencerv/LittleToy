package com.v.leetcode.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @anthor v
 * Create on 2018/9/9
 * 并查集校验,满足以下条件的图即为树
 * 1.所有节点联通
 * 2.无环 -> 即当输入一条边，边的两个端点不在一个集合中
 */
public class FUSet {

    private int [] fathers;

    /**
     * 集合的数量
     */
    private int count;

    public int getCount() {
        return count;
    }

    public FUSet(int cap) {
        this.count = cap;
        int [] arr = new int[cap];
        for (int i = 0 ; i < cap; i++){
            arr[i] = i;
        }
        fathers = arr;
    }

    private int find(int x) {
        if (fathers[x] != x) {
            fathers[x] = find(fathers[x]);
        }
        return fathers[x];
    }


    public void union(int p, int q) {
        int pF, qF;
        if ( (pF = find(p)) != (qF = find(q))) {
            fathers[q] = pF;
            count--;
        }
    }

    @Override
    public String toString() {
        return "FUSet{" +
                "fathers=" + Arrays.toString(fathers) +
                ", count=" + count +
                '}';
    }

    public static  boolean validTree(int[][] edges) {
        Set<Integer> pointSet = new HashSet<>();
        for (int [] edge : edges) {
            for (int p : edge)
                pointSet.add(p);
        }

        FUSet fuSet = new FUSet(pointSet.size());
        for (int [] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            if (fuSet.find(x) == fuSet.find(y)) {
                System.out.println("graph has cycle!");
                return false;
            }
            fuSet.union(x, y);
        }

        System.out.println(fuSet.toString());
        if (fuSet.getCount() != 1){
            System.out.println("graph is not tree!");
            return false;
        }
        System.out.println("graph is tree!");
        return true;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{0, 3}, new int[]{1, 4}};
        int[][] edges2 = new int[][]{new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 3}, new int[]{1, 3}, new int[]{1, 4}};

        validTree(edges2);
    }
}
