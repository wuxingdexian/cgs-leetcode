package tree.easy;

import depthfirstsearch.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/" />
 * 653. Two Sum IV - Input is a BST
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

 Example 1:
 Input:
 5
 / \
 3   6
 / \   \
 2   4   7

 Target = 9

 Output: True
 Example 2:
 Input:
 5
 / \
 3   6
 / \   \
 2   4   7

 Target = 28

 Output: False
 * <p>
 * 0. 本质：combination + ordered pair
 * 1. 建模：
 * sum = a + b, a = sum - b
 * 2. 算法范式：
 * backtracking
 * 3. 算法：
 *
 * refer to in-order traversal
 *
 * depth-first search
 *
 * （1）以root 为起点，找左子树和右子树是否有sum-root.val的值，有即完成目标。
 * （2）每次遍历节点时，先查查map中是否有sum-root.val，有即完成目标，不必再继续。并查询map是否有root.val，没有则插入
 *
 * 4. 数据结构：map
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：{@link Map#containsKey(Object)}contain后面有s
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 30/08/2017
 * @see
 * @since cgs-leetcode on  30/08/2017
 */
public class TwoSumIVInputIsABST {
    Map<Integer, Integer> cache = new HashMap();
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) {
            return false;
        }
        if(findTarget(root.left, k)) {
            return true;
        }

        if(cache.containsKey(k - root.val)) {
            return true;
        }
        cache.put(root.val, 1);

        if(findTarget(root.right, k)) {
            return true;
        }
        return false;
    }
}
