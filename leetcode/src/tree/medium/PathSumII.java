package tree.medium;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：DFS
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link Stack} jdk8 可以使用stream foreach方法
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 24/08/2018
 * @see
 * @since cgs-leetcode on  24/08/2018
 */
public class PathSumII {

    List<List<Integer>> solutions = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        Stack<TreeNode> path = new Stack<>();

        if (root == null) {
            return solutions;
        }

        solveDFS(root, sum, path, 0);
        return solutions;

    }

    void solveDFS(TreeNode root, int sum, Stack<TreeNode> path, int pathSum) {
        if (null == root) {
            return;
        }

        path.push(root);
        pathSum = pathSum + root.val;

        if (isLeaf(root) && sum == pathSum) {
            List<Integer> solution = new ArrayList<>();
            // TODO: 24/08/2018 这块比较耗费性能
            path.forEach(node -> solution.add(node.val));
            solutions.add(solution);
        }

        solveDFS(root.left, sum, path, pathSum);
        solveDFS(root.right, sum, path, pathSum);

        path.pop();
    }

    private boolean isLeaf(TreeNode root) {
        return null != root && null == root.left && null == root.right;
    }
}
