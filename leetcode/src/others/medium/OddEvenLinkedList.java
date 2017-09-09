package others.medium;

import divideandconquer.ListNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/odd-even-linked-list/description/" />
 * 328. Odd Even Linked List
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

 You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

 Example:
 Given 1->2->3->4->5->NULL,
 return 1->3->5->2->4->NULL.

 Note:
 The relative order inside both the even and odd groups should remain as it was in the input.
 The first node is considered odd, the second node even and so on ...
 * <p>
 * 0. 本质：序列
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：两个指针
 * （1）先设置两个ho和he指针分别指向第一个odd和even节点
 * （2）然后设置两个指针po和pe分别指向第一个odd和even节点，不断后移指向下一个odd和even节点；
 * （3）当pe.next不为null且pe不为null，停止
 * （4）最后po.next指向he
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/09/2017
 * @see
 * @since cgs-leetcode on  08/09/2017
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode he = head.next;
        ListNode po = head;
        ListNode pe = head.next;

        while(pe != null && pe.next != null) {
            po.next = pe.next;
            po = pe.next;
            pe.next = po.next;
            pe = po.next;
        }
        po.next = he;
        return head;
    }
}
