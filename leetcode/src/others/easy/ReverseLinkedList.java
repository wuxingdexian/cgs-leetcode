package others.easy;

import divideandconquer.ListNode;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/reverse-linked-list/description/" />
 * 206. Reverse Linked List
 * Reverse a singly linked list.
 * Example:

 Input: 1->2->3->4->5->NULL
 Output: 5->4->3->2->1->NULL
 Follow up:

 A linked list can be reversed either iteratively or recursively. Could you implement both?
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
 * 栈和dfs处理，在逆转后，需要对尾节点（即原来的头节点的next）指向null
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

    public ListNode reverseListWithStack(ListNode head) {
        if (null == head) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        head = stack.pop();
        ListNode tempNode = head;
        while (!stack.isEmpty()) {
            tempNode.next = stack.pop();
            tempNode = tempNode.next;
        }
        // 需要处理，否则形成环
        tempNode.next = null;
        return head;
    }

    // 需要使用一个全局变量，用来
    ListNode gardNode = new ListNode(-1);
    public ListNode reverseListWithDFS(ListNode head) {

        if (null == head) {
            return null;
        }

        ListNode oldTempHead = head;

        ListNode tmpNode = gardNode;
        dfs(head);
        // 需要处理，否则形成环
        oldTempHead.next = null;
        return tmpNode.next;
    }

    private void dfs(ListNode head) {
        if (head == null) {
            return;
        } else {
            dfs(head.next);
            gardNode.next = head;
            gardNode = gardNode.next;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        ListNode node1 = new ReverseLinkedList().reverseListWithDFS(node);
        System.out.println(node1);
    }
}
