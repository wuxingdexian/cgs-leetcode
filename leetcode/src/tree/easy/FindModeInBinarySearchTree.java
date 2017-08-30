package tree.easy;

import depthfirstsearch.TreeNode;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/find-mode-in-binary-search-tree/description/" />
 * 501. Find Mode in Binary Search Tree
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 Both the left and right subtrees must also be binary search trees.
 For example:
 Given BST [1,null,2,2],
 1
 \
 2
 /
 2
 return [2].

 Note: If a tree has more than one mode, you can return them in any order.

 Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 * <p>
 * 0. 本质：组合、关系
 * 1. 建模：
 * 审题：找出出现频率最高的数字 frequently
 * 众数（Mode）是统计学名词，在统计分布上具有明显集中趋势点的数值，代表数据的一般水平（众数可以不存在或多于一个）。
 * 修正定义：是一组数据中出现次数最多的数值，叫众数，有时众数在一组数中有好几个。用M表示。 理性理解：简单的说，就是一组数据中占比例最多的那个数。
 *
 * 二叉搜索树
 *
 * 2. 算法范式：
 * （2）backtracking
 * 3. 算法：
 * 算法一：
 * （1）hashMap，相同的值放在同一个key。 因为借助额外数组，不太可取
 * 算法二：
 * （2）dfs，设定结果集合
 *      1）找左子树和节点相同的数的个数；l
 *      2）找右子树和节点相同的数的个数；r
 *      3）如果l+r+1大于当前众数的个数，则结果集合清零，并把给数放进去；若等于则放入结果集合
 *
 * 算法三：
 * 因为是二叉搜索树，将其线索化，然后从左树最左节点开始遍历一遍即可。
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * // TODO: 26/08/2017 集合的遍历，若是使用迭代器来遍历，则必须把迭代器拿出来
 *
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 26/08/2017
 * @see
 * @since cgs-leetcode on  26/08/2017
 */
public class FindModeInBinarySearchTree {
    int maxCounter = 0;
    public int[] findMode(TreeNode root) {
        if(root == null) {
            return new int[0];
        }

        List<Integer> solutions = new ArrayList();
        findMode(root, solutions);
        return formatSolutions(solutions);
    }
    void findMode(TreeNode root, List<Integer> solutions) {
        if(root == null) {
            return;
        }

        int counter = dfsCounter(root, root.val);
        if(counter > maxCounter) {
            solutions.clear();
            solutions.add(root.val);
            maxCounter = counter;
        } else if(counter == maxCounter) {
            solutions.add(root.val);
        }

        findMode(root.left, solutions);
        findMode(root.right, solutions);
    }

    int dfsCounter(TreeNode root, int value) {
        if(root == null) {
            return 0;
        }

        return dfsCounter(root.left, value) + dfsCounter(root.right, value) + (root.val == value? 1: 0);
    }

    int[] formatSolutions(List<Integer> solutions) {
        int[] solutionArray = new int[solutions.size()];
        for(int i = 0; i < solutions.size(); i++) {
            solutionArray[i] = solutions.get(i);
        }
        return solutionArray;
    }

    public static void main(String[] args) {
        FindModeInBinarySearchTree findModeInBinarySearchTree = new FindModeInBinarySearchTree();
        int[] mode = findModeInBinarySearchTree.findMode(findModeInBinarySearchTree.build());
        System.out.println("mode = " + mode);

    }

    TreeNode build() {
        TreeNode root = new TreeNode(1);
//        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        root.right = right;
//        root.right.right = left;
        return root;
    }
}
