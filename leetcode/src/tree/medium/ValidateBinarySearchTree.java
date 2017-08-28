package tree.medium;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/validate-binary-search-tree/description/" />
 * 98. Validate Binary Search Tree
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Example 1:
 2
 / \
 1   3
 Binary tree [2,1,3], return true.
 Example 2:
 1
 / \
 2   3
 Binary tree [1,2,3], return false.
 * <p>
 * 0. 本质：关系relation：满足二叉搜索树的性质，左子树所有节点小于等于父节点；右子树所有节点大于等于父节点。
 * 1. 建模：
 * value(root) >= value(root.left); value(root) <= value(root.right)
 * 2. 算法范式：
 * backtracking(dfs)
 * 3. 算法：
 * （1）. 当前节点值和左节点值比较，若符合则继续比较该节点值和左子树所有节点值比较。
 * （2）. 当前节点值和右节点值比较，若符合则继续比较该节点值和右子树所有节点值比较。
 * （3）. 上述（1）和（2）都符合，则以左子树和右子树分别为根，继续比较
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：题设要求的是小于和大于，没有包含等于，所有注意传入最大最小值时，出现节点值是int最大值和最小值的情况，导致失败，所以改为long传入。
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 26/08/2017
 * @see
 * @since cgs-leetcode on  26/08/2017
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if(root == null) {
            return true;
        }

        if((long)root.val <= minVal || (long)root.val >= maxVal) {
            return false;
        }

        if(!isValidBST(root.left, minVal, root.val)) {
            return false;
        }
        if(!isValidBST(root.right, root.val, maxVal)) {
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        ValidateBinarySearchTree validateBinarySearchTree = new ValidateBinarySearchTree();
        boolean validBST = validateBinarySearchTree.isValidBST(validateBinarySearchTree.build());
        System.out.println(validBST);
    }

    TreeNode build() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(1);
        root.left = left;
        return root;
    }

}
