package twopointers.easy;

import divideandconquer.ListNode;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/palindrome-linked-list/description/" />
 * 234. Palindrome Linked List
 * Given a singly linked list, determine if it is a palindrome.

 Follow up:
 Could you do it in O(n) time and O(1) space?
 * <p>
 * 0. 本质：排列
 * 1. 建模：O(1)空间，只能改变链表的结构了
 * 2. 算法范式：
 * 3. 算法：
 * （1）改变链表结构，找到中间点，从哪里开始，使用快慢指针，正好拿到中间位置
 * （2）栈
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 03/09/2017
 * @see
 * @since cgs-leetcode on  03/09/2017
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        Stack<ListNode> stack = new Stack();
        int counter = 0;
        ListNode point = head;
        while(point != null) {
            stack.push(point);
            counter++;
            point = point.next;
        }
        for(int i = 0; i < counter / 2; i++) {
            if(head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;

    }

}
