package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/permutations/description/" />
 * 46. Permutations
 *
 * Given a collection of distinct numbers, return all possible permutations.

 For example,
 [1,2,3] have the following permutations:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]
 * <p>
 * 1. 建模：建立有序对(permutation_i, remain_n-i)， 一共有n个元素，permutation_i为i个元素的排列，remain_n-i为剩下n-i个元素尚未排列
 * 画个决策树即可
 * 2. 算法范式：backtracking
 * 3. 算法：
 * 4. 数据结构：辅助的bit数组，用来判断数组中哪些元素尚未拿来排序
 * 5. 改进：
 * 6. 启发：
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 09/08/2017
 * @see
 * @since DiscreteMathematics on  09/08/2017
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> solutions = new ArrayList<>();
        generate(new ArrayList<>(), nums, 0, new boolean[nums.length], solutions);
        return solutions;

    }
    private void generate(List<Integer> permutation, int[] nums, int step, boolean[] flag, List<List<Integer>> solutions) {
        if(step == nums.length) {
            solutions.add(new ArrayList<>(permutation));
            return;
        }

        for(int i = 0; i < flag.length; i++) {
            if(!flag[i]) {
                flag[i] = !flag[i];
                permutation.add(nums[i]);
                generate(permutation, nums, step + 1, flag, solutions);
                if(permutation.size() > 0) {
                    permutation.remove(permutation.size() - 1);
                }
                flag[i] = !flag[i];
            }
        }
    }
}

