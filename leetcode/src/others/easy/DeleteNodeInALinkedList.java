package others.easy;

import divideandconquer.ListNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/delete-node-in-a-linked-list/description/" />
 * 237. Delete Node in a Linked List
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

 Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class DeleteNodeInALinkedList {
    // ~.~  follow up 真替换呢？。。。
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
