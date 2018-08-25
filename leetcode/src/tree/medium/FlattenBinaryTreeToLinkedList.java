package tree.medium;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.List;

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
public class FlattenBinaryTreeToLinkedList {

    List<TreeNode> solutions = new ArrayList<>();

    public void flatten(TreeNode root) {

        solveDFS(root);

        TreeNode tmpNode = root;
        for (int i = 1; i < solutions.size(); i++) {
            tmpNode.left = null;
            tmpNode.right = solutions.get(i);
            tmpNode = solutions.get(i);
        }
    }

    void solveDFS(TreeNode root) {
        if (null == root) {
            return;
        }

        solutions.add(root);
        solveDFS(root.left);
        solveDFS(root.right);
    }
}
