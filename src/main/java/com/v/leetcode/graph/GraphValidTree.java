package com.v.leetcode.graph;

import java.util.*;

/**
 * @anthor v
 * Create on 2018/9/5
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges
 * (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 * <p>
 * For example:
 * <p>
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * <p>
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]],
 * return false.
 * <p>
 * Hint:
 * <p>
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
 * Show More Hint Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 */
public class GraphValidTree {

    public static boolean dfsValid(int[][] edges) {
        Map<Integer, Set<Integer>> reachablePoints = new HashMap<>();
        for (int[] edge : edges) {
            //一端点
            Set<Integer> childPoints = reachablePoints.get(edge[0]);
            if (childPoints == null) {
                childPoints = new HashSet<>();
                childPoints.add(edge[1]);
                reachablePoints.put(edge[0], childPoints);
            } else {
                childPoints.add(edge[1]);
            }
            //另一端点
            Set<Integer> otherChildPoints = reachablePoints.get(edge[1]);
            if (otherChildPoints == null) {
                otherChildPoints = new HashSet<>();
                otherChildPoints.add(edge[0]);
                reachablePoints.put(edge[1], otherChildPoints);
            } else {
                otherChildPoints.add(edge[0]);
            }
        }
        int pointCount = pointCount(edges);
        Set<Integer> visited = new HashSet<>();
        boolean hasCycle = hasCycle(reachablePoints, visited, 0, -1);

        //是否存在环
        if (hasCycle) {
            System.out.println("graph has cycle!");
            return false;
        }
        System.out.println(String.format("total:%d，DFS reachable:%d", pointCount, visited.size()));
        //是否全部联通
        if (pointCount != visited.size()) {
            System.out.println("graph not all reachable!");
        }

        System.out.println("graph is tree!");
        return true;
    }

    public static boolean hasCycle(Map<Integer, Set<Integer>> reachablePoints, Set<Integer> visited, int current, int parent) {
//        System.out.println("访问节点:".concat(String.valueOf(current)));
        if (!visited.add(current))
            return true;
        Set<Integer> childSet = reachablePoints.get(current);
        //到达叶子节点
        if (childSet == null || childSet.isEmpty())
            return false;
        for (int childPoint : childSet) {
            if (childPoint != parent && hasCycle(reachablePoints, visited, childPoint, current))
                return true;
        }
        return false;
    }

    /**
     * 端点个数
     *
     * @param edges
     * @return
     */
    private static int pointCount(int[][] edges) {
        Set<Integer> pointSet = new HashSet<>();
        for (int[] endPoint : edges) {
            for (int point : endPoint)
                pointSet.add(point);
        }
        return pointSet.size();
    }


    public static void main(String[] args) {
        int[][] edges = new int[][]{new int[]{0, 1}, new int[]{0, 2}, new int[]{0, 3}, new int[]{1, 4}};
        int[][] edges2 = new int[][]{new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 3}, new int[]{1, 3}, new int[]{1, 4}};

        dfsValid(edges);
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println();
        System.out.println();
        dfsValid(edges2);
    }
}
