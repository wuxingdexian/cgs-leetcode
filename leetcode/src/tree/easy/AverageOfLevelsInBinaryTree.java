package tree.easy;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/average-of-levels-in-binary-tree/description/" />
 * 637. Average of Levels in Binary Tree
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

 Example 1:
 Input:
 3
 / \
 9  20
 /  \
 15   7
 Output: [3, 14.5, 11]
 Explanation:
 The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 Note:
 The range of node's value is in the range of 32-bit signed integer.
 * <p>
 * 0. 本质：集合
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * breadth-first search
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * // FIXME: 30/08/2017 如果声明sum是long类型<code>long sum = 0;</code>，那么除法后<code>sum / currentLevelNodes.size()</code>得到的默认将是long，所以需要把sum先定义为double
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 30/08/2017
 * @see
 * @since cgs-leetcode on  30/08/2017
 */
public class AverageOfLevelsInBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> solutions = new ArrayList();
        if(root == null) {
            return solutions;
        }
        bfs(root, solutions);
        return solutions;

    }

    void bfs(TreeNode root, List<Double> solutions) {
        List<TreeNode> currentLevelNodes = new ArrayList();
        List<TreeNode> nextLevelNodes;
        currentLevelNodes.add(root);

        while(!currentLevelNodes.isEmpty()) {
            nextLevelNodes = new ArrayList();
            double sum = 0;
            for(TreeNode node: currentLevelNodes) {
                sum += node.val;
                if(node.left != null) {
                    nextLevelNodes.add(node.left);
                }
                if(node.right != null) {
                    nextLevelNodes.add(node.right);
                }
            }
            solutions.add(sum / currentLevelNodes.size());
            currentLevelNodes = nextLevelNodes;
        }
    }
}
