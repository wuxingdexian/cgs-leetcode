package tree.medium;

import depthfirstsearch.TreeNode;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/print-binary-tree/description/" />
 * Print a binary tree in an m*n 2D string array following these rules:

 The row number m should be equal to the height of the given binary tree.
 The column number n should always be an odd number.
 The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
 Each unused space should contain an empty string "".
 Print the subtrees following the same rules.
 Example 1:
 Input:
 1
 /
 2
 Output:
 [["", "1", ""],
 ["2", "", ""]]
 Example 2:
 Input:
 1
 / \
 2   3
 \
 4
 Output:
 [["", "", "", "1", "", "", ""],
 ["", "2", "", "", "", "3", ""],
 ["", "", "4", "", "", "", ""]]
 Example 3:
 Input:
 1
 / \
 2   5
 /
 3
 /
 4
 Output:

 [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 Note: The height of binary tree is in the range of [1, 10].
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 这里的填充好烦，先不做这题了~~
 * 2. 算法范式：分治法
 *
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link LinkedList}能实现头尾插入，{@link List}接口却没有，需要显示使用{@link LinkedList}
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 25/08/2018
 * @see
 * @since cgs-leetcode on  25/08/2018
 */
public class PrintBinaryTree {


    Map<TreeNode, LinkedList<String>> node2itselfMap = new HashMap<>();
    Map<TreeNode, LinkedList<String>> node2itsChildMap = new HashMap<>();


    public List<List<String>> printTree(TreeNode root) {

        solve(root, 0);
        List<List<String>> solutions = new LinkedList<>();

        return solutions;
    }

    private LinkedList<String> solve(TreeNode root, int depth) {
        if (null == root) {
            return new LinkedList<>();
        }

        LinkedList<String> leftList;
        if (node2itselfMap.containsKey(root.left)) {
            leftList = node2itselfMap.get(root.left);
        } else {
            leftList = solve(root.left, depth + 1);
        }

        LinkedList<String> rightList;
        if (node2itselfMap.containsKey(root.right)) {
            rightList = node2itselfMap.get(root.right);
        } else {
            rightList = solve(root.right, depth + 1);
        }

        // 对不平衡一方进行填充
        if (leftList.size() < rightList.size()) {
            while (leftList.size() < rightList.size()) {
                leftList.addLast("");
            }
        } else {
            while (rightList.size() < leftList.size()) {
                rightList.addFirst("");
            }
        }
        LinkedList<String> childrenList = new LinkedList<>(leftList);
        childrenList.addAll(leftList);
        childrenList.add("");
        childrenList.addAll(rightList);
        node2itsChildMap.put(root, childrenList);

        int paddingSize = Math.max(leftList.size(), rightList.size());
        LinkedList<String> parentList = new LinkedList<>();
        parentList.add(String.valueOf(root.val));

        for (int i = 0; i < paddingSize; i++) {
            parentList.addFirst("");
            parentList.addLast("");
        }
        node2itselfMap.put(root, parentList);
        return parentList;
    }

    private void paddingChildren(TreeNode root) {

    }
}
