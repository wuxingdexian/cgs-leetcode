package depthfirstsearch.mediu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/increasing-subsequences/description/" />
 * 491. Increasing Subsequences
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

 Example:
 Input: [4, 6, 7, 7]
 Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 Note:
 The length of the given array will not exceed 15.
 The range of integer in the given array is [-100,100].
 The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 * <p>
 * 1. 建模：决策树建模
 * 设定有序对(combination, remainder[])，如果combination的个数大于1，则为一个答案。remainder为选择该元素后剩下的元素。
 * 需要去重
 * 2. 算法范式：backtracking
 * 3. 算法：dfs
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
public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {

        List<List<Integer>> allSolutions = new LinkedList();
        generate(nums, 0, allSolutions, new LinkedList<Integer>(), new HashMap<String, Integer>());
        return allSolutions;
    }

    void generate(int[] nums, int index, List<List<Integer>> allSolutions, LinkedList<Integer> solution,
                  Map<String, Integer> tmpMap) {

        for(int i = index; i < nums.length; i++) {

            if(solution.isEmpty()) {
                solution.add(nums[i]);
            } else {
                if (nums[i] < solution.getLast()) {
                    continue;
                } else {
                    solution.add(nums[i]);
                }
            }

            if(solution.size() > 1) {
                if(tmpMap.get(solution.toString()) == null) {
                    List<Integer> fixedSolution = new LinkedList(solution);
                    allSolutions.add(fixedSolution);
                    tmpMap.put(solution.toString(), 1);
                }
            }
            // 这里传入i+1  不要误写为index + 1
            generate(nums, i + 1, allSolutions, solution, tmpMap);
            solution.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,6,7,7};
//        int[] nums = {4,3,2,1};
        List<List<Integer>> subsequences = new IncreasingSubsequences().findSubsequences(nums);
        System.out.println(subsequences);
    }
}
