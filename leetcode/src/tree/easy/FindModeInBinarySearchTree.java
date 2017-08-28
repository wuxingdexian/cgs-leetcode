package tree.easy;

import depthfirstsearch.TreeNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
 * （1）相等的值放在同一个集合中
 *
 * 2. 算法范式：
 * （2）backtracking
 * 3. 算法：
 * （1）hashMap，相同的值放在同一个key。
 * （2）dfs，
 *      1）找左子树和节点相同的数的个数；l
 *      2）找右子树和节点相同的数的个数；r
 *      3）如果l+r+1大于结果数组的最大值，则结果数组清零，并把给数放进去。
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
    public int[] findMode(TreeNode root) {
        if(root == null) {
            return new int[0];
        }
        Mode basiMode = new Mode(root.val, 1);
        basiMode = findMode(root, basiMode, new Mode(root.val, 1));
        int[] solutions = new int[1];
        solutions[0] = basiMode.val;
        return solutions;
    }
    Mode findMode(TreeNode root, Mode basicMode, Mode mode) {
        dfs(root, mode);
        if(mode.counter > basicMode.counter) {
            basicMode = mode;
        }
        if(root != null && root.left != null) {
            basicMode = findMode(root.left, basicMode, new Mode(root.left.val, 1));
        }
        if(root != null && root.right != null) {
            basicMode = findMode(root.right, basicMode, new Mode(root.right.val, 1));
        }
        return basicMode;
    }


    void dfs(TreeNode root, Mode mode) {
        if(root == null) {
            return;
        }
        if(root.val == mode.val) {
            mode.counter++;
        }

        dfs(root.left, mode);
        dfs(root.right, mode);

    }

    class Mode{
        int val;
        int counter;
        Mode(int val, int counter) {
            this.val = val;
            this.counter = counter;
        }
    }

    public static void main(String[] args) {
        FindModeInBinarySearchTree findModeInBinarySearchTree = new FindModeInBinarySearchTree();
        int[] mode = findModeInBinarySearchTree.findMode(findModeInBinarySearchTree.build());
        System.out.println("mode = " + mode);

    }

    TreeNode build() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        root.right = right;
        root.right.right = left;
        return root;
    }
}
