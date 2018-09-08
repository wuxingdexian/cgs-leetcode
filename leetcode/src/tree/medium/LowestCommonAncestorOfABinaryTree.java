package tree.medium;

import depthfirstsearch.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/" />
 * 236. Lowest Common Ancestor of a Binary Tree
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

 _______3______
 /              \
 ___5__          ___1__
 /      \        /      \
 6      _2       0       8
 /  \
 7   4
 For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 * <p>
 * 0. 本质：组合
 * 1. 建模：
 * * suppose ordered-pair (parent, leftTree, rightTree)，when two of them are the target numbers. then we will get the answer.
 * premise: (1)p and q must respectively be in parent's leftTree and rightTree; or(2) q or p equal to parent, and another in left or rightTree
 *
 * whether the left is less or less and equal to root? whether the right is larger or larger and equal to root?
 * 2. 算法范式：
 * backtracking
 *
 * 3. 算法：
 * depth-first search: pre-order traversal
 *
 * suppose all the elements are unique.
 *
 * (1)if current node if one of p and q, then respectively search leftTree and rightTree to check whether they they have another.
 *  1) if true, then current node is the LCA;
 *  2) if false, then current is not.
 * (2)if left contains p or q, then check right contains the other?
 *  1)if true, then current node is the LCA;

 * 4. 数据结构：
 * 5. 改进：// FIXME: 30/08/2017 这个属于子问题，可以用动态规划缓存中间结果
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 30/08/2017
 * @see
 * @since cgs-leetcode on  30/08/2017
 */
public class LowestCommonAncestorOfABinaryTree {

    /**
     * 这个答案NB，简洁
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65225/4-lines-C++JavaPythonRuby
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    // cache dynamic programming
    Map<TreeNode, TreeNode> cache = new HashMap();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if(null != left) {
            return left;
        }

        if(root == p) {
            if(dfs(root.left, q) || dfs(root.right, q)) {
                return root;
            }
        } else if(root == q) {
            if(dfs(root.left, p) || dfs(root.right, p)) {
                return root;
            }
        } else {
            if((dfs(root.left, p) && dfs(root.right, q)) || (dfs(root.left, q) && dfs(root.right, p))) {
                return root;
            }
        }

        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(null != right) {
            return right;
        }
        return null;
    }

    public boolean dfs(TreeNode root, TreeNode target) {
        if(root == null) {
            return false;
        }
        if(root == target) {
            return true;
        }
        if(cache.get(root.left) == target) {
            return true;
        }
        if(cache.get(root.right) == target) {
            return true;
        }

        if(dfs(root.left, target)) {
            cache.put(root.left, target);
            return true;
        }
        if(dfs(root.right, target)) {
            cache.put(root.right, target);
            return true;
        }
        return false;
    }
}
