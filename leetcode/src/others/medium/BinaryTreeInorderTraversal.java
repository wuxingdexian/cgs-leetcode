package others.medium;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/binary-tree-inorder-traversal/description/" />
 * 94. Binary Tree Inorder Traversal
 * Given a binary tree, return the inorder traversal of its nodes' values.

 For example:
 Given binary tree [1,null,2,3],
 1
 \
 2
 /
 3
 return [1,3,2].

 Note: Recursive solution is trivial, could you do it iteratively?
 * <p>
 * 0. 本质：关系
 * 1. 建模：关系组成的树，遍历
 * 2. 算法范式：
 * 3. 算法：
 * （1）递归中序遍历，很容易实现
 *
 * （2）迭代？
 * 用栈来保存路径吧
 *
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 07/09/2017
 * @see
 * @since cgs-leetcode on  07/09/2017
 */
public class BinaryTreeInorderTraversal {

    // recursively solution
    List<Integer> solutions = new ArrayList();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return solutions;
        }
        inorderTraversal(root.left);
        solutions.add(root.val);
        inorderTraversal(root.right);
        return solutions;
    }

    public List<Integer> inorderTraversalIterator(TreeNode root) {

        Stack<Pair> stack = new Stack<>();
        // 第一条左链路压栈
        push(root, stack);

        Stack<Pair> stackGone = new Stack<>();
        List<Integer> solutions = new ArrayList<>();
        while (!stack.isEmpty()) {
            Pair pair = stack.peek();
            if (!pair.isTraversal) {
                solutions.add(pair.root.val);
                pair.isTraversal = true;
            }
            if (stackGone.isEmpty()) {
                if (pair.root.right != null) {
                    push(pair.root.right, stack);
                } else {
                    stackGone.push(stack.pop());
                }
            } else if(!stackGone.isEmpty()) {
                if(stackGone.peek().root == pair.root.right || pair.root.right == null) {
                    stackGone.push(stack.pop());
                } else if(pair.root.right != null) {
                    push(pair.root.right, stack);
                }
            }

        }
        return solutions;
    }

    private void push(TreeNode node, Stack<Pair> stack) {
        while (node != null) {
            stack.push(new Pair(node, false));
            node = node.left;
        }
    }

    class Pair{
        TreeNode root;
        boolean isTraversal;
        Pair(TreeNode root, boolean isTraversal) {
            this.root = root;
            this.isTraversal = isTraversal;
        }
    }

    public static void main(String[] args) {

        BinaryTreeInorderTraversal traversal = new BinaryTreeInorderTraversal();
        TreeNode build = traversal.build2();
        List<Integer> integers = traversal.inorderTraversalIterator(build);
        System.out.println(integers);
    }

    TreeNode build() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        return root;
    }
    TreeNode build2() {
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left = new TreeNode(1);
        return root;
    }
}