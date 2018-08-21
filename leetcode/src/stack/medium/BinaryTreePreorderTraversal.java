package stack.medium;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：图/树
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * （1）直接DFS
 * （2）模拟DFS，保存栈信息，注意顺序即可
 * 4. 数据结构：栈
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 20/08/2018
 * @see
 * @since cgs-leetcode on  20/08/2018
 */
public class BinaryTreePreorderTraversal {

    List<Integer> solutions = new ArrayList();

    /**
     * DFS
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalDFS(TreeNode root) {
        if (null != root) {
            solutions.add(root.val);
            preorderTraversalDFS(root.left);
            preorderTraversalDFS(root.right);
        }
        return solutions;
    }


    public List<Integer> preorderTraversalIteratively(TreeNode root) {
        List<Integer> solutions = new ArrayList();

        Stack<TreeNode> stack = new Stack<>();
        if (null != root) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            TreeNode popNode = stack.pop();
            solutions.add(popNode.val);
            if (null != popNode.right) {
                stack.push(popNode.right);
            }
            if (null != popNode.left) {
                stack.push(popNode.left);
            }
        }
        return solutions;
    }
}
