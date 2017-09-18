package others.medium;

import divideandconquer.ListNode;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/" />
 * 19. Remove Nth Node From End of List
 * Given a linked list, remove the nth node from the end of list and return its head.

 For example,

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:
 Given n will always be valid.
 Try to do this in one pass.
 * <p>
 * 0. 本质：序列
 * 1. 建模：
 * 确定序列总长度L，然后删除第L-n个元素
 * 2. 算法范式：
 * 3. 算法：
 * （1）快慢指针，分别拿到总长度，注意快指针的临界情况
 * // FIXME: 15/09/2017 快慢指针不是最优，最优是两个指针直接间隔n，一起向前遍历 https://leetcode.com/problems/remove-nth-node-from-end-of-list/solution/
 * （2）栈
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：使用哨兵，大大降低处理边界的处理难度
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 15/09/2017
 * @see
 * @since cgs-leetcode on  15/09/2017
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n == 0) {
            return head;
        }

        Stack<ListNode> stack = new Stack();

        ListNode guard = new ListNode(-1);
        guard.next = head;
        head = guard;
        while(head != null) {
            stack.push(head);
            head = head.next;
        }

        int counter = 0;
        while(!stack.isEmpty()) {

            ListNode node = stack.pop();
            if(++counter == n+1) {
                node.next = node.next.next;
            }
        }

        return guard.next;
    }

    // two pointer
    public ListNode removeNthFromEndUsingTwoPoint(ListNode head, int n) {
        if(head == null || n == 0) {
            return head;
        }

        ListNode guard = new ListNode(-1);
        guard.next = head;
        // let two pointer difference n step
        ListNode firstPointer = guard;
        ListNode secondPointer = guard;
        int counter = 0;
        while(firstPointer != null && counter < n) {
            firstPointer = firstPointer.next;
            counter++;
        }

        while(firstPointer != null && firstPointer.next != null) {
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next;
        }

        if(firstPointer != null) {
            secondPointer.next = secondPointer.next.next;
        }

        return guard.next;
    }
}
