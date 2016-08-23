package com.v.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by v on 2016/8/20.
 * 不计数不允许出现重复值
 */
public class AVLTreeImpl<K extends Comparable,V> {

    public static final int PREORDER = -1;

    public static final int INORDER = 0;

    public static final int POSTORDER = 1;

    private TreeNode<K,V> root;

    private int size;

    private int modeCount;

    private TreeNode<K,V> insert(TreeNode currentNode, K key,V value) {
        if (key == null)
            return currentNode;
        //如果是空树 直接插入根节点
        if (currentNode == null) {
            size++;
            return new TreeNode<>(key,value, 0, null, null, null);
        }

        int comResult = key.compareTo(currentNode.key);
        //递归右子树
        if (comResult > 0) {
            currentNode.right = insert(currentNode.right, key,value);
            int balanceFac = getHeight(currentNode.left) - getHeight(currentNode.right);
            if (balanceFac == -2) {//R旋转
                if (key.compareTo(currentNode.right.key) < 0) //L旋转
                    currentNode = doubleRightLeftRotation(currentNode);
                else//R旋转
                    currentNode = rightRotation(currentNode);
            }
        } else if (comResult < 0) {//递归左子树
            currentNode.left = insert(currentNode.left, key,value);
            int balanceFac = getHeight(currentNode.left) - getHeight(currentNode.right);
            if (balanceFac == 2) {//L旋转
                if (key.compareTo(currentNode.left.key) > 0) //R旋转
                    currentNode = doubleLeftRightRotation(currentNode);
                else//L旋转
                    currentNode = leftRotation(currentNode);
            }

        } else {
            size--;
            return currentNode;
        }
        //更新树高度
        currentNode.height = Math.max(
                getHeight(currentNode.left), getHeight
                        (currentNode.right)) + 1;
        return currentNode;
    }

    public V find(K key){
        if (root  == null || key == null)
            return null;
        TreeNode<K,V> currentNode = root;
        while (currentNode != null){
            if (key.compareTo(currentNode.key) > 0)
                currentNode = currentNode.right;
            else if (key.compareTo(currentNode.key) < 0)
                currentNode = currentNode.left;
            else
                return currentNode.value;
        }
        return null;
    }

    public void remove(K key){
        root = remove(key,root);
    }

    private TreeNode remove(K key,TreeNode<K,V> currentNode){
        if ( key == null || currentNode == null)
            return null;
        int comResult = key.compareTo(currentNode.key);
        if (comResult > 0){//递归右子树
            currentNode.right = remove(key,currentNode.right);
            if (getHeight(currentNode.left) - getHeight(currentNode.right) == 2){//L
                if (getHeight(currentNode.left) > getHeight(currentNode.right))//L
                    leftRotation(currentNode);
                else//R
                    doubleLeftRightRotation(currentNode);

            }

        }else if (comResult < 0){
            currentNode.left = remove(key,currentNode.left);
            if (getHeight(currentNode.left) - getHeight(currentNode.right) == -2){//R
                if (getHeight(currentNode.left) > getHeight(currentNode.right))//L
                    doubleRightLeftRotation(currentNode);
                else//R
                    rightRotation(currentNode);

            }
        }else {
            if (currentNode.right != null && currentNode.left != null){
                if (getHeight(currentNode.left) > getHeight(currentNode.right)){//从左子树上面找一个最大的
                    TreeNode<K,V> rightNode = currentNode.right;
                    currentNode = getMaxKey(currentNode.left);
                    currentNode.right = rightNode;
                    currentNode.left = remove(currentNode.key,currentNode.left);

                }else {//右子树上面找一个最小的
                    TreeNode<K,V> leftNode = currentNode.left;
                    currentNode = getMinKey(currentNode.right);
                    currentNode.left = leftNode;
                    currentNode.right = remove(currentNode.getKey(),currentNode.right);
                }
            }else if (currentNode.left != null){
                return currentNode.left;
            }else if (currentNode.right != null){
                return currentNode.right;
            }else {
                return null;
            }
            size--;
        }
        //更新树高
        currentNode.height = Math.max(getHeight(currentNode.left),getHeight(currentNode.right)) + 1;
        return currentNode;
    }

