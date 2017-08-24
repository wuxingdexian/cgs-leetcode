package tree.easy;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/sum-of-left-leaves/description/" />
 * 404. Sum of Left Leaves
 * Find the sum of all left leaves in a given binary tree.

 Example:

 3
 / \
 9  20
 /  \
 15   7

 There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

 * <p>
 * 0. 本质：关系，找所有左叶子节点
 * 1. 建模：
 * 2. 算法范式：backtracking
 * 3. 算法：depth-first search
 * 4. 数据结构：树
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 24/08/2017
 * @see
 * @since cgs-leetcode on  24/08/2017
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if(root == null) {
            return sum;
        }

        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);

        if(isLeaves(root.left)) {
            sum += root.left.val;
        }
        return sum;
    }

    boolean isLeaves(TreeNode root) {
        return root != null && root.left == null && root.right == null;
    }
}
