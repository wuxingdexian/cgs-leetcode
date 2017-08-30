package tree.easy;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/binary-tree-tilt/description/" />
 * 563. Binary Tree Tilt
 * Given a binary tree, return the tilt of the whole tree.

 The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values. Null node has tilt 0.

 The tilt of the whole tree is defined as the sum of all nodes' tilt.

 Example:
 Input:
 1
 /   \
 2     3
 Output: 1
 Explanation:
 Tilt of node 2 : 0
 Tilt of node 3 : 0
 Tilt of node 1 : |2-3| = 1
 Tilt of binary tree : 0 + 0 + 1 = 1
 Note:

 The sum of node values in any subtree won't exceed the range of 32-bit integer.
 All the tilt values won't exceed the range of 32-bit integer.

 * <p>
 * 0. 本质：集合求和
 * 1. 建模：absolute_difference=|sum(left_tree) - sum(right_tree)|
 * 2. 算法范式：backtracking
 * 3. 算法：
 * (1) get left tree sum
 * (2) get right tree sum
 * (3) get absolute difference and cache it
 * (4) finally sum all the absolute difference
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * // FIXME: 30/08/2017
 * 基本数据类型转换：（1）低长度可以直接转高长度；（2）高长度可以强制转低长度；
 * <code>
 long a = 0;
 int b = (int)a;

 int c = 0;
 long d = c;</code>
 * 基本数据类型的包装类，互相之间不能强转
 * <code>
 Long aa = 0L;
 Integer bb = aa;

 Integer cc = 0;
 Long dd = cc;
 * </code>
 * 除非存在父类（接口）和子类（实现）的继承关系
 * <code>
 Long aa = 0L;
 Number bb = aa;

 Integer cc = 0;
 Number dd = cc;
 </code>
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 30/08/2017
 * @see
 * @since cgs-leetcode on  30/08/2017
 */
public class BinaryTreeTilt {
    public int findTilt(TreeNode root) {
        List<Integer> tilts = new ArrayList();
        sumByDfs(root, tilts);
        return getTilt(tilts);
    }

    int getTilt(List<Integer> tilts) {
        int result = 0;
        for(Integer num: tilts) {
            result += num;
        }
        return result;
    }

    long sumByDfs(TreeNode root, List<Integer> tilts) {
        if(root == null) {
            return 0;
        }
        long leftSum = sumByDfs(root.left, tilts);
        long rightSum = sumByDfs(root.right, tilts);

        tilts.add((int)Math.abs(leftSum - rightSum));

        return leftSum + rightSum + root.val;
    }

    public static void main(String[] args) {
        long a = 0;
        int b = (int)a;

        int c = 0;
        long d = c;

        Long aa = 0L;
        Number bb = aa;

        Integer cc = 0;
        Number dd = cc;



    }
}
