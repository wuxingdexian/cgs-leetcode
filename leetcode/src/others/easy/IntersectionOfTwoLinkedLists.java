package others.easy;

import divideandconquer.ListNode;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/intersection-of-two-linked-lists/description/" />
 * 160. Intersection of Two Linked Lists
 * Write a program to find the node at which the intersection of two singly linked lists begins.


 For example, the following two linked lists:

 A:          a1 → a2
 ↘
 c1 → c2 → c3
 ↗
 B:     b1 → b2 → b3
 begin to intersect at node c1.


 Notes:

 If the two linked lists have no intersection at all, return null.
 The linked lists must retain their original structure after the function returns.
 You may assume there are no cycles anywhere in the entire linked structure.
 Your code should preferably run in O(n) time and use only O(1) memory.
 * <p>
 * 0. 本质：序列
 * 1. 建模：两个序列末尾的交集
 *
 * 2. 算法范式：
 * 3. 算法：
 * （1）O(n) time and use only O(n) memory
 * 使用栈完成。
 * （2）O(n) time and use only O(1) memory
 * 通过栈的实现，可以得知，如果重叠，则对象是一致的，从第一个重叠的开始，后面都一致。
 * 所以，长链表移动头部指针，直到剩下的长度和短链表长度相同，然后逐一比较即可
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
public class IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNodeUsingStack(ListNode headA, ListNode headB) {
        Stack<ListNode> stackA = new Stack();
        Stack<ListNode> stackB = new Stack();

        while(headA != null) {
            stackA.push(headA);
            headA = headA.next;
        }
        while(headB != null) {
            stackB.push(headB);
            headB = headB.next;
        }

        ListNode start = null;
        while(!stackA.isEmpty() && !stackB.isEmpty()) {
            ListNode tmp = stackA.pop();
            if(tmp == stackB.pop()) {
                start = tmp;
            } else {
                break;
            }
        }
        return start;

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        int lengthA = 0;
        int lengthB = 0;
        ListNode tmpA = headA;
        ListNode tmpB = headB;

        // get length of A and B
        while(tmpA != null) {
            lengthA++;
            tmpA = tmpA.next;
        }
        while(tmpB != null) {
            lengthB++;
            tmpB = tmpB.next;
        }

        // get same length start
        if(lengthA > lengthB) {
            for(int i = 0; i < lengthA - lengthB; i++) {
                headA = headA.next;
            }
        } else if(lengthA < lengthB) {
            for(int i = 0; i < lengthB - lengthA; i++) {
                headB = headB.next;
            }
        }

        // get first same node
        while(headA != null) {
            if(headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;


    }
}
