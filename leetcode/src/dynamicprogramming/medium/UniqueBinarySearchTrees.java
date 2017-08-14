package dynamicprogramming.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/unique-binary-search-trees/description/" />
 * 96. Unique Binary Search Trees
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

 For example,
 Given n = 3, there are a total of 5 unique BST's.

 1         3     3      2      1
 \       /     /      / \      \
 3     2     1      1   3      2
 /     /       \                 \
 2     1         2                 3
 * <p>
 * 1. 建模：
 * （1）决策树：找到了规则，可以旋转。但是性能慢的不行不行的。 看{@link UniqueBinarySearchTreesII 描述}
 * （2）recurrence relation
 * sum(n)：数n时总得二叉排序树个数
 * p(i,n)，n个数中，以i为根的二叉排序树个数
 * sum(n) = p(1,n) + p(2,n) + .... + p(n,n)
 * 由计数乘积原理，得到p(i,n) = sum(i-1)*sum(n-i)
 * sum(n) = sum(0)*sum(n-1) + sum(1)*sum(n-2) + ...+ sum(n)*sum(n-n)
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：https://leetcode.com/problems/unique-binary-search-trees/discuss/
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 11/08/2017
 * @see
 * @since DiscreteMathematics on  11/08/2017
 */
public class UniqueBinarySearchTrees {

    public int numTrees(int n) {
        if (n == 0) {
            return 1;
        }
        int[] sums = new int[n + 1];
        sums[0] = 1;
        sums[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                sums[i] += sums[j] * sums[i - j - 1];
            }
        }

        return sums[n];

    }

    //----------------------------------------------------------------------------------------------------------------------------------------
    /*  很烂的代码，并且性能很慢
    public int numTrees(int n) {

        if (n == 0) {
            return 0;
        }

        List<TreeNode> treeNodes = new ArrayList();
        treeNodes.add(new TreeNode(-1));

        for (int i = 1; i <= n; i++) {
            List<TreeNode> tmpTreeNodes = new ArrayList<>();
            for (TreeNode treeNode: treeNodes) {
                insert(treeNode, i);
                TreeNode copyTree1 = copy(index(treeNode, -1), null);
                tmpTreeNodes.add(copyTree1);

                TreeNode indexTree = index(treeNode, i);

                do {
                    if(change(indexTree)) {
                        TreeNode indexRoot = index(treeNode, -1);
                        TreeNode copyTree = copy(indexRoot, null);
                        tmpTreeNodes.add(copyTree);
                        indexTree = index(treeNode, i);
                    }
                } while(indexTree != null && indexTree.parent.val != -1);
            }
            treeNodes = tmpTreeNodes;
        }


        return treeNodes.size();

    }

    TreeNode index(TreeNode treeNode, int n) {
        TreeNode tmpTreeNode = treeNode;
        while (null != tmpTreeNode) {
            if (tmpTreeNode.val == n) {
                return tmpTreeNode;
            }
            if (tmpTreeNode.val > n) {
                tmpTreeNode = tmpTreeNode.left;
            } else {
                tmpTreeNode = tmpTreeNode.right;
            }
        }
        return null;
    }

    void insert(TreeNode treeNode, int n) {
        TreeNode treeNodeIndex = treeNode;
        while (treeNodeIndex.right != null) {
            if (treeNodeIndex.val > n) {
                treeNodeIndex = treeNodeIndex.left;
            } else {
                treeNodeIndex = treeNodeIndex.right;
            }
        }
        TreeNode newTree = new TreeNode(n);
        newTree.parent = treeNodeIndex;
        treeNodeIndex.right = newTree;
        return;

    }

    TreeNode copy(TreeNode treeNode, TreeNode parentTreeNode) {
        if (null !=  treeNode) {
            TreeNode treeNodeCopy = new TreeNode(treeNode.val);
            treeNodeCopy.parent = parentTreeNode;
            if (treeNode.left != null) {
                treeNodeCopy.left = copy(treeNode.left, treeNodeCopy);
            }
            if (treeNode.right != null) {
                treeNodeCopy.right = copy(treeNode.right, treeNodeCopy);
            }
            return treeNodeCopy;
        }

        return null;
    }

    boolean change(TreeNode treeNode) {
        TreeNode parentTree = treeNode.parent;
        if (null != parentTree && parentTree.val == -1) {
            return false;
        }

        parentTree.parent.right = treeNode;

        parentTree.right = treeNode.left;
        if (null != treeNode.left) {
            treeNode.left.parent = parentTree;
        }

        treeNode.left = parentTree;
        treeNode.parent = parentTree.parent;

        parentTree.parent = treeNode;

        return true;
    }

    TreeNode copy(TreeNode treeNodeInner) {
        if (null !=  treeNodeInner) {
            TreeNode treeNodeCopy = new TreeNode(treeNodeInner.val);
            if (treeNodeInner.left != null) {
                treeNodeCopy.left = copy(treeNodeInner.left);
            }
            if (treeNodeInner.right != null) {
                treeNodeCopy.right = copy(treeNodeInner.right);
            }
            return treeNodeCopy;
        }

        return null;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "val:" + val + ",left:" + (null != left? left.val: "") + ",right:" + (null != right? right.val: right);
        }
    }

    */
    //----------------------------------------------------------------------------------------------------------------------------------------
}
