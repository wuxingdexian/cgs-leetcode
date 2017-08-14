package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/combination-sum-iii/description/" />
 * 216. Combination Sum III
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


 Example 1:

 Input: k = 3, n = 7

 Output:

 [[1,2,4]]

 Example 2:

 Input: k = 3, n = 9

 Output:

 [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * 1. 建模：决策树
 * 建立有序对((_,_,_),n)，前面三个组合元素，n为求和值
 * 举例：
 *                                      ((_,_,_),n)
 *                  ((1,_,_),n-1)                             |                       ((2,_,_),n-2)   |                  ((3,_,_),n-3)
 * ((1,1,_),n-1-1)  | ((1,2,_),n-1-2)   |   ((1,3,_),x-1-3) ....
 * 条件：只有满足前面三个组合元素填满，并且n = 0
 * 2. 算法范式：backtracking
 * 3. 算法：因为是组合，注意排除掉重复的情况，<code>for(int i = (combinations.size() != 0? combinations.get(combinations.size() - 1) + 1: 1); i < 10; i++) {</code>
 * 4. 数据结构：<code>List<Integer> combinations</code>用来当栈的作用，记录数据轨迹/组合
 * 5. 改进：
 * 6. 启发：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 09/08/2017
 * @see
 * @since DiscreteMathematics on  09/08/2017
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> solutions = new ArrayList();
        generate(new ArrayList(), n, k, solutions);
        return solutions;
    }

    private void generate(List<Integer> combinations, int remainder, int k, List<List<Integer>> solutions) {
        if(k == combinations.size() && remainder == 0) {
            solutions.add(new ArrayList(combinations));
            return;
        }
        if(remainder < 0 || combinations.size() > k) {
            return;
        }

        for(int i = (combinations.size() != 0? combinations.get(combinations.size() - 1) + 1: 1); i < 10; i++) {
            combinations.add(i);
            generate(combinations, remainder - i, k, solutions);
            combinations.remove(combinations.size() - 1);
        }
    }
}