    public TreeNode<K,V> getMinKey(TreeNode<K,V> node){
        if (node == null)
            return null;
        TreeNode<K,V> currentNode = node;

        while(currentNode.left != null){
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    public TreeNode<K,V> getMaxKey(TreeNode<K,V> node){
        if (root == node)
            return null;
        TreeNode<K,V> currentNode = node;

        while(currentNode.right != null){
            currentNode = currentNode.right;
        }
        return currentNode;
    }

    //左右旋
    private TreeNode doubleLeftRightRotation(TreeNode currentNode) {
        currentNode.left = rightRotation(currentNode.left);
        return leftRotation(currentNode);
    }

    //右左旋
    private TreeNode doubleRightLeftRotation(TreeNode currentNode) {
        currentNode.right = leftRotation(currentNode.right);
        return rightRotation(currentNode);
    }

    //左旋
    private TreeNode leftRotation(TreeNode currentNode) {
        modeCount++;
        TreeNode<K,V> targetNode = currentNode.left;
        targetNode.parent = currentNode.parent;
        currentNode.parent = targetNode;
        currentNode.left = targetNode.right;
        targetNode.right = currentNode;
        //更新树高
        currentNode.height = Math.max(getHeight(currentNode.left), getHeight(currentNode.right)) + 1;
        targetNode.height = Math.max(getHeight(currentNode.left), getHeight(currentNode.right)) + 1;
        return targetNode;
    }

    //右旋
    private TreeNode rightRotation(TreeNode currentNode) {
        modeCount++;
        TreeNode<K,V> targetNode = currentNode.right;
        targetNode.parent = currentNode.parent;
        currentNode.parent = targetNode;
        currentNode.right = targetNode.left;
        targetNode.left = currentNode;
        //更新树高
        targetNode.height = Math.max(getHeight(targetNode.left), getHeight(targetNode.right)) + 1;
        currentNode.height = Math.max(getHeight(currentNode.left), getHeight(currentNode.right)) + 1;
        return targetNode;
    }


    public void insert(K key,V value) {
        root = insert(root, key,value);
    }

    private int getHeight(TreeNode node) {
        return node == null ? -1 : node.height;
    }

    /*
    深度优先
     */
    private void each(TreeNode currentNode,int eachType,ProcessNode each){
        if (currentNode == null)
            return;
        else {
            if (eachType == PREORDER )
                each.each(currentNode);
            if (currentNode.left != null)
                each(currentNode.left,eachType,each);
            if (eachType == INORDER )
                each.each(currentNode);
            if (currentNode.right != null)
                midForeach(currentNode.right,each);
            if (eachType == INORDER )
                each.each(currentNode);
        }
    }

    public void preForeach(TreeNode currentNode,ProcessNode each){
        each(currentNode,PREORDER,each);
    }

    private void midForeach(TreeNode currentNode,ProcessNode each) {
        each(currentNode,INORDER,each);
    }

    public void postForeach(TreeNode currentNode,ProcessNode each){
        each(currentNode,POSTORDER,each);
    }

    public void preForeach(ProcessNode each) {
        preForeach(root,each);
    }

    public void midForeach(ProcessNode each) {
        midForeach(root,each);
    }

    public void postForeach(ProcessNode each) {
        postForeach(root,each);
    }

    public int getTreeHeight(){
        return getHeight(root);
    }

    /*
      广度优先遍历
      @param each
     */
    public void BFSForeach(ProcessNode each){
        if(null == root)
            return;
        Deque<TreeNode> que = new LinkedList<>();
        TreeNode currentNode = root;
        que.addLast(root);
        while(que.size() > 0){
            currentNode = que.poll();
            each.each(currentNode);
            if (currentNode.left != null)
                que.addLast(currentNode.left);
            if (currentNode.right != null)
                que.addLast(currentNode.right);
        }

    }

    private static class TreeNode<K extends Comparable,V> implements Map.Entry<K,V>{

        K key;

        V value;

        int height;

        TreeNode<K,V> left;

        TreeNode<K,V> right;

        TreeNode<K,V> parent;

        public TreeNode(K key,V value, int height, TreeNode<K,V> left, TreeNode<K,V> right, TreeNode<K,V> parent) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         * @throws IllegalStateException implementations may, but are not
         *                               required to, throw this exception if the entry has been
         *                               removed from the backing map.
         */
        @Override
        public K getKey() {
            return key;
        }

        /**
         * Returns the value corresponding to this entry.  If the mapping
         * has been removed from the backing map (by the iterator's
         * <tt>remove</tt> operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry
         * @throws IllegalStateException implementations may, but are not
         *                               required to, throw this exception if the entry has been
         *                               removed from the backing map.
         */
        @Override
        public V getValue() {
            return value;
        }

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the map.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the map (by the iterator's <tt>remove</tt> operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws UnsupportedOperationException if the <tt>put</tt> operation
         *                                       is not supported by the backing map
         * @throws ClassCastException            if the class of the specified value
         *                                       prevents it from being stored in the backing map
         * @throws NullPointerException          if the backing map does not permit
         *                                       null values, and the specified value is null
         * @throws IllegalArgumentException      if some property of this value
         *                                       prevents it from being stored in the backing map
         * @throws IllegalStateException         implementations may, but are not
         *                                       required to, throw this exception if the entry has been
         *                                       removed from the backing map.
         */
        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    @FunctionalInterface
    public  interface ProcessNode<K extends Comparable,V>{

        void each(Map.Entry<K,V> node);
    }
}
