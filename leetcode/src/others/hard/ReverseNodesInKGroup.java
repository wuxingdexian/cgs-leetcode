package others.hard;

import divideandconquer.ListNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/reverse-nodes-in-k-group/description/" />
 * 25. Reverse Nodes in k-Group
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 You may not alter the values in the nodes, only nodes itself may be changed.

 Only constant memory is allowed.

 For example,
 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5
 * <p>
 * 0. 本质：combinatorics->generating->permutation
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：双指针
 * （1）head指向k个中的第一个；
 * （2）tail指向k个中的最后一个
 *      1）若tail为null，则不操作，直接结束了
 *      2）若tail不为null，则tmp指向head的next，head指向tail的next；
 *      3）tail的next指向head
 *      4）循环执行，知道head等于tail；最后返回head
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：TODO 链表结构变动时，注意链表head，head一般在很多情况下会增加处理难度。（1）要么特殊处理，穷尽可能的情况，<code>head = tail != null? tail: head;</code>；（2）要么使用哨兵，建议使用哨兵
 * 7. jdk知识：// FIXME: 04/09/2017 再次栽在java引用传递上。引用的复制
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 04/09/2017
 * @see
 * @since cgs-leetcode on  04/09/2017
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null) {
            return head;
        }
        ListNode tmp = head;
        ListNode tail = moveK(tmp, k);
        // get head
        head = tail != null? tail: head;

        // reverse
        ListNode join = null;
        while(tail != null) {
            if (join != null) {
                join.next = tail;
            }
            reverse(tmp, tail);
            join =moveK(tail, k);
            tmp = join.next;
            tail = moveK(tmp, k);
        }
        return head;
    }

    // 使用哨兵，降低头部处理的复杂度
    public ListNode reverseKGroupWithGuard(ListNode head, int k) {
        ListNode guard = new ListNode(-1);
        guard.next = head;
        ListNode tmp = head;
        ListNode tail = moveK(tmp, k);

        // reverse
        ListNode join = guard;
        while(tail != null) {
            join.next = tail;
            reverse(tmp, tail);
            join =moveK(tail, k);
            tmp = join.next;
            tail = moveK(tmp, k);
        }
        return guard.next;
    }

    ListNode reverse(ListNode head, ListNode tail) {
        while(head != tail) {
            ListNode tmp = head.next;
            head.next = tail.next;
            tail.next = head;
            head = tmp;
        }
        return head;
    }

    ListNode moveK(ListNode head, int k) {
        for(int i = 0; i < k - 1 && head != null; i++) {
            head = head.next;
        }
        return head;
    }

    public static void main(String[] args) {

        ReverseNodesInKGroup reverseNodesInKGroup = new ReverseNodesInKGroup();
        ListNode head = reverseNodesInKGroup.build();
        ListNode listNode = reverseNodesInKGroup.reverseKGroupWithGuard(head, 2);
        System.out.println(listNode);

    }
    ListNode build() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        return head;
    }
}
