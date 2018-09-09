package com.v.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * Created by v on 2016/8/20.
 * 不计数不允许出现重复值，不允许 key 空
 */
public class AVLTree<K extends Comparable,V> {

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
                    currentNode.right = remove(currentNode.key,currentNode.right);
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

    public int getTreeHeight(){
        return getHeight(root);
    }

    /*
      广度优先遍历
      @param consumer
     */
    public void BFSForeach(Consumer<TreeNode<K,V>> consumer){
        if(null == root)
            return;
        Deque<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        while(!que.isEmpty()){
            TreeNode currentNode = que.poll();
            consumer.accept(currentNode);
            if (currentNode.left != null)
                que.addLast(currentNode.left);
            if (currentNode.right != null)
                que.addLast(currentNode.right);
        }

    }


    /**
     * 深度优先
     * @param consumer
     */
    public void DfsForeach(Consumer<TreeNode<K,V>> consumer){
        if(null == root)
            return;
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode currentNode = stack.poll();
            consumer.accept(currentNode);
            if (currentNode.right != null)
                stack.push(currentNode.right);
            if (currentNode.left != null)
                stack.push(currentNode.left);
        }

    }
}
