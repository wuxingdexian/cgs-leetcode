package tree.easy;

import depthfirstsearch.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/construct-string-from-binary-tree/description/" />
 * 606. Construct String from Binary Tree
 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

 The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

 Example 1:
 Input: Binary tree: [1,2,3,4]
 1
 /   \
 2     3
 /
 4

 Output: "1(2(4))(3)"

 Explanation: Originallay it needs to be "1(2(4)())(3()())",
 but you need to omit all the unnecessary empty parenthesis pairs.
 And it will be "1(2(4))(3)".
 Example 2:
 Input: Binary tree: [1,2,3,null,4]
 1
 /   \
 2     3
 \
 4

 Output: "1(2()(4))(3)"

 Explanation: Almost the same as the first example,
 except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
 * <p>
 * 0. 本质：排列
 * 1. 建模：
 * 2. 算法范式：backtracking
 * 3. 算法：depth-first search
 * 左节点若为空，则保留括号
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 24/08/2017
 * @see
 * @since cgs-leetcode on  24/08/2017
 */
public class ConstructStringFromBinaryTree {
    public String tree2str(TreeNode t) {
        Deque<String> solution = new LinkedList();
        generate(t, solution);
        solution.removeFirst();
        solution.removeLast();

        return formatSolution(solution);
    }

    // 1. reserve left parentheses if left child is null but right is not null.
    void generate(TreeNode t, Deque<String> solution) {
        solution.add("(");
        if(t != null) {
            solution.add(String.valueOf(t.val));
            if(t.right != null) {
                generate(t.left, solution);
                generate(t.right, solution);
            } else if(t.left != null) {
                generate(t.left, solution);
            }
        }
        solution.add(")");
    }

    String formatSolution(Deque<String> solution) {
        StringBuilder sb = new StringBuilder(solution.size());
        for(String c: solution) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = new ConstructStringFromBinaryTree().tree2str(null);
        System.out.println(s);
    }
}
