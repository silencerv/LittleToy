package com.v.leetcode.tree;

/**
 * Created by v
 * Date: 2017/2/3
 * Time: 14:42
 *
 * @author: v
 */
public class BinaryTreeMaximumPathSum {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxPathSum(TreeNode root) {
        int maxLeft = maxSum(root.left);
        int maxRight = maxSum(root.right);
        if (root.left == null && root.right == null)
            return root.val;
        if (root.left == null){
           return Math.max(Math.max(root.val,maxRight),root.val + maxRight);
        }
        if (root.right == null){
            return Math.max(Math.max(root.val,maxLeft),root.val + maxLeft);
        }
        return Math.max(root.val,Math.max(Math.max(Math.max(maxLeft + root.val,maxRight + root.val),Math.max(Math.max(maxLeft,maxRight),root.val)),root.val + maxLeft + maxRight));
    }

    public int maxSum(TreeNode root){
        if (root == null)
            return 0;
        return root.val + Math.max(maxSum(root.left),maxSum(root.right));
    }

    public static void main(String [] args){
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);

//        root.right = new TreeNode(3);
//        root.right.left = new TreeNode(1);
//        root.right.right = new TreeNode(3);
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(root));
    }
}
