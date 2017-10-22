package others.medium;

import divideandconquer.ListNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/swap-nodes-in-pairs/description/" />
 * 24. Swap Nodes in Pairs
 * Given a linked list, swap every two adjacent nodes and return its head.

 For example,
 Given 1->2->3->4, you should return the list as 2->1->4->3.

 Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

 * <p>
 * 0. 本质：序列
 * 1. 建模：将序列从开开始分为两两节点一组，然后交换组内的节点，然后再将不同组进行连接
 * 如 口  口  口  口，分为两个组，组内交换后，再组之间连接
 * 2. 算法范式：
 * 3. 算法：
 * （1）设定哨兵为head，设定两个指针p1、p2分别指向组内的两个元素，
 * （2）交换两个元素的位置，并同事和下一个组连接；
 * （3）head指向交换后的组内最后一个元素，p1=head.next，p2=p1.next；
 * 注意：判断组只有一个元素的情况
 * 4. 数据结构：哨兵
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 22/10/2017
 * @see
 * @since cgs-leetcode on  22/10/2017
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if(head == null) {
            return head;
        }

        ListNode tmp = new ListNode(-1);
        tmp.next = head;
        head = tmp;

        do{
            ListNode p1 = tmp.next;
            if(p1 == null) {
                break;
            }
            ListNode p2 = p1.next;
            if(p2 == null) {
                break;
            }

            p1.next = p2.next;
            p2.next = p1;
            tmp.next = p2;

            tmp = p1;
        } while(tmp != null);

        return head.next;
    }
}
