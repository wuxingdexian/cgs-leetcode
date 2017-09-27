package others.medium;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/binary-tree-right-side-view/description/" />
 * 199. Binary Tree Right Side View
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

 For example:
 Given the following binary tree,
 1            <---
 /   \
 2     3         <---
 \     \
 5     4       <---
 You should return [1, 3, 4].
 * <p>
 * 0. 本质：关系
 * 1. 建模：
 * 从左到右，每层只选择最后的元素
 * 2. 算法范式：
 * 3. 算法：
 * breath-first search
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 19/09/2017
 * @see
 * @since cgs-leetcode on  19/09/2017
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) {
            return new ArrayList();
        }
        List<TreeNode> currentLevelNodes = new ArrayList();
        currentLevelNodes.add(root);

        return bfs(currentLevelNodes);

    }

    List<Integer> bfs(List<TreeNode> currentLevelNodes) {
        List<Integer> result = new ArrayList();
        while(!currentLevelNodes.isEmpty()) {
            result.add(currentLevelNodes.get(currentLevelNodes.size() - 1).val);

            List<TreeNode> nextLevelNodes = new ArrayList();
            for(TreeNode node: currentLevelNodes) {
                if(node.left != null) {
                    nextLevelNodes.add(node.left);
                }
                if(node.right != null) {
                    nextLevelNodes.add(node.right);
                }
            }
            currentLevelNodes = nextLevelNodes;
        }
        return result;
    }
}
