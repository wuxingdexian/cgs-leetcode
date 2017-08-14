package depthfirstsearch.easy;

import com.shen.depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/description/" />
 * 104. Maximum Depth of Binary Tree
 * Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 * <p>
 * 1. 建模：
 * 模型已经是二叉树了，不需要再建模。
 * 深度遍历得场景有时候和决策树还是点差别。决策树类似于广度或逐渐下沉，到最后叶子时得到答案。
 * 如果深度遍历是在叶子节点得到答案，那和决策树是一致的。 这里也可以看作是在叶子节点得到改节点得深度，然后比较所有得节点深度，得到最大值。从这个角度看，这是决策树
 *
 * 2. 算法范式：决策树
 * 3. 算法：depth-first search，决策树和深度遍历几乎是直接相关的
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 14/08/2017
 * @see
 * @since DiscreteMathematics on  14/08/2017
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        return deepDfs(root);
    }

    int deepDfs(TreeNode root) {
        if(root != null) {
            int leftDeep = deepDfs(root.left) + 1;
            int rightDeep = deepDfs(root.right) + 1;
            return Math.max(leftDeep, rightDeep);
        } else {
            return 0;
        }

    }
}
