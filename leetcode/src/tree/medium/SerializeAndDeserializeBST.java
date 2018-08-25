package tree.medium;

import depthfirstsearch.TreeNode;

import java.util.Stack;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 首先了解BST（binary search tree）的特点，当然，像leetcode那样使用通用的二叉树serialize和deserialize也可以
 * （1）画出一些图，看看特点。
 * （2）一个重要突破口是把二叉树的pre-order traversal、in-order traversal、post-order traversal都打印出来，
 * （3）再把极端情况的遍历打印出来，最左单链、最右单链，再和正常单链比较，就发现规律了
 * 1               3                7
 *  \             /               /   \
 *   2           2               5     9
 *    \         /               / \   /
 *     3       1               2  6  8
 *                            /
 *                           1
 * 打印所有pre-order traversal
 * 1,2,3     3,2,1          7,5,2,1,6,9,8
 * 2. 算法范式：DFS 序列化；回溯（backtrack） 反序列化
 * 3. 算法：
 * 4. 数据结构：栈
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 25/08/2018
 * @see
 * @since cgs-leetcode on  25/08/2018
 */
public class SerializeAndDeserializeBST {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder solution = new StringBuilder();
        serializeDFS(root, solution);
        return solution.length() > 0? solution.deleteCharAt(solution.length() - 1).toString(): solution.toString();
    }

    public void serializeDFS(TreeNode root, StringBuilder solution) {
        if (null == root) {
            return;
        }

        solution.append(root.val).append(" ");
        serializeDFS(root.left, solution);
        serializeDFS(root.right, solution);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (null == data || data.length() == 0) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        String[] vals = data.split(" ");
        TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
        stack.push(root);

        for (int i = 1; i < vals.length; i++) {
            if (Integer.valueOf(vals[i]) < stack.peek().val) {
                TreeNode node = new TreeNode(Integer.valueOf(vals[i]));
                stack.peek().left = node;
                stack.push(node);
            } else {
                while (!stack.isEmpty()) {
                    TreeNode tmpNode = stack.pop();
                    if (stack.isEmpty() || stack.peek().val > Integer.valueOf(vals[i])) {
                        TreeNode node = new TreeNode(Integer.valueOf(vals[i]));
                        tmpNode.right = node;
                        stack.push(node);
                        break;
                    }
                }
            }
        }
        return root;
    }
}
