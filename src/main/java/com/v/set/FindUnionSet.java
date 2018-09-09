package com.v.set;

/**
 * @anthor v
 * Create on 2018/9/8
 */
public class FindUnionSet {

    private int [] s;

    public FindUnionSet(int cap) {
        int [] arr = new int[cap];
        for (int i = 0 ; i < cap; i++){
            arr[i] = i;
        }
        s = arr;
    }

    /**
     * 返回x所在的集合
     * @param x
     * @return
     */
    public int find(int x) {
        int r = x;
        while (r != s[r]) {
            r = s[r];
        }
        //路径压缩
        int k = x;
        while (k != r) {
            int j = s[k];
            s[k] = r;
            k = j;
        }
        return r;
    }

    /**
     * 递归查找
     * @param x
     * @return
     */
    public int findByRecursive(int x) {
        if (x != s[x]) {
            s[x] = findByRecursive(s[x]);
        }
        return s[x];
    }

    /**
     * 将p 合并到 q
     * 及 q 的father 为p,q的代表
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int pFather = find(p);
        int qFather = find(q);
        if (pFather != qFather) {
            s[p] = qFather;
        }
    }
}
