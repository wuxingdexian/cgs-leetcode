package depthfirstsearch.easy;

import depthfirstsearch.TreeNode;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/" />
 * 108. Convert Sorted Array to Binary Search Tree
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * <p>
 * 1. 建模：两种
 * （1）recurrence relation，平衡二叉树，分治法
 * 从而也可以看到分治法使用了recurrence relation模型，f(n) = af(n/b) + g(n)，分治法实现时借助了深度优先来递归
 *
 * （2）决策树，每次插入时，深度遍历找到只有一个子节点的节点，然后插入右节点；若都没有一个子节点的节点，则找最左叶子节点，插入其左节点。 这个效率应该比较慢
 * 2. 算法范式：
 * （1）divided-and-conquer
 * （2）backtracking
 * 3. 算法：
 * 深度优先 depth-first search
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 14/08/2017
 * @see
 * @since cgs-leetcode on  14/08/2017
 */
public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return dividedAndConquerWithDfs(nums, 0, nums.length - 1);
    }

    TreeNode dividedAndConquerWithDfs(int[] nums, int start, int end) {
        if(start > end) {
            return null;
        }

        int middle = (start + end) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        TreeNode left = dividedAndConquerWithDfs(nums, start, middle - 1);
        TreeNode right = dividedAndConquerWithDfs(nums, middle + 1, end);
        root.left = left;
        root.right = right;
        return root;

    }
}
