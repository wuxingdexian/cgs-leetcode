package others.easy;

import divideandconquer.ListNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/reverse-linked-list/description/" />
 * 206. Reverse Linked List
 * Reverse a singly linked list.
 * <p>
 * 0. 本质：combinatorics-》permutation-》generating
 * 1. 建模：
 * 使用三个指针，一个前一个节点指针，一个当前节点指针，一个下一个节点指针
 *
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
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode preNode = null;
        ListNode currentNode = head;
        ListNode nextNode = head.next;
        while(currentNode != null) {
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode == null? null: nextNode;
            nextNode = currentNode == null? null: currentNode.next;
        }
        return preNode;

    }
}
