package others.medium;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/maximum-width-of-binary-tree/description/" />
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

 The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

 Example 1:
 Input:

 1
 /   \
 3     2
 / \     \
 5   3     9

 Output: 4
 Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 Example 2:
 Input:

 1
 /
 3
 / \
 5   3

 Output: 2
 Explanation: The maximum width existing in the third level with the length 2 (5,3).
 Example 3:
 Input:

 1
 / \
 3   2
 /
 5

 Output: 2
 Explanation: The maximum width existing in the second level with the length 2 (3,2).
 Example 4:
 Input:

 1
 / \
 3   2
 /     \
 5       9
 /         \
 6           7
 Output: 8
 Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


 Note: Answer will in the range of 32-bit signed integer.
 * <p>
 * 0. 本质：relation
 * 1. 建模：
 * 设每个节点p属性(depth, index) pair，其中depth为深度，index为该节点在本层中的位置，
 * 则max-with为遍历每层，取max(right_most_p.index-left_most_p.index)
 *
 * 2. 算法范式：BFS
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 08/09/2018
 * @see
 * @since cgs-leetcode on  08/09/2018
 */
public class MaximumWidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<TreeNodeWithProperty> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(new TreeNodeWithProperty(root, 1, 1));

        int maxWith = 1;
        while (!currentLevelNodes.isEmpty()) {
            int currentWith = currentLevelNodes.size() > 1 ? currentLevelNodes.get(currentLevelNodes.size() - 1).index - currentLevelNodes.get(0).index + 1 : 1;
            maxWith = Math.max(maxWith, currentWith);

            List<TreeNodeWithProperty> nextLevelNodes = new ArrayList<>();
            for (TreeNodeWithProperty nodeWithProperty: currentLevelNodes) {
                if (null != nodeWithProperty.node.left) {
                    // index关系式
                    nextLevelNodes.add(new TreeNodeWithProperty(nodeWithProperty.node.left, nodeWithProperty.depth + 1, nodeWithProperty.index * 2 - 1));
                }
                if (null != nodeWithProperty.node.right) {
                    // index关系式
                    nextLevelNodes.add(new TreeNodeWithProperty(nodeWithProperty.node.right, nodeWithProperty.depth + 1, nodeWithProperty.index * 2));
                }
            }
            currentLevelNodes = nextLevelNodes;
        }
        return maxWith;
    }

    class TreeNodeWithProperty {
        TreeNode node;
        int depth;
        int index;

        TreeNodeWithProperty(TreeNode node, int depth, int index) {
            this.node = node;
            this.depth = depth;
            this.index = index;
        }
    }
}
