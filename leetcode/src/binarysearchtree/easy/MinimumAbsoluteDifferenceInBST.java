package binarysearchtree.easy;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/" />
 * 530. Minimum Absolute Difference in BST
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

 Example:

 Input:

 1
 \
 3
 /
 2

 Output:
 1

 Explanation:
 The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 * <p>
 * 0. 本质：组合，当然也隐含了数字的大于等于小于的关系
 *
 * 1. 建模：
 * 根据关系排序，找相邻的两个数的最小差
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：
 * {@link ArrayList#sort(Comparator)}需要实现排序
 * 如果默认为升序排序，那就直接使用{@link java.util.Collections#sort(List)}
 *
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 24/08/2017
 * @see
 * @since cgs-leetcode on  24/08/2017
 */
public class MinimumAbsoluteDifferenceInBST {
    public int getMinimumDifference(TreeNode root) {
        if(root == null) {
            return 0;
        }
        List<Integer> valList = new ArrayList();
        getValFromTree(root, valList);
        if(valList.size() == 1) {
            return valList.get(0);
        } else {
            return findTheMinimalDifference(valList);
        }
    }

    // save val to array
    void getValFromTree(TreeNode root, List<Integer> valList) {
        if(root != null) {
            valList.add(root.val);
            getValFromTree(root.left, valList);
            getValFromTree(root.right, valList);
        }
    }

    int findTheMinimalDifference(List<Integer> valList) {
        int minimal = Integer.MAX_VALUE;
        Collections.sort(valList);
        for(int i = 1; i < valList.size(); i++) {
            minimal = Math.min(valList.get(i) - valList.get(i - 1), minimal);
        }
        return minimal;
    }

    //-----------------------别人ac代码--者in-order traversal应该有bug吧，怎么能通过的----------------------------
    // 下面以1 7 8 100 100 null null测试，结果不对
    int min = Integer.MAX_VALUE;
    TreeNode pre = null;

    public int getMinimumDifference1(TreeNode root) {
        if(root==null) return min;
        getMinimumDifference1(root.left);

        if(pre!=null){
            int cur = Math.abs(pre.val-root.val);
            min = Math.min(cur, min);
        }
        pre=root;
        getMinimumDifference1(root.right);
        return min;
    }
    TreeNode buildTestTree() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(7);
        TreeNode right = new TreeNode(8);
        root.left =left;
        root.right =right;

        TreeNode Lleft1 = new TreeNode(100);
        TreeNode Lright1 = new TreeNode(100);
        left.left = Lleft1;
        left.right = Lright1;

        return root;
    }
    //-----------------------别人ac代码--者in-order traversal应该有bug吧，怎么能通过的----------------------------

    public static void main(String[] args) {
        MinimumAbsoluteDifferenceInBST differenceInBST = new MinimumAbsoluteDifferenceInBST();
        TreeNode treeNode = differenceInBST.buildTestTree();
        int minimumDifference1 = differenceInBST.getMinimumDifference1(treeNode);
        System.out.println(minimumDifference1);
    }


}
