package others.easy;

import divideandconquer.ListNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/merge-two-sorted-lists/description/" />
 * 21. Merge Two Sorted Lists
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * 0. 本质：序列
 * 1. 建模：合并序列，保证整体顺序性 升序？降序？
 * 2. 算法范式：
 * 3. 算法：
 * （1）先找最小的那个，拿到head
 * （2）两个列表分别两个指针，从小的指针开始，定位最后一个小于等于另一个指针的值，小指针指向大的指针，并使得小指针指向之前遍历的下一个节点
 * （3）这时候小指针变大指针，大指针变小指针，交替遍历
 *
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
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        } else if(l2 == null) {
            return l1;
        }

        // get head
        ListNode head = l1.val <= l2.val? l1: l2;

        // get smaller
        ListNode smaller = l1.val <= l2.val? l1: l2;
        ListNode bigger = l1.val <= l2.val? l2: l1;
        // get the last smaller than next point's val

        while(smaller != null && bigger != null) {
            smaller = getLastSmaller(smaller, bigger.val);
            ListNode tmp = smaller.next;

            smaller.next = bigger;
            smaller = bigger;
            bigger = tmp;
        }

        return head;
    }

    ListNode getLastSmaller(ListNode node, int val) {
        while(node != null) {
            if(node.next == null) {
                return node;
            } else {
                if(node.val <= val && node.next.val > val) {
                    return node;
                }
            }
            node = node.next;
        }
        return node;
    }
}
