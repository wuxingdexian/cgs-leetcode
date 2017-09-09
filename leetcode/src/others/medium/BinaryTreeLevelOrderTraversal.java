package others.medium;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/description/" />
 * 102. Binary Tree Level Order Traversal
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its level order traversal as:
 [
 [3],
 [9,20],
 [15,7]
 ]
 * <p>
 * 0. 本质：关系/树
 * 1. 建模：二叉树，从上往下，从左到右遍历即可
 * 2. 算法范式：
 * 3. 算法：
 * breadth-first search
 * （0）从root开始，先放入currentLevelNodes，
 * （1）然后将依次遍历currentLevelNodes节点，依次取左右节点（如果有）放入nextLevelNodes
 * （2）不断执行（1）
 *
 * TODO depth-first search 也可以
 * 4. 数据结构：数组
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
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) {
            return new ArrayList();
        }

        List<List<Integer>> solutions = new ArrayList();

        List<TreeNode> currentLevelNodes = new ArrayList();
        currentLevelNodes.add(root);
        while(!currentLevelNodes.isEmpty()) {
            solutions.add(getOneSolution(currentLevelNodes));
            currentLevelNodes = getNextLevelNode(currentLevelNodes);
        }
        return solutions;
    }

    List<TreeNode> getNextLevelNode(List<TreeNode> currentLevelNodes) {
        List<TreeNode> nextLevelNodes = new ArrayList();
        for(TreeNode node: currentLevelNodes) {
            if(node.left != null) {
                nextLevelNodes.add(node.left);
            }
            if(node.right != null) {
                nextLevelNodes.add(node.right);
            }
        }
        return nextLevelNodes;
    }

    List<Integer> getOneSolution(List<TreeNode> nodes) {
        List<Integer> solution = new ArrayList();
        for(TreeNode node:nodes) {
            solution.add(node.val);
        }
        return solution;
    }
}
