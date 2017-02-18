package com.v.tree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by v
 * Date: 2017/2/17
 * Time: 16:45
 *
 * @author: v
 */
public class RedBlackTree<K,V> {

    private TreeNode<K,V> root;

    private int size;

    private AtomicInteger modCount;

    public RedBlackTree() {
        modCount = new AtomicInteger();
    }

    public boolean insert(K key , V value){
        //首次插入
        if (root == null){
            root = new TreeNode<>(key,value,null);
            modCount.getAndIncrement();
            return true;
        }
        return true;
    }

    /**
     * 定义一个节点类型
     */
    public static class TreeNode<K,V> {

        public TreeNode() {
        }

        public TreeNode(K key, V value, TreeNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public K key;

        public V value;

        public TreeNode<K,V> parent;

        public TreeNode<K,V> left;

        public TreeNode<K,V> right;

    }
}
