package tree.easy;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/subtree-of-another-tree/description/" />
 * 572. Subtree of Another Tree
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

 Example 1:
 Given tree s:

 3
 / \
 4   5
 / \
 1   2
 Given tree t:
 4
 / \
 1   2
 Return true, because t has the same structure and node values with a subtree of s.
 Example 2:
 Given tree s:

 3
 / \
 4   5
 / \
 1   2
 /
 0
 Given tree t:
 4
 / \
 1   2
 Return false.
 * <p>
 * 0. 本质：集合和子集
 * 1. 建模：
 * 2. 算法范式：backtracking
 * 3. 算法：
 * dfs
 * (1) when the current node in s has the same value of current node in t, then continue compare their left and right
 * (2) only all the correspondent node has the same value and the leaves are the same, then we got the answer.
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 30/08/2017
 * @see
 * @since cgs-leetcode on  30/08/2017
 */
public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null || t == null) {
            return false;
        }
        if(s.val == t.val && compare(s, t)) {
            return true;
        }
        if(isSubtree(s.left, t)) {
            return true;
        }
        if(isSubtree(s.right, t)) {
            return true;
        }
        return false;
    }

    boolean compare(TreeNode s, TreeNode t) {
        if(s == null && t == null) {
            return true;
        }
        if((s == null && t != null) || (s != null && t == null)) {
            return false;
        }
        if(s.val != t.val) {
            return false;
        }
        if(!compare(s.left, t.left)) {
            return false;
        }
        if(!compare(s.right, t.right)) {
            return false;
        }
        return true;
    }
}
