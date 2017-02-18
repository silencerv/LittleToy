package com.v.leetcode.list;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by v
 * Date: 2017/2/3
 * Time: 11:35
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * @author: v
 */
public class LinkedListCycle {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> existed = new HashSet<>();
        ListNode currentNode = head;
        while ( currentNode != null){
            if (existed.contains(currentNode)){
                return currentNode;
            }else {
                existed.add(currentNode);
            }
            currentNode = currentNode.next;
        }
        return null;
    }
}
