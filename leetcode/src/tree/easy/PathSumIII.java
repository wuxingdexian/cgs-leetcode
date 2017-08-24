package tree.easy;

import depthfirstsearch.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/path-sum-iii/description/" />
 * 437. Path Sum III
 * You are given a binary tree in which each node contains an integer value.

 Find the number of paths that sum to a given value.

 The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

 The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

 Example:

 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

 10
 /  \
 5   -3
 / \    \
 3   2   11
 / \   \
 3  -2   1

 Return 3. The paths that sum to 8 are:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3. -3 -> 11
 * <p>
 * 0. 本质：组合+序列
 * 1. 建模：
 * 集合+function
 * 一个序列中连续的数字相加等于固定的某个数
 * domain：连续的节点
 * codomian：固定的数
 * function：连续节点的sum等于固定的数，非单射函数
 *
 * 每条路径下来，得到的将是和这个题目一样{@link dynamicprogramming.medium.ContinuousSubarraySum}
 * 简单化，先求单条路径下的模型
 * 设从根到叶子节点的单条路径的节点序列为[a(1),a(2),...,a(n)]
 * 设从根到叶子节点的路径和序列为(sum(1),sum(2),...,sum(n))
 * 设目标数为target，则执行到第i个节点时，判断sum(i)-target是否在前面某个节点j存在sum(j)=sum(i)-target，若存在则找到一个解
 * 初始化：sum(0)=0;
 *
 * 2. 算法范式：backtracking + dynamic programming
 * 3. 算法：
 * （1）depth-first search，找到所有的路径，
 * （2）找到节点i时，计算sum(i)，并保存到map（key=[sum(i)], value=[...,a[i]]）中，直到根节点。
 * 注意：sum(i)可能和前面某个节点的路径和相等，所以value是个列表
 * （3）找到节点i时，计算sum(i)-target是否在map中存在，若存在，则路径数即为value的个数。
 * （4）节点回退时，删除的该节点在map中的sum(i)和其value
 *
 * 4. 数据结构：hashMap+deque
 * 5. 改进：// TODO: 24/08/2017 看了别人的答案，直接递归，没有这么多封装。应该简单的使用backtracking也是可以的
 * 6. 启发：
 * 7. jdk知识：
 * {@link HashMap#remove(Object)}直接根据key删除
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 24/08/2017
 * @see
 * @since cgs-leetcode on  24/08/2017
 */
public class PathSumIII {
    // cache path sum
    Map<Integer, Deque<TreeNode>> pathSumMap = new HashMap();
    // record path and its path sum
    Deque<MyTreeNode> pathNodes = new LinkedList();
    // solution counter
    int solutionNum = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) {
            return solutionNum;
        }

        TreeNode guard = new TreeNode(0);
        Deque<TreeNode> sumDeque = new LinkedList();
        sumDeque.add(guard);
        pathSumMap.put(0, sumDeque);
        pathNodes.addLast(new MyTreeNode(guard, 0));

        traversal(root, sum);
        return solutionNum;
    }


    // preorder traversal
    void traversal(TreeNode root, int targetSum) {
        if(root == null) {
            return;
        }
        updatePathSum(root);
        updatePathNodes(root);
        Integer needFindSum = pathNodes.getLast().sum - targetSum;
        if(pathSumMap.get(needFindSum) != null) {
            solutionNum += pathSumMap.get(needFindSum).size();
            // make sure the last element not in pathSumMap's deque
            if (pathSumMap.get(needFindSum).getLast().equals(root)) {
                solutionNum--;
            }
        }
        traversal(root.left, targetSum);
        traversal(root.right, targetSum);
        MyTreeNode myTreeNode = deletePathNodes();
        deletePathSum(myTreeNode);
    }

    // record nodes when traversal
    void updatePathNodes(TreeNode root) {
        int sum = pathNodes.getLast().sum + root.val;
        MyTreeNode node = new MyTreeNode(root, sum);
        pathNodes.addLast(node);
    }

    // delete pathNodes when backtracking. return the sum
    MyTreeNode deletePathNodes() {
        return pathNodes.removeLast();
    }

    // update when traversal
    void updatePathSum(TreeNode root) {
        int sum = pathNodes.getLast().sum + root.val;
        Deque<TreeNode> sumDeque = pathSumMap.get(sum) != null? pathSumMap.get(sum): new LinkedList();
        sumDeque.addLast(root);
        pathSumMap.put(sum, sumDeque);
    }

    // delete pathSum when backtracking
    void deletePathSum(MyTreeNode myTreeNode) {
        Deque<TreeNode> sumDeque = pathSumMap.get(myTreeNode.sum);
        sumDeque.remove(myTreeNode.node);
        if(sumDeque.size() == 0) {
            pathSumMap.remove(myTreeNode.sum);
        }
    }

    // wrapper class to save TreeNode and its path sum from root.
    class MyTreeNode {
        TreeNode node;
        Integer sum;
        MyTreeNode(TreeNode node, Integer sum) {
            this.node = node;
            this.sum = sum;
        }
    }

    public static void main(String[] args) {

        PathSumIII pathSumIII = new PathSumIII();
        TreeNode treeNode = pathSumIII.buildTestTree();
//        int i = pathSumIII.pathSum(treeNode, 8);
        int i = pathSumIII.pathSum(new TreeNode(1), 0);
        System.out.println(i);
    }

    TreeNode buildTestTree() {
        TreeNode root = new TreeNode(10);
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(-3);
        root.left =left;
        root.right =right;

        TreeNode Lleft1 = new TreeNode(3);
        TreeNode Lright1 = new TreeNode(2);
        left.left = Lleft1;
        left.right = Lright1;

        TreeNode Rright1 = new TreeNode(11);
        right.right = Rright1;

        TreeNode Lleft1L = new TreeNode(3);
        TreeNode Lright1L = new TreeNode(-2);
        Lleft1.left = Lleft1L;
        Lleft1.right = Lright1L;


        TreeNode Lright1R = new TreeNode(1);
        Lright1.right = Lright1R;
        return root;
    }
}
