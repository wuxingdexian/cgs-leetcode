package tree.easy;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/" />
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

 According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

 _______6______
 /              \
 ___2__          ___8__
 /      \        /      \
 0      _4       7       9
 /  \
 3   5
 For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 * <p>
 * 0. 本质：组合
 * 1. 建模：
 * suppose ordered-pair (parent, leftTree, rightTree)，when two of them are the target numbers. then we will get the answer.
 * premise: (1)p and q must respectively be in parent's leftTree and rightTree; or(2) q or p equal to parent, and another in left or rightTree
 *
 * whether the left is less or less and equal to root? whether the right is larger or larger and equal to root?
 * 2. 算法范式：
 * backtracking
 * 3. 算法：
 * depth-first search
 *
 * suppose all the elements are unique.
 *
 * (1)if current node if one of p and q, then respectively search leftTree and rightTree to check whether they they have another.
 *  1) if true, then current node is the LCA;
 *  2) if false, then current is not.
 * (2)if left contains p or q, then check right contains the other?
 *  1)if true, then current node is the LCA;
 * 若当前元素不是
 * 4. 数据结构：list
 * 5. 改进：// FIXME: 30/08/2017 没有利用上二叉搜索树的性质。。。
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 30/08/2017
 * @see
 * @since cgs-leetcode on  30/08/2017
 */
public class LowestCommonAncestorOfABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
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
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if(null != left) {
            return left;
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
        if(dfs(root.left, target)) {
            return true;
        }
        if(dfs(root.right, target)) {
            return true;
        }
        return false;
    }

    // ------------------------take advantage of binary search tree properties------------
    // suppose all the elements are unique.
    public TreeNode lowestCommonAncestorAdvanced(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode maxNode = p.val > q.val? p: q;
        TreeNode minNode = p.val > q.val? q: p;

        while(true) {
            if (maxNode.val == root.val) {
                return root;
            } else if(minNode.val == root.val) {
                return root;
            } else if (root.val > maxNode.val) {
                root = root.left;
            } else if(root.val < minNode.val) {
                root = root.right;
            } else if(root.val > minNode.val && root.val < maxNode.val) {
                return root;
            }
        }
    }
    // ------------------------take advantage of binary search tree properties------------
}
