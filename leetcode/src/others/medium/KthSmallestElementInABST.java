package others.medium;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/" />
 * 230. Kth Smallest Element in a BST
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 * <p>
 * 0. 本质：relation 树的本质
 * 1. 建模：二叉搜索树
 * 二叉搜索树和中序遍历天然联系
 *
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/09/2017
 * @see
 * @since cgs-leetcode on  08/09/2017
 */
public class KthSmallestElementInABST {
    TreeNode target;
    int count;
    // 二叉搜索树和中序遍历天然联系
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return target.val;
    }

    // return i-th
    void dfs(TreeNode root, int k) {
        if(root == null || target != null) {
            return;
        }
        dfs(root.left, k);
        count++;
        if(count == k) {
            target = root;
        }
        dfs(root.right, k);
        return;

    }
}
