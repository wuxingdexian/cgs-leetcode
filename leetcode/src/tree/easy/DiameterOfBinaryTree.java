package tree.easy;

import depthfirstsearch.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/diameter-of-binary-tree/description/" />
 * 543. Diameter of Binary Tree
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

 Example:
 Given a binary tree
 1
 / \
 2   3
 / \
 4   5
 Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

 Note: The length of path between two nodes is represented by the number of edges between them.

 * <p>
 * 0. 本质：序列
 * 1. 建模：二叉树，for every node, its diameter(root) = max_length(left) + max_length(right)
 * the whole longest diameter is max(diameter(i)) where i is all the node
 * define ordered-pair (node, longest_path) express the longest_path = max(max_length(node.left), max_length(node.right))
 * 2. 算法范式：backtracking + dynamic programming
 * 3. 算法：
 * (1) dfs, get left tree's longest path;
 * (2) dfs, get right tree's longest path;
 * 4. 数据结构：map, cache longest path length for every node's left and right trees.
 * 5. 改进：// FIXME: 30/08/2017 代码不够精简 <code>diameterOfBinaryTreeImprovement</code>
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 30/08/2017
 * @see
 * @since cgs-leetcode on  30/08/2017
 */
public class DiameterOfBinaryTree {
    Map<TreeNode, Integer> pathCache = new HashMap();
    int diameter = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }

        diameter = Math.max(diameter, getLongestPath(root.left) + getLongestPath(root.right));
        diameterOfBinaryTree(root.left);
        diameterOfBinaryTree(root.right);
        return diameter;
    }

    // TODO 从写recurrence relation 和画出树结构，可以得知这个具有子问题，但是节点的问题可以从左右节点子问题导出结果。
    // TODO 但是左右节点不存在子问题重叠，不会像斐波那契数列的那样，当画出二叉树，可以左右节点的子树存在很多节点重叠的情况。
    // TODO 也就是说，这里返回的是当前节点下的最长路径，返回的直接是子问题的结果，不需要额外加map来缓存了。
    int getLongestPath(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int leftPath = 0;
        if(pathCache.containsKey(root.left)) {
            leftPath = pathCache.get(root.left);
        } else {
            leftPath = getLongestPath(root.left);
        }

        int rightPath = 0;
        if(pathCache.containsKey(root.right)) {
            rightPath = pathCache.get(root.right);
        } else {
            rightPath = getLongestPath(root.right);
        }

        int longestPath = 1 + Math.max(leftPath, rightPath);
        pathCache.put(root, 1 + Math.max(leftPath, rightPath));
        return longestPath;
    }


    //-------------------------improvement. no need for cache ---------------------------------
    int diameterImprovement = 0;
    public int diameterOfBinaryTreeImprovement(TreeNode root) {
        dfs(root);
        return diameterImprovement;
    }
    public int dfs(TreeNode root) {

        if(root == null) {
            return -1;
        }
        int leftPath = dfs(root.left);
        int rightPath = dfs(root.right);
        diameterImprovement = Math.max(diameterImprovement, leftPath + rightPath + 2);
        return Math.max(leftPath, rightPath) + 1;
    }
    //-------------------------improvement. no need for cache ---------------------------------


}
