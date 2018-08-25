package tree.medium;

import depthfirstsearch.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 站在树结构看要找最大值，观察规律，可以由整体到局部（先总后分）的思路思考，从根节点想；也可以由局部到整体的思路思考，从叶子节点思考。
 * 假设从整体到局部思考，要不相邻，那一棵树最大值（1）要么左右两棵子树的最大值相加；（2）要么根节点和左右节点的所有直接子节点的最大值相加
 * 2. 算法范式：分治法、递归
 * 3. 算法：
 *
 * 4. 数据结构：
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
public class HouseRobberIII {

    /**
     * 最简单的理解，但是需要重复计算路径了
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int subLeftLeftMax = null != root.left? rob(root.left.left): 0;
        int subLeftRightMax = null != root.left? rob(root.left.right): 0;

        int subRightLeftMax = null != root.right? rob(root.right.left): 0;
        int subRightRightMax = null != root.right? rob(root.right.right): 0;

        int max1 = root.val + subLeftLeftMax + subLeftRightMax + subRightLeftMax + subRightRightMax;

        return Math.max(rob(root.left) + rob(root.right), max1);

    }

    /**
     * 上面重复计算，需要保存路径，避免重新计算
     * @param root
     * @return
     */
    Map<TreeNode, Integer> rememberMap = new HashMap<>();
    int robOpt(TreeNode root) {
        if (null == root) {
            return 0;
        }

        int subLeftLeftMax = null != root.left && null != root.left.left? getTreeMax(root.left.left): 0;
        int subLeftRightMax = null != root.left && null != root.left.right? getTreeMax(root.left.right): 0;

        int subRightLeftMax = null != root.right && null != root.right.left? getTreeMax(root.right.left): 0;
        int subRightRightMax = null != root.right && null != root.right.right? getTreeMax(root.right.right):0;

        int max1 = root.val + subLeftLeftMax + subLeftRightMax + subRightLeftMax + subRightRightMax;

        int leftMax = null != root.left? getTreeMax(root.left): 0;
        int rightMax = null != root.right? getTreeMax(root.right): 0;

        int max2 = rightMax + leftMax;

        int max = Math.max(max1, max2);
        rememberMap.put(root, max);

        return max;
    }

    int getTreeMax(TreeNode root) {
        int max;
        return rememberMap.containsKey(root)? rememberMap.get(root): robOpt(root);
//        if (rememberMap.containsKey(root)) {
//            max = rememberMap.get(root);
//        } else {
//            max = robOpt(root);
//            rememberMap.put(root, max);
//        }
//        return max;
    }
}
