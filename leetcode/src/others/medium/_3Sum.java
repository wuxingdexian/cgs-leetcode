package others.medium;

import java.util.*;

/**
 * <p>
 * 背景描述：
 * <a href="https://leetcode.com/problems/3sum/description/" />
 * 15. 3Sum
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note: The solution set must not contain duplicate triplets.

 For example, given array S = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]
 * <p>
 * 0. 本质：序列/集合-》子集合、combinatorics-》combination-》generating
 * 1. 建模：
 * nums(i) + nums(j) + nums(k) = 0; where i < j < k
 * sum(j_k) = nums(j) + nums(k) = 0-nums(i)
 * 简化为求两个数相加等于某个固定的值
 * 2. 算法范式：
 * 3. 算法：
 * 4. 数据结构：
 * 5. 改进：
 * 6. 启发：
 * 7. jdk知识：TODO {@link Arrays#asList(Object[])}多用用，避免多写代码
 * <p>
 * <a href="dhshenchanggan@163.com" />
 *
 * @author changgan on 18/09/2017
 * @see
 * @since cgs-leetcode on  18/09/2017
 */
public class _3Sum {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);
        List<List<Integer>> solutions = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int head = i + 1, tail = nums.length - 1, goal = -nums[i];

                while (head < tail) {
                    if (nums[head] + nums[tail] < goal) {
                        head++;
                    } else if (nums[head] + nums[tail] > goal) {
                        tail--;
                    } else {
                        solutions.add(Arrays.asList(nums[i], nums[head], nums[tail]));
                        while (head < tail && nums[head] == nums[head + 1]) {
                            head++;
                        }
                        while (head < tail && nums[tail] == nums[tail - 1]) {
                            tail--;
                        }
                        head++;
                        tail--;
                    }
                }
            }
        }
        return solutions;
    }


    public List<List<Integer>> threeSumTest(int[] nums) {

        Arrays.sort(nums);
        Map<Integer, List<Integer>> cache = new HashMap<>(1000);
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexValues = cache.getOrDefault(nums[i], new ArrayList<>());
            indexValues.add(i);
            cache.put(nums[i], indexValues);
        }

        List<List<Integer>> solutions = new ArrayList<>();
        int head = 0, tail = nums.length - 1;
        while (head < tail + 1) {

            if (containLegalIndex(cache, head, tail, 0 - (nums[head] + nums[tail]))) {
                solutions.add(Arrays.asList(nums[head], 0 - (nums[head] + nums[tail]), nums[tail]));

            }
            while (head < tail && nums[head] == nums[head+1]) {
                head++;
            }
            while (head < tail && nums[tail] == nums[tail-1]) {
                tail--;
            }
            head++;
            tail--;
        }
        return solutions;
    }

    boolean containLegalIndex(Map<Integer, List<Integer>> cache, int head, int tail, int key) {
        if (!cache.containsKey(key)) {
            return false;
        }
        for (Integer i: cache.get(key)) {
            if (i > head && i < tail) {
                return true;
            }
        }
        return false;
    }

}
