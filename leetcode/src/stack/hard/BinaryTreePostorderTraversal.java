package stack.hard;

import depthfirstsearch.TreeNode;
import others.selfpractice.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：
 * 3. 算法：
 * （1）DFS
 * （2）迭代，模拟DFS后续遍历的数据特点，每个节点只有左右节点都已经出栈后才能出栈；子节点按右节点先入栈，再左节点入栈
 * 4. 数据结构：
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
public class BinaryTreePostorderTraversal {

    List<Integer> solutions = new ArrayList<>();
    public List<Integer> postorderTraversalDFS(TreeNode root) {
        if (null != root) {
            postorderTraversalDFS(root.left);
            postorderTraversalDFS(root.right);
            solutions.add(root.val);
        }
        return solutions;
    }

    /**
     * 参考 https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45551/Preorder-Inorder-and-Postorder-Iteratively-Summarization
     * 优雅解，直接按要求顺序不断前加值
     * @param root
     * @return
     */
    public List<Integer> postorderTraversalInteratively(TreeNode root) {
        List<TreeNode> solutionNodes = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        if (null != root) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            TreeNode peekNode = stack.peek();
            if (null == peekNode.right && null == peekNode.left) {
                solutionNodes.add(peekNode);
                stack.pop();
            } else if (null == peekNode.right && null != peekNode.left) {
                if (solutionNodes.isEmpty()) {
                    stack.push(peekNode.left);
                    continue;
                }

                if (solutionNodes.get(solutionNodes.size() - 1).equals(peekNode.left)) {
                    solutionNodes.add(peekNode);
                    stack.pop();
                } else {
                    stack.push(peekNode.left);
                }
                continue;
            } else if (null != peekNode.right && null == peekNode.left) {
                if (solutionNodes.isEmpty()) {
                    stack.push(peekNode.right);
                    continue;
                }

                if (solutionNodes.get(solutionNodes.size() - 1).equals(peekNode.right)) {
                    solutionNodes.add(peekNode);
                    stack.pop();
                } else {
                    stack.push(peekNode.right);
                }
                continue;
            } else if (null != peekNode.right && null != peekNode.left) {
                if (solutionNodes.isEmpty()) {
                    stack.push(peekNode.right);
                    stack.push(peekNode.left);
                    continue;
                }

                if (solutionNodes.get(solutionNodes.size() - 1).equals(peekNode.right)) {
                    solutionNodes.add(peekNode);
                    stack.pop();
                } else {
                    stack.push(peekNode.right);
                    stack.push(peekNode.left);
                }
                continue;
            }
        }

        return solutionNodes.stream().map(treeNode -> treeNode.val).collect(Collectors.toList());
    }
}
