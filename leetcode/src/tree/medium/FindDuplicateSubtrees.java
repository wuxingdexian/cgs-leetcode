package tree.medium;

import depthfirstsearch.TreeNode;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/find-duplicate-subtrees/description/" />
 * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

 Two trees are duplicate if they have the same structure with same node values.

 Example 1:

 1
 / \
 2   3
 /   / \
 4   2   4
 /
 4
 The following are two duplicate subtrees:

 2
 /
 4
 and

 4
 Therefore, you need to return above trees' root in the form of a list.
 * <p>
 * 0. 本质：序列、函数
 *
 * 1. 建模：
 * 画图，看其中的规律。
 * 要找到相同子树，那么需要用一个方式来比较，则对应到一个函数，相同子树的函数值具有相等，不同则不等。
 * 既然如此，比较容易想到的是树的遍历序列，这里选pre-order traverse。要保证一样，则需要加一些扩展符号，
 * 如子节点为null，则也要标注出来
 *
 * 2. 算法范式：DFS
 * 3. 算法：
 * 4. 数据结构：Map，记录遍历过的路径
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
public class FindDuplicateSubtrees {

    Map<TreeNode, String> tree2itsPathMap = new HashMap<>();
    Map<String, List<TreeNode>> treePath2ItselfMap = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        find(root);
        List<TreeNode> solutions = new ArrayList<>();
        treePath2ItselfMap.values().forEach(value -> {
            if (value.size() > 1) {
                solutions.add(value.get(0));
            }
        });
        return solutions;
    }

    private String find(TreeNode root) {
        if (null == root) {
            return "#";
        }

        String leftSubTreePath = tree2itsPathMap.containsKey(root.left)? tree2itsPathMap.get(root.left): find(root.left);
        String rightSubTreePath = tree2itsPathMap.containsKey(root.right)? tree2itsPathMap.get(root.right): find(root.right);

        String path = root.val + leftSubTreePath + rightSubTreePath;

        tree2itsPathMap.put(root, path);

        List<TreeNode> nodeWithSamePathList = treePath2ItselfMap.getOrDefault(path, new ArrayList<>());
        nodeWithSamePathList.add(root);
        treePath2ItselfMap.put(path, nodeWithSamePathList);
        return path;
    }

}
