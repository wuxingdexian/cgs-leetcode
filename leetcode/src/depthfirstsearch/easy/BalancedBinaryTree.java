package depthfirstsearch.easy;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/balanced-binary-tree/description/" />
 * 110. Balanced Binary Tree
 * Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * 1. 建模：recurrence relation
 * f(n) = 2f(n/2) + C
 * // FIXME: 14/08/2017 判断下面三个不等式，是否都在[-1,0,1]范围
 * // FIXME: 14/08/2017 “max_left_depth - min_left_depth、max_left_depth - min_right_depth、max_right_depth - min_left_depth“
 *
 * 上面两个判断是错的，判断满足不了平衡二叉树得定义：任何一个节点的左右子树深度差不超过1。 需要改
 *
 * 这里就用到了最大路径了。{@link MaximumDepthOfBinaryTree}
 * 2. 算法范式：
 * 分治法
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 14/08/2017
 * @see
 * @since cgs-leetcode on  14/08/2017
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }

        if(!isBalanced(root.left) || !isBalanced(root.right)) {
            return false;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return leftDepth - rightDepth >= -1 && leftDepth - rightDepth <= 1?
                true: false;
    }

    int maxDepth(TreeNode root) {
        if(root != null) {
            int leftDeep = maxDepth(root.left);
            int rightDeep = maxDepth(root.right);
            return Math.max(leftDeep, rightDeep) + 1;
        } else {
            return 0;
        }
    }
}
