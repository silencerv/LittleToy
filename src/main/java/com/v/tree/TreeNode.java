package com.v.tree;

public class TreeNode<K extends Comparable,V> {

        public K key;

        public V value;

        public int height;

        public TreeNode<K,V> left;

        public TreeNode<K,V> right;

        public TreeNode<K,V> parent;

        public TreeNode(K key,V value, int height, TreeNode<K,V> left, TreeNode<K,V> right, TreeNode<K,V> parent) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
