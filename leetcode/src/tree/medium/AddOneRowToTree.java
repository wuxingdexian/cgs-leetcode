package tree.medium;

import depthfirstsearch.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/add-one-row-to-tree/description/" />
 * Given the root of a binary tree, then value v and depth d, you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.

 The adding rule is: given a positive integer depth d, for each NOT null tree nodes N in depth d-1, create two tree nodes with value v as N's left subtree root and right subtree root. And N's original left subtree should be the left subtree of the new left subtree root, its original right subtree should be the right subtree of the new right subtree root. If depth d is 1 that means there is no depth d-1 at all, then create a tree node with value v as the new root of the whole original tree, and the original tree is the new root's left subtree.

 Example 1:
 Input:
 A binary tree as following:
 4
 /   \
 2     6
 / \   /
 3   1 5

 v = 1

 d = 2

 Output:
 4
 / \
 1   1
 /     \
 2       6
 / \     /
 3   1   5

 Example 2:
 Input:
 A binary tree as following:
 4
 /
 2
 / \
 3   1

 v = 1

 d = 3

 Output:
 4
 /
 2
 / \
 1   1
 /     \
 3       1
 Note:
 The given d is in range [1, maximum depth of the given tree + 1].
 The given binary tree has at least one tree node.
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：breath-first traverse BFT
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 25/08/2018
 * @see
 * @since cgs-leetcode on  25/08/2018
 */
public class AddOneRowToTree {

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null) {
            return new TreeNode(v);
        }

        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }

        List<TreeNode> parentNodes = new LinkedList<>();
        parentNodes.add(root);
        int currentDepth = 1;
        while (!parentNodes.isEmpty()) {
            if (currentDepth++ == d - 1) {
                break;
            }

            List<TreeNode> childNodes = new LinkedList<>();
            for (TreeNode node: parentNodes) {
                if (null != node.left) {
                    childNodes.add(node.left);
                }
                if (null != node.right) {
                    childNodes.add(node.right);
                }
            }
            parentNodes = childNodes;
        }

        for (TreeNode node: parentNodes) {
            TreeNode leftNode = new TreeNode(v);
            leftNode.left = node.left;
            node.left = leftNode;

            TreeNode rightNode = new TreeNode(v);
            rightNode.right = node.right;
            node.right = rightNode;
        }

        return root;

    }
}
