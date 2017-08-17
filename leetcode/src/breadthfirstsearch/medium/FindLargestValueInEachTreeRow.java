package breadthfirstsearch.medium;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/" />
 * 515. Find Largest Value in Each Tree Row
 * You need to find the largest value in each row of a binary tree.

 Example:
 Input:

 1
 / \
 3   2
 / \   \
 5   3   9

 Output: [1, 3, 9]
 * <p>
 * 1. 建模：
 * 树
 * 2. 算法范式：
 * 3. 算法：dfs和bfs都可以
 * 4. 数据结构：
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
public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {


        List<Integer> values = new ArrayList();
        if(root == null) {
            return values;
        }
        List<TreeNode> currentLevelNodes = new ArrayList();
        currentLevelNodes.add(root);
        bfs(values, currentLevelNodes);
        return values;

    }

    void bfs(List<Integer> values, List<TreeNode> currentLevelNodes) {
        if(currentLevelNodes.size() == 0) {
            return;
        }
        int min = Integer.MIN_VALUE;
        List<TreeNode> nextLevelNodes = new ArrayList();
        for(TreeNode root: currentLevelNodes) {
            min = Math.max(min, root.val);
            if(root.left != null) {
                nextLevelNodes.add(root.left);
            }
            if(root.right != null) {
                nextLevelNodes.add(root.right);
            }
        }
        values.add(min);
        bfs(values, nextLevelNodes);
    }
}
