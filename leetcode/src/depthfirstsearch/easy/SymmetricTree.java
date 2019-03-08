package depthfirstsearch.easy;


import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/symmetric-tree/description/" />
 * 101. Symmetric Tree
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

 1
 / \
 2   2
 / \ / \
 3  4 4  3
 But the following [1,2,2,null,3,null,3] is not:
 1
 / \
 2   2
 \   \
 3    3
 * <p>
 * 1. 建模：
 * （1）中序遍历后得回文序列；这个处理叶子节点和只有一个孩子的内部节点不好处理。
 * （2）决策树，递归
 * 2. 算法范式：
 * 3. 算法：
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
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return root == null || compare(root.right, root.left);
    }

    boolean compare(TreeNode right, TreeNode left) {
        if(null == right || left == null) {
            // 引用比较
            return right == left;
        }
        if(right.val != left.val) {
            return false;
        }
        return compare(right.left, left.right) && compare(right.right, left.left);

    }

    // TODO: 13/02/2019 中序遍历，针对回文性质，也可以使用栈来比对，根节点的左子树不断入栈，右子树不断比较栈顶，然后元素出栈
}
