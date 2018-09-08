package tree.medium;

import depthfirstsearch.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="" />
 *
 * <p>
 * 0. 本质：
 * 1. 建模：
 * 2. 算法范式：回溯法（存在重复子问题，应该排除分治法；感觉和回溯法有点像，但回溯法应该更多是从一个起点到一个目标点，），
 * 这题可以清楚的比对几类算法范式：动态规划（dynamic programming）、回溯法（backtracking）、分治法（divide and conquer）
 * 3. 算法：递归
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
public class MostFrequentSubtreeSum {

    Map<Integer, Integer> sum2countMap = new HashMap<>();
    Map<TreeNode, Integer> tree2sumMap = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {

        traverse(root);

        List<Integer> maxCountSumList = new ArrayList<>();
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> entry: sum2countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxCountSumList.clear();
                maxCountSumList.add(entry.getKey());
            } else if (entry.getValue() == maxCount) {
                maxCountSumList.add(entry.getKey());
            } else {
                continue;
            }
        }

        int[] solution = new int[maxCountSumList.size()];
        for (int i = 0; i < maxCountSumList.size(); i++) {
            solution[i] = maxCountSumList.get(i);
        }

        return solution;
    }

    private Integer traverse(TreeNode root) {
        if (null == root) {
            return 0;
        }

        Integer leftTreeSum = tree2sumMap.containsKey(root.left) ? tree2sumMap.get(root.left) : traverse(root.left);
        Integer rightTreeSum = tree2sumMap.containsKey(root.right) ? tree2sumMap.get(root.right) : traverse(root.right);

        Integer sum = root.val + leftTreeSum + rightTreeSum;
        tree2sumMap.put(root, sum);
        Integer count = sum2countMap.getOrDefault(sum, 0);
        sum2countMap.put(sum, ++count);

//        traverse(root.left);
//        traverse(root.right);

        return sum;
    }
}
