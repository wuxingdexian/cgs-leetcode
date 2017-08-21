package linkedlist.easy;

import divideandconquer.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/linked-list-cycle/description/" />
 * 141. Linked List Cycle
 * Given a linked list, determine if it has a cycle in it.

 Follow up:
 Can you solve it without using extra space?
 * <p>
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * （1）快慢指针
 * （2）HashMap去重
 *
 * 注意：比对的是对象不是值
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 19/08/2017
 * @see
 * @since cgs-leetcode on  19/08/2017
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        // return hashSet(head);
        return fastAndSlow(head);

    }

    boolean hashSet(ListNode head) {
        Set<ListNode> nodesSet = new HashSet<>();
        while(head != null) {
            if(nodesSet.contains(head)) {
                return true;
            } else {
                nodesSet.add(head);
            }
            head = head.next;
        }
        return false;
    }

    boolean fastAndSlow(ListNode head) {
        if(head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while(slow != null && fast != null && fast.next != null) {
            if(slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    public static void main(String[] args) {
        boolean b = new LinkedListCycle().hasCycle(new ListNode(1));
        System.out.println(b);
    }
}

