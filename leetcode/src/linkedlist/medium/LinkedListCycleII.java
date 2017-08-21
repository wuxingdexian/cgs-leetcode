package linkedlist.medium;

import divideandconquer.ListNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/linked-list-cycle-ii/discuss/" />
 * 142. Linked List Cycle II
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

 Note: Do not modify the linked list.

 Follow up:
 Can you solve it without using extra space?
 * <p>
 * 1. 建模：
 * 快慢指针方式
 * 公式：
 * fast=2slow，快指针一次走慢指针两倍的距离。
 * 若存在环，设head到环起始点的距离为x，从x到环内相遇的距离为y，设环的长度为cycle。
 * 则两个指针会相遇，在第一次相遇时，快指针走过的长度=x + cycle + y，慢指针走过的长度=x + y，
 * 依据fast=2slow，x + cycle + y=2(x + y)，则x+y=cycle，慢指针在环内已经走了y距离。
 * 则此时慢指针继续往前走cycle-y=x距离就到达环起始位置。而若此时有一个指针从head开始，也走x距离，则两个指针在环起始位置相遇
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
 * @author changgan on 21/08/2017
 * @see
 * @since cgs-leetcode on  21/08/2017
 */
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        // slow和fast一定要成倍关系，否则~~  比如ListNode slow = head; ListNode fast = head.next.next;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(fast != null) {
            if(slow == fast) {
                ListNode flag = head;
                while(flag != slow) {
                    flag = flag.next;
                    slow = slow.next;
                }
                return flag;
            } else {
                slow = slow.next;
                fast = fast.next != null? fast.next.next: null;
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        3,2,0,-4
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        ListNode listNode = new LinkedListCycleII().detectCycle(node1);
        System.out.println(listNode);
    }
}
