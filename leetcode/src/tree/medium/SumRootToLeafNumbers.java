package tree.medium;

import depthfirstsearch.TreeNode;

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
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 24/08/2018
 * @see
 * @since cgs-leetcode on  24/08/2018
 */
public class SumRootToLeafNumbers {

    int sum = 0;
    public int sumNumbers(TreeNode root) {

        StringBuilder path = new StringBuilder();
        solveDFS(root, path);

        return sum;
    }

    private void solveDFS(TreeNode root, StringBuilder path) {
        if (null == root) {
            return;
        }

        path.append(root.val);

        if (isLeaf(root)) {
            sum = sum + Integer.valueOf(path.toString());
        }
        solveDFS(root.left, path);
        solveDFS(root.right, path);
        path.deleteCharAt(path.length() - 1);
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

}
