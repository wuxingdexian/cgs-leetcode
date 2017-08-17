package breadthfirstsearch.easy;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/" />
 * 107. Binary Tree Level Order Traversal II
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its bottom-up level order traversal as:
 [
 [15,7],
 [9,20],
 [3]
 ]
 * <p>
 * 1. 建模：二叉树
 * 2. 算法范式：
 * （1）backtracking（dfs）
 * 设定有序对(level,values[])
 * level表示当前深度，values[]表示这一层深度的所有值，
 * （2）bfs，
 * 设定有序对(level,preNode[],currentNode[],values[])，
 * level表示当前深度，preNode[]表示上一层深度的所有节点，currentNode[]表示当前深度的所有节点，values[]表示这一层深度的所有值，
 * 3. 算法：
 * （1）dfs，遍历每个节点，前序、中序、后序都可以，记录下深度和值。
 * （2）bfs，
 * 4. 数据结构：
 * （1）箱：数组+链表的形式，数组下标代表层级，链表代表这一层的所有值。最后输出时利用linkedList，方便头插入
 * （2）
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
public class BinaryTreeLevelOrderTraversalII {
    //--------------深度优先----------------------
    /*
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List[] levelList = new List[1000];
        preOrderDfs(root, levelList, 0);
        // LinkedList 方便头插入 提升插入效率
        List<List<Integer>> bottomUp = new LinkedList();
        for(int i = 0; i < levelList.length; i++) {
            if(levelList[i] == null) {
                break;
            }
            bottomUp.add(0, levelList[i]);
        }
        return bottomUp;
    }

    void preOrderDfs(TreeNode root, List[] levelList, int level) {
        if(root != null) {

            List<Integer> values = levelList[level] != null? levelList[level]: new ArrayList();
            values.add(root.val);
            levelList[level] = values;
            preOrderDfs(root.left, levelList, level + 1);
            preOrderDfs(root.right, levelList, level + 1);
        }
    }
    */
    //--------------深度优先----------------------


    //--------------广度优先----------------------
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        // 制定LinkedList提升插入效率
        List<List<Integer>> values = new LinkedList();
        if(root != null) {
            List<TreeNode> currentLevelNodes = new ArrayList();
            currentLevelNodes.add(root);
            bfs(currentLevelNodes, values);
        }

        return values;

    }

    // 由于是bfs，肯定是逐层下降，这里使用LinkedList方便插入，就不记录level了
    void bfs(List<TreeNode> currentLevelNodes, List<List<Integer>> values) {
        if(currentLevelNodes == null || currentLevelNodes.size() == 0) {
            return;
        }
        List<TreeNode> nextLevelNodes = new ArrayList();
        List<Integer> currentLevelValues = new ArrayList();
        for(TreeNode root: currentLevelNodes) {
            currentLevelValues.add(root.val);
            if(root.left != null) {
                nextLevelNodes.add(root.left);
            }
            if(root.right != null) {
                nextLevelNodes.add(root.right);
            }
        }
        // 前插
        values.add(0, currentLevelValues);
        bfs(nextLevelNodes, values);
    }
    //--------------深度优先----------------------

}
