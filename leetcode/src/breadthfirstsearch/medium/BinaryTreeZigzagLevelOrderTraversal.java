package breadthfirstsearch.medium;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/" />
 * 103. Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]
 * <p>
 * 1. 建模：
 * 二叉树，设定有序对(level, currentNode[], currentValues[])，深度从0开始，当深度为偶数是从左到右排序，奇数时则反过来。
 * currentValues当前层级节点值集合，currentNode当前层级的节点集合
 *
 * 2. 算法范式：
 *
 * 3. 算法：
 * bfs
 * 4. 数据结构：
 * LinkedList
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 17/08/2017
 * @see
 * @since cgs-leetcode on  17/08/2017
 */
public class BinaryTreeZigzagLevelOrderTraversal {
}
