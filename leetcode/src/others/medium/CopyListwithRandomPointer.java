package others.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：函数映射 -> map
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：map
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 29/08/2018
 * @see
 * @since cgs-leetcode on  29/08/2018
 */
public class CopyListwithRandomPointer {
    static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    /**
     * https://leetcode.com/problems/copy-list-with-random-pointer/discuss/43488/Java-O(n)-solution
     * 这个方法更简洁，直接把所有的映射关系都通过一个map展现
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomListGood(RandomListNode head) {

        if (null == head) {
            return null;
        }

        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode tmpHead = head;
        while (tmpHead!= null) {
            map.put(tmpHead, new RandomListNode(tmpHead.label));
        }

        tmpHead = head;
        while (tmpHead != null) {
            map.get(tmpHead).next = map.get(tmpHead.next);
            map.get(tmpHead).random = map.get(tmpHead.random);
            tmpHead = tmpHead.next;
        }
        return map.get(head);

    }

    /**
     * 有bug
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (null == head) {
            return null;
        }

        Map<RandomListNode, Integer> sourceIndex2nodeMap = new HashMap<>();
        sourceIndex2nodeMap.put(head, 0);
        Map<Integer, RandomListNode> targetIndex2nodeMap = new HashMap<>();
        RandomListNode newHead = new RandomListNode(head.label);
        targetIndex2nodeMap.put(0, newHead);

        RandomListNode tmpHead = head.next;
        RandomListNode tmpNewHead = newHead;
        int index = 1;
        while (tmpHead != null) {
            sourceIndex2nodeMap.put(tmpHead, index);
            tmpNewHead.next = new RandomListNode(tmpHead.label);
            tmpNewHead = tmpNewHead.next;
            targetIndex2nodeMap.put(index, tmpNewHead);
            index++;
            tmpHead = tmpHead.next;
        }

        tmpNewHead = newHead;
        while (head != null) {
            tmpNewHead.random = targetIndex2nodeMap.get(sourceIndex2nodeMap.get(head));
            head = head.next;
        }

        return newHead;

    }

    public static void main(String[] args) {
        RandomListNode randomListNode = new RandomListNode(-1);

    }
}
