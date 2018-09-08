package divideandconquer.hard;

import divideandconquer.ListNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/merge-k-sorted-lists/description/" />
 * 23. Merge k Sorted Lists
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * 1. 建模：k个有序结合
 * 2. 算法范式：分治法
 * 3. 算法：二分，左右分别得到有序链
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：引用的变换很浪费脑袋，将两个链条拉平，演练一下即可
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 16/08/2017
 * @see
 * @since cgs-leetcode on  16/08/2017
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(null == lists || lists.length == 0) {
            return null;
        }

        return merge(lists, 0, lists.length - 1);
    }

    ListNode merge(ListNode[] lists, int start, int end) {

        if (start > end) {
            return null;
        }
        if (start == end) {
            return lists[start];
        }

        // 31/08/2018 递归，站在高处抽象，将多化为1，如这里就简单化为左右两边；DP也是
        int middle = (start + end) >>> 1;
        ListNode left = merge(lists, start, middle);
        ListNode right = merge(lists, middle + 1, end);
        return merge(left, right);
    }

//    TODO 引用的转移，画个图  两个链平放
    ListNode merge(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }

        ListNode head = left.val <= right.val? left: right;

        ListNode tmp = left;
        if (left.val > right.val) {
            left = right;
            right = tmp;
        }
        tmp = left.next;
        while (true) {
            if (tmp == null) {
                left.next = right;
                break;
            }
            if (tmp.val <= right.val) {
                left = left.next;
                tmp = tmp.next;
            } else {
                left.next = right;
                left = right;
                right = tmp;
                tmp = left.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {

        ListNode[] listNodes = new ListNode[2];
        listNodes[0] = new ListNode(0);
        listNodes[1] = new ListNode(1);
        listNodes[0].next = new ListNode(3);

        ListNode listNode = new MergeKSortedLists().mergeKLists(listNodes);
        System.out.println(listNode);

    }

}
