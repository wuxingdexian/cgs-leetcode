package depthfirstsearch.mediu;

import depthfirstsearch.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/find-bottom-left-tree-value/description/" />
 * 513. Find Bottom Left Tree Value
 * Given a binary tree, find the leftmost value in the last row of the tree.

 Example 1:
 Input:

 2
 / \
 1   3

 Output:
 1
 Example 2:
 Input:

 1
 / \
 2   3
 /   / \
 4   5   6
 /
 7

 Output:
 7
 * <p>
 * 1. 建模：
 * 二叉树，
 * //----------------------错误理解---------------------------start-----
 * 加上有序对(left_leave_depth, value)，depth为路径深度，value为路径叶子节点的值
 * 则取取左树叶子节点为左节点和右树叶子节点为左节点的最长深度的value
 * //----------------------错误理解---------------------------end-----
 *
 * leftmost只最后一行最左边的叶子节点，不是最后一行的最左的左节点
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 15/08/2017
 * @see
 * @since cgs-leetcode on  15/08/2017
 */
public class FindBottomLeftTreeValue {

    public int findBottomLeftValue(TreeNode root) {

        Map<Integer, Integer> map = new HashMap();
        int[] deep2value = new int[2];
        if(findLeave(root, deep2value, 1)) {
            deep2value[0] = 1;
            deep2value[1] = root.val;
        }
        return deep2value[1];
    }

    boolean findLeave(TreeNode root,int[] deep2value, int deep) {
        if(root != null) {
            if(root.left == null && root.right == null) {
                return true;
            }
            if(findLeave(root.left, deep2value, deep + 1)) {
                if(deep2value[0] < deep) {
                    deep2value[0] = deep;
                    deep2value[1] = root.left.val;
                }
            }

            if(findLeave(root.right, deep2value, deep + 1)) {
                if(deep2value[0] < deep) {
                    deep2value[0] = deep;
                    deep2value[1] = root.right.val;
                }
            }
        }
        return false;
    }

}
