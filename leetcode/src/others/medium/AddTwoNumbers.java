package others.medium;

import divideandconquer.ListNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/add-two-numbers/description/" />
 * 2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 * <p>
 * 0. 本质：序列
 * 1. 建模：
 * 两个序列一次相加，跟踪进位
 * 2. 算法范式：
 * 3. 算法：
 * 如果可以更改链表
 * （1）判断两个链表，如果一个为null，则返回另一个
 * （2）两个指针分别指向两个链表，两个数相加，进位保存到临时变量，个位数直接更新到某个链表；
 * （3）执行（2）直到一个链表为空，此时将链表的最后元素指向另一个链表，并保持进位相加。
 *
 * 如果不能更改链表
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/09/2017
 * @see
 * @since cgs-leetcode on  18/09/2017
 */
public class AddTwoNumbers {
    // 不改变链表会更容易实现
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            return l1 == null? l1: l2;
        }

        ListNode head = new ListNode(-1); // guard
        ListNode pointer = head;

        int carry = 0;
        while(l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            pointer.next = new ListNode(sum % 10);
            l1 = l1.next;
            l2 = l2.next;
            pointer = pointer.next;
        }

        ListNode tmp = l1 != null? l1: l2;
        while(tmp != null) {
            int sum = tmp.val + carry;
            carry = sum / 10;
            pointer.next = new ListNode(sum % 10);
            tmp = tmp.next;
            pointer = pointer.next;
        }
        if(carry != 0) {
            pointer.next = new ListNode(carry);
        }

        return head.next;
    }
}
