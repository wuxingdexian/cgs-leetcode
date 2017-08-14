package depthfirstsearch.easy;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/minimum-depth-of-binary-tree/description/" />
 * 111. Minimum Depth of Binary Tree
 * Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 * <p>
 * 1. 建模：recurrence relation
 * f(n) = 2f(n/2) + C
 * 2. 算法范式：divided-and-conquer
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
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if(root != null) {
            if(root.left == null && root.right == null) {
                return 1;
            }
            int leftDepth = minDepth(root.left);
            int rightDepth = minDepth(root.right);
            // 当节点只有一个子节点时，该节点不是叶子节点。注意了
            if(leftDepth == 0 || rightDepth == 0) {
                return (leftDepth == 0? rightDepth: leftDepth) + 1;
            } else {
                return Math.min(leftDepth, rightDepth) + 1;
            }
        }
        return 0;
    }
}
