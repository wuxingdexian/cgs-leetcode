package others.medium;

import divideandconquer.ListNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/" />
 * 82. Remove Duplicates from Sorted List II
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

 For example,
 Given 1->2->3->3->4->4->5, return 1->2->5.
 Given 1->1->1->2->3, return 2->3.
 * <p>
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：链表操作，记得加上哨兵
 * 5. 改进：
 * 6. 启发：面对这种的删除重复的元素的情况，最麻烦的就是一开始就直接有重复了。要避免这种情况，很好的办法是哨兵。该哨兵保证后续紧挨着的元素不和其重复
 * 小功能一定要函数取代
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 20/08/2017
 * @see
 * @since cgs-leetcode on  20/08/2017
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode guard = new ListNode(Integer.MIN_VALUE);
        guard.next = head;
        ListNode assist = guard;

        while(head != null) {
            if(head.next == null) {
                break;
            }
            ListNode notSameValNode = findNotSameValNode(head.val, head.next);
            if(notSameValNode == null) {
                assist.next = notSameValNode;
                break;
            }
            // 安全移动
            if(notSameValNode == head.next) {
                assist = head;
                head = head.next;
            } else {
                assist.next = notSameValNode;
                head = notSameValNode;
            }
        }
        return guard.next;
    }

    ListNode findNotSameValNode(int val, ListNode head) {
        if(head == null) {
            return null;
        }

        if(head.val != val) {
            return head;
        } else {
            return findNotSameValNode(val, head.next);
        }
    }
}
