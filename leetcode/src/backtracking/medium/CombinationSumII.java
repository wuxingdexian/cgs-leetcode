package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/combination-sum-ii/description/" />
 * 40. Combination Sum II
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 Each number in C may only be used once in the combination.

 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 A solution set is:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 * <p>
 * 1. 建模：决策树，画出决策树，参考{@link CombinationSum}
 * 2. 算法范式：backtracking
 * 3. 算法：先执行一次排序，保证集合无重复，因为结果集合无序
 * 4. 数据结构：栈，可以stack或arrayList实现
 * 5. 改进：这里使用的是<code>java.util.List#indexOf(java.lang.Object)</code>来去重，其实这一步应该还是很好性能的，可以参考其他人的去重方法
 * 6. 启发：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 09/08/2017
 * @see
 * @since DiscreteMathematics on  09/08/2017
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        List<List<Integer>> solutions = new ArrayList();
        generate(new ArrayList<Integer>(), candidates, 0, target, 0, solutions);
        return solutions;
    }

    private void generate(List<Integer> numberList, int[] candidates, int index, int target,
                          int tmpSum, List<List<Integer>> solutions) {
        /*判断上一层的结果*/
        if(tmpSum > target) {
            return;
        }
        if(tmpSum == target) {
            if(solutions.indexOf(numberList) < 0) {
                solutions.add(new ArrayList<>(numberList));
            }
            return;
        }

        /*执行本层次遍历*/
        for(int i = index; i < candidates.length; i++) {
            numberList.add(candidates[i]);
            generate(numberList, candidates, i + 1, target, tmpSum + candidates[i], solutions);
            if(numberList.size() > 0) {
                numberList.remove(numberList.size() - 1);
            }
        }
    }
}
