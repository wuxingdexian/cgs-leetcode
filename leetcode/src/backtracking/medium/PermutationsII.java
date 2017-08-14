package backtracking.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/permutations-ii/description/" />
 * 47. Permutations II
 * <p>
 * 1. 建模：{@link Permutations} 在重复的情况需要额外处理
 * 将元素分类，建立有序对p_i = (i, sum(i))，对整数i，统计其个数
 * 举例，假设数组中只有三个元素1、2、3，其中元素1有2个，元素2由1个元素3有2个
 * 决策树模型如下：
 *                                                                                          (1, 2),(2, 3),(3,2): []
 *                                                   (1, 2-1),(2, 3),(3,2):[1]                          |               (1, 2),(2, 3-2),(3,2):[2]    |   (1, 2),(2, 3),(3,2-1):[3]
 *   (1, 2-2),(2, 3),(3,2):[1,1]    |   (1, 2-1),(2, 3-1),(3,2):[1,2]   |   (1, 2-1),(2, 3),(3,2-1):[1,3]       .....
 * 2. 算法范式：backtracking
 * 3. 算法：先将重复元素归类，然后再进行决策树建模，将会简化决策树建模难度，并且决策树不会出现重复结果的情况。
 * 4. 数据结构：辅助map
 * 5. 改进：其他更快速的答案后续在看，
 * 6. 启发：决策树建模，面对重复的情况，可以先在源头解决，比如本题目在源头将重复元素规整到map中，然后建模，模型很清晰，无重复结果出现，此时再来写代码，则会很容易啊！！！！
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 09/08/2017
 * @see
 * @since DiscreteMathematics on  09/08/2017
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> solutions = new ArrayList();
        Map<Integer, Integer> number2count = mapNumber2count(nums);
        generate(new ArrayList(), number2count, nums.length, solutions);
        return solutions;
    }

    private void generate(List<Integer> permutation, Map<Integer, Integer> number2count, int totalCount, List<List<Integer>> solutions) {
        if (totalCount == permutation.size()) {
            solutions.add(new ArrayList<>(permutation));
            return;
        }

        for (Map.Entry<Integer, Integer> entry: number2count.entrySet()) {
            if (entry.getValue() > 0) {
                permutation.add(entry.getKey());
                entry.setValue(entry.getValue() - 1);
                generate(permutation, number2count, totalCount, solutions);
                entry.setValue(entry.getValue() + 1);
                permutation.remove(permutation.size() - 1);
            }
        }
    }

    private Map<Integer, Integer> mapNumber2count(int[] nums) {
        Map<Integer, Integer> number2count = new HashMap<>();
        for(int number: nums) {
            Integer count = null != number2count.get(number)? number2count.get(number): 0;
            number2count.put(number, count + 1);
        }
        return number2count;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,3};
        List<List<Integer>> lists = new PermutationsII().permuteUnique(nums);
        System.out.println(lists);
    }
}
